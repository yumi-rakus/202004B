package com.example.controller;

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

  @RequestMapping("")
  public String index() {
    return "login";
  }

  @RequestMapping("/list")
  public String list() {
    return "item_list_curry";
  }

}
