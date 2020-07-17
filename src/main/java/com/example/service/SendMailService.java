package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

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

	/**
	 * 注文時にメールをユーザに送信する.
	 * 
	 * @param mailAddress メールアドレス
	 * 
	 * @author shoya fujisawa
	 */
	public void sendMail(String mailAddress) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("6150fs@gmail.com");
		msg.setTo(mailAddress);
		msg.setSubject("注文完了メール");
		msg.setText("注文が完了しました。\nご利用ありがとうございました。");

		this.mailSender.send(msg);
	}
}
