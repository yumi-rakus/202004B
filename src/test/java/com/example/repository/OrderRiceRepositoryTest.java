package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.example.domain.OrderRice;

/**
 * OrderRiceRepository.javaのテスト.
 * 
 * @author yumi takahashi
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderRiceRepositoryTest {

	@Autowired
	private OrderRiceRepository orderRiceRepository;
	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeEach
	public void 事前処理() {

		// orderItemId : 1
		OrderRice orderRice1 = new OrderRice();

		orderRice1.setRiceId(3);
		orderRice1.setOrderItemId(1);

		orderRiceRepository.insertOrderRice(orderRice1);

		// orderItemId : 2
		OrderRice orderRice2 = new OrderRice();

		orderRice2.setRiceId(12);
		orderRice2.setOrderItemId(2);

		orderRiceRepository.insertOrderRice(orderRice2);

		// orderItemId : 3
		OrderRice orderRice3 = new OrderRice();

		orderRice3.setRiceId(7);
		orderRice3.setOrderItemId(3);

		orderRiceRepository.insertOrderRice(orderRice3);
	}

	@Test
	public void getRiceIdByOrderItemIdTest1() {

		Integer riceId = orderRiceRepository.getRiceIdByOrderItemId(1);

		assertEquals(3, riceId, "米IDが期待される結果と異なります。");

	}

	@Test
	public void getRiceIdByOrderItemIdTest2() {

		Integer riceId = orderRiceRepository.getRiceIdByOrderItemId(2);

		assertEquals(12, riceId, "米IDが期待される結果と異なります。");

	}

	@Test
	public void getRiceIdByOrderItemIdTest3() {

		Integer riceId = orderRiceRepository.getRiceIdByOrderItemId(3);

		assertEquals(7, riceId, "米IDが期待される結果と異なります。");

	}

	@Test
	public void getRiceIdByOrderItemIdTest4() {

		Integer riceId = orderRiceRepository.getRiceIdByOrderItemId(4);

		assertEquals(0, riceId, "戻り値が期待される結果と異なります。");

	}

	@AfterEach
	public void 事後処理() {
		String sql1 = "TRUNCATE TABLE order_rices RESTART IDENTITY;";
		SqlParameterSource param = new MapSqlParameterSource();

		template.update(sql1, param);
	}
}
