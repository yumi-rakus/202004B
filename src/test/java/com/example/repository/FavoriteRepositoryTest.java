package com.example.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.List;

import com.example.domain.Favorite;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FavoriteRepositoryTest {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private FavoriteRepository favoriteRepository;

	// Fields
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@BeforeEach
	void setUp() throws Exception {
		// addedDateの下準備

		Favorite favorite1 = new Favorite();
		favorite1.setUserId(1);
		favorite1.setItemId(1);
		favorite1.setAddedDate(sdf.parse("20200401"));
		favoriteRepository.save(favorite1);

		Favorite favorite2 = new Favorite();
		favorite2.setUserId(1);
		favorite2.setItemId(2);
		favorite2.setAddedDate(sdf.parse("20200502"));
		favoriteRepository.save(favorite2);

		Favorite favorite3 = new Favorite();
		favorite3.setUserId(1);
		favorite3.setItemId(3);
		favorite3.setAddedDate(sdf.parse("20200603"));
		favoriteRepository.save(favorite3);
	}

	@AfterEach
	void tearDown() throws Exception {
		String sql = "TRUNCATE TABLE favorites RESTART IDENTITY;";
		SqlParameterSource param = new MapSqlParameterSource();
		template.update(sql, param);
	}

	@Test
	void findAllByUserIdのテスト() throws Exception {
		List<Favorite> favoriteList = favoriteRepository.findAllByUserId(1);

		assertEquals(1, favoriteList.get(0).getId(), "index=0のidが期待される結果と異なります。");
		assertEquals(1, favoriteList.get(0).getUserId(), "index=0のuserIdが期待される結果と異なります。");
		assertEquals(1, favoriteList.get(0).getItemId(), "index=0のitemIdが期待される結果と異なります。");
		assertEquals(sdf.parse("20200401"), favoriteList.get(0).getAddedDate(), "index=0のaddedDateが期待される結果と異なります。");
		
		assertEquals(3, favoriteList.get(2).getId(), "index=2のidが期待される結果と異なります。");
		assertEquals(1, favoriteList.get(2).getUserId(), "index=2のuserIdが期待される結果と異なります。");
		assertEquals(3, favoriteList.get(2).getItemId(), "index=2のitemIdが期待される結果と異なります。");
		assertEquals(sdf.parse("20200603"), favoriteList.get(2).getAddedDate(), "index=2のaddedDateが期待される結果と異なります。");
	}

}
