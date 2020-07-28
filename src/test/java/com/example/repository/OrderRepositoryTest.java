package com.example.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private OrderRiceRepository orderRiceRepository;
	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeEach
	public void 事前処理() {

		/////// orderId : 1 (status0)
		Order order1 = new Order();
		order1.setUserId(10);
		order1.setStatus(0);
		order1.setTotalPrice(0);
		order1 = orderRepository.insertOrderStatus0(order1);
		assertEquals(1, order1.getId(), "注文IDが期待される結果と異なります。");

		// item1
		OrderItem orderItem1of1 = new OrderItem();
		orderItem1of1.setItemId(2);
		orderItem1of1.setOrderId(1);
		orderItem1of1.setQuantity(1);
		orderItem1of1.setSize("M".charAt(0));
		orderItem1of1 = orderItemRepository.insertOrderItem(orderItem1of1);
		assertEquals(1, orderItem1of1.getId(), "注文商品IDが期待される結果と異なります。");

		OrderTopping orderTopping1of1of1 = new OrderTopping();
		orderTopping1of1of1.setToppingId(5);
		orderTopping1of1of1.setOrderItemId(1);
		orderToppingRepository.insertOrderTopping(orderTopping1of1of1);

		OrderTopping orderTopping2of1of1 = new OrderTopping();
		orderTopping2of1of1.setToppingId(19);
		orderTopping2of1of1.setOrderItemId(1);
		orderToppingRepository.insertOrderTopping(orderTopping2of1of1);

		OrderRice orderRice1of1 = new OrderRice();
		orderRice1of1.setRiceId(7);
		orderRice1of1.setOrderItemId(1);
		orderRiceRepository.insertOrderRice(orderRice1of1);

		// item2
		OrderItem orderItem2of1 = new OrderItem();
		orderItem2of1.setItemId(8);
		orderItem2of1.setOrderId(1);
		orderItem2of1.setQuantity(4);
		orderItem2of1.setSize("L".charAt(0));
		orderItem2of1 = orderItemRepository.insertOrderItem(orderItem2of1);
		assertEquals(2, orderItem2of1.getId(), "注文商品IDが期待される結果と異なります。");

		OrderTopping orderTopping1of2of1 = new OrderTopping();
		orderTopping1of2of1.setToppingId(1);
		orderTopping1of2of1.setOrderItemId(2);
		orderToppingRepository.insertOrderTopping(orderTopping1of2of1);

		OrderRice orderRice2of1 = new OrderRice();
		orderRice2of1.setRiceId(3);
		orderRice2of1.setOrderItemId(2);
		orderRiceRepository.insertOrderRice(orderRice2of1);

		////// orderId : 2 (status0)
		Order order2 = new Order();
		order2.setUserId(20);
		order2.setStatus(0);
		order2.setTotalPrice(0);
		order2 = orderRepository.insertOrderStatus0(order2);
		assertEquals(2, order2.getId(), "注文IDが期待される結果と異なります。");

		// item1
		OrderItem orderItem1of2 = new OrderItem();
		orderItem1of2.setItemId(1);
		orderItem1of2.setOrderId(2);
		orderItem1of2.setQuantity(6);
		orderItem1of2.setSize("M".charAt(0));
		orderItem1of2 = orderItemRepository.insertOrderItem(orderItem1of2);
		assertEquals(3, orderItem1of2.getId(), "注文商品IDが期待される結果と異なります。");

		OrderRice orderRice1of2 = new OrderRice();
		orderRice1of2.setRiceId(9);
		orderRice1of2.setOrderItemId(3);
		orderRiceRepository.insertOrderRice(orderRice1of2);

		// item2
		OrderItem orderItem2of2 = new OrderItem();
		orderItem2of2.setItemId(2);
		orderItem2of2.setOrderId(2);
		orderItem2of2.setQuantity(2);
		orderItem2of2.setSize("L".charAt(0));
		orderItem2of2 = orderItemRepository.insertOrderItem(orderItem2of2);
		assertEquals(4, orderItem2of2.getId(), "注文商品IDが期待される結果と異なります。");

		OrderTopping orderTopping1of2of2 = new OrderTopping();
		orderTopping1of2of2.setToppingId(20);
		orderTopping1of2of2.setOrderItemId(4);
		orderToppingRepository.insertOrderTopping(orderTopping1of2of2);

		OrderRice orderRice2of2 = new OrderRice();
		orderRice2of2.setRiceId(5);
		orderRice2of2.setOrderItemId(4);
		orderRiceRepository.insertOrderRice(orderRice2of2);

		// item3
		OrderItem orderItem3of2 = new OrderItem();
		orderItem3of2.setItemId(13);
		orderItem3of2.setOrderId(2);
		orderItem3of2.setQuantity(4);
		orderItem3of2.setSize("M".charAt(0));
		orderItem3of2 = orderItemRepository.insertOrderItem(orderItem3of2);
		assertEquals(5, orderItem3of2.getId(), "注文商品IDが期待される結果と異なります。");

		OrderRice orderRice3of2 = new OrderRice();
		orderRice3of2.setRiceId(1);
		orderRice3of2.setOrderItemId(5);
		orderRiceRepository.insertOrderRice(orderRice3of2);

		////// orderId : 3 (status1)
		Order order3 = new Order();
		order3.setUserId(30);
		order3.setStatus(1);
		order3.setTotalPrice(1800);
		LocalDate ld3 = LocalDate.of(2020, 7, 30);
		Date date3 = Date.valueOf(ld3);
		order3.setOrderDate(date3);
		order3.setDestinationName("山田太郎");
		order3.setDestinationEmail("yamada@sample.com");
		order3.setDestinationZipcode("1112222");
		order3.setDestinationAddress("山田住所");
		order3.setDestinationTel("09011112222");
		LocalDateTime ldt3 = LocalDateTime.of(2020, 7, 15, 11, 0);
		Timestamp timestamp3 = Timestamp.valueOf(ldt3);
		order3.setDeliveryTime(timestamp3);
		order3.setPaymentMethod(1);
		order3.setDiscountPrice(1700);
		order3.setTax(170);
		order3.setTaxIncludedPrice(1970);
		order3.setUsedPoints(100);
		order3 = orderRepository.insertOrderStatus0(order3);
		assertEquals(3, order3.getId(), "注文IDが期待される結果と異なります。");

		// item1
		OrderItem orderItem1of3 = new OrderItem();
		orderItem1of3.setItemId(4);
		orderItem1of3.setOrderId(3);
		orderItem1of3.setQuantity(2);
		orderItem1of3.setSize("L".charAt(0));
		orderItem1of3 = orderItemRepository.insertOrderItem(orderItem1of3);
		assertEquals(6, orderItem1of3.getId(), "注文商品IDが期待される結果と異なります。");

		OrderRice orderRice1of3 = new OrderRice();
		orderRice1of3.setRiceId(8);
		orderRice1of3.setOrderItemId(6);
		orderRiceRepository.insertOrderRice(orderRice1of3);

		////// orderId : 4 (status2)
		Order order4 = new Order();
		order4.setUserId(40);
		order4.setStatus(2);
		order4.setTotalPrice(2000);
		LocalDate ld4 = LocalDate.of(2020, 8, 1);
		Date date4 = Date.valueOf(ld4);
		order4.setOrderDate(date4);
		order4.setDestinationName("鈴木一郎");
		order4.setDestinationEmail("suzuki@sample.com");
		order4.setDestinationZipcode("3334444");
		order4.setDestinationAddress("鈴木住所");
		order4.setDestinationTel("09033334444");
		LocalDateTime ldt4 = LocalDateTime.of(2020, 7, 20, 13, 0);
		Timestamp timestamp4 = Timestamp.valueOf(ldt4);
		order4.setDeliveryTime(timestamp4);
		order4.setPaymentMethod(2);
		order4.setDiscountPrice(1900);
		order4.setTax(190);
		order4.setTaxIncludedPrice(2090);
		order4.setUsedPoints(100);
		order4 = orderRepository.insertOrderStatus0(order4);
		assertEquals(4, order4.getId(), "注文IDが期待される結果と異なります。");

		// item1
		OrderItem orderItem1of4 = new OrderItem();
		orderItem1of4.setItemId(4);
		orderItem1of4.setOrderId(3);
		orderItem1of4.setQuantity(2);
		orderItem1of4.setSize("L".charAt(0));
		orderItem1of4 = orderItemRepository.insertOrderItem(orderItem1of4);
		assertEquals(7, orderItem1of4.getId(), "注文商品IDが期待される結果と異なります。");

		OrderRice orderRice1of4 = new OrderRice();
		orderRice1of4.setRiceId(17);
		orderRice1of4.setOrderItemId(7);
		orderRiceRepository.insertOrderRice(orderRice1of4);
	}

	@Test
	public void findByUserIdAndStatus0Test() {

		// findByUserIdAndStatus0()

		List<Order> orderList = orderRepository.findByUserIdAndStatus0(10);
		assertEquals(3, orderList.size(), "件数が期待される結果と異なります。");

		assertEquals(10, orderList.get(0).getUserId(), "ユーザIDが期待される結果と異なります。");
		assertEquals(0, orderList.get(0).getTotalPrice(), "合計金額が期待される結果と異なります。");
		assertEquals(2, orderList.get(0).getOrderItemList().get(0).getId(), "注文商品IDが期待される結果と異なります。");
		assertEquals(8, orderList.get(0).getOrderItemList().get(0).getItemId(), "商品IDが期待される結果と異なります。");
		assertEquals(1, orderList.get(0).getOrderItemList().get(0).getOrderId(), "注文IDが期待される結果と異なります。");
		assertEquals(4, orderList.get(0).getOrderItemList().get(0).getQuantity(), "数量が期待される結果と異なります。");
		assertEquals("L".charAt(0), orderList.get(0).getOrderItemList().get(0).getSize(), "サイズが期待される結果と異なります。");
		assertEquals(8, orderList.get(0).getOrderItemList().get(0).getItem().getId(), "商品IDが期待される結果と異なります。");
		assertEquals("カレーうどん", orderList.get(0).getOrderItemList().get(0).getItem().getName(), "商品名が期待される結果と異なります。");
		assertEquals("ラクラクカレー自慢のカレーに魚介のダシ、ローストオニオンのコクが加わった絶妙なスープをお楽しみください",
				orderList.get(0).getOrderItemList().get(0).getItem().getDescription(), "商品説明が期待される結果と異なります。");
		assertEquals(2160, orderList.get(0).getOrderItemList().get(0).getItem().getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(3380, orderList.get(0).getOrderItemList().get(0).getItem().getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("8.jpg", orderList.get(0).getOrderItemList().get(0).getItem().getImagePath(), "画像パスが期待される結果と異なります。");
		assertEquals(3, orderList.get(0).getOrderItemList().get(0).getOrderToppingList().get(0).getId(),
				"注文トッピングIDが期待される結果と異なります。");
		assertEquals(2, orderList.get(0).getOrderItemList().get(0).getOrderToppingList().get(0).getOrderItemId(),
				"注文商品IDが期待される結果と異なります。");
		assertEquals(1, orderList.get(0).getOrderItemList().get(0).getOrderToppingList().get(0).getToppingId(),
				"トッピングIDが期待される結果と異なります。");
		assertEquals(1, orderList.get(0).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getId(),
				"トッピングIDが期待される結果と異なります。");
		assertEquals("オニオン", orderList.get(0).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getName(),
				"トッピング名が期待される結果と異なります。");
		assertEquals(200, orderList.get(0).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getPriceM(),
				"Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, orderList.get(0).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getPriceL(),
				"Lサイズ価格が期待される結果と異なります。");
		assertEquals(2, orderList.get(0).getOrderItemList().get(0).getOrderRice().getId(), "注文米IDが期待される結果と異なります。");
		assertEquals(2, orderList.get(0).getOrderItemList().get(0).getOrderRice().getOrderItemId(),
				"注文商品IDが期待される結果と異なります。");
		assertEquals(3, orderList.get(0).getOrderItemList().get(0).getOrderRice().getRiceId(), "米IDが期待される結果と異なります。");
		assertEquals(3, orderList.get(0).getOrderItemList().get(0).getOrderRice().getRice().getId(), "米IDが期待される結果と異なります。");
		assertEquals("新潟県産新之助", orderList.get(0).getOrderItemList().get(0).getOrderRice().getRice().getName(),
				"米種名が期待される結果と異なります。");
		assertEquals(
				"「コシヒカリとはベクトルが異なるおいしさを持つ米を提供したい」との思いで誕生した2017年販売開始の新品種。大粒できれいなツヤがあり、ほんのりとした香り、豊潤な甘みとコク、しっかりした粘りと弾力を併せ持ちます。 「硬さ」と「粘り」という相反する要素を兼ね備えており、冷めても硬くなりにくく、おいしさを保つ新潟自慢のお米です。",
				orderList.get(0).getOrderItemList().get(0).getOrderRice().getRice().getDescription(), "米説明が期待される結果と異なります。");
		assertEquals("ngt_shinnosuke.png",
				orderList.get(0).getOrderItemList().get(0).getOrderRice().getRice().getImagePath(), "米画像パスが期待される結果と異なります。");

		assertEquals(10, orderList.get(1).getUserId(), "ユーザIDが期待される結果と異なります。");
		assertEquals(0, orderList.get(1).getTotalPrice(), "合計金額が期待される結果と異なります。");
		assertEquals(1, orderList.get(1).getOrderItemList().get(0).getId(), "注文商品IDが期待される結果と異なります。");
		assertEquals(2, orderList.get(1).getOrderItemList().get(0).getItemId(), "商品IDが期待される結果と異なります。");
		assertEquals(1, orderList.get(1).getOrderItemList().get(0).getOrderId(), "注文IDが期待される結果と異なります。");
		assertEquals(1, orderList.get(1).getOrderItemList().get(0).getQuantity(), "数量が期待される結果と異なります。");
		assertEquals("M".charAt(0), orderList.get(1).getOrderItemList().get(0).getSize(), "サイズが期待される結果と異なります。");
		assertEquals(2, orderList.get(1).getOrderItemList().get(0).getItem().getId(), "商品IDが期待される結果と異なります。");
		assertEquals("ポークポークカレー・ミート", orderList.get(1).getOrderItemList().get(0).getItem().getName(), "商品名が期待される結果と異なります。");
		assertEquals("グリーンアスパラと相性の良いベーコンにいろどりのフレッシュトマトをトッピングし特製マヨソースでまとめた商品です",
				orderList.get(1).getOrderItemList().get(0).getItem().getDescription(), "商品説明が期待される結果と異なります。");
		assertEquals(1490, orderList.get(1).getOrderItemList().get(0).getItem().getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, orderList.get(1).getOrderItemList().get(0).getItem().getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("2.jpg", orderList.get(1).getOrderItemList().get(0).getItem().getImagePath(), "画像パスが期待される結果と異なります。");
		assertEquals(1, orderList.get(1).getOrderItemList().get(0).getOrderToppingList().get(0).getId(),
				"注文トッピングIDが期待される結果と異なります。");
		assertEquals(1, orderList.get(1).getOrderItemList().get(0).getOrderToppingList().get(0).getOrderItemId(),
				"注文商品IDが期待される結果と異なります。");
		assertEquals(5, orderList.get(1).getOrderItemList().get(0).getOrderToppingList().get(0).getToppingId(),
				"トッピングIDが期待される結果と異なります。");
		assertEquals(5, orderList.get(1).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getId(),
				"トッピングIDが期待される結果と異なります。");
		assertEquals("プルコギ", orderList.get(1).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getName(),
				"トッピング名が期待される結果と異なります。");
		assertEquals(200, orderList.get(1).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getPriceM(),
				"Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, orderList.get(1).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getPriceL(),
				"Lサイズ価格が期待される結果と異なります。");
		assertEquals(1, orderList.get(1).getOrderItemList().get(0).getOrderRice().getId(), "注文米IDが期待される結果と異なります。");
		assertEquals(1, orderList.get(1).getOrderItemList().get(0).getOrderRice().getOrderItemId(),
				"注文商品IDが期待される結果と異なります。");
		assertEquals(7, orderList.get(1).getOrderItemList().get(0).getOrderRice().getRiceId(), "米IDが期待される結果と異なります。");
		assertEquals(7, orderList.get(1).getOrderItemList().get(0).getOrderRice().getRice().getId(), "米IDが期待される結果と異なります。");
		assertEquals("秋田あきたこまち", orderList.get(1).getOrderItemList().get(0).getOrderRice().getRice().getName(),
				"米種名が期待される結果と異なります。");
		assertEquals("「あきたこまち」は、自然豊かな秋田で丹精込めて作られ、食味の評判は最高。光沢が冴え、あっさりとした食感で冷めても味が落ちない特性を備えています。",
				orderList.get(1).getOrderItemList().get(0).getOrderRice().getRice().getDescription(), "米説明が期待される結果と異なります。");
		assertEquals("akt_komachi.png", orderList.get(1).getOrderItemList().get(0).getOrderRice().getRice().getImagePath(),
				"米画像パスが期待される結果と異なります。");

		assertEquals(10, orderList.get(2).getUserId(), "ユーザIDが期待される結果と異なります。");
		assertEquals(0, orderList.get(2).getTotalPrice(), "合計金額が期待される結果と異なります。");
		assertEquals(1, orderList.get(2).getOrderItemList().get(0).getId(), "注文商品IDが期待される結果と異なります。");
		assertEquals(2, orderList.get(2).getOrderItemList().get(0).getItemId(), "商品IDが期待される結果と異なります。");
		assertEquals(1, orderList.get(2).getOrderItemList().get(0).getOrderId(), "注文IDが期待される結果と異なります。");
		assertEquals(1, orderList.get(2).getOrderItemList().get(0).getQuantity(), "数量が期待される結果と異なります。");
		assertEquals("M".charAt(0), orderList.get(2).getOrderItemList().get(0).getSize(), "サイズが期待される結果と異なります。");
		assertEquals(2, orderList.get(2).getOrderItemList().get(0).getItem().getId(), "商品IDが期待される結果と異なります。");
		assertEquals("ポークポークカレー・ミート", orderList.get(2).getOrderItemList().get(0).getItem().getName(), "商品名が期待される結果と異なります。");
		assertEquals("グリーンアスパラと相性の良いベーコンにいろどりのフレッシュトマトをトッピングし特製マヨソースでまとめた商品です",
				orderList.get(2).getOrderItemList().get(0).getItem().getDescription(), "商品説明が期待される結果と異なります。");
		assertEquals(1490, orderList.get(2).getOrderItemList().get(0).getItem().getPriceM(), "Mサイズ価格が期待される結果と異なります。");
		assertEquals(2570, orderList.get(2).getOrderItemList().get(0).getItem().getPriceL(), "Lサイズ価格が期待される結果と異なります。");
		assertEquals("2.jpg", orderList.get(2).getOrderItemList().get(0).getItem().getImagePath(), "画像パスが期待される結果と異なります。");
		assertEquals(2, orderList.get(2).getOrderItemList().get(0).getOrderToppingList().get(0).getId(),
				"注文トッピングIDが期待される結果と異なります。");
		assertEquals(1, orderList.get(2).getOrderItemList().get(0).getOrderToppingList().get(0).getOrderItemId(),
				"注文商品IDが期待される結果と異なります。");
		assertEquals(19, orderList.get(2).getOrderItemList().get(0).getOrderToppingList().get(0).getToppingId(),
				"トッピングIDが期待される結果と異なります。");
		assertEquals(19, orderList.get(2).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getId(),
				"トッピングIDが期待される結果と異なります。");
		assertEquals("あらびきスライスソｰセｰジ",
				orderList.get(2).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getName(),
				"トッピング名が期待される結果と異なります。");
		assertEquals(200, orderList.get(2).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getPriceM(),
				"Mサイズ価格が期待される結果と異なります。");
		assertEquals(300, orderList.get(2).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getPriceL(),
				"Lサイズ価格が期待される結果と異なります。");
		assertEquals(1, orderList.get(2).getOrderItemList().get(0).getOrderRice().getId(), "注文米IDが期待される結果と異なります。");
		assertEquals(1, orderList.get(2).getOrderItemList().get(0).getOrderRice().getOrderItemId(),
				"注文商品IDが期待される結果と異なります。");
		assertEquals(7, orderList.get(2).getOrderItemList().get(0).getOrderRice().getRiceId(), "米IDが期待される結果と異なります。");
		assertEquals(7, orderList.get(2).getOrderItemList().get(0).getOrderRice().getRice().getId(), "米IDが期待される結果と異なります。");
		assertEquals("秋田あきたこまち", orderList.get(2).getOrderItemList().get(0).getOrderRice().getRice().getName(),
				"米種名が期待される結果と異なります。");
		assertEquals("「あきたこまち」は、自然豊かな秋田で丹精込めて作られ、食味の評判は最高。光沢が冴え、あっさりとした食感で冷めても味が落ちない特性を備えています。",
				orderList.get(2).getOrderItemList().get(0).getOrderRice().getRice().getDescription(), "米説明が期待される結果と異なります。");
		assertEquals("akt_komachi.png", orderList.get(2).getOrderItemList().get(0).getOrderRice().getRice().getImagePath(),
				"米画像パスが期待される結果と異なります。");
	}

	@Test
	public void status0ExistByUserIdTest1() {

		assertTrue(orderRepository.status0ExistByUserId(10), "期待される結果と異なります。");
		assertTrue(orderRepository.status0ExistByUserId(20), "期待される結果と異なります。");
	}

	@Test
	public void status0ExistByUserIdTest2() {

		assertFalse(orderRepository.status0ExistByUserId(1000), "期待される結果と異なります。");
		assertFalse(orderRepository.status0ExistByUserId(30), "期待される結果と異なります。");
		assertFalse(orderRepository.status0ExistByUserId(40), "期待される結果と異なります。");
	}

	@Test
	public void getOrderIdByUserIdTest() {

		assertEquals(1, orderRepository.getOrderIdByUserId(10));
		assertEquals(2, orderRepository.getOrderIdByUserId(20));
	}

	@Test
	public void updateTotalPriceTest() {

		orderRepository.updateTotalPrice(10, 1000);
	}

	@Test
	public void orderメソッドのテスト() {
		List<Order> orderList = orderRepository.findByUserIdAndStatus0(10);
		System.out.println(orderList);
		assertEquals(0, orderList.get(0).getStatus(), "ステータスが期待される結果と異なります");
		assertEquals(null, orderList.get(0).getDestinationName(), "名前が期待される結果と異なります");
		assertEquals(null, orderList.get(0).getOrderDate(), "ステータスが期待される結果と異なります");
		assertEquals(null, orderList.get(0).getDestinationEmail(), "メールアドレスが期待される結果と異なります");
		assertEquals(null, orderList.get(0).getDestinationZipcode(), "郵便番号が期待される結果と異なります");
		assertEquals(null, orderList.get(0).getDestinationAddress(), "住所が期待される結果と異なります");
		assertEquals(null, orderList.get(0).getDestinationTel(), "電話番号が期待される結果と異なります");
		assertEquals(null, orderList.get(0).getOrderDate(), "配達日が期待される結果と異なります");
		assertEquals(null, orderList.get(0).getDeliveryTime(), "配達時間が期待される結果と異なります");
		assertEquals(0, orderList.get(0).getPaymentMethod(), "支払い方法が期待される結果と異なります");
		assertEquals(10, orderList.get(0).getUserId(), "ユーザIDが期待される結果と異なります");
		assertEquals(0, orderList.get(0).getDiscountPrice(), "ポイント利用時金額が期待される結果と異なります");
		assertEquals(0, orderList.get(0).getTax(), "消費税が期待される結果と異なります");
		assertEquals(0, orderList.get(0).getTaxIncludedPrice(), "税込合計金額が期待される結果と異なります");
		assertEquals(0, orderList.get(0).getUsedPoints(), "利用ポイントが期待される結果と異なります");

		Order order = new Order();
		order.setStatus(1);
		order.setDestinationName("山田太郎");
		order.setStatus(1);
		order.setDestinationEmail("yamada@mail.com");
		order.setDestinationZipcode("1111111");
		order.setDestinationAddress("東京都新宿区1-1-1");
		order.setDestinationTel("0000000000");
		java.util.Date date = new java.util.Date();
		order.setOrderDate(date);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateTime = "2020-07-28 9:00:00";
		try {
			java.util.Date dTime = (java.util.Date) sdf.parse(dateTime);
			Timestamp ts = new Timestamp(dTime.getTime());
			order.setDeliveryTime(ts);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		order.setPaymentMethod(1);
		order.setUserId(10);
		order.setDiscountPrice(1500);
		order.setTotalPrice(1800);
		order.setTax(200);
		order.setTaxIncludedPrice(2000);
		order.setUsedPoints(500);
		System.out.println(order);
		orderRepository.order(order);
		orderList = orderRepository.findByUserIdAndNonStatus0(10);
		System.out.println(orderList);
		assertEquals(1, orderList.get(0).getStatus(), "ステータスが期待される結果と異なります");
		assertEquals("山田太郎", orderList.get(0).getDestinationName(), "名前が期待される結果と異なります");
		assertEquals("yamada@mail.com", orderList.get(0).getDestinationEmail(), "メールアドレスが期待される結果と異なります");
		assertEquals("1111111", orderList.get(0).getDestinationZipcode(), "郵便番号が期待される結果と異なります");
		assertEquals("東京都新宿区1-1-1", orderList.get(0).getDestinationAddress(), "住所が期待される結果と異なります");
		assertEquals("0000000000", orderList.get(0).getDestinationTel(), "電話番号が期待される結果と異なります");
		assertEquals(1, orderList.get(0).getPaymentMethod(), "支払い方法が期待される結果と異なります");
		assertEquals(10, orderList.get(0).getUserId(), "ユーザIDが期待される結果と異なります");
		assertEquals(1500, orderList.get(0).getDiscountPrice(), "ポイント利用時金額が期待される結果と異なります");
		assertEquals(2000, orderList.get(0).getTaxIncludedPrice(), "税込合計金額が期待される結果と異なります");
		assertEquals(500, orderList.get(0).getUsedPoints(), "利用ポイントが期待される結果と異なります");
	}

	@Test
	public void findByUserIdAndNonStatus0のテスト_異常系() {
		// findByUserIdAndNonStatus0
		
		List<Order> orderList1 = orderRepository.findByUserIdAndNonStatus0(1000);
		assertTrue(orderList1.isEmpty(), "期待される結果と異なります");
		
		List<Order> orderList2 = orderRepository.findByUserIdAndNonStatus0(123);
		assertTrue(orderList2.isEmpty(), "期待される結果と異なります");
	}

	@AfterEach
	public void 事後処理() {

		StringBuilder sql = new StringBuilder();
		sql.append("TRUNCATE TABLE orders RESTART IDENTITY; ");
		sql.append("TRUNCATE TABLE order_items RESTART IDENTITY; ");
		sql.append("TRUNCATE TABLE order_toppings RESTART IDENTITY; ");
		sql.append("TRUNCATE TABLE order_rices RESTART IDENTITY;");

		SqlParameterSource param = new MapSqlParameterSource();

		template.update(sql.toString(), param);
	}

}