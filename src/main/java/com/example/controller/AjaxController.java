package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.service.ItemService;
import com.example.service.OrderItemService;
import com.example.service.OrderService;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private HttpSession session;

	/**
	 * 検索において商品名オートコンプリートする.
	 * 
	 * @param searchName 検索キー
	 * @return 商品名リスト
	 * 
	 * @author kohei eto
	 */
	@RequestMapping("/findName")
	public List<String> findName(String searchName) {

		List<Item> itemList = itemService.findByItemName(searchName);
		List<String> nameList = itemList.stream().map(item -> item.getName()).collect(Collectors.toList());

		return nameList;
	}

	/**
	 * 数量を更新し、更新された消費税、合計金額をMapで返す.
	 * 
	 * @param newQuantity 数量
	 * @param itemId      商品ID
	 * @param size        サイズ
	 * @return Map(key: tax, totalPrice)
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/totalPrice")
	public Map<String, Integer> getNewTotalPrice(Integer newQuantity, Integer itemId, String size) {

		Map<String, Integer> updateMap = new HashMap<>();

		Integer userId = (Integer) session.getAttribute("userId");
		Integer orderId = orderService.getOrderIdByUserId(userId);
		Integer orderItemId = orderItemService.getOrderItemIdByOrderIdAndItemIdAndSize(orderId, itemId, size);

		orderItemService.updateNewQuantityByOrderItemId(newQuantity, orderItemId);
		orderService.updateTotalPrice(userId);

		List<Order> order = orderService.getOrderListByUserIdAndStatus0(userId);

		updateMap.put("tax", order.get(0).getTax());
		updateMap.put("totalPrice", order.get(0).getTotalPrice() + order.get(0).getTax());

		return updateMap;
	}

	/**
	 * 注文のstatusを更新する.
	 * 
	 * @param status  状態
	 * @param orderId 注文ID
	 * @return ステータスコード
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/updateStatus")
	public Map<String, Integer> updateStatus(Integer status, Integer orderId) {

		Map<String, Integer> resultMap = new HashMap<>();

		try {
			orderService.updateStatusByOrderId(status, orderId);
			resultMap.put("result", 200);
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", 400);
		}

		return resultMap;
	}

	/**
	 * 削除フラグを更新する.
	 * 
	 * @param deleted 削除フラグ
	 * @param itemId  商品ID
	 * @return ステータスコード
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/updateDeleteFlag")
	public Map<String, Integer> updateDeleteFlag(Boolean deleted, Integer itemId) {

		Map<String, Integer> resultMap = new HashMap<>();

		try {
			itemService.updateDeleteFlag(deleted, itemId);
			resultMap.put("result", 200);
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("result", 400);
		}

		return resultMap;
	}
}
