package com.example.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	private UserService userService;
	@Autowired
	private SendMailService sendMailService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ToppingService toppingService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderToppingService orderToppingService;
	@Autowired
	private HttpSession session;
	@Autowired
	private static final int VIEW_SIZE = 9;

	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}

	@ModelAttribute
	public ItemForm setUpItemForm() {
		return new ItemForm();
	}

	@ModelAttribute
	private OrderForm setUpOrderForm() {
		return new OrderForm();
	}

	@ModelAttribute
	public ItemForm setUpForm() {
		return new ItemForm();
	}

	//////////////////////////////////////////////
	//// 商品一覧画面の表示
	//////////////////////////////////////////////
	/**
	 * 商品一覧を表示
	 * 
	 * @param model モデル
	 * @return 商品一覧画面
	 * 
	 * @author kohei eto
	 */
	@RequestMapping("")
	public String index(Model model, Integer page, ItemForm form, String searchName) {
		/*
		 * List<Item> itemList = itemService.findAll(); model.addAttribute("itemList",
		 * itemList); // オートコンプリート用にJavaScriptの配列の中身を文字列で作ってスコープへ格納 StringBuilder
		 * itemListForAutocomplete = itemService.getItemListForAutocomplete(itemList);
		 * model.addAttribute("itemListForAutocomplete", itemListForAutocomplete);
		 */

		Map<Integer, String> itemMap = new LinkedHashMap<>();
		itemMap.put(1, "価格安い順");
		itemMap.put(2, "価格高い順");
		itemMap.put(3, "人気順");
		model.addAttribute("itemMap", itemMap);

		/*
		 * List<Item> itemList = itemService.findAll(); model.addAttribute("itemList",
		 * itemList);
		 */

		// ページング機能追加
		if (page == null) {
			// ページ数の指定が無い場合は1ページ目を表示させる
			page = 1;
		}

		/* List<Item> itemList = null; */

		// 検索文字列があれば曖昧検索

		try {
			if (form.getId() == 1) {
				if (searchName == null) {
					// 検索文字列が空なら全件検索
					List<Item> itemList = itemService.findAll();
					// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
					Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
					model.addAttribute("itemPage", itemPage);
					// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
					List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
					model.addAttribute("pageNumbers", pageNumbers);
				} else {
					List<Item> itemList = itemService.findByItemName(searchName);
					if (itemList.size() == 0) {
						String no = "該当する商品がありません";
						model.addAttribute("no", no);
						List<Item> itemList2 = itemService.findAll();
						// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
						Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList2);
						model.addAttribute("itemPage", itemPage);
						// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
						List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
						model.addAttribute("pageNumbers", pageNumbers);
					} else {
						// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
						Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
						model.addAttribute("itemPage", itemPage);
						// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
						List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
						model.addAttribute("pageNumbers", pageNumbers);
					}
				}

			} else if (form.getId() == 2) {
				if (searchName == null) {
					// 検索文字列が空なら全件検索
					List<Item> itemList = itemService.findAllByPrice2();
					// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
					Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
					model.addAttribute("itemPage", itemPage);
					// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
					List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
					model.addAttribute("pageNumbers", pageNumbers);
				}

				List<Item> itemList = itemService.findByItemName2(searchName);
				if (itemList.size() == 0) {
					String no = "該当する商品がありません";
					model.addAttribute("no", no);
					List<Item> itemList2 = itemService.findAllByPrice2();
					// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
					Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList2);
					model.addAttribute("itemPage", itemPage);
					// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
					List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
					model.addAttribute("pageNumbers", pageNumbers);
				} else {
					// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
					Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
					model.addAttribute("itemPage", itemPage);
					// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
					List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
					model.addAttribute("pageNumbers", pageNumbers);
				}

			} else if (form.getId() == 3) {
				if (searchName == null) {
					// 検索文字列が空なら全件検索
					List<Item> itemList = itemService.findAllByPrice3();
					// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
					Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
					model.addAttribute("itemPage", itemPage);
					// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
					List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
					model.addAttribute("pageNumbers", pageNumbers);
				}

				List<Item> itemList = itemService.findByItemName3(searchName);
				if (itemList.size() == 0) {
					String no = "該当する商品がありません";
					model.addAttribute("no", no);
					List<Item> itemList2 = itemService.findAllByPrice3();
					// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
					Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList2);
					model.addAttribute("itemPage", itemPage);
					// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
					List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
					model.addAttribute("pageNumbers", pageNumbers);
				} else {
					// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
					Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
					model.addAttribute("itemPage", itemPage);
					// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
					List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
					model.addAttribute("pageNumbers", pageNumbers);
				}

			}

		} catch (NullPointerException e) {
			List<Item> itemList = itemService.findAllByPrice3();
			if (itemList.size() == 0) {
				String no = "該当する商品がありません";
				model.addAttribute("no", no);
				List<Item> itemList2 = itemService.findAll();
				// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
				Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList2);
				model.addAttribute("itemPage", itemPage);
				// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
				List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
				model.addAttribute("pageNumbers", pageNumbers);
			} else {
				// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
				Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
				model.addAttribute("itemPage", itemPage);
				// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
				List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
				model.addAttribute("pageNumbers", pageNumbers);
			}

		}

		return "item_list_curry";
	}

	private List<Integer> calcPageNumbers(Model model, Page<Item> itemPage) {
		int totalPages = itemPage.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}

	//////////////////////////////////////////////
	//// 商品の検索
	//////////////////////////////////////////////
	/**
	 * 商品検索を行う
	 * 
	 * @param searchName 検索する名前
	 * @param model      モデル
	 * @return 検索された商品一覧画面
	 * 
	 * @author kohei eto
	 */
	@RequestMapping("/search")
	public String findByItemName(String searchName, Model model) {
		if (Objects.isNull(searchName)) { // 検索文字列が空なら全件検索
			List<Item> itemList = itemService.findAll();
			model.addAttribute("itemList", itemList);
		} else { // 検索文字列があれば曖昧検索
			List<Item> itemList = itemService.findByItemName(searchName);
			if (itemList.size() == 0) {
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
	 * 
	 * @author kohei eto
	 */
	@RequestMapping("/indexRegister")
	public String indexRegister() {
		return "register_user";
	}

	/**
	 * ユーザー登録
	 * 
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
	//////////////////////////////////////////////
	@RequestMapping("/orderConfirm")
	public String Confirm(OrderForm form, Model model) {
		try {
			Integer userId = (Integer) session.getAttribute("userId");
			List<Order> order = orderService.getOrderListByUserIdAndStatus0(userId);
			List<OrderItem> orderItemList = order.get(0).getOrderItemList();
			model.addAttribute("orderItemList", orderItemList);
			model.addAttribute("tax", order.get(0).getTax());
			model.addAttribute("totalPrice", order.get(0).getCalcTotalPrice() + order.get(0).getTax());
			Map<String, String> orderTimeMap=new LinkedHashMap<>();
			orderTimeMap.put("10:59:59", "10時");
			orderTimeMap.put("11:59:59", "11時");
			orderTimeMap.put("12:59:59", "12時");
			orderTimeMap.put("13:59:59", "13時");
			orderTimeMap.put("14:59:59", "14時");
			orderTimeMap.put("15:59:59", "15時");
			orderTimeMap.put("16:59:59", "16時");
			orderTimeMap.put("17:59:59", "17時");
			orderTimeMap.put("18:59:59", "18時");
			model.addAttribute("orderTime",orderTimeMap);
			return "order_confirm";
		} catch (IndexOutOfBoundsException e) {
			return showCartList(model);
		}
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
		Date nowDate = new Date();
		order.setOrderDate(nowDate);
		order.setDestinationName(form.getName());
		order.setDestinationEmail(form.getMailAddress());
		// 郵便番号のハイフンを除去
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

		try {
			Date dTime = df.parse(delivery);
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
		return "redirect:/orderFinished";
	}

	/**
	 * 注文完了画面を表示
	 * 
	 * @author shoya fujisawa
	 */
	@RequestMapping("/orderFinished")
	public String finished() {
		return "order_finished";
	}

	//////////////////////////////////////////////
	//// 商品詳細画面の表示
	//////////////////////////////////////////////

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
		item.setToppingList(toppingList);

		model.addAttribute("item", item);

		itemForm.setSize("M");
		itemForm.setQuantity(1);

		return "item_detail";
	}

	//////////////////////////////////////////////
	//// ショッピングカートに商品を追加する
	//////////////////////////////////////////////
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

		Integer userId;

		// userIdにセット
		if (Objects.isNull((Integer) session.getAttribute("userId"))) {
			// ログインしていない場合
			// UUIDの発行
			UUID uuid = UUID.randomUUID();
			userId = uuid.hashCode();

			session.setAttribute("userId", userId);
		} else {
			// ログインしている場合
			userId = (Integer) session.getAttribute("userId");
		}

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

			// totalPriceの計算
			OrderItem orderItem = new OrderItem();
			orderItem.setItem(itemService.showDetail(form.getItemId()));
			orderItem.setSize(form.getSize().charAt(0));
			orderItem.setQuantity(form.getQuantity());

			List<OrderTopping> orderToppingList = new ArrayList<>();

			if (!form.getToppingIdList().isEmpty()) {
				for (Integer toppingId : form.getToppingIdList()) {
					Topping topping = toppingService.findById(toppingId);
					OrderTopping orderTopping = new OrderTopping();
					orderTopping.setTopping(topping);
					orderToppingList.add(orderTopping);
				}

				orderItem.setOrderToppingList(orderToppingList);
			}

			Integer subTotal = orderItem.getSubTotal();
			order.setTotalPrice(subTotal);

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
		List<Order> orderList = orderService.getOrderListByUserIdAndStatus0(userId);
		Integer totalPrice = 0;
		totalPrice = orderList.get(0).getCalcTotalPrice();

		orderService.updateTotalPriceByUserId(userId, totalPrice);

		return "redirect:/cartInComplete";
	}

	/**
	 * 商品追加完了画面を出力する.
	 * 
	 * @param model モデル
	 * @return 商品追加完了画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/cartInComplete")

	public String cartInComplete(Model model, Integer page, ItemForm form, String searchname) {

		if (Objects.nonNull((Integer) session.getAttribute("userId"))) {

			if (orderService.status0ExistByUserId((Integer) session.getAttribute("userId"))) {
				List<Order> order = orderService
						.getOrderListByUserIdAndStatus0((Integer) session.getAttribute("userId"));

				if (order.get(0).getOrderItemList().get(0).getItem().getId() == 0) {
					model.addAttribute("notExistOrderItemList", "カートに商品がありません");
				} else {
					model.addAttribute("orderItemList", order.get(0).getOrderItemList());
					model.addAttribute("tax", order.get(0).getTax());
					model.addAttribute("totalPrice", order.get(0).getTotalPrice() + order.get(0).getTax());
					model.addAttribute("cartInComplete", "cartInComplete");
				}
			} else {
				return index(model, page, form, searchname);
			}
		} else {
			return index(model, page, form, searchname);
		}

		return "cart_list";
	}

	//////////////////////////////////////////////
	//// ショッピングカートの中身を表示する
	//////////////////////////////////////////////
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

		if (Objects.isNull((Integer) session.getAttribute("userId"))) {

			model.addAttribute("notExistOrderItemList", "カートに商品がありません");
		} else {
			// ログインまたはUUIDを発行している場合
			Integer userId = (Integer) session.getAttribute("userId");

			if (orderService.status0ExistByUserId(userId)) {
				// status0がレコードに存在したら
				List<Order> order = orderService.getOrderListByUserIdAndStatus0(userId);

				if (order.get(0).getOrderItemList().get(0).getItem().getId() == 0) {
					// orderItemが無かったら（カートの中身が空だったら)
					model.addAttribute("notExistOrderItemList", "カートに商品がありません");
				} else {

					model.addAttribute("orderItemList", order.get(0).getOrderItemList());
					model.addAttribute("tax", order.get(0).getTax());
					model.addAttribute("totalPrice", order.get(0).getTotalPrice() + order.get(0).getTax());
				}
			} else {
				// status0がレコードに存在しなかったら
				model.addAttribute("notExistOrderItemList", "カートに商品がありません");
			}
		}

		return "cart_list";
	}

	//////////////////////////////////////////////
	//// ショッピングカートから商品を削除する
	//////////////////////////////////////////////
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
	public String deleteOrderItem(String orderItemId) {

		orderItemService.deleteByOrderItemId(Integer.parseInt(orderItemId));

		Integer userId = (Integer) session.getAttribute("userId");

		List<Order> order = orderService.getOrderListByUserIdAndStatus0(userId);
		Integer totalPrice = order.get(0).getCalcTotalPrice();
		orderService.updateTotalPriceByUserId(userId, totalPrice);

		return "redirect:/showCartList";
	}

	//////////////////////////////////////////////
	//// ショッピングカートの中身を全て削除する
	//////////////////////////////////////////////
	@RequestMapping("/deleteAll")
	public String deleteOrderItemAll() {

		Integer userId = (Integer) session.getAttribute("userId");

		orderItemService.deleteOrderItemsAndOrderToppingsAll(userId);
		orderService.updateTotalPriceByUserId(userId, 0);

		return "redirect:/showCartList";
	}

	//////////////////////////////////////////////
	//// ログイン・ログアウト機能
	//////////////////////////////////////////////
	/**
	 * ログイン画面を表示
	 * 
	 * @author soshi morita
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
	 * 
	 * @author soshi morita
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

	//////////////////////////////////////////////
	//// マイページの表示
	//////////////////////////////////////////////
	/**
	 * @author soshi morita
	 */
	@RequestMapping("/mypage")
	public String mypage(){
		return "mypage";
	}
	
	//////////////////////////////////////////////
	//// お気に入り商品の一覧画面
	//////////////////////////////////////////////
	/**
	 * @author soshi morita
	 */
	@RequestMapping("/favorite")
	public String favorite(){
		return "favorite";
	}
}