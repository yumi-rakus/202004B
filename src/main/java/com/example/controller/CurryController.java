package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderService;

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
	
	

	/**
	 * 注文情報を登録
	 * 
	 * @author shoya fujisawa
	 */
	@RequestMapping("/insert")
	public String insert(@Validated OrderForm form, BindingResult result) {
		if(result.hasErrors()) {
			
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
				order.setOrderDate(orderDate);
			}
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
}
