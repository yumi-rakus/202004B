package com.example.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * order_itemsテーブルを操作するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {

		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());

		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");

		insert = withTableName.usingGeneratedKeyColumns("id");

	}

	/**
	 * 注文商品をorder_itemsテーブルに挿入する.
	 * 
	 * @param orderItem 注文商品情報
	 * @return 注文商品情報
	 * 
	 * @author yumi takahashi
	 */
	public OrderItem insertOrderItem(OrderItem orderItem) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);

		Number key = insert.executeAndReturnKey(param);

		orderItem.setId(key.intValue());

		return orderItem;
	}

	/**
	 * 注文商品をorder_itemsテーブルとorder_toppingsテーブルから削除する.
	 * 
	 * @param orderItemId 注文商品ID
	 * 
	 * @author yumi takahashi
	 */
	public void deleteByItemId(Integer orderItemId) {

		StringBuilder sql = new StringBuilder();

		sql.append("BEGIN; ");
		sql.append("DELETE FROM order_toppings ");
		sql.append("WHERE order_item_id = :orderItemId; ");
		sql.append("DELETE FROM order_items ");
		sql.append("WHERE id = :id; ");
		sql.append("COMMIT;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId).addValue("id",
				orderItemId);

		template.update(sql.toString(), param);

	}

	/**
	 * order_idの注文商品を全件削除する.
	 * 
	 * @param orderId order_id
	 * 
	 * @author yumi takahashi
	 */
	public void deleteOrderItemAll(Integer orderId) {

		String sql = "DELETE FROM order_items WHERE order_id = :orderId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);

		template.update(sql, param);
	}

	public List<Integer> getOrderItemIdByOrderId(Integer orderId) {

		String sql = "SELECT id FROM order_items WHERE order_id = :orderId ORDER BY id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);

		List<Integer> orderItemIdList = template.queryForList(sql, param, Integer.class);

		return orderItemIdList;
	}

	public void deleteOrderToppingsByOrderItemId(Integer orderItemId) {

		String sql = "DELETE FROM order_toppings WHERE order_item_id = :orderItemId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);

		template.update(sql, param);
	}

}
