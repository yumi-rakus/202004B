package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

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
	private PasswordEncoder passwordEncoder;

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
		user.setIsAdmin(rs.getBoolean("is_admin"));
		
		return user;
	};

	/**
	 * メールアドレスから、既にDBに登録されているかを判定する.
	 * 
	 * @param email メールアドレス
	 * @return 既に登録されているメールアドレスならtrueを、そうでないならfalseを返す
	 * 
	 * @author yumi takahashi
	 */
	public boolean existByEmail(String email) {

		String sql = "SELECT count(*) FROM users WHERE email = :email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		Integer count = template.queryForObject(sql, param, Integer.class);

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * メールアドレスからユーザ情報を取得する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザ情報
	 * 
	 * @author kohei eto
	 */
	public User findByEmail(String email) {

		String sql = "SELECT id, name, email, password, address, zipcode, telephone, is_admin FROM users WHERE email = :email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		List<User> user = template.query(sql, param, USER_ROW_MAPPER);

		return user.get(0);
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
		String sql = "insert into users(name,email,password,zipcode,address,telephone,is_admin)values(:name,:email,:password,:zipcode,:address,:telephone,:isAdmin);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
		return true;
	}

	/**
	 * ユーザidからユーザ情報を取得
	 * 
	 * @param userId
	 * @return shoya fujisawa
	 */
	public User findById(Integer id) {
		String sql = "select id,name,email,password,zipcode,address,telephone,is_admin from users where id=:id";
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}
}
