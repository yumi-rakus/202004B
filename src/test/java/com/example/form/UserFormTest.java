package com.example.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserFormTest {

	@Autowired
	private Validator validator;

	private UserForm userForm = new UserForm();

	@BeforeEach
	public void 事前処理() throws Exception {

		validator = Validation.buildDefaultValidatorFactory().getValidator();

		userForm.setName("山田太郎");
		userForm.setEmail("yamada@sample.com");
		userForm.setZipcodeFirst("111");
		userForm.setZipcodeLast("1111");
		userForm.setAddressFirst("東京都世田谷区宮坂");
		userForm.setAddressLast("1-1-1");
		userForm.setTelephone("09011112222");
		userForm.setPassword("yamadayamada");
	}

	@Test
	public void 成功Test() {

		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	/////////// name
	@Test
	public void 名前successTest1() {

		userForm.setName("あ");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前successTest2() {

		userForm.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前failureTest1() {

		userForm.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおか");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 名前failureTest2() {

		userForm.setName("あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおかきくけこさしすせそ");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 名前failureTest3() {

		userForm.setName("");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	/////////// email
	@Test
	public void メールアドレスfailureTest1() {

		userForm.setEmail("");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	@Test
	public void メールアドレスfailureTest2() {

		userForm.setEmail("yamada");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Email, "エラーの種類が異なります。");
		}
	}

	/////////// password
	@Test
	public void パスワードsuccessTest1() {
		// 8文字
		userForm.setPassword("yamadaya");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void パスワードsuccessTest2() {
		// 16文字
		userForm.setPassword("yamadayamadayama");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void パスワードfailureTest1() {
		// 7文字
		userForm.setPassword("yamaday");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void パスワードfailureTest2() {
		// 17文字
		userForm.setPassword("yamadayamadayamad");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void パスワードfailureTest3() {
		// 20文字
		userForm.setPassword("yamadayamadayamadaya");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void パスワードfailureTest4() {
		// 未入力
		userForm.setPassword("");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	/////////// zipcodeFirst
	@Test
	public void 郵便番号上3桁failureTest1() {

		userForm.setZipcodeFirst("11");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号上3桁failureTest2() {

		userForm.setZipcodeFirst("");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号上3桁failureTest3() {

		userForm.setZipcodeFirst("1111");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号上3桁failureTest4() {

		userForm.setZipcodeFirst("111111");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号上3桁failureTest5() {

		userForm.setZipcodeFirst("aaa");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	/////////// zipcodeLast
	@Test
	public void 郵便番号下4桁failureTest1() {

		userForm.setZipcodeLast("111");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号下4桁failureTest2() {

		userForm.setZipcodeLast("11111");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号下4桁failureTest3() {

		userForm.setZipcodeLast("");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号下4桁failureTest4() {

		userForm.setZipcodeLast("1111111");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 郵便番号下4桁failureTest5() {

		userForm.setZipcodeLast("aaaa");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Pattern, "エラーの種類が異なります。");
		}
	}

	/////////// addressFirst
	@Test
	public void 住所都道府県市区町村failureTest1() {

		userForm.setAddressFirst("");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	/////////// addressLast
	@Test
	public void 住所番地以降failureTest1() {

		userForm.setAddressLast("");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
		}
	}

	/////////// telephone
	@Test
	public void 電話番号successTest1() {

		userForm.setTelephone("1234567890");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 電話番号failureTest1() {

		userForm.setTelephone("123456789");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号failureTest2() {

		userForm.setTelephone("12345");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号failureTest3() {

		userForm.setTelephone("123456789012");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void 電話番号failureTest4() {

		userForm.setTelephone("12345678901234");
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UserForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
		}
	}

	@Test
	public void failureTest1() {

		userForm.setName("");
		userForm.setEmail("aaa");
		userForm.setZipcodeFirst("");
		userForm.setZipcodeLast("");
		userForm.setAddressFirst("");
		userForm.setAddressLast("");
		userForm.setTelephone("");
		userForm.setPassword("");

		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

		assertEquals(8, violations.size(), "エラーの数が異なります。");
	}

}
