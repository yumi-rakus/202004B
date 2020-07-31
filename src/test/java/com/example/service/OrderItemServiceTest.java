package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

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

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderRice;
import com.example.domain.OrderTopping;
import com.example.form.ItemForm;

/**
 * OrderItemService.javaのテスト.
 * 
 * @author yumi takahashi
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderItemServiceTest {

	// 各メソッドのコメントアウトはテストしたメソッドを表す.

	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderToppingService orderToppingService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderRiceService orderRiceService;
	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeEach
	public void 事前処理() {

		// insertOrderItem()

		/////// orderId : 1
		Order order1 = new Order();
		order1.setUserId(10);
		order1.setStatus(0);
		order1.setTotalPrice(0);
		orderService.insertOrderStatus0(order1);

		// 1
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setItemId(2);
		orderItem1.setOrderId(1);
		orderItem1.setQuantity(1);
		orderItem1.setSize("M".charAt(0));
		orderItem1 = orderItemService.insertOrderItem(orderItem1);
		assertEquals(1, orderItem1.getId(), "注文商品IDが期待される結果と異なります。");

		OrderTopping orderTopping1 = new OrderTopping();
		orderTopping1.setToppingId(8);
		orderTopping1.setOrderItemId(1);

		OrderTopping orderTopping2 = new OrderTopping();
		orderTopping2.setToppingId(3);
		orderTopping2.setOrderItemId(1);

		orderToppingService.insertOrderTopping(orderTopping1);
		orderToppingService.insertOrderTopping(orderTopping2);

		OrderRice orderRice1 = new OrderRice();
		orderRice1.setOrderItemId(1);
		orderRice1.setRiceId(4);
		orderRiceService.insertOrderRice(orderRice1);

		// 2
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setItemId(10);
		orderItem2.setOrderId(1);
		orderItem2.setQuantity(4);
		orderItem2.setSize("L".charAt(0));
		orderItem2 = orderItemService.insertOrderItem(orderItem2);
		assertEquals(2, orderItem2.getId(), "注文商品IDが期待される結果と異なります。");

		OrderRice orderRice2 = new OrderRice();
		orderRice2.setOrderItemId(2);
		orderRice2.setRiceId(7);
		orderRiceService.insertOrderRice(orderRice2);

		/////// orderId : 2
		Order order2 = new Order();
		order2.setUserId(20);
		order2.setStatus(0);
		order2.setTotalPrice(0);
		orderService.insertOrderStatus0(order2);

		// 1
		OrderItem orderItem3 = new OrderItem();
		orderItem3.setItemId(1);
		orderItem3.setOrderId(2);
		orderItem3.setQuantity(3);
		orderItem3.setSize("L".charAt(0));
		orderItem3 = orderItemService.insertOrderItem(orderItem3);
		assertEquals(3, orderItem3.getId(), "注文商品IDが期待される結果と異なります。");

		OrderRice orderRice3 = new OrderRice();
		orderRice3.setOrderItemId(3);
		orderRice3.setRiceId(4);
		orderRiceService.insertOrderRice(orderRice3);

		// 2
		OrderItem orderItem4 = new OrderItem();
		orderItem4.setItemId(5);
		orderItem4.setOrderId(2);
		orderItem4.setQuantity(1);
		orderItem4.setSize("M".charAt(0));
		orderItem4 = orderItemService.insertOrderItem(orderItem4);
		assertEquals(4, orderItem4.getId(), "注文商品IDが期待される結果と異なります。");

		OrderRice orderRice4 = new OrderRice();
		orderRice4.setOrderItemId(4);
		orderRice4.setRiceId(11);
		orderRiceService.insertOrderRice(orderRice4);

		/////// orderId : 3
		Order order3 = new Order();
		order3.setUserId(30);
		order3.setStatus(0);
		order3.setTotalPrice(0);
		orderService.insertOrderStatus0(order3);

		// 1
		OrderItem orderItem5 = new OrderItem();
		orderItem5.setItemId(2);
		orderItem5.setOrderId(3);
		orderItem5.setQuantity(1);
		orderItem5.setSize("M".charAt(0));
		orderItem5 = orderItemService.insertOrderItem(orderItem5);
		assertEquals(5, orderItem5.getId(), "注文商品IDが期待される結果と異なります。");

		OrderRice orderRice5 = new OrderRice();
		orderRice5.setOrderItemId(5);
		orderRice5.setRiceId(9);
		orderRiceService.insertOrderRice(orderRice5);
	}

	@Test
	public void deleteByOrderItemIdTest() {

		// getOrderItemIdListByOrderId()
		// deleteByOrderItemId()

		List<Integer> orderItemIdList = orderItemService.getOrderItemIdListByOrderId(1);
		assertEquals(2, orderItemIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(1, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
		assertEquals(2, orderItemIdList.get(1), "注文商品IDが期待される結果と異なります。");

		List<Integer> toppingIdList = orderToppingService.getToppingIdListByOrderItemId(1);
		assertEquals(2, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemService.deleteByOrderItemId(1);

		toppingIdList = orderToppingService.getToppingIdListByOrderItemId(1);
		assertEquals(0, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemIdList = orderItemService.getOrderItemIdListByOrderId(1);
		assertEquals(1, orderItemIdList.size(), "件数が期待される結果と異なります。");

		assertEquals(2, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
	}

	@Test
	public void deleteOrderItemAllTest() {

		// getOrderItemIdListByOrderId()
		// deleteOrderItemAll()
		// deleteOrderToppingsByOrderItemId()

		List<Integer> orderItemIdList = orderItemService.getOrderItemIdListByOrderId(1);
		assertEquals(2, orderItemIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(1, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
		assertEquals(2, orderItemIdList.get(1), "注文商品IDが期待される結果と異なります。");

		List<Integer> toppingIdList = orderToppingService.getToppingIdListByOrderItemId(1);
		assertEquals(2, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemService.deleteOrderItemAll(1);
		orderItemService.deleteOrderToppingsByOrderItemId(1);

		toppingIdList = orderToppingService.getToppingIdListByOrderItemId(1);
		assertEquals(0, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemIdList = orderItemService.getOrderItemIdListByOrderId(1);
		assertEquals(0, orderItemIdList.size(), "件数が期待される結果と異なります。");

		String sql = "select count(*) from order_items;";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer count = template.queryForObject(sql, param, Integer.class);

		assertEquals(3, count, "件数が期待される結果と異なります。");
	}

	@Test
	public void deleteOrderItemsAndOrderToppingsAllTest() {

		// deleteOrderItemsAndOrderToppingsAll()

		List<Integer> orderItemIdList = orderItemService.getOrderItemIdListByOrderId(1);
		assertEquals(2, orderItemIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(1, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
		assertEquals(2, orderItemIdList.get(1), "注文商品IDが期待される結果と異なります。");

		List<Integer> toppingIdList = orderToppingService.getToppingIdListByOrderItemId(1);
		assertEquals(2, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemService.deleteOrderItemsAndOrderToppingsAll(10);

		toppingIdList = orderToppingService.getToppingIdListByOrderItemId(1);
		assertEquals(0, toppingIdList.size(), "件数が期待される結果と異なります。");

		orderItemIdList = orderItemService.getOrderItemIdListByOrderId(1);
		assertEquals(0, orderItemIdList.size(), "件数が期待される結果と異なります。");

		String sql = "select count(*) from order_items;";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer count = template.queryForObject(sql, param, Integer.class);

		assertEquals(3, count, "件数が期待される結果と異なります。");
	}

	@Test
	public void getOrderItemIdByOrderIdAndItemIdAndSizeTest() {

		// getOrderItemIdByOrderIdAndItemIdAndSize()

		Integer orderItemId1 = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(1, 2, "M");
		assertEquals(1, orderItemId1, "注文商品IDが期待される結果と異なります。");

		Integer orderItemId2 = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(1, 10, "L");
		assertEquals(2, orderItemId2, "注文商品IDが期待される結果と異なります。");

		Integer orderItemId3 = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(2, 1, "L");
		assertEquals(3, orderItemId3, "注文商品IDが期待される結果と異なります。");

		Integer orderItemId4 = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(2, 5, "M");
		assertEquals(4, orderItemId4, "注文商品IDが期待される結果と異なります。");

		Integer orderItemId5 = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(1, 2, "M");
		assertEquals(1, orderItemId5, "注文商品IDが期待される結果と異なります。");
	}

	@Test
	public void getOrderItemIdByOrderIdAndItemIdAndSizeFailureTest() {

		// getOrderItemIdByOrderIdAndItemIdAndSize()

		Integer orderItemId5 = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(1, 11, "L");
		assertEquals(0, orderItemId5, "戻り値が期待される結果と異なります。");
	}

	@Test
	public void updateQuantityByOrderItemIdTest1() {

		// updateQuantityByOrderItemId()

		String sql = "SELECT quantity FROM order_items WHERE id = 5;";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer exQuantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(1, exQuantity, "数量が期待される結果と異なります。");

		orderItemService.updateQuantityByOrderItemId(4, 5);

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

		orderItemService.updateQuantityByOrderItemId(6, 3);

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

		orderItemService.updateNewQuantityByOrderItemId(3, 1);

		Integer quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(3, quantity, "数量が期待される結果と異なります。");

		orderItemService.updateNewQuantityByOrderItemId(8, 1);

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

		orderItemService.updateNewQuantityByOrderItemId(1, 2);

		Integer quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(1, quantity, "数量が期待される結果と異なります。");

		orderItemService.updateNewQuantityByOrderItemId(6, 2);

		quantity = template.queryForObject(sql, param, Integer.class);
		assertEquals(6, quantity, "数量が期待される結果と異なります。");
	}

	@Test
	public void cartInTest1() {

		// cartIn()

		// ordersテーブルにレコードがない場合
		ItemForm form = new ItemForm();

		form.setItemId(13);
		form.setSize("L");
		form.setQuantity(2);
		form.setRiceId(3);
		List<Integer> toppingIdList = Arrays.asList(6, 18, 21);
		form.setToppingIdList(toppingIdList);

		orderItemService.cartIn(form, 40);

		String sql = "SELECT count(*) FROM orders WHERE status = 0";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer count1 = template.queryForObject(sql, param, Integer.class);
		assertEquals(4, count1, "件数が期待される結果と異なります。");

		String sql1 = "SELECT count(*) FROM orders WHERE user_id = 40 AND status = 0";
		Integer count2 = template.queryForObject(sql1, param, Integer.class);
		assertEquals(1, count2, "件数が期待される結果と異なります。");

		Integer orderItemId = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(4, 13, "L");
		assertEquals(6, orderItemId, "注文商品IDが期待される結果と異なります。");

		List<Integer> orderItemIdList = orderItemService.getOrderItemIdListByOrderId(4);
		assertEquals(1, orderItemIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(6, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");

		List<Integer> topIdList = orderToppingService.getToppingIdListByOrderItemId(6);
		assertEquals(3, topIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(6, topIdList.get(0), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(18, topIdList.get(1), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(21, topIdList.get(2), "注文トッピングIDが期待される結果と異なります。");

		String sql2 = "SELECT size FROM order_items WHERE id = 6";
		Character size = template.queryForObject(sql2, param, Character.class);
		assertEquals("L".charAt(0), size, "サイズが期待される結果と異なります。");

		String sql3 = "SELECT quantity FROM order_items WHERE id = 6";
		Integer quantity = template.queryForObject(sql3, param, Integer.class);
		assertEquals(2, quantity, "数量が期待される結果と異なります。");

		String sql4 = "SELECT rice_id FROM order_rices WHERE order_item_id = 6";
		Integer riceId = template.queryForObject(sql4, param, Integer.class);
		assertEquals(3, riceId, "米IDが期待される結果と異なります。");
	}

	@Test
	public void cartInTest2() {

		// cartIn()

		// ordersテーブルにレコードがある場合 注文内容の被り無し
		ItemForm form = new ItemForm();

		form.setItemId(13);
		form.setSize("L");
		form.setQuantity(2);
		form.setRiceId(3);
		List<Integer> toppingIdList = Arrays.asList(6, 18, 21);
		form.setToppingIdList(toppingIdList);

		orderItemService.cartIn(form, 20);

		String sql = "SELECT count(*) FROM orders WHERE status = 0";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer count1 = template.queryForObject(sql, param, Integer.class);
		assertEquals(3, count1, "件数が期待される結果と異なります。");

		String sql1 = "SELECT count(*) FROM orders WHERE user_id = 20 AND status = 0";
		Integer count2 = template.queryForObject(sql1, param, Integer.class);
		assertEquals(1, count2, "件数が期待される結果と異なります。");

		Integer orderItemId = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(2, 13, "L");
		assertEquals(6, orderItemId, "注文商品IDが期待される結果と異なります。");

		List<Integer> orderItemIdList = orderItemService.getOrderItemIdListByOrderId(2);
		assertEquals(3, orderItemIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(3, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
		assertEquals(4, orderItemIdList.get(1), "注文商品IDが期待される結果と異なります。");
		assertEquals(6, orderItemIdList.get(2), "注文商品IDが期待される結果と異なります。");

		List<Integer> topIdList = orderToppingService.getToppingIdListByOrderItemId(6);
		assertEquals(3, topIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(6, topIdList.get(0), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(18, topIdList.get(1), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(21, topIdList.get(2), "注文トッピングIDが期待される結果と異なります。");

		String sql2 = "SELECT size FROM order_items WHERE id = 6";
		Character size = template.queryForObject(sql2, param, Character.class);
		assertEquals("L".charAt(0), size, "サイズが期待される結果と異なります。");

		String sql3 = "SELECT quantity FROM order_items WHERE id = 6";
		Integer quantity = template.queryForObject(sql3, param, Integer.class);
		assertEquals(2, quantity, "数量が期待される結果と異なります。");

		String sql4 = "SELECT rice_id FROM order_rices WHERE order_item_id = 6";
		Integer riceId = template.queryForObject(sql4, param, Integer.class);
		assertEquals(3, riceId, "米IDが期待される結果と異なります。");
	}

	@Test
	public void cartInTest3() {

		// cartIn()

		// ordersテーブルにレコードがある場合 注文内容の被り有り
		ItemForm form = new ItemForm();

		form.setItemId(2);
		form.setSize("M");
		form.setQuantity(3);
		form.setRiceId(4);
		List<Integer> toppingIdList = Arrays.asList(8, 3);
		form.setToppingIdList(toppingIdList);

		orderItemService.cartIn(form, 10);

		String sql = "SELECT count(*) FROM orders WHERE status = 0";
		SqlParameterSource param = new MapSqlParameterSource();
		Integer count1 = template.queryForObject(sql, param, Integer.class);
		assertEquals(3, count1, "件数が期待される結果と異なります。");

		String sql1 = "SELECT count(*) FROM orders WHERE user_id = 10 AND status = 0";
		Integer count2 = template.queryForObject(sql1, param, Integer.class);
		assertEquals(1, count2, "件数が期待される結果と異なります。");

		Integer orderItemId = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(1, 2, "M");
		assertEquals(1, orderItemId, "注文商品IDが期待される結果と異なります。");

		List<Integer> orderItemIdList = orderItemService.getOrderItemIdListByOrderId(1);
		assertEquals(2, orderItemIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(1, orderItemIdList.get(0), "注文商品IDが期待される結果と異なります。");
		assertEquals(2, orderItemIdList.get(1), "注文商品IDが期待される結果と異なります。");

		List<Integer> topIdList = orderToppingService.getToppingIdListByOrderItemId(1);
		assertEquals(2, topIdList.size(), "件数が期待される結果と異なります。");
		assertEquals(3, topIdList.get(0), "注文トッピングIDが期待される結果と異なります。");
		assertEquals(8, topIdList.get(1), "注文トッピングIDが期待される結果と異なります。");

		String sql2 = "SELECT size FROM order_items WHERE id = 1";
		Character size = template.queryForObject(sql2, param, Character.class);
		assertEquals("M".charAt(0), size, "サイズが期待される結果と異なります。");

		String sql3 = "SELECT quantity FROM order_items WHERE id = 1";
		Integer quantity = template.queryForObject(sql3, param, Integer.class);
		assertEquals(4, quantity, "数量が期待される結果と異なります。");

		String sql4 = "SELECT rice_id FROM order_rices WHERE order_item_id = 1";
		Integer riceId = template.queryForObject(sql4, param, Integer.class);
		assertEquals(4, riceId, "米IDが期待される結果と異なります。");

		String sql5 = "SELECT count(*) FROM order_items";
		Integer count3 = template.queryForObject(sql5, param, Integer.class);
		assertEquals(5, count3, "件数が期待される結果と異なります。");
	}

	@AfterEach
	public void 事後処理() {

		StringBuilder sql = new StringBuilder();
		sql.append("TRUNCATE TABLE order_items RESTART IDENTITY; ");
		sql.append("TRUNCATE TABLE order_toppings RESTART IDENTITY; ");
		sql.append("TRUNCATE TABLE orders RESTART IDENTITY; ");
		sql.append("TRUNCATE TABLE order_rices RESTART IDENTITY;");

		SqlParameterSource param = new MapSqlParameterSource();

		template.update(sql.toString(), param);
	}

}
