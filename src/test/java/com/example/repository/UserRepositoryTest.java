package com.example.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.domain.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * UserRepositoryのテストクラス
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private NamedParameterJdbcTemplate template;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	// Fields
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
		user.setPoints(rs.getInt("points"));
		return user;
	};

	@BeforeEach
	void setUp() throws Exception {
		User user1 = new User();
		user1.setName("test");
		user1.setEmail("test@test");
		user1.setPassword("test");
		user1.setZipcode("1111111");
		user1.setZipcodefirst("111");
		user1.setZipcodelast("1111");
		user1.setAddress("test住所");
		user1.setTelephone("11111111111");
		user1.setIsAdmin(true);
		user1.setPoints(0);
		userRepository.insert(user1);
	}

	@AfterEach
	void tearDown() throws Exception {
		String sql = "TRUNCATE TABLE users RESTART IDENTITY;";
		SqlParameterSource param = new MapSqlParameterSource();
		template.update(sql, param);
	}

	@Test
	void insertのテスト() throws Exception {
		String sql = "SELECT id, name, email, password, address, zipcode, telephone, is_admin, points FROM users WHERE email = 'test@test'";
		SqlParameterSource param = new MapSqlParameterSource();
		User user = template.query(sql, param, USER_ROW_MAPPER).get(0);

		assertEquals("test", user.getName(), "期待される結果と異なります。");
		assertEquals("test@test", user.getEmail(), "期待される結果と異なります。");
		assertTrue(passwordEncoder.matches("test", user.getPassword()), "期待される結果と異なります。");
		assertEquals("test住所", user.getAddress(), "期待される結果と異なります。");
		assertEquals("1111111", user.getZipcode(), "期待される結果と異なります。");
		assertEquals("11111111111", user.getTelephone(), "期待される結果と異なります。");
		assertEquals(true, user.getIsAdmin(), "期待される結果と異なります。");
		assertEquals(0, user.getPoints(), "期待される結果と異なります。");
	}

	@Test
	void findByEmailのテスト(){
		User user = userRepository.findByEmail("test@test");
		assertEquals("test", user.getName(), "期待される結果と異なります。");

	}
	
	@Test
	void findByIdメソッドのテスト() {
		String sql = "select id,name,email,password,zipcode,address,telephone,is_admin,points from users where id=1";
		SqlParameterSource param = new MapSqlParameterSource();
		User user = template.query(sql, param, USER_ROW_MAPPER).get(0);
		
		assertEquals("test", user.getName(), "期待される結果と異なります。");
		assertEquals("test@test", user.getEmail(), "期待される結果と異なります。");
		assertTrue(passwordEncoder.matches("test", user.getPassword()), "期待される結果と異なります。");
		assertEquals("test住所", user.getAddress(), "期待される結果と異なります。");
		assertEquals("1111111", user.getZipcode(), "期待される結果と異なります。");
		assertEquals("11111111111", user.getTelephone(), "期待される結果と異なります。");
		assertEquals(true, user.getIsAdmin(), "期待される結果と異なります。");
		assertEquals(0, user.getPoints(), "期待される結果と異なります。");
	}
}
