package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderRice;

/**
 * order_ricesテーブルを操作するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */
@Repository
public class OrderRiceRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文米情報を挿入する.
	 * 
	 * @param orderRice 注文米情報
	 * 
	 * @author yumi takahashi
	 */
	public void insertOrderRice(OrderRice orderRice) {

		String sql = "INSERT INTO order_rices (rice_id, order_item_id) VALUES (:riceId, :orderItemId)";

		SqlParameterSource param = new MapSqlParameterSource().addValue("riceId", orderRice.getRiceId())
				.addValue("orderItemId", orderRice.getOrderItemId());

		template.update(sql, param);
	}

	/**
	 * 注文商品IDから米IDを取得する.
	 * 
	 * @param orderItemId 注文商品ID
	 * @return 米ID（存在しない場合は0を返す）
	 * 
	 * @author yumi takahashi
	 */
	public Integer getRiceIdByOrderItemId(Integer orderItemId) {

		String sql1 = "SELECT count(*) FROM order_rices WHERE order_item_id = :orderItemId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);

		Integer count = template.queryForObject(sql1, param, Integer.class);

		if (count == 0) {
			return 0;
		} else {

			String sql2 = "SELECT rice_id FROM order_rices WHERE order_item_id = :orderItemId";

			Integer riceId = template.queryForObject(sql2, param, Integer.class);
			return riceId;
		}
	}

}
