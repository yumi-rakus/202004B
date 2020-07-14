package com.example.repository;

import java.util.List;

import com.example.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * usersテーブルを操作するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */

@Repository
public class UserRepository {
  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
    User user = new User();
    user.setId(rs.getInt("id"));
    user.setName(rs.getString("name"));
    user.setEmail(rs.getString("email"));
    user.setPassword(rs.getString("password"));
    user.setAddress(rs.getString("address"));
    user.setZipcode(rs.getString("zipcode"));
    user.setTelephone(rs.getString("telephone"));
    return user;
  };

  public User findByEmail(String email) {
    String sql = "select id,name,email,password,zipcode,address,telephone from users where email=:email";
    SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
    List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
    if (userList.size() == 0) {
      return null;
    }
    return userList.get(0);
  }

  @Autowired
  PasswordEncoder passwordEncoder;

  public boolean insert(User user) {
    // パスワードをハッシュ化
    String password = user.getPassword();
    user.setPassword(passwordEncoder.encode(password));

    // ハッシュ化確認用
    System.out.println("前 : " + password);
    System.out.println("後 : " + user.getPassword());

    // DBに同じメールアドレスが存在しない場合のみinsert文を実行
    String sql = "insert into users(name,email,password,zipcode,address,telephone)values(:name,:email,:password,:zipcode,:address,:telephone);";
    SqlParameterSource param = new BeanPropertySqlParameterSource(user);
    template.update(sql, param);
    return true;
  }
}
