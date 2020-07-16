package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 注文情報を操作するサービス
 * 
 * @author shoya fujisawa
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public void order(Order order) {
		orderRepository.order(order);
	}

	/**
	 * 注文履歴を検索する
	 * 
	 * @param userId
	 * @return 注文履歴
	 */
	public List<Order> findOrderHistory(Integer userId) {
		return orderRepository.findByUserIdAndStatus4(userId);
	}
}
