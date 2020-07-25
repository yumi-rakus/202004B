package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Favorite;
import com.example.domain.Item;
import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.Rice;
import com.example.service.FavoriteService;
import com.example.service.ItemService;
import com.example.service.OrderItemService;
import com.example.service.OrderService;
import com.example.service.RiceService;

/**
 * 非同期通信を行うレストコントローラー.
 * 
 * @author yumi takahashi
 *
 */
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
	private FavoriteService favoriteService;
	@Autowired
	private RiceService riceService;
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
			resultMap.put("result", 400);
		}

		return resultMap;
	}

	/**
	 * 商品の削除フラグを更新する.
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
			resultMap.put("result", 400);
		}

		return resultMap;
	}

	@RequestMapping("/toFavorite")
	public String ajaxToFavorite(@AuthenticationPrincipal LoginUser loginUser, Integer itemId) {
		// 追加に成功したら200、追加できなかった場合は201を返す。
		// 不正なリクエストの場合は400、ログインしていなかった場合は401を返す。
		System.out.println("restcontroller");
		if (Objects.isNull(loginUser)) {
			return "401";
		}
		try {
			Favorite favorite = new Favorite(loginUser.getUser().getId(), itemId, new Date());
			boolean hasInserted = favoriteService.create(favorite);
			if (hasInserted) {
				return "200";
			} else {
				return "201";
			}
		} catch (Exception e) {
			return "400";
		}

	}

	@RequestMapping("/isRegistered")
	public String isRegistered(@AuthenticationPrincipal LoginUser loginUser, Integer itemId) {
		if (Objects.isNull(loginUser)) {
			return "400";
		} else {
			return favoriteService.isRegistered(loginUser.getUser().getId(), itemId);
		}
	}

	/**
	 * 選択された米情報を取得する.
	 * 
	 * @param riceId 米ID
	 * @return 米情報が格納されたMap
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/getRiceInfo")
	public Map<String, String> getRiceInfo(Integer riceId) {

		Map<String, String> riceMap = new HashMap<>();

		try {
			Rice rice = riceService.findById(riceId);

			riceMap.put("result", "200");
			riceMap.put("description", rice.getDescription());
			riceMap.put("imagePath", rice.getImagePath());

		} catch (Exception e) {
			riceMap.put("result", "400");
		}

		return riceMap;
	}

	/**
	 * 米の削除フラグを更新する.
	 * 
	 * @param deleted 削除フラグ
	 * @param riceId  米ID
	 * @return ステータスコード
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/updateRiceDeleteFlag")
	public Map<String, Integer> updateRiceDeleteFlag(Boolean deleted, Integer riceId) {

		Map<String, Integer> resultMap = new HashMap<>();

		try {
			riceService.updateDeleteFlag(deleted, riceId);
			resultMap.put("result", 200);

		} catch (Exception e) {
			resultMap.put("result", 400);
		}

		return resultMap;
	}
}
