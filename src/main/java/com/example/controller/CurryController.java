package com.example.controller;

import org.springframework.beans.BeanUtils;
import com.example.domain.User;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.example.domain.Order;

import java.util.List;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.form.ItemForm;
import com.example.form.OrderForm;
import com.example.service.ItemService;
import com.example.service.OrderService;
import com.example.service.ToppingService;

/**
 * カレーECサイトを操作するコントローラ.
 * 
 * @author yumi takahashi
 *
 */

@Controller
@RequestMapping("/")
public class CurryController {

  @Autowired
  private UserRepository userRipository;

  @Autowired
  private OrderService orderService;

  @Autowired
  private UserService userService;

  @ModelAttribute
  private OrderForm orderForm() {
    return new OrderForm();
  }

  @ModelAttribute
  public UserForm setUpUserForm() {
    return new UserForm();
  }

  // ログイン画面を表示
  @RequestMapping("")
  public String index() {
    return "login";
  }

  // 従業員登録画面を表示
  @RequestMapping("/indexRegister")
  public String indexRegister() {
    return "register_user";
  }

  // 従業員登録をする
  @RequestMapping("/register")
  public String register(UserForm userForm) {
    User user = new User();
    BeanUtils.copyProperties(userForm, user);
    userService.insert(user);
    return "redirect:/";
  }

  @RequestMapping("/confirm")
  public String Confirm() {
    return "order_confirm";
  }

  /**
   * 注文情報を登録
   * 
   * @author shoya fujisawa
   */
  @RequestMapping("/insert")
  public String insert(@Validated OrderForm form, BindingResult result, Model model) {
    if (result.hasErrors()) {

    }
    Order order = new Order();
    order.setUserId(form.getUserId());

    if (form.getPaymentMethod() == 1) {
      order.setStatus(1);
    } else if (form.getPaymentMethod() == 2) {
      order.setStatus(2);
    }

    order.setTotalPrice(form.getTotalPrice());

    String orderDt = form.getOrderDate() + form.getOrderTime();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat nowDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    String strDate = date.toString();

    try {
      Date orderDate = df.parse(orderDt);
      Date nowDate = nowDateFormat.parse(strDate);
      long dateTimeTo = orderDate.getTime();
      long dateTimeFrom = nowDate.getTime();
      if ((dateTimeTo - dateTimeFrom) / (1000 * 60 * 60) <= 180) {
        model.addAttribute("今から3時間後の日時をご入力ください");
      }
      order.setOrderDate(orderDate);
    } catch (java.text.ParseException e) {
      e.printStackTrace();
    }
    order.setDestinationName(form.getName());
    order.setDestinationEmail(form.getMailAddress());
    order.setDestinationZipcode(form.getZipCode());
    order.setDestinationAddress(form.getAddress());
    order.setDestinationTel(form.getTelephone());
    order.setDeliveryTime(form.getTime());
    order.setPaymentMethod(form.getPaymentMethod());

    orderService.insert(order);
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

  @RequestMapping("/cart-in")
  public String cartIn(@Validated ItemForm form, BindingResult result) {

    if (result.hasErrors()) {
      return "item_detail";
    }
    return "cart-in-complete";
  }

  /**
   * SpringSecurity実装後のログインユーザーを登録するメソッドです 一度だけこのURLを叩いてください ※2回目は絶対に叩かないでください
   * 全員がこのメソッドを実行後、直ちにこのメソッドは削除します
   */
  @RequestMapping("/insert-user")
  public String insertUser() {
    User user = new User();
    user.setName("test");
    user.setEmail("t@t"); // ログインID
    user.setPassword("ttt"); // ログインPW
    user.setAddress("test住所");
    user.setTelephone("テスト電話番号");
    user.setZipcode("1111111");
    userRipository.insert(user);
    return "login";
  }

  @RequestMapping("/success")
  public String success() {
    return "item_list_curry";
  }

}
