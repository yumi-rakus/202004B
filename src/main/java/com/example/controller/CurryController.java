package com.example.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Favorite;
import com.example.domain.Item;
import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.Rice;
import com.example.domain.Topping;
import com.example.domain.User;
import com.example.form.ItemForm;
import com.example.form.ItemSearchForm;
import com.example.form.OrderForm;
import com.example.form.UpdateItemForm;
import com.example.form.UserForm;
import com.example.service.FavoriteService;
import com.example.service.ItemService;
import com.example.service.OrderItemService;
import com.example.service.OrderService;
import com.example.service.RiceService;
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
	private HttpSession session;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private RiceService riceService;

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
	public UpdateItemForm setUpUpdateItemForm() {
		return new UpdateItemForm();
	}

	@ModelAttribute
	public ItemSearchForm setUpItemSearchForm() {
		return new ItemSearchForm();
	}

	@ModelAttribute
	public void setUp(Model model) {
		Map<String, String> itemMap = new LinkedHashMap<>();
		itemMap.put("1", "価格安い順");
		itemMap.put("2", "価格高い順");
		itemMap.put("3", "人気順");
		model.addAttribute("itemMap", itemMap);
	}

	//////////////////////////////////////////////
	//// 商品一覧を表示, 商品検索を行う
	//////////////////////////////////////////////
	/**
	 * 商品一覧を表示 商品検索を行う
	 * 
	 * @return 商品一覧画面
	 * 
	 * @author kohei eto
	 */
	@RequestMapping("")
	public String index(Model model, Integer page, ItemSearchForm form) {

		List<Item> itemList = new ArrayList<>();

		try {
			if (form.getId().equals("1")) {
				if (form.getSearchName() == null) {
					// 検索文字列が空なら全件検索
					itemList = itemService.findAllByPrice();
				} else {
					itemList = itemService.findByItemName(form.getSearchName());
					if (itemList.size() == 0) {
						String no = "該当する商品がありません";
						model.addAttribute("no", no);
						itemList = itemService.findAllByPrice();
					}
				}

			} else if (form.getId().equals("2")) {
				if (form.getSearchName() == null) {
					// 検索文字列が空なら全件検索
					itemList = itemService.findAllByPrice2();
				}
				itemList = itemService.findByItemName2(form.getSearchName());
				if (itemList.size() == 0) {
					String no = "該当する商品がありません";
					model.addAttribute("no", no);
					itemList = itemService.findAllByPrice2();

				}

			} else if (form.getId().equals("3")) {
				if (form.getSearchName() == null) {
					// 検索文字列が空なら全件検索
					itemList = itemService.findAllByPrice3();
				}
				itemList = itemService.findByItemName3(form.getSearchName());
				if (itemList.size() == 0) {
					String no = "該当する商品がありません";
					model.addAttribute("no", no);
					itemList = itemService.findAllByPrice3();
				}
			}

		} catch (NullPointerException e) {
			itemList = itemService.findAllByPrice();
		}

		// ページが範囲外の場合は1ページ目を表示
		if (page == null || page <= 0 || page > 2) {
			page = 1;
		}
		model.addAttribute("searchName", form.getSearchName());
		model.addAttribute("id", form.getId());
		// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
		Page<Item> itemPage = itemService.showListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);
		// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		model.addAttribute("pageNumbers", pageNumbers);

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

		if (!userForm.getPassword().equals(userForm.getConpassword())) {
			model.addAttribute("passwordResult", "パスワードが一致していません");
			return indexRegister();
		}

		if (result.hasErrors()) {
			return indexRegister();
		}

		if (userService.existByEmail(userForm.getEmail())) {
			model.addAttribute("emailResult", "そのメールアドレスは既に登録されています");
			return indexRegister();
		}

		User user = new User();

		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setZipcode(userForm.getZipcodefirst() + userForm.getZipcodelast());
		user.setAddress(userForm.getAddressFirst() + userForm.getAddressLast()
		);
		user.setTelephone(userForm.getTelephone());
		user.setPassword(userForm.getPassword());
		user.setIsAdmin(false);

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
			model.addAttribute("price", order.get(0).getTotalPrice());
			model.addAttribute("totalPrice", order.get(0).getCalcTotalPrice() + order.get(0).getTax());
			Map<String, String> orderTimeMap = new LinkedHashMap<>();
			orderTimeMap.put("10:59:59", "10時");
			orderTimeMap.put("11:59:59", "11時");
			orderTimeMap.put("12:59:59", "12時");
			orderTimeMap.put("13:59:59", "13時");
			orderTimeMap.put("14:59:59", "14時");
			orderTimeMap.put("15:59:59", "15時");
			orderTimeMap.put("16:59:59", "16時");
			orderTimeMap.put("17:59:59", "17時");
			orderTimeMap.put("18:59:59", "18時");
			User user = userService.getUserById(userId);
			Integer points = userService.getPoints(userId);
			model.addAttribute("user", user);
			model.addAttribute("orderTime", orderTimeMap);
			model.addAttribute("points", points);
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
	public String order(@Validated OrderForm form, BindingResult result, Model model) {

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
		order.setDestinationEmail(form.getEmail());
		// 郵便番号を合体させる
		String zipCodeFirst = form.getZipcodefirst();
		String zipCodeLast = form.getZipcodelast();
		String zipCode = zipCodeFirst + zipCodeLast;
		order.setDestinationZipcode(zipCode);

		order.setDestinationAddress(form.getAddress());
		order.setDestinationTel(form.getTelephone());

		String delivery = form.getOrderDate() + " " + form.getTime();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date dTime = df.parse(delivery);
			Instant instant = dTime.toInstant();
			LocalDateTime dTime2 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			dTime2 = dTime2.minusMinutes(59).minusSeconds(59);
			Timestamp deliveryTime = Timestamp.valueOf(dTime2);
			order.setDeliveryTime(deliveryTime);
			// 選択時間から3時間以内は選択不可にする
			long diff = dTime.getTime() - nowDate.getTime();
			if (diff / (60 * 60 * 1000) < 3) {
				model.addAttribute("message", "今から3時間後以降の日時をご入力ください");
				return Confirm(form, model);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		order.setPaymentMethod(form.getPaymentMethod());
		order.setDiscountPrice(form.getDiscountPrice());
		order.setTax(order.getTax());
		order.setTaxIncludedPrice(order.getTotalPrice() + order.getTax());
		if (form.getDiscountPrice() != 0) {
			order.setUsedPoints(order.getTaxIncludedPrice() - order.getDiscountPrice());
		} else if (form.getDiscountPrice() == 0) {
			order.setUsedPoints(0);
		}
		orderService.order(order);
		Integer points = (int) (order.getTotalPrice() * 0.05);
		Integer usedPoints = form.getUsedPoints();
		Integer userId = (Integer) session.getAttribute("userId");
		userService.addPoints(points, userId);
		userService.subPoints(usedPoints, userId);
		sendMailService.sendMail(userId);
		return "redirect:/orderFinished";
	}

	/**
	 * 注文完了画面を表示
	 * 
	 * @author shoya fujisawa
	 */
	@RequestMapping("/orderFinished")
	public String finished() {
		Integer userId = (Integer) session.getAttribute("userId");
		Integer points = userService.getPoints(userId);
		session.setAttribute("points", points);
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
		List<Rice> riceList = riceService.findAllNonDeleted();
		Rice rice = riceService.findById(1);

		model.addAttribute("item", item);
		model.addAttribute("riceList", riceList);
		model.addAttribute("riceSample", rice);

		itemForm.setSize("M");
		itemForm.setQuantity(1);
		itemForm.setRiceId(1);

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

		orderItemService.cartIn(form, userId);

		// ordersテーブルのtotalPriceをupdate (where:status=0, userId)
		orderService.updateTotalPrice(userId);

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

	public String cartInComplete(Model model, Integer page, ItemSearchForm form, String searchname) {

		if (Objects.nonNull((Integer) session.getAttribute("userId"))) {

			if (orderService.status0ExistByUserId((Integer) session.getAttribute("userId"))) {
				List<Order> order = orderService.getOrderListByUserIdAndStatus0((Integer) session.getAttribute("userId"));

				if (order.get(0).getOrderItemList().get(0).getItem().getId() == 0) {
					model.addAttribute("notExistOrderItemList", "カートに商品がありません");
				} else {
					model.addAttribute("orderItemList", order.get(0).getOrderItemList());
					model.addAttribute("tax", order.get(0).getTax());
					model.addAttribute("totalPrice", order.get(0).getTotalPrice() + order.get(0).getTax());
					model.addAttribute("cartInComplete", "cartInComplete");
				}
			} else {
				return index(model, page, form);
			}
		} else {
			return index(model, page, form);
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

		orderService.updateTotalPrice(userId);

		return "redirect:/showCartList";
	}

	//////////////////////////////////////////////
	//// ショッピングカートの中身を全て削除する
	//////////////////////////////////////////////
	@RequestMapping("/deleteAll")
	public String deleteOrderItemAll() {

		Integer userId = (Integer) session.getAttribute("userId");

		orderItemService.deleteOrderItemsAndOrderToppingsAll(userId);
		orderService.updateTotalPrice(userId);

		return "redirect:/showCartList";
	}

	//////////////////////////////////////////////
	//// お米についてページの表示
	//////////////////////////////////////////////
	/**
	 * お米について画面を出力する.
	 * 
	 * @param model モデル
	 * @return お米について画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/riceInfo")
	public String showRiceInfo(Model model) {

		List<Rice> riceList = riceService.findAll();
		model.addAttribute("riceList", riceList);

		return "rice_info";
	}

	//////////////////////////////////////////////
	//// 管理者ページの表示
	//////////////////////////////////////////////
	/**
	 * 管理者ページ画面を出力する.
	 * 
	 * @return 管理者ページ画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/admin/adminPage")
	public String toAdminPage() {
		return "admin_page";
	}

	//////////////////////////////////////////////
	//// 注文一覧管理ページを表示
	//////////////////////////////////////////////
	/**
	 * すべての注文のstatusを管理する注文一覧画面を出力する.
	 * 
	 * @param model モデル
	 * @return 注文一覧管理画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/admin/orderList")
	public String manageOrderList(Model model) {

		List<Order> orderAllList = orderService.getOrderListByStatusNot0();
		model.addAttribute("orderList", orderAllList);

		return "order_list";
	}

	//////////////////////////////////////////////
	//// 商品管理ページを表示
	//////////////////////////////////////////////
	/**
	 * 削除フラグを管理する商品一覧ページを出力する.
	 * 
	 * @param model モデル
	 * @return 商品管理画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/admin/itemList")
	public String manageItemList(Model model) {

		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);

		return "admin_item_list";
	}

	//////////////////////////////////////////////
	//// 管理者登録ページの表示
	//////////////////////////////////////////////
	/**
	 * 管理者登録画面を出力する.
	 * 
	 * @return 管理者登録画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/admin/toRegister")
	public String toRegisterAdmin() {
		return "register_admin";
	}

	//////////////////////////////////////////////
	//// 管理者登録をする
	//////////////////////////////////////////////
	/**
	 * 管理者を登録する.
	 * 
	 * @param userForm フォーム
	 * @param result   BindingResultオブジェクト
	 * @param model    モデル
	 * @return 管理者ページ画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/admin/register")
	public String registerAdmin(@Validated UserForm userForm, BindingResult result, Model model) {

		if (!userForm.getPassword().equals(userForm.getConpassword())) {
			model.addAttribute("passwordResult", "パスワードが一致していません");
			return toRegisterAdmin();
		}

		if (result.hasErrors()) {
			return toRegisterAdmin();
		}

		if (userService.existByEmail(userForm.getEmail())) {
			model.addAttribute("emailResult", "そのメールアドレスは既に登録されています");
			return toRegisterAdmin();
		}

		User user = new User();

		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setZipcode(userForm.getZipcodefirst() + userForm.getZipcodelast());
		user.setAddress(userForm.getAddress());
		user.setTelephone(userForm.getTelephone());
		user.setPassword(userForm.getPassword());
		user.setIsAdmin(true);

		userService.insert(user);

		return "redirect:/admin/adminPage";
	}

	//////////////////////////////////////////////
	//// 商品情報編集ページの表示
	//////////////////////////////////////////////
	/**
	 * 商品情報編集画面を出力する.
	 * 
	 * @param model          モデル
	 * @param itemId         商品ID
	 * @param updateItemForm フォーム
	 * @return 商品情報編集画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/admin/toEditItem")
	public String toEditItem(Model model, String itemId, UpdateItemForm updateItemForm) {

		Item item = itemService.showDetail(Integer.parseInt(itemId));
		model.addAttribute("item", item);

		updateItemForm.setName(item.getName());
		updateItemForm.setDescription(item.getDescription());
		updateItemForm.setPriceM(item.getPriceM());
		updateItemForm.setPriceL(item.getPriceL());

		return "item_edit";
	}

	//////////////////////////////////////////////
	//// 商品情報を更新する
	//////////////////////////////////////////////
	/**
	 * 商品情報を更新する.
	 * 
	 * @param updateItemForm フォーム
	 * @param result         BindingResultオブジェクト
	 * @param id             商品ID
	 * @param model          モデル
	 * @return 商品管理画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/admin/editItem")
	public String editItem(@Validated UpdateItemForm updateItemForm, BindingResult result, String id, Model model) {

		if (result.hasErrors()) {
			Item item = itemService.showDetail(Integer.parseInt(id));
			model.addAttribute("item", item);

			return "item_edit";
		}

		Item item = new Item();

		item.setId(Integer.parseInt(id));
		item.setName(updateItemForm.getName());
		item.setDescription(updateItemForm.getDescription());
		item.setPriceM(updateItemForm.getPriceM());
		item.setPriceL(updateItemForm.getPriceL());

		itemService.updateItem(item);

		return "redirect:/admin/itemList";
	}

	//////////////////////////////////////////////
	//// 米管理ページを表示
	//////////////////////////////////////////////
	/**
	 * 削除フラグを管理する米一覧ページを出力する.
	 * 
	 * @param model モデル
	 * @return 米管理画面
	 * 
	 * @author yumi takahashi
	 */
	@RequestMapping("/admin/riceList")
	public String manageRiceList(Model model) {

		List<Rice> riceList = riceService.findAll();
		model.addAttribute("riceList", riceList);

		return "admin_rice_list";
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
	 * @param model     モデル
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
	public String mypage() {
		return "mypage";
	}

	/**
	 * @author kohei eto マイページ一覧画面を表示
	 */
	@RequestMapping("/edit-mypage")
	public String editProfile(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		User user = userService.findByidl(loginUser.getUser().getId());
		model.addAttribute("user", user);

		return "edit-mypage";
	}

	/**
	 * @author kohei eto マイページ情報変更画面
	 */
	@RequestMapping("/update-mypage")
	public String mypageEdit(UserForm userForm, Model model, @AuthenticationPrincipal LoginUser loginUser) {
		User user = userService.getUserById(loginUser.getUser().getId());

		model.addAttribute("user", user);

		return "editing-mypage";
	}

	/**
	 * @author suisu kohei eto マイページ情報変更
	 */
	@RequestMapping("/updating-mypage")
	public String edit(@Validated UserForm userForm, BindingResult result, @AuthenticationPrincipal LoginUser loginUser,
			Model model) {
		if (result.hasErrors()) {
			return mypageEdit(userForm, model, loginUser);
		}
		User user2 = new User();
		user2.setName(userForm.getName());
		user2.setEmail(userForm.getEmail());
		user2.setZipcode(userForm.getZipcodefirst() + userForm.getZipcodelast());
		user2.setAddress(userForm.getAddress());
		user2.setTelephone(userForm.getTelephone());

		userService.update(loginUser.getUser().getId(), user2);

		return "login";
	}

	//////////////////////////////////////////////
	//// お気に入り商品の一覧画面
	//////////////////////////////////////////////
	/**
	 * @author soshi morita
	 */
	@RequestMapping("/favorite")
	public String favorite(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		Integer userId = loginUser.getUser().getId();
		List<Favorite> favoriteList = favoriteService.showAll(userId);
		model.addAttribute("favoriteList", favoriteList);
		return "favorite";
	}

	//////////////////////////////////////////////
	//// お気に入り商品の追加と削除
	//////////////////////////////////////////////
	/**
	 * お気に入りへの追加
	 * 
	 * @author soshi morita
	 */
	@RequestMapping("/favorite-insert")
	public String favoriteInsert(@AuthenticationPrincipal LoginUser loginUser, Integer itemId, Model model) {
		Favorite favorite = new Favorite(loginUser.getUser().getId(), itemId, new Date());
		favoriteService.create(favorite);
		return favorite(loginUser, model);
	}

	/**
	 * お気に入りからの削除
	 * 
	 * @author soshi morita
	 */
	@RequestMapping("/favorite-delete")
	public String favoriteDelete(@AuthenticationPrincipal LoginUser loginUser, Integer itemId, Model model) {
		Favorite favorite = new Favorite(loginUser.getUser().getId(), itemId, new Date());
		favoriteService.delete(favorite);
		return favorite(loginUser, model);
	}
}