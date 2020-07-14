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

  @RequestMapping("/success")
  public String success() {
    return "success";
  }

  @RequestMapping("/fail")
  public String fail() {
    return "fail";
  }

  @RequestMapping("/login")
  public String login(String email, String password) {
    User user = userRipository.findByEmail(email);
    return "forward:/success";
  }

}
