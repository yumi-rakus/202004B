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

import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

/**
 * OrderItemRepository.javaのテスト.
 * 
 * @author yumi takahashi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderItemRepositoryTest {

	// 各メソッドのコメントアウトはテストしたメソッドを表す.

	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeEach
	public void 事前処理() {

		// insertOrderItem()

		// orderId : 1
		// 1
		OrderItem orderItem1 = new OrderItem();

		orderItem1.setItemId(2);
		orderItem1.setOrderId(1);
		orderItem1.setQuantity(1);
		orderItem1.setSize("M".charAt(0));

		orderItem1 = orderItemRepository.insertOrderItem(orderItem1);
		assertEquals(1, orderItem1.getId(), "注文商品IDが期待される結果と異なります。");

		OrderTopping orderTopping1 = new OrderTopping();

		orderTopping1.setToppingId(8);
		orderTopping1.setOrderItemId(1);

		OrderTopping orderTopping2 = new OrderTopping();

		orderTopping2.setToppingId(3);
		orderTopping2.setOrderItemId(1);

		orderToppingRepository.insertOrderTopping(orderTopping1);
		orderToppingRepository.insertOrderTopping(orderTopping2);

		// 2
		OrderItem orderItem2 = new OrderItem();

		orderItem2.setItemId(10);
		orderItem2.setOrderId(1);
		orderItem2.setQuantity(4);
		orderItem2.setSize("L".charAt(0));

		orderItem2 = orderItemRepository.insertOrderItem(orderItem2);
		assertEquals(2, orderItem2.getId(), "注文商品IDが期待される結果と異なります。");

		// orderId : 2
		// 1
		OrderItem orderItem3 = new OrderItem();

		orderItem3.setItemId(1);
		orderItem3.setOrderId(2);
		orderItem3.setQuantity(3);
		orderItem3.setSize("L".charAt(0));

		orderItem3 = orderItemRepository.insertOrderItem(orderItem3);
		assertEquals(3, orderItem3.getId(), "注文商品IDが期待される結果と異なります。");

		// 2
		OrderItem orderItem4 = new OrderItem();

		orderItem4.setItemId(5);
		orderItem4.setOrderId(2);
		orderItem4.setQuantity(1);
		orderItem4.setSize("M".charAt(0));

		orderItem4 = orderItemRepository.insertOrderItem(orderItem4);
		assertEquals(4, orderItem4.getId(), "注文商品IDが期待される結果と異なります。");

		// orderId : 3
		OrderItem orderItem5 = new OrderItem();

		orderItem5.setItemId(2);
		orderItem5.setOrderId(3);
		orderItem5.setQuantity(1);
		orderItem5.setSize("M".charAt(0));

		orderItem5 = orderItemRepository.insertOrderItem(orderItem5);
		assertEquals(5, orderItem5.getId(), "注文商品IDが期待される結果と異なります。");
	}

	@Test
	public void deleteByOrderItemIdTest() {

		// getOrderItemIdListByOrderId()
		// deleteByOrderItemId()

		List<Integer> orderItemIdList = orderItemRepository.getOrderItemIdListByOrderId(1);
		assertEquals(2, orderItemIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(1, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
		assertEquals(2, orderItemIdList.get(1), "注文商品IDが期待される結果と異なります。");

		List<Integer> toppingIdList = orderToppingRepository.getToppingIdListByOrderItemId(1);
		assertEquals(2, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemRepository.deleteByOrderItemId(1);

		toppingIdList = orderToppingRepository.getToppingIdListByOrderItemId(1);
		assertEquals(0, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemIdList = orderItemRepository.getOrderItemIdListByOrderId(1);
		assertEquals(1, orderItemIdList.size(), "件数が期待される結果と異なります。");

		assertEquals(2, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
	}

	@Test
	public void deleteOrderItemAllTest() {

		// getOrderItemIdListByOrderId()
		// deleteOrderItemAll()
		// deleteOrderToppingsByOrderItemId()

		List<Integer> orderItemIdList = orderItemRepository.getOrderItemIdListByOrderId(1);
		assertEquals(2, orderItemIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(1, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
		assertEquals(2, orderItemIdList.get(1), "注文商品IDが期待される結果と異なります。");

		List<Integer> toppingIdList = orderToppingRepository.getToppingIdListByOrderItemId(1);
		assertEquals(2, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemRepository.deleteOrderItemAll(1);
		orderItemRepository.deleteOrderToppingsByOrderItemId(1);

		toppingIdList = orderToppingRepository.getToppingIdListByOrderItemId(1);
		assertEquals(0, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemIdList = orderItemRepository.getOrderItemIdListByOrderId(1);
		assertEquals(0, orderItemIdList.size(), "件数が期待される結果と異なります。");

		String sql = "select count(*) from order_items;";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer count = template.queryForObject(sql, param, Integer.class);

		assertEquals(3, count, "件数が期待される結果と異なります。");
	}

	@Test
	public void getOrderItemIdByOrderIdAndItemIdAndSizeTest() {

		// getOrderItemIdByOrderIdAndItemIdAndSize()

		Integer orderItemId1 = orderItemRepository.getOrderItemIdByOrderIdAndItemIdAndSize(1, 2, "M");
		assertEquals(1, orderItemId1, "注文商品IDが期待される結果と異なります。");

		Integer orderItemId2 = orderItemRepository.getOrderItemIdByOrderIdAndItemIdAndSize(1, 10, "L");
		assertEquals(2, orderItemId2, "注文商品IDが期待される結果と異なります。");

		Integer orderItemId3 = orderItemRepository.getOrderItemIdByOrderIdAndItemIdAndSize(2, 1, "L");
		assertEquals(3, orderItemId3, "注文商品IDが期待される結果と異なります。");

		Integer orderItemId4 = orderItemRepository.getOrderItemIdByOrderIdAndItemIdAndSize(2, 5, "M");
		assertEquals(4, orderItemId4, "注文商品IDが期待される結果と異なります。");

		Integer orderItemId5 = orderItemRepository.getOrderItemIdByOrderIdAndItemIdAndSize(1, 2, "M");
		assertEquals(1, orderItemId5, "注文商品IDが期待される結果と異なります。");
	}

	@Test
	public void getOrderItemIdByOrderIdAndItemIdAndSizeFailureTest() {

		// getOrderItemIdByOrderIdAndItemIdAndSize()

		Integer orderItemId5 = orderItemRepository.getOrderItemIdByOrderIdAndItemIdAndSize(1, 11, "L");
		assertEquals(0, orderItemId5, "戻り値が期待される結果と異なります。");
	}

	@Test
	public void updateQuantityByOrderItemIdTest1() {

		// updateQuantityByOrderItemId()

		String sql = "SELECT quantity FROM order_items WHERE id = 5;";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer exQuantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(1, exQuantity, "数量が期待される結果と異なります。");

		orderItemRepository.updateQuantityByOrderItemId(4, 5);

		Integer quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(5, quantity, "数量が期待される結果と異なります。");
	}

	@Test
	public void updateQuantityByOrderItemIdTest2() {

		// updateQuantityByOrderItemId()

		String sql = "SELECT quantity FROM order_items WHERE id = 3;";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer exQuantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(3, exQuantity, "数量が期待される結果と異なります。");

		orderItemRepository.updateQuantityByOrderItemId(6, 3);

		Integer quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(9, quantity, "数量が期待される結果と異なります。");
	}

	@Test
	public void updateNewQuantityByOrderItemIdTest1() {

		// updateNewQuantityByOrderItemId()

		String sql = "SELECT quantity FROM order_items WHERE id = 1;";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer exQuantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(1, exQuantity, "数量が期待される結果と異なります。");

		orderItemRepository.updateNewQuantityByOrderItemId(3, 1);

		Integer quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(3, quantity, "数量が期待される結果と異なります。");

		orderItemRepository.updateNewQuantityByOrderItemId(8, 1);

		quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(8, quantity, "数量が期待される結果と異なります。");
	}

	@Test
	public void updateNewQuantityByOrderItemIdTest2() {

		// updateNewQuantityByOrderItemId()

		String sql = "SELECT quantity FROM order_items WHERE id = 2;";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer exQuantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(4, exQuantity, "数量が期待される結果と異なります。");

		orderItemRepository.updateNewQuantityByOrderItemId(1, 2);

		Integer quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(1, quantity, "数量が期待される結果と異なります。");

		orderItemRepository.updateNewQuantityByOrderItemId(6, 2);

		quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(6, quantity, "数量が期待される結果と異なります。");
	}

	@AfterEach
	public void 事後処理() {

		String sql1 = "TRUNCATE TABLE order_items RESTART IDENTITY; TRUNCATE TABLE order_toppings RESTART IDENTITY;";
		SqlParameterSource param = new MapSqlParameterSource();

		template.update(sql1, param);
	}

}
