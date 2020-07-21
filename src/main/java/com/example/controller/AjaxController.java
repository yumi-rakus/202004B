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
}
