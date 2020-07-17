package com.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * ショッピングカートの中身を注文する.
	 * 
	 * @param order 注文情報
	 * 
	 * @author shoya fujisawa
	 */
	public void order(Order order) {
		orderRepository.order(order);
	}

	/**
	 * ショッピングカートリストを取得する.
	 * 
	 * @param userId ユーザID
	 * @return カートの商品一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Order> getOrderListByUserIdAndStatus0(Integer userId) {

		List<Order> orderList = orderRepository.findByUserIdAndStatus0(userId);

		// Map1 … キー：order_item_id、バリュー:注文トッピングリストのマップ
		Map<Integer, List<OrderTopping>> orderToppingMap = new HashMap<>();

		// Map2 … キー：order_item_id、バリュー:注文商品のマップ
		Map<Integer, OrderItem> orderItemMap = new HashMap<>();

		// Map3 … キー：order_id、バリュー:注文のマップ
		Map<Integer, Order> orderMap = new HashMap<>();

		// Map1を完成させる
		for (Order order : orderList) {
			orderToppingMap.put(order.getOrderItemList().get(0).getId(), new ArrayList<>());
		}

		for (Order order : orderList) {
			List<OrderTopping> orderToppingList = orderToppingMap.get(order.getOrderItemList().get(0).getId());
			orderToppingList.add(order.getOrderItemList().get(0).getOrderToppingList().get(0));
		}
		// Map1完成 （order_item_id１つに対して１つの注文トッピングリストorderToppingList(中身は0~複数)）

		// Map2を完成させる
		for (Order order : orderList) {
			orderItemMap.put(order.getOrderItemList().get(0).getId(), order.getOrderItemList().get(0));
		}

		for (Order order : orderList) {
			OrderItem orderItem = orderItemMap.get(order.getOrderItemList().get(0).getId());

			List<OrderTopping> orderToppingList = orderToppingMap.get(order.getOrderItemList().get(0).getId());
			orderItem.setOrderToppingList(orderToppingList);

			orderItemMap.put(order.getOrderItemList().get(0).getId(), orderItem);
		}
		// Map2完成 （order_item_id１つに対して１つの注文商品orderItem(先程作成したorderToppingListがセットされたもの)）

		// 注文商品リストのリストを作る（サイズは注文商品の数と一致）
		List<OrderItem> orderItemList = new ArrayList<>(orderItemMap.values());

		// Map3を完成させる
		for (Order order : orderList) {
			order.setOrderItemList(orderItemList);
			orderMap.put(order.getId(), order);
		}
		// Map3完成 （注文商品リスト(サイズは注文商品の数と一致)を注文にセット）（Map3の中身は１件になるはず）

		// サイズ１件の注文
		List<Order> distinctOrderList = new ArrayList<>(orderMap.values());

		return distinctOrderList;
	}

	/**
	 * 注文履歴を検索する.
	 * 
	 * @param userId ユーザID
	 * @return 注文履歴
	 * 
	 * @author soshi morita
	 */
	public List<Order> findOrderHistory(Integer userId) {
		List<Order> orderList = orderRepository.findByUserIdAndNonStatus0(userId);

		Map<Integer, List<OrderTopping>> orderToppingMap = new HashMap<>();
		Map<Integer, OrderItem> orderItemMap = new HashMap<>();
		// キーがオーダーID、バリューがオーダーアイテムリストのマップを作成
		Map<Integer, List<OrderItem>> orderItemListMap = new HashMap<>();

		//// トッピングの詰め替え
		// キーがorder_item_id、バリューを空のリストとする
		for (Order order : orderList) {
			orderToppingMap.put(order.getOrderItemList().get(0).getId(), new ArrayList<>());
		}

		// orderItemIdをキー、トッピングリストをバリューとするマップが完成
		for (Order order : orderList) {
			List<OrderTopping> orderToppingList = orderToppingMap.get(order.getOrderItemList().get(0).getId());
			OrderTopping orderTopping = order.getOrderItemList().get(0).getOrderToppingList().get(0);
			// オブジェクトが空ではない場合のみトッピングリストに追加
			if (Objects.nonNull(orderTopping.getTopping().getName())) {
				orderToppingList.add(order.getOrderItemList().get(0).getOrderToppingList().get(0));
			}
		}

		//// 商品本体の詰め替え
		// orderItemIdをキー、オーダーアイテムとするマップを作成
		for (Order order : orderList) {
			orderItemMap.put(order.getOrderItemList().get(0).getId(), order.getOrderItemList().get(0));
		}
		// オーダーアイテムごとにトッピングリストを追加をバリューとするマップが完成
		for (Order order : orderList) {
			OrderItem orderItem = orderItemMap.get(order.getOrderItemList().get(0).getId());
			List<OrderTopping> orderToppingList = orderToppingMap.get(order.getOrderItemList().get(0).getId());
			orderItem.setOrderToppingList(orderToppingList);
			orderItemMap.put(order.getOrderItemList().get(0).getId(), orderItem);
		}

		//// 注文番号ごとにオーダーアイテムのリストを作成
		for (Order order : orderList) {
			orderItemListMap.put(order.getId(), new ArrayList<>());
		}
		for (OrderItem orderItem : orderItemMap.values()) {
			List<OrderItem> list = orderItemListMap.get(orderItem.getOrderId());
			list.add(orderItem);
		}

		// オーダーIDをキー、オーダーをバリューとするマップを作成
		// オーダーのオーダーアイテムリストは空にする
		Map<Integer, Order> distinctOrderMap = new HashMap<>();
		for (Order order : orderList) {
			Integer key = order.getId();
			Order value = order;
			order.getOrderItemList().clear();
			distinctOrderMap.put(key, value);
		}

		for (Order order : distinctOrderMap.values()) {
			Integer key = order.getId();
			distinctOrderMap.get(key).setOrderItemList(orderItemListMap.get(key));
		}
		List<Order> oList = new ArrayList<>(distinctOrderMap.values());

		// 最新の注文順に並び替え
		Collections.reverse(oList);
		return oList;
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

	/**
	 * UUID(仮UserId)をログイン後のUserIdに更新する.
	 * 
	 * @param userId ユーザID
	 * @param uuid   UUID
	 * 
	 * @author yumi takahashi
	 */
	public void updateUserId(Integer userId, Integer uuid) {
		orderRepository.updateUserId(userId, uuid);
	}

	/**
	 * order_itemsのorder_idをログインユーザのstatus = 0のものに変更する.
	 * 
	 * @param uuidOrderId UUIDのorder_id
	 * @param userOrderId ログインユーザのorder_id
	 * 
	 * @author yumi takahashi
	 */
	public void updateOrderId(Integer uuidOrderId, Integer userOrderId) {
		orderRepository.updateOrderId(uuidOrderId, userOrderId);
	}
}
