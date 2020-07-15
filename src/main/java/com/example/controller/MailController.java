package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MailController {
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	@Autowired
	private MailSender mailSender;
	
	@RequestMapping(value="/mail/test",method = RequestMethod.GET)
	public String index() {
		return "order_finished";
	}
	
	@RequestMapping(value="/mail",method = RequestMethod.GET)
	public String sendMail() {
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setTo("6150fs@gmail.com");
		msg.setSubject("注文完了メール");
		msg.setText("注文が完了しました。\nご利用ありがとうございました。");
		mailSender.send(msg);
		return "order_finished";
	}
}
