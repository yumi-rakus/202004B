package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.OrderItem;
import com.example.repository.OrderItemRepository;

/**
 * 注文商品情報を操作するサービス.
 * 
 * @author yumi takahashi
 *
 */
@Service
@Transactional
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	/**
	 * 注文商品情報をカートに入れる.
	 * 
	 * @param orderItem 注文商品情報
	 * @return 注文商品情報
	 * 
	 * @author yumi takahashi
	 */
	public OrderItem insertOrderItem(OrderItem orderItem) {
		return orderItemRepository.insertOrderItem(orderItem);
	}

	/**
	 * 注文商品情報をカートから削除する.
	 * 
	 * @param orderItemId 注文商品ID
	 * 
	 * @author yumi takahashi
	 */
	public void deleteByOrderItemId(Integer orderItemId) {
		orderItemRepository.deleteByItemId(orderItemId);
	}

}
