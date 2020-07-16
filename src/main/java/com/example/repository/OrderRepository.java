package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;

import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

/**
 * ordersテーブルを操作するリポジトリー
 * 
 * @author shoya
 *
 */
@Repository
public class OrderRepository {

	/**
	 * Orderオブジェクトを生成するローマッパー
	 */

	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));

		OrderItem orderItem = new OrderItem();

		orderItem.setId(rs.getInt("order_item_id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setSize(rs.getString("size").charAt(0));

		Item item = new Item();

		item.setId(rs.getInt("item_id"));
		item.setName(rs.getString("item_name"));
		item.setDescription(rs.getString("item_description"));
		item.setPriceM(rs.getInt("item_pricem"));
		item.setPriceL(rs.getInt("item_pricel"));
		item.setImagePath(rs.getString("item_imagepath"));

		orderItem.setItem(item);

		OrderTopping orderTopping = new OrderTopping();

		orderTopping.setId(rs.getInt("order_topping_id"));
		orderTopping.setToppingId(rs.getInt("topping_id"));
		orderTopping.setOrderItemId(rs.getInt("order_item_id"));

		Topping topping = new Topping();

		topping.setId(rs.getInt("topping_id"));
		topping.setName(rs.getString("topping_name"));
		topping.setPriceM(rs.getInt("topping_pricem"));
		topping.setPriceL(rs.getInt("topping_pricel"));

		orderTopping.setTopping(topping);

		List<OrderTopping> orderToppingList = new ArrayList<>();

		orderToppingList.add(orderTopping);

		orderItem.setOrderToppingList(orderToppingList);

		List<OrderItem> orderItemList = new ArrayList<>();

		orderItemList.add(orderItem);

		order.setOrderItemList(orderItemList);

		return order;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文情報を挿入
	 * 
	 * @author shoya fujisawa
	 */

	public void order(Order order) {
		String sql = "UPDATE orders SET status=:status,order_date=:orderDate,destination_name=:destinationName,destination_email=:destinationEmail"
				+ ",destination_zipcode=:destinationZipcode,destination_address=:destinationAddress,destination_tel=:destinationTel,delivery_time=:deliveryTime,payment_method=:paymentMethod where user_id=1";

		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		template.update(sql, param);
	}

	/**
	 * 未注文の注文リストを取得する.
	 * 
	 * @param userId ユーザID
	 * @return 未注文の注文リスト
	 * 
	 * @author yumi takahashi
	 */
	public List<Order> findByUserIdAndStatus0(Integer userId) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("ord.id AS id, ");
		sql.append("ord.user_id AS user_id, ");
		sql.append("ord.status AS status, ");
		sql.append("ord.total_price AS total_price, ");
		sql.append("ord.order_date AS order_date, ");
		sql.append("ord.destination_name AS destination_name, ");
		sql.append("ord.destination_email AS destination_email, ");
		sql.append("ord.destination_zipcode AS destination_zipcode, ");
		sql.append("ord.destination_address AS destination_address, ");
		sql.append("ord.destination_tel AS destination_tel, ");
		sql.append("ord.delivery_time AS delivery_time, ");
		sql.append("ord.payment_method AS payment_method, ");
		sql.append("orditem.id AS order_item_id, ");
		sql.append("items.id AS item_id, ");
		sql.append("ord.id AS order_id, ");
		sql.append("orditem.quantity AS quantity, ");
		sql.append("orditem.size AS size, ");
		sql.append("items.name AS item_name, ");
		sql.append("items.description AS item_description, ");
		sql.append("items.price_m AS item_pricem, ");
		sql.append("items.price_l AS item_pricel, ");
		sql.append("items.image_path AS item_imagepath, ");
		sql.append("ordtop.id AS order_topping_id, ");
		sql.append("toppings.id AS topping_id, ");
		sql.append("toppings.name AS topping_name, ");
		sql.append("toppings.price_m AS topping_pricem, ");
		sql.append("toppings.price_l AS topping_pricel ");
		sql.append("FROM ");
		sql.append("orders AS ord ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("order_items AS orditem ");
		sql.append("ON ord.id = orditem.order_id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("order_toppings AS ordtop ");
		sql.append("ON orditem.id = ordtop.order_item_id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("items ");
		sql.append("ON items.id = orditem.item_id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("toppings ");
		sql.append("ON toppings.id = ordtop.topping_id ");
		sql.append("WHERE ");
		sql.append("ord.user_id = :userId ");
		sql.append("AND");
		sql.append("ord.status = 0 ");
		sql.append("ORDER BY ");
		sql.append("orditem.id;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);

		List<Order> orderList = template.query(sql.toString(), param, ORDER_ROW_MAPPER);

		return orderList;
	}

	/**
	 * 注文済みの注文リストを取得する.
	 * 
	 * @param userId ユーザID
	 * @return 注文済みの注文リスト
	 * 
	 * @author moritasoshi
	 */
	public List<Order> findByUserIdAndStatus4(Integer userId) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ");
		sql.append("ord.id AS id, ");
		sql.append("ord.user_id AS user_id, ");
		sql.append("ord.status AS status, ");
		sql.append("ord.total_price AS total_price, ");
		sql.append("ord.order_date AS order_date, ");
		sql.append("ord.destination_name AS destination_name, ");
		sql.append("ord.destination_email AS destination_email, ");
		sql.append("ord.destination_zipcode AS destination_zipcode, ");
		sql.append("ord.destination_address AS destination_address, ");
		sql.append("ord.destination_tel AS destination_tel, ");
		sql.append("ord.delivery_time AS delivery_time, ");
		sql.append("ord.payment_method AS payment_method, ");
		sql.append("orditem.id AS order_item_id, ");
		sql.append("items.id AS item_id, ");
		sql.append("ord.id AS order_id, ");
		sql.append("orditem.quantity AS quantity, ");
		sql.append("orditem.size AS size, ");
		sql.append("items.name AS item_name, ");
		sql.append("items.description AS item_description, ");
		sql.append("items.price_m AS item_pricem, ");
		sql.append("items.price_l AS item_pricel, ");
		sql.append("items.image_path AS item_imagepath, ");
		sql.append("ordtop.id AS order_topping_id, ");
		sql.append("toppings.id AS topping_id, ");
		sql.append("toppings.name AS topping_name, ");
		sql.append("toppings.price_m AS topping_pricem, ");
		sql.append("toppings.price_l AS topping_pricel ");
		sql.append("FROM ");
		sql.append("orders AS ord ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("order_items AS orditem ");
		sql.append("ON ord.id = orditem.order_id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("order_toppings AS ordtop ");
		sql.append("ON orditem.id = ordtop.order_item_id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("items ");
		sql.append("ON items.id = orditem.item_id ");
		sql.append("LEFT OUTER JOIN ");
		sql.append("toppings ");
		sql.append("ON toppings.id = ordtop.topping_id ");
		sql.append("WHERE ");
		sql.append("ord.user_id = :userId ");
		sql.append("AND");
		sql.append("ord.status = 4 ");
		sql.append("ORDER BY ");
		sql.append("orditem.id;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);

		List<Order> orderList = template.query(sql.toString(), param, ORDER_ROW_MAPPER);

		return orderList;
	}
}
