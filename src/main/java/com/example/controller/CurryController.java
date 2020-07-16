package com.example.controller;




import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpSession;


import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;
import com.example.domain.User;
import com.example.form.ItemForm;
import com.example.form.OrderForm;
import com.example.form.UserForm;
import com.example.service.ItemService;
import com.example.service.OrderItemService;
import com.example.service.OrderService;


import com.example.service.OrderToppingService;

import com.example.service.SendMailService;

import com.example.service.ToppingService;

import com.example.service.UserService;


/**
 * カレーECサイトを操作するコントローラ.
 * 
 * @author yumi takahashi
 *
 */
@Controller
@RequestMapping("")
public class CurryController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private SendMailService sendMailService;
	@Autowired
	private HttpSession session;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ToppingService toppingService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderToppingService orderToppingService;

	@ModelAttribute
	public ItemForm setUpItemForm() {
		return new ItemForm();
	}
	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}
	@ModelAttribute
	private OrderForm setUpOrderForm() {
		return new OrderForm();
	}

	//////////////////////////////////////////////
	//// 商品一覧画面の表示
	//////////////////////////////////////////////
	/**
	 * 商品一覧を表示
	 * 
	 * @param model
	 * @return
	 * @author kohei eto
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		// オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納
		StringBuilder itemListForAutocomplete = itemService.getItemListForAutocomplete(itemList);
		model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);
		return "item_list_curry";
	}

	//////////////////////////////////////////////
	//// 商品の検索
	//////////////////////////////////////////////
	/**
	 * 商品検索を行う
	 * @param searchName 検索する名前
	 * @param model
	 * @return
	 */
	@RequestMapping("/search")
	public String findByItemName(String searchName, Model model) {
		if (Objects.isNull(searchName)) { // 検索文字列が空なら全件検索
			List<Item> itemList = itemService.findAll();
			model.addAttribute("itemList", itemList);
		} else { // 検索文字列があれば曖昧検索
			List<Item> itemList = itemService.findByItemName(searchName);
			if (itemList.equals("")) {
				String no = "該当する商品がありません";
				model.addAttribute("no", no);
			}

			model.addAttribute("itemList", itemList);
		}

		return "item_list_curry";
	}
	
	//////////////////////////////////////////////
	//// ユーザー登録
	//////////////////////////////////////////////
	/**
	 * ユーザー登録画面を表示
	 * @author kohei eto
	 */
	@RequestMapping("/indexRegister")
	public String indexRegister() {
		return "register_user";
	}

	/**
	 * ユーザー登録
	 * @author kohei eto
	 */
	@RequestMapping("/register")
	public String register(@Validated UserForm userForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return indexRegister();
		}
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		userService.insert(user);
		return "login";
	}

	//////////////////////////////////////////////
	//// 注文確認画面を表示する
	@RequestMapping("/orderConfirm")
	public String Confirm(OrderForm form, Model model) {
		List<Order> order = orderService.getOrderListByUserIdAndStatus0(2);
		List<OrderItem> orderItemList = order.get(0).getOrderItemList();
		model.addAttribute("orderItemList", orderItemList);
		model.addAttribute("tax", order.get(0).getTax());
		model.addAttribute("totalPrice", order.get(0).getCalcTotalPrice() + order.get(0).getTax());
		return "order_confirm";
	}

	/**
	 * 注文情報を登録
	 * 
	 * @author shoya fujisawa
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form, BindingResult result, Model model, Integer userId) {

		if (result.hasErrors()) {
			return Confirm(form, model);
		}
		Order order = new Order();
		order.setUserId((Integer) session.getAttribute("userId"));

		if (form.getPaymentMethod() == 1) {
			order.setStatus(1);
		} else if (form.getPaymentMethod() == 2) {
			order.setStatus(2);
		}

		order.setTotalPrice(form.getTotalPrice());
		Date orderDate = Date.valueOf(form.getOrderDate());
		order.setOrderDate(orderDate);
		order.setDestinationName(form.getName());
		order.setDestinationEmail(form.getMailAddress());
		// 郵便番号の-を除去
		String zipCodeStr = form.getZipCode();
		String zipCode = zipCodeStr.replace("-", "");
		order.setDestinationZipcode(zipCode);

		order.setDestinationAddress(form.getAddress());
		order.setDestinationTel(form.getTelephone());
		String delivery = form.getOrderDate() + " " + form.getTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime time = LocalDateTime.parse(delivery, formatter);
		Timestamp deliveryTime = Timestamp.valueOf(time);
		order.setDeliveryTime(deliveryTime);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date nowDate = new java.util.Date();
		try {
			java.util.Date dTime = df.parse(delivery);
			long diff = dTime.getTime() - nowDate.getTime();

			if (diff / (60 * 60 * 1000) < 3) {
				model.addAttribute("message", "今から3時間後以降の日時をご入力ください");
				return Confirm(form, model);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		order.setPaymentMethod(form.getPaymentMethod());
		orderService.order(order);

		sendMailService.sendMail(order.getDestinationEmail());
		return "order_finished";
	}

	/**
	 * 商品情報詳細画面を出力する.
	 * 
	 * @param id    商品ID
	 * @param model モデル
	 * @return 商品情報詳細画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model, ItemForm itemForm) {

		Item item = itemService.showDetail(Integer.parseInt(id));
		List<Topping> toppingList = toppingService.showToppingList();

		model.addAttribute("item", item);
		model.addAttribute("toppingList", toppingList);

		itemForm.setSize("M");
		itemForm.setQuantity(1);

		return "item_detail";
	}

	/**
	 * カートに商品を追加し、商品追加完了画面へリダイレクト.
	 * 
	 * @param form   フォーム
	 * @param result BindingResultオブジェクト
	 * @return /cartInComplete にリダイレクト
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/cartIn")
	public String cartIn(@Validated ItemForm form, BindingResult result) {

		if (result.hasErrors()) {
			return "item_detail";
		}

		////////////////////// orders table
		User user = new User();

		// userにuserIdをセット
		if (Objects.isNull((Integer) session.getAttribute("userId"))) {

			// ログインしていない場合
			// UUIDの発行

			UUID uuid = UUID.randomUUID();
			Integer intUuid = uuid.hashCode();

			user.setId(intUuid);

			session.setAttribute("userId", intUuid);

		} else {

			// ログインしている場合
			user.setId((Integer) session.getAttribute("userId"));
		}

		Integer orderId;

		if (orderService.status0ExistByUserId(user.getId())) {
			// status0がレコードに存在したらオーダーIDをとってくる
			orderId = orderService.getOrderIdByUserId(user.getId());

		} else {
			// status0がレコードに存在しなかったらordersテーブルにinsert(where:userId, status=0)

			Order order = new Order();

			order.setUserId(user.getId());
			order.setStatus(0);

			Item item = itemService.showDetail(form.getItemId());

			Integer price = 0;

			if (form.getSize().equals("M")) {
				price += item.getPriceM();
			} else if (form.getSize().equals("L")) {
				price += item.getPriceL();
			}

			if (!Objects.isNull(form.getToppingIdList())) {

				for (Integer toppingId : form.getToppingIdList()) {
					Topping topping = toppingService.findById(toppingId);

					if (form.getSize().equals("M")) {
						price += topping.getPriceM();
					} else if (form.getSize().equals("L")) {
						price += topping.getPriceL();
					}
				}
			}

			order.setTotalPrice(price);

			// insertで自動採番されたorder_IdをorderIdに代入
			order = orderService.insertOrderStatus0(order);

			orderId = order.getId();
		}

		////////////////////// order_items table insert
		OrderItem orderItem = new OrderItem();

		orderItem.setItemId(form.getItemId());
		orderItem.setOrderId(orderId);
		orderItem.setQuantity(form.getQuantity());
		orderItem.setSize(form.getSize().charAt(0));

		orderItem = orderItemService.insertOrderItem(orderItem);

		////////////////////// order_toppings table insert
		for (Integer toppingId : form.getToppingIdList()) {

			OrderTopping orderTopping = new OrderTopping();

			orderTopping.setToppingId(toppingId);
			orderTopping.setOrderItemId(orderItem.getId());

			orderToppingService.insertOrderTopping(orderTopping);
		}

		// ordersテーブルのtotalPriceをupdate (where:status=0, userId)
		List<Order> orderList = orderService.getOrderListByUserIdAndStatus0(user.getId());

		Integer totalPrice = 0;
		totalPrice = orderList.get(0).getCalcTotalPrice();

		orderService.updateTotalPriceByUserId(user.getId(), totalPrice);

		return "redirect:/cartInComplete";
	}

	/**
	 * 商品追加完了画面を出力する.
	 * 
	 * @return 商品追加完了画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/cartInComplete")
	public String cartInComplete(Model model) {

		// カートの中身を表示させたい
		
		List<Order> order = orderService.getOrderListByUserIdAndStatus0((Integer)session.getAttribute("userId"));
		
		Integer totalPrice = 0;
		totalPrice = order.get(0).getCalcTotalPrice();

		orderService.updateTotalPriceByUserId((Integer)session.getAttribute("userId"), totalPrice);

		order = orderService.getOrderListByUserIdAndStatus0((Integer)session.getAttribute("userId"));

		model.addAttribute("orderItemList", order.get(0).getOrderItemList());
		model.addAttribute("tax", order.get(0).getTax());
		model.addAttribute("totalPrice", order.get(0).getTotalPrice() + order.get(0).getTax());
		

		return "cart-in-complete";
	}

	/**
	 * ショッピングカート画面を出力する.
	 * 
	 * @param model モデル
	 * @return ショッピングカート画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/showCartList")
	public String showCartList(Model model) {

		User user = new User();

		if (Objects.isNull((Integer) session.getAttribute("userId"))) { // ログインしていない場合

			// UUIDの発行
			UUID uuid = UUID.randomUUID();
			Integer intUuid = uuid.hashCode();

			user.setId(intUuid);

			session.setAttribute("userId", intUuid);

		} else { // ログインしている場合

			user.setId((Integer) session.getAttribute("userId"));
		}

		if (orderService.status0ExistByUserId(user.getId())) {
			// status0がレコードに存在したら

			List<Order> order = orderService.getOrderListByUserIdAndStatus0(user.getId());

			if (!order.get(0).getOrderItemList().isEmpty()) {

				List<OrderItem> orderItemList = order.get(0).getOrderItemList();

				if (orderItemList.get(0).getItem().getId() == 0) {
					// orderItemが無かったら（カートの中身が空だったら）

					model.addAttribute("notExistOrderItemList", "カートに商品がありません");
					model.addAttribute("tax", 0);
					model.addAttribute("totalPrice", 0);
				} else {

					Integer totalPrice = 0;
					totalPrice = order.get(0).getCalcTotalPrice();

					orderService.updateTotalPriceByUserId(user.getId(), totalPrice);

					order = orderService.getOrderListByUserIdAndStatus0(user.getId());

					model.addAttribute("orderItemList", orderItemList);
					model.addAttribute("tax", order.get(0).getTax());
					model.addAttribute("totalPrice", order.get(0).getTotalPrice() + order.get(0).getTax());
				}

			} else {
				model.addAttribute("notExistOrderItemList", "カートに商品がありません");
				model.addAttribute("tax", 0);
				model.addAttribute("totalPrice", 0);
			}
		} else {

			// status0がレコードに存在しなかったら

			model.addAttribute("notExistOrderItemList", "カートに商品がありません");
			model.addAttribute("tax", 0);
			model.addAttribute("totalPrice", 0);
		}

		return "cart_list";
	}

	/**
	 * ショッピングカートから商品を削除する.
	 * 
	 * @param model       モデル
	 * @param orderItemId 注文商品ID
	 * @return ショッピングカート画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/deleteOrderItem")
	public String deleteOrderItem(Model model, String orderItemId) {

		// order_itemsテーブルから削除(where:orderItemId)
		// order_toppingsテーブルから削除(where:orderItemId)
		// transactionで begin commit
		// window.confirmで確認させたい

		orderItemService.deleteByOrderItemId(Integer.parseInt(orderItemId));

		return "forward:/showCartList";// showCartList(model);
	}

	//////////////////////////////////////////////
	//// ログイン・ログアウト機能
	//////////////////////////////////////////////
	/**
	 * ログイン画面を表示
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	//////////////////////////////////////////////
	//// 注文履歴の表示
	//////////////////////////////////////////////
	/**
	 * 注文履歴画面の表示
	 * 
	 * @param loginUser 認証済みユーザー
	 * @param model
	 */
	@RequestMapping("/showOrderHistory")
	public String showOrderHistory(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		// user情報を取得
		Integer userId = loginUser.getUser().getId();
		// user情報をもとに注文履歴をDBから検索
		List<Order> orderedList = orderService.findOrderHistory(userId);
		// List<>をスコープに詰める
		model.addAttribute("orderList", orderedList);
		return "order_history";
	}

}