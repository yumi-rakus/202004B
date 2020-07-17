package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.domain.User;

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

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Userオブジェクトを生成するローマッパー.
	 */
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

	/**
	 * ユーザ情報をメールアドレスから取得する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザ情報
	 * 
	 * @author kohei eto
	 */
	public User findByEmail(String email) {
		String sql = "select id,name,email,password,zipcode,address,telephone from users where email=:email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	/**
	 * ユーザ情報を挿入する.
	 * 
	 * @param user ユーザ情報
	 * @return true
	 * 
	 * @author soshi morita
	 */
	public boolean insert(User user) {
		// パスワードをハッシュ化
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		String sql = "insert into users(name,email,password,zipcode,address,telephone)values(:name,:email,:password,:zipcode,:address,:telephone);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
		return true;
	}
}
