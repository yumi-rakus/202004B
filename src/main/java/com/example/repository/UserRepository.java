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
import com.example.form.UserForm;

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

		String sql = "SELECT id, name, email, password, address, zipcode, telephone FROM users WHERE email = :email";
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
		String sql = "insert into users(name,email,password,zipcode,address,telephone)values(:name,:email,:password,:zipcode,:address,:telephone);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
		return true;
	}

	/**
	 * ユーザ情報を更新する
	 * 
	 * @param user ユーザ情報
	 * @param name ユーザメールアドレス
	 * @return true
	 * @author kohei eto
	 */
	public boolean update(User user, String name) {
		// パスワードをハッシュ化
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		String sql = "update users SET name = :name, email = :email, password = :password where email = :email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", name);
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
		String sql = "select id,name,email,password,zipcode,address,telephone from users where id=:id";
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}

	/**
	 * 
	 * @param userid
	 * @param user
	 * @author kohei eto
	 */
	public void update(Integer id, User user) {

		/* SqlParameterSource param = new BeanPropertySqlParameterSource(user); */
		String sql = "update users SET name = :name, email = :email, zipcode = :zipcode, address = :address, telephone = :telephone  where id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", user.getName())
				.addValue("email", user.getEmail()).addValue("zipcode", user.getZipcode())
				.addValue("address", user.getAddress()).addValue("telephone", user.getTelephone()).addValue("id", id);

		template.update(sql, param);
	}
}
