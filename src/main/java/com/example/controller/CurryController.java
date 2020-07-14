package com.example.controller;

import com.example.domain.User;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @RequestMapping("")
  public String index() {
    return "login";
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
