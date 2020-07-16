package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
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

	/**
	 * 
	 * 
	 * @param order 注文情報
	 * 
	 * @author shoya
	 */
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
		List<Order> orderList = orderRepository.findByUserIdAndNonStatus0(userId);

		Map<Integer, List<OrderTopping>> orderToppingMap = new HashMap<>();
		Map<Integer, OrderItem> orderItemMap = new HashMap<>();
		Map<Integer, Order> orderMap = new HashMap<>();

		for (Order order : orderList) {
			orderToppingMap.put(order.getOrderItemList().get(0).getId(), new ArrayList<>());
		}
		for (Order order : orderList) {
			List<OrderTopping> orderToppingList = orderToppingMap.get(order.getOrderItemList().get(0).getId());
			orderToppingList.add(order.getOrderItemList().get(0).getOrderToppingList().get(0));
		}
		for (Order order : orderList) {
			orderItemMap.put(order.getOrderItemList().get(0).getId(), order.getOrderItemList().get(0));
		}
		for (Order order : orderList) {
			OrderItem orderItem = orderItemMap.get(order.getOrderItemList().get(0).getId());
			List<OrderTopping> orderToppingList = orderToppingMap.get(order.getOrderItemList().get(0).getId());
			orderItem.setOrderToppingList(orderToppingList);
			orderItemMap.put(order.getOrderItemList().get(0).getId(), orderItem);
		}

		List<OrderItem> orderItemList = new ArrayList<>(orderItemMap.values());

		for (Order order : orderList) {
			order.setOrderItemList(orderItemList);
			orderMap.put(order.getId(), order);
		}

		List<Order> distinctOrderList = new ArrayList<>(orderMap.values());

		return distinctOrderList;
	}

	/**
	 * カートリストを取得する.
	 * 
	 * @param userId ユーザID
	 * @return カートの商品一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Order> getOrderListByUserIdAndStatus0(Integer userId) {

		List<Order> orderList = orderRepository.findByUserIdAndStatus0(userId);

		Map<Integer, List<OrderTopping>> orderToppingMap = new HashMap<>();

		Map<Integer, OrderItem> orderItemMap = new HashMap<>();

		Map<Integer, Order> orderMap = new HashMap<>();

		for (Order order : orderList) {
			orderToppingMap.put(order.getOrderItemList().get(0).getId(), new ArrayList<>());
		}

		for (Order order : orderList) {
			List<OrderTopping> orderToppingList = orderToppingMap.get(order.getOrderItemList().get(0).getId());
			orderToppingList.add(order.getOrderItemList().get(0).getOrderToppingList().get(0));
		}

		for (Order order : orderList) {
			orderItemMap.put(order.getOrderItemList().get(0).getId(), order.getOrderItemList().get(0));
		}

		for (Order order : orderList) {
			OrderItem orderItem = orderItemMap.get(order.getOrderItemList().get(0).getId());

			List<OrderTopping> orderToppingList = orderToppingMap.get(order.getOrderItemList().get(0).getId());
			orderItem.setOrderToppingList(orderToppingList);

			orderItemMap.put(order.getOrderItemList().get(0).getId(), orderItem);

		}

		List<OrderItem> orderItemList = new ArrayList<>(orderItemMap.values());

		for (Order order : orderList) {

			order.setOrderItemList(orderItemList);

			orderMap.put(order.getId(), order);

		}

		List<Order> distinctOrderList = new ArrayList<>(orderMap.values());

		return distinctOrderList;
	}

	/**
	 * status = 0 (未注文)のレコードが存在するかどうかを返す.
	 * 
	 * @param userId ユーザID
	 * @return status = 0 (未注文)のレコードが存在していればtrue、存在していなければfalseを返す
	 * 
	 * @author yumi takahashi
	 */
	public boolean status0ExistByUserId(Integer userId) {
		return orderRepository.status0ExistByUserId(userId);
	}

	/**
	 * status = 0 （未注文）のorderIdを取得する.
	 * 
	 * @param userId ユーザID
	 * @return orderId
	 * 
	 * @author yumi takahashi
	 */
	public Integer getOrderIdByUserId(Integer userId) {
		return orderRepository.getOrderIdByUserId(userId);
	}

	/**
	 * ショッピングカートを生成する.
	 * 
	 * @param order 注文情報
	 * @return 注文情報
	 * 
	 * @author yumi takahashi
	 */
	public Order insertOrderStatus0(Order order) {
		return orderRepository.insertOrderStatus0(order);
	}

	/**
	 * 注文商品の合計金額を更新する.
	 * 
	 * @param userId     ユーザID
	 * @param totalPrice 合計金額
	 * 
	 * @author yumi takahashi
	 */
	public void updateTotalPriceByUserId(Integer userId, Integer totalPrice) {
		orderRepository.updateTotalPrice(userId, totalPrice);
	}
}
