package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.User;

/**
 * 注文時にメールをユーザに送信するサービス.
 * 
 * @author shoya fujisawa
 *
 */
@Service
public class SendMailService {
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	/**
	 * 注文時にメールをユーザに送信する.
	 * 
	 * @param mailAddress メールアドレス
	 * 
	 * @author shoya fujisawa
	 */
	public void sendMail() {
		Integer userId=(Integer)session.getAttribute("userId");
		User user=userService.getUser(userId);
		List<Order> order=orderService.getLatestOrderList(userId);
		List<OrderItem> itemList=order.get(0).getOrderItemList();
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("6150fs@gmail.com");
		msg.setTo(order.get(0).getDestinationEmail());
		msg.setSubject("注文完了メール");
		StringBuilder mailContent=new StringBuilder();
		mailContent.append(order.get(0).getDestinationName()+"さん\n");
		mailContent.append("\n");
		mailContent.append("ラクラクカリーをご利用いただき、ありがとうございます。\nご注文を承りました。\n");
		mailContent.append("\n");
		mailContent.append("■ご注文内容■\n");
		for(OrderItem item:itemList) {
			mailContent.append("商品: "+item.getItem().getName()+"\n");
			mailContent.append("サイズ: "+item.getSize()+"\n");
			mailContent.append("数量: "+item.getQuantity()+"\n");
			mailContent.append("トッピング: ");
			if(!item.getOrderToppingList().isEmpty()) {
				for(OrderTopping topping:item.getOrderToppingList()) {
					
					mailContent.append(topping.getTopping().getName()+" ");
				}
			}else {
				mailContent.append("--");
			}
			mailContent.append("\n");
			mailContent.append("\n");
		}
		mailContent.append("\n");
		mailContent.append("商品代金: "+order.get(0).getTotalPrice()+"円\n");
		mailContent.append("消費税: "+order.get(0).getTax()+"円\n");
		mailContent.append("合計金額: "+(order.get(0).getTotalPrice()+order.get(0).getTax())+"円\n");
		mailContent.append("\n");
		if(order.get(0).getStatus()==1) {
			mailContent.append("お支払い方法: 代金引換\n配達時にお支払いください。\n");
		}else if(order.get(0).getStatus()==2) {
			mailContent.append("お支払い方法: クレジットカード\n決済済みです。\n");
		}
		mailContent.append("\n");
		mailContent.append("またのご利用をお待ちしております。");
		msg.setText(mailContent.toString());
		
		
//		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//		
//		templateResolver.setTemplateMode(TemplateMode.TEXT);
//		
//		templateResolver.setCharacterEncoding("UTF-8");
//		
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//	    engine.setTemplateResolver(templateResolver);
//	    
//	    Map<String, Object> variables = new HashMap<>();
//	    variables.put("name", order.get(0).getDestinationName());
//	    variables.put("items",order.get(0).getOrderItemList());
//	    variables.put("toppings", order.get(0).getOrderItemList().get(0).getOrderToppingList());
//	    
//	    Context context = new Context();
//	    context.setVariables(variables);
//	    
//	    String text = engine.process("mail.txt", context);
//	    msg.setText(text);
	    
		this.mailSender.send(msg);
	}
}
