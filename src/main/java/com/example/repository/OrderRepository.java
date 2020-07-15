package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;

/**
 * ordersテーブルを操作するリポジトリー
 * @author shoya
 *
 */
@Repository
public class OrderRepository {
	/**
	 * Orderオブジェクトを生成するローマッパー
	 */
	private static final RowMapper<Order> ORDER_ROW_MAPPER=(rs,i)->{
		Order order=new Order();
		
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("otal_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		
		return order;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 注文情報を挿入
	 * @author shoya fujisawa
	 */
	public void insert(Order order) {
		String sql="insert into orders (user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method)"
				+ "values (:userId,:status,:totalPrice,:orderDate,:destinationName,:destinationEmail,:destinationZipcode,:destinationAddress,:destinationTel,:deliveryTime,:paymentMethod)";
		
		SqlParameterSource param=new BeanPropertySqlParameterSource(order);
		
		template.update(sql, param);
	}
}
