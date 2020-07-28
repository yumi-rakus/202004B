package com.example.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderFormTest {
	@Autowired
	private Validator validator;

	private OrderForm orderForm = new OrderForm();

	@BeforeEach
	void setUp() throws Exception {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		orderForm.setName("山田太郎");
		orderForm.setEmail("yamada@test.com");
		orderForm.setZipcodefirst("000");
		orderForm.setZipcodelast("0000");
		orderForm.setAddress("東京都新宿区1-1-1");
		orderForm.setTelephone("0000000000");
		orderForm.setOrderDate("2020-07-28");
		orderForm.setTime("9:00:00");
		orderForm.setPaymentMethod(0);
	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void 全てのバリデーションチェック成功テスト() {
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 名前入力テスト() {
		orderForm.setName("鈴木一郎");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 名前の文字数テスト() {
		// 20文字入力
		orderForm.setName("ああああああああああああああああああああ");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 名前未入力テスト() {
		orderForm.setName("");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	void 名前の文字数超過テスト() {
		// 21文字入力
		orderForm.setName("あああああああああああああああああああああ");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	void メールアドレス入力テスト() {
		orderForm.setEmail("test@mail.com");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void メールアドレス未入力テスト() {
		orderForm.setEmail("");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	void メールアドレスの不正な形式テスト() {
		orderForm.setEmail("a");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Email, "エラーの種類が異なります。");
		}
	}

	@Test
	void 郵便番号上3桁入力テスト() {
		orderForm.setZipcodefirst("000");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 郵便番号上3桁欄に2桁入力するテスト() {
		orderForm.setZipcodefirst("00");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");
		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}
	
	@Test
	void 郵便番号上3桁欄に4桁入力するテスト() {
		orderForm.setZipcodefirst("0000");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");
		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}


	@Test
	void 郵便番号下4桁入力テスト() {
		orderForm.setZipcodelast("0000");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 郵便番号下4桁欄に3桁入力するテスト() {
		orderForm.setZipcodelast("000");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");
		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}
	
	@Test
	void 郵便番号下4桁欄に5桁入力するテスト() {
		orderForm.setZipcodelast("00000");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");
		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	void 住所入力テスト() {
		orderForm.setAddress("東京都新宿区1-1-1");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 住所未入力テスト() {
		orderForm.setAddress("");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	void 電話番号10桁入力テスト() {
		orderForm.setTelephone("0000000000");

		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}
	
	@Test
	void 電話番号12桁入力テスト() {
		orderForm.setTelephone("000000000000");

		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}


	@Test
	void 電話番号9桁入力テスト() {
		orderForm.setTelephone("000000000");

		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	void 電話番号13桁入力テスト() {
		orderForm.setTelephone("0000000000000");

		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	void 配達日入力テスト() {
		orderForm.setOrderDate("2020-07-28");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 配達日未入力テスト() {
		orderForm.setOrderDate("");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	void 配達時間入力テスト() {
		orderForm.setTime("9:00:00");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 配達時間未入力テスト() {
		orderForm.setTime("");
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	void 支払い方法入力テスト() {
		orderForm.setPaymentMethod(0);
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(0, violations.size(), "期待される結果と異なります");
	}

	@Test
	void 支払い方法未入力テスト() {
		orderForm.setPaymentMethod((Integer)null);
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(1, violations.size(), "期待される結果と異なります");

		for (ConstraintViolation<OrderForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotNull, "エラーの種類が異なります。");
		}
	}

	@Test
	void 全てのバリデーションチェック失敗テスト() {
		orderForm.setName("");
		orderForm.setEmail("aaa");
		orderForm.setZipcodefirst("");
		orderForm.setZipcodelast("");
		orderForm.setAddress("");
		orderForm.setTelephone("");
		orderForm.setOrderDate("");
		orderForm.setTime("");
		orderForm.setPaymentMethod((Integer)null);
		
		Set<ConstraintViolation<OrderForm>> violations = validator.validate(orderForm);

		assertEquals(9, violations.size(), "期待される結果と異なります");
	}

}
