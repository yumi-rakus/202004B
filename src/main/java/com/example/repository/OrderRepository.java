package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.Topping;

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
	public void order(Order order) {
		String sql="UPDATE orders SET status=:status,order_date=:orderDate,destination_name=:destinationName,destination_email=:destinationEmail"
				+ ",destination_zipcode=:destinationZipcode,destination_address=:destinationAddress,destination_tel=:destinationTel,delivery_time=:deliveryTime,payment_method=:paymentMethod where user_id=1";
		
		SqlParameterSource param=new BeanPropertySqlParameterSource(order);
		
		template.update(sql, param);
	}
}
