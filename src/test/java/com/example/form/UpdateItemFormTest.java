package com.example.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * UpdateItemFormTest.javaのテスト
 * 
 * @author yumi takahashi
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UpdateItemFormTest {

	@Autowired
	private Validator validator;

	private UpdateItemForm updateItemForm = new UpdateItemForm();

	@BeforeEach
	public void 事前処理() throws Exception {

		validator = Validation.buildDefaultValidatorFactory().getValidator();

		updateItemForm.setName("カレーライス");
		updateItemForm.setDescription("実家のカレーのような懐かしい美味しさ");
		updateItemForm.setPriceM(1500);
		updateItemForm.setPriceL(2000);
	}

	@Test
	public void 成功Test() {

		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	/////////// name
	@Test
	public void 名前successTest1() {

		updateItemForm.setName("あ");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前successTest2() {

		updateItemForm.setName("あいうえおかきくけこさしすせそたちつてとなにぬねのまみむめも");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 名前failureTest1() {

		updateItemForm.setName("あいうえおかきくけこさしすせそたちつてとなにぬねのまみむめもや");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
			assertEquals("商品名は30文字以内で入力してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void 名前failureTest2() {

		updateItemForm.setName("あいうえおかきくけこさしすせそたちつてとなにぬねのまみむめもやいゆえよらりるれろ");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
			assertEquals("商品名は30文字以内で入力してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void 名前failureTest3() {

		updateItemForm.setName("");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
			assertEquals("商品名を入力してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	/////////// description
	@Test
	public void 商品説明successTest1() {

		updateItemForm.setDescription("あ");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 商品説明successTest2() {

		updateItemForm.setDescription(
				"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 商品説明failureTest1() {

		updateItemForm.setDescription(
				"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおか");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
			assertEquals("商品説明は140文字以内で入力してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void 商品説明failureTest2() {

		updateItemForm.setDescription(
				"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおかきくけこさしすせそ");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Size, "エラーの種類が異なります。");
			assertEquals("商品説明は140文字以内で入力してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void 商品説明failureTest3() {

		updateItemForm.setDescription("");
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
			assertEquals("商品説明を入力してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	/////////// priceM
	@Test
	public void Mの価格failureTest() {

		updateItemForm.setPriceM(null);
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotNull, "エラーの種類が異なります。");
			assertEquals("Mサイズの価格を入力してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	/////////// priceL
	@Test
	public void Lの価格failureTest() {

		updateItemForm.setPriceM(null);
		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<UpdateItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotNull, "エラーの種類が異なります。");
			assertEquals("Mサイズの価格を入力してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void failureTest() {

		updateItemForm.setName("");
		updateItemForm.setDescription("");
		updateItemForm.setPriceM(null);
		updateItemForm.setPriceL(null);

		Set<ConstraintViolation<UpdateItemForm>> violations = validator.validate(updateItemForm);

		assertEquals(4, violations.size(), "エラーの数が異なります。");
	}

}
