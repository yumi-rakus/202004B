package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
class SendMailServiceTest {
	@Autowired
	private SendMailService sendMailService;
	
	@Autowired
	private OrderService orderService;
	
	private static Wiser wiser;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("テスト開始");
		wiser = new Wiser();
        wiser.setPort(9999);
        wiser.setHostname("dummy.mail.com");
        wiser.start();
	}

	@AfterEach
	void tearDown() throws Exception {
		wiser.stop();
		System.out.println("テスト終了");
	}

	@Test
	void メール送信テスト() {
		System.out.println("送信テスト");
		sendMailService.sendMail(7);
		List<WiserMessage> messages = wiser.getMessages();
		assertEquals(1, messages.size(), "メール件数が期待される結果と異なります");
		assertEquals("6150fs@gmail.com", messages.get(0).getEnvelopeSender(), "送信元が異なります");
//		assertEquals("6150fs@gmail.com", messages.get(0).getEnvelopeReceiver(), "送信先が異なります");
	}

}
