package com.example.service;

import java.util.List;

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

	@Autowired
	private OrderService orderService;

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

	/**
	 * 注文IDから注文商品をすべて削除する.
	 * 
	 * @param orderId 注文ID
	 * 
	 * @author yumi takahashi
	 */
	public void deleteOrderItemAll(Integer orderId) {
		orderItemRepository.deleteOrderItemAll(orderId);
	}

	/**
	 * 注文IDから注文商品IDリストを取得する.
	 * 
	 * @param orderId 注文ID
	 * @return 注文商品IDリスト
	 * 
	 * @author yumi takahahsi
	 */
	public List<Integer> getOrderItemIdByOrderId(Integer orderId) {
		return orderItemRepository.getOrderItemIdByOrderId(orderId);
	}

	/**
	 * 注文商品IDから注文トッピングをすべて削除する.
	 * 
	 * @param orderItemId 注文商品ID
	 * 
	 * @author yumi takahashi
	 */
	public void deleteOrderToppingsByOrderItemId(Integer orderItemId) {
		orderItemRepository.deleteOrderToppingsByOrderItemId(orderItemId);
	}

	/**
	 * ユーザIDからカートの中身を全件削除する.
	 * 
	 * @param userId ユーザID
	 * 
	 * @author yumi takahashi
	 */
	public void deleteOrderItemsAndOrderToppingsAll(Integer userId) {

		Integer orderId = orderService.getOrderIdByUserId(userId);

		List<Integer> orderItemIdList = getOrderItemIdByOrderId(orderId);

		for (Integer orderItemId : orderItemIdList) {
			deleteOrderToppingsByOrderItemId(orderItemId);
		}

		deleteOrderItemAll(orderId);
	}

}
