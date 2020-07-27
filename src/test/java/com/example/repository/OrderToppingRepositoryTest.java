package com.example.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.OrderTopping;

/**
 * OrderToppingRepository.javaのテスト.
 * 
 * @author yumi takahashi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderToppingRepositoryTest {

	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeEach
	public void 事前処理() {

		// orderItemId : 1
		OrderTopping orderTopping1of1 = new OrderTopping();

		orderTopping1of1.setToppingId(8);
		orderTopping1of1.setOrderItemId(1);

		OrderTopping orderTopping2of1 = new OrderTopping();

		orderTopping2of1.setToppingId(3);
		orderTopping2of1.setOrderItemId(1);

		OrderTopping orderTopping3of1 = new OrderTopping();

		orderTopping3of1.setToppingId(2);
		orderTopping3of1.setOrderItemId(1);

		orderToppingRepository.insertOrderTopping(orderTopping1of1);
		orderToppingRepository.insertOrderTopping(orderTopping2of1);
		orderToppingRepository.insertOrderTopping(orderTopping3of1);

		// orderItemId : 2
		OrderTopping orderTopping1of2 = new OrderTopping();

		orderTopping1of2.setToppingId(9);
		orderTopping1of2.setOrderItemId(2);

		OrderTopping orderTopping2of2 = new OrderTopping();

		orderTopping2of2.setToppingId(15);
		orderTopping2of2.setOrderItemId(2);

		orderToppingRepository.insertOrderTopping(orderTopping1of2);
		orderToppingRepository.insertOrderTopping(orderTopping2of2);

		// orderItemId : 3
		OrderTopping orderTopping1of3 = new OrderTopping();

		orderTopping1of3.setToppingId(4);
		orderTopping1of3.setOrderItemId(3);

		OrderTopping orderTopping2of3 = new OrderTopping();

		orderTopping2of3.setToppingId(18);
		orderTopping2of3.setOrderItemId(3);

		OrderTopping orderTopping3of3 = new OrderTopping();

		orderTopping3of3.setToppingId(10);
		orderTopping3of3.setOrderItemId(3);

		OrderTopping orderTopping4of3 = new OrderTopping();

		orderTopping4of3.setToppingId(22);
		orderTopping4of3.setOrderItemId(3);

		orderToppingRepository.insertOrderTopping(orderTopping1of3);
		orderToppingRepository.insertOrderTopping(orderTopping2of3);
		orderToppingRepository.insertOrderTopping(orderTopping3of3);
		orderToppingRepository.insertOrderTopping(orderTopping4of3);
	}

	@Test
	public void getToppingIdListByOrderItemIdTest1() {

		List<Integer> toppingIdList = orderToppingRepository.getToppingIdListByOrderItemId(1);

		assertEquals(3, toppingIdList.size(), "件数が期待される結果と異なります。");

		assertEquals(2, toppingIdList.get(0), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(3, toppingIdList.get(1), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(8, toppingIdList.get(2), "注文トッピングIDが期待される結果と異なります。");
	}

	@Test
	public void getToppingIdListByOrderItemIdTest2() {

		List<Integer> toppingIdList = orderToppingRepository.getToppingIdListByOrderItemId(2);

		assertEquals(2, toppingIdList.size(), "件数が期待される結果と異なります。");

		assertEquals(9, toppingIdList.get(0), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(15, toppingIdList.get(1), "注文トッピングIDが期待される結果と異なります。");
	}

	@Test
	public void getToppingIdListByOrderItemIdTest3() {

		List<Integer> toppingIdList = orderToppingRepository.getToppingIdListByOrderItemId(3);

		assertEquals(4, toppingIdList.size(), "件数が期待される結果と異なります。");

		assertEquals(4, toppingIdList.get(0), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(10, toppingIdList.get(1), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(18, toppingIdList.get(2), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(22, toppingIdList.get(3), "注文トッピングIDが期待される結果と異なります。");
	}

	@AfterEach
	public void 事後処理() {

		String sql1 = "TRUNCATE TABLE order_toppings RESTART IDENTITY;";
		SqlParameterSource param = new MapSqlParameterSource();

		template.update(sql1, param);
	}

}
