package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.domain.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@BeforeEach
	void 事前処理() throws Exception {
		User user = new User();
		user.setEmail("test@test");
		user.setName("テスト");
		user.setPassword("test");
		user.setZipcode("1111111");
		user.setZipcodefirst("111");
		user.setZipcodelast("1111");
		user.setAddress("test住所");
		user.setTelephone("11111111111");
		user.setIsAdmin(true);
		userService.insert(user);
	}

	@AfterEach
	void 事後処理() throws Exception {
		String sql = "TRUNCATE TABLE users RESTART IDENTITY;";
		SqlParameterSource param = new MapSqlParameterSource();
		template.update(sql, param);
	}
	
	@Test
	void getPointsメソッドのテスト() {
		Integer points = userService.getPoints(1);
		assertEquals(0, points, "ポイントが期待される結果と異なります");
	}
	
	@Test
	void addPointsメソッドのテスト() {
		userService.addPoints(1, 1);
		Integer points = userService.getPoints(1);
		assertEquals(1, points, "ポイントが期待される結果と異なります");
	}
	
	@Test
	void subPointsメソッドのテスト() {
		userService.subPoints(1, 1);
		Integer points = userService.getPoints(1);
		assertEquals(-1, points, "ポイントが期待される結果と異なります");
	}

}
