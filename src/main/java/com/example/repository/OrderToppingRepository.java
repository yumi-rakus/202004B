package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

/**
 * order_toppingsテーブルを操作するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */
@Repository
public class OrderToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文トッピング情報をorder_toppingsテーブルに挿入する.
	 * 
	 * @param orderTopping 注文トッピング情報
	 * 
	 * @author yumi takahashi
	 */
	public void insertOrderTopping(OrderTopping orderTopping) {

		String sql = "INSERT INTO order_toppings (topping_id, order_item_id) values (:toppingId, :orderItemId)";

		SqlParameterSource param = new MapSqlParameterSource().addValue("toppingId", orderTopping.getToppingId())
				.addValue("orderItemId", orderTopping.getOrderItemId());

		template.update(sql, param);

	}

}
