package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.ItemForm;
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

	@Autowired
	private OrderToppingService orderToppingService;

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
	public List<Integer> getOrderItemIdListByOrderId(Integer orderId) {
		return orderItemRepository.getOrderItemIdListByOrderId(orderId);
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

		List<Integer> orderItemIdList = getOrderItemIdListByOrderId(orderId);

		for (Integer orderItemId : orderItemIdList) {
			deleteOrderToppingsByOrderItemId(orderItemId);
		}

		deleteOrderItemAll(orderId);
	}

	/**
	 * 同じ商品・同じサイズの注文商品の注文商品IDを取得する.
	 * 
	 * @param orderId 注文ID
	 * @param itemId  商品ID
	 * @param size    サイズ
	 * @return 同じ商品・同じサイズの注文商品があればその注文商品IDを、なければ0を返す
	 * 
	 * @author yumi takahashi
	 */
	public Integer getOrderItemIdByOrderIdAndItemIdAndSize(Integer orderId, Integer itemId, String size) {
		return orderItemRepository.getOrderItemIdByOrderIdAndItemIdAndSize(orderId, itemId, size);
	}

	/**
	 * 注文商品の個数を加算する.
	 * 
	 * @param plusQuantity 新たに加える個数
	 * @param orderItemId  注文商品ID
	 * 
	 * @author yumi takahashi
	 */
	public void updateQuantityByOrderItemId(Integer plusQuantity, Integer orderItemId) {
		orderItemRepository.updateQuantityByOrderItemId(plusQuantity, orderItemId);
	}

	/**
	 * ショッピングカートに商品を追加する.
	 * 
	 * @param form   フォーム
	 * @param userId ユーザID
	 * 
	 * @author yumi takahashi
	 */
	public void cartIn(ItemForm form, Integer userId) {

		////////////////////// orders table getId or insert
		Integer orderId;

		if (orderService.status0ExistByUserId(userId)) {
			// status0がレコードに存在したらオーダーIDをとってくる
			orderId = orderService.getOrderIdByUserId(userId);
		} else {
			// status0がレコードに存在しなかったらordersテーブルにinsert(where:userId, status=0)
			Order order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(0);

			// insertで自動採番されたorder_IdをorderIdに代入
			order = orderService.insertOrderStatus0(order);
			orderId = order.getId();
		}

		// ここにifで同じ内容の注文があるかどうかの分岐
		// なかったらinsert、あったらupdate

		// まず同じ商品・サイズが存在するかどうか(存在すればそのorder_item_id、存在しなかったら0)
		Integer orderItemId = getOrderItemIdByOrderIdAndItemIdAndSize(orderId, form.getItemId(), form.getSize());

		boolean judge = true;

		List<Integer> orderToppingList = orderToppingService.getToppingIdListByOrderItemId(orderItemId);

		for (Integer num : form.getToppingIdList()) {
			if (orderToppingList.contains(num)) {
				judge = true;
			} else {
				judge = false;
				break;
			}
		}

		if (orderItemId != 0 && form.getToppingIdList().size() == orderToppingService
				.getToppingIdListByOrderItemId(orderItemId).size() && judge) {

			updateQuantityByOrderItemId(form.getQuantity(), orderItemId);

		} else {
			// 同じ商品・サイズが存在しなかった場合
			////////////////////// order_items table insert
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(form.getItemId());
			orderItem.setOrderId(orderId);
			orderItem.setQuantity(form.getQuantity());
			orderItem.setSize(form.getSize().charAt(0));

			orderItem = insertOrderItem(orderItem);

			////////////////////// order_toppings table insert
			for (Integer toppingId : form.getToppingIdList()) {

				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setToppingId(toppingId);
				orderTopping.setOrderItemId(orderItem.getId());

				orderToppingService.insertOrderTopping(orderTopping);
			}

		}

	}

}
