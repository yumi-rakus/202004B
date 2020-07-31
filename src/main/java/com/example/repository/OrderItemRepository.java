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
	public void deleteByOrderItemId(Integer orderItemId) {

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
	 * @param orderId 注文ID
	 * 
	 * @author yumi takahashi
	 */
	public void deleteOrderItemAll(Integer orderId) {

		String sql = "DELETE FROM order_items WHERE order_id = :orderId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);

		template.update(sql, param);
	}

	/**
	 * order_idからorder_item_idのリストを取得する.
	 * 
	 * @param orderId 注文ID
	 * @return 注文商品IDリスト
	 * 
	 * @author yumi takahashi
	 */
	public List<Integer> getOrderItemIdListByOrderId(Integer orderId) {

		String sql = "SELECT id FROM order_items WHERE order_id = :orderId ORDER BY id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);

		List<Integer> orderItemIdList = template.queryForList(sql, param, Integer.class);

		return orderItemIdList;
	}

	/**
	 * order_item_idから注文トッピング情報を削除する.
	 * 
	 * @param orderItemId 注文商品ID
	 * 
	 * @author yumi takahahsi
	 */
	public void deleteOrderToppingsByOrderItemId(Integer orderItemId) {

		String sql = "DELETE FROM order_toppings WHERE order_item_id = :orderItemId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);

		template.update(sql, param);
	}

	/**
	 * 注文ID、商品ID、サイズから注文商品IDを取得する.
	 * 
	 * @param orderId 注文ID
	 * @param itemId  商品ID
	 * @param size    サイズ
	 * @return 同じ商品・同じサイズの注文商品があればその注文商品IDを、なければ0を返す
	 * 
	 * @author yumi takahashi
	 */
	public Integer getOrderItemIdByOrderIdAndItemIdAndSize(Integer orderId, Integer itemId, String size) {

		String sql1 = "SELECT count(*) FROM order_items WHERE order_id = :orderId AND item_id = :itemId AND size = :size";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId).addValue("itemId", itemId)
				.addValue("size", size);

		Integer count = template.queryForObject(sql1, param, Integer.class);

		if (count == 0) {
			return 0;
		} else {

			String sql2 = "SELECT id FROM order_items WHERE order_id = :orderId AND item_id = :itemId AND size = :size";

			Integer orderItemId = template.queryForObject(sql2, param, Integer.class);
			return orderItemId;
		}
	}

	/**
	 * 注文商品IDから数量を加算する.
	 * 
	 * @param plusQuantity 新たに加える数量
	 * @param orderItemId  注文商品ID
	 * 
	 * @author yumi takahashi
	 */
	public void updateQuantityByOrderItemId(Integer plusQuantity, Integer orderItemId) {

		String sql = "UPDATE order_items SET quantity = quantity + :plusQuantity WHERE id = :orderItemId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("plusQuantity", plusQuantity)
				.addValue("orderItemId", orderItemId);

		template.update(sql, param);
	}

	/**
	 * 注文商品IDから数量を更新する.
	 * 
	 * @param newQuantity 数量
	 * @param orderItemId 注文商品ID
	 * 
	 * @author yumi takahashi
	 */
	public void updateNewQuantityByOrderItemId(Integer newQuantity, Integer orderItemId) {

		String sql = "UPDATE order_items SET quantity = :newQuantity WHERE id = :orderItemId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("newQuantity", newQuantity)
				.addValue("orderItemId", orderItemId);

		template.update(sql, param);
	}
}
