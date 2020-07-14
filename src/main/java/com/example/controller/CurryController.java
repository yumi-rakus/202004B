package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.UserService;

import org.springframework.beans.BeanUtils;

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

}
