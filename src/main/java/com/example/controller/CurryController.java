package com.example.controller;

import java.text.SimpleDateFormat;

import java.util.List;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.UserService;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.Topping;
import com.example.domain.User;
import com.example.form.ItemForm;
import com.example.form.OrderForm;
import com.example.form.UserForm;
import com.example.repository.UserRepository;
import com.example.service.ItemService;
import com.example.service.OrderService;
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

	@ModelAttribute
	private OrderForm orderForm() {
		return new OrderForm();
	}

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService ItemService;

	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}

	// 商品一覧を表示
	@RequestMapping("")
	public String index(Model model) {

		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "item_list_curry";
	}

	// 商品検索を行う
	@RequestMapping("/search")
	public String findByItemName(String searchName, Model model) {

		if (searchName == null) {
			// 検索文字列が空なら全件検索
			List<Item> itemList = itemService.findAll();
			model.addAttribute("itemList", itemList);
		} else {
			// 検索文字列があれば曖昧検索
			List<Item> itemList = itemService.findByItemName(searchName);
			if (itemList.equals("")) {
				String no = "該当する商品がありません";
				model.addAttribute("no", no);
			}

			model.addAttribute("itemList", itemList);
		}

		return "item_list_curry";
	}

	// ユーザー登録画面を表示
	@RequestMapping("/indexRegister")
	public String indexRegister() {

		return "register_user";
	}

	// ユーザー登録をする
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

	@RequestMapping("/orderConfirm")
	public String Confirm(OrderForm form) {
		return "order_confirm";
	}

	/**
	 * 注文情報を登録
	 * 
	 * @author shoya fujisawa
	 */
	@RequestMapping("/order")
	public String insert(@Validated OrderForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return Confirm(form);
		}
		Order order = new Order();
		order.setUserId(form.getUserId());

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
			if (diff / (60 * 60 * 1000) % 24 < 3) {
				model.addAttribute("message", "今から3時間後以降の日時をご入力ください");
				return Confirm(form);
			}
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		order.setPaymentMethod(form.getPaymentMethod());

		orderService.order(order);
		return "order_finished";
	}

	@Autowired
	private ItemService itemService;

	@Autowired
	private ToppingService toppingService;

	@ModelAttribute
	public ItemForm setUpItemForm() {
		return new ItemForm();
	}

	/**
	 * 商品情報詳細画面を出力する.
	 * 
	 * @param id    商品ID
	 * @param model モデル
	 * @return 商品情報詳細画面
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

	@RequestMapping("/cartIn")
	public String cartIn(@Validated ItemForm form, BindingResult result) {

		if (result.hasErrors()) {
			return "item_detail";
		}

		return "cartInComplete";
	}

	//////////////////////////////////////////////
	//// ログイン・ログアウト機能
	//////////////////////////////////////////////
	@Autowired
	private UserRepository userRepository;

	/**
	 * SpringSecurity実装後のログインユーザーを登録するメソッドです 一度だけこのURLを叩いてください ※2回目は絶対に叩かないでください
	 * 全員がこのメソッドを実行後、直ちにこのメソッドは削除します
	 */
	@RequestMapping("/insertUser")
	public String insertUser() {
		User user = new User();
		user.setName("test");
		user.setEmail("t@t"); // ログインID
		user.setPassword("ttt"); // ログインPW
		user.setAddress("test住所");
		user.setTelephone("テスト電話番号");
		user.setZipcode("1111111");
		userRepository.insert(user);
		return "login";
	}

	/**
	 * ログイン画面を表示
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

}
