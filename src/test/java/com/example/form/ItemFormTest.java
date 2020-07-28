package com.example.form;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * ItemForm.javaのテスト.
 * 
 * @author yumi takahashi
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ItemFormTest {

	@Autowired
	private Validator validator;

	private ItemForm itemForm = new ItemForm();

	@BeforeEach
	public void 事前処理() throws Exception {

		validator = Validation.buildDefaultValidatorFactory().getValidator();

		itemForm.setSize("M");
		itemForm.setQuantity(3);
		itemForm.setRiceId(1);
	}

	@Test
	public void 成功Test() {

		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	/////////// size
	@Test
	public void サイズfailureTest() {

		itemForm.setSize("");
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<ItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotBlank, "エラーの種類が異なります。");
			assertEquals("サイズを選択してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	/////////// quantity
	@Test
	public void 数量successTest1() {

		itemForm.setQuantity(1);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 数量successTest2() {

		itemForm.setQuantity(7);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 数量successTest3() {

		itemForm.setQuantity(12);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 数量failureTest1() {

		itemForm.setQuantity(0);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<ItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
			assertEquals("範囲外の数量です", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void 数量failureTest2() {

		itemForm.setQuantity(13);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<ItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
			assertEquals("範囲外の数量です", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void 数量failureTest3() {

		itemForm.setQuantity(null);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<ItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotNull, "エラーの種類が異なります。");
			assertEquals("数量を選択してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	/////////// riceId
	@Test
	public void 米successTest1() {
		itemForm.setRiceId(1);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 米successTest2() {
		itemForm.setRiceId(16);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(0, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void 米failureTest1() {
		itemForm.setRiceId(0);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<ItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
			assertEquals("範囲外です", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void 米failureTest2() {
		itemForm.setRiceId(17);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<ItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof Range, "エラーの種類が異なります。");
			assertEquals("範囲外です", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void 米failureTest3() {
		itemForm.setRiceId(null);
		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(1, violations.size(), "エラーの数が異なります。");

		for (ConstraintViolation<ItemForm> v : violations) {
			assertTrue(v.getConstraintDescriptor().getAnnotation() instanceof NotNull, "エラーの種類が異なります。");
			assertEquals("ライスを選択してください", v.getMessage(), "エラー文が期待される結果と異なります。");
		}
	}

	@Test
	public void failureTest1() {

		itemForm.setSize("");
		itemForm.setQuantity(null);
		itemForm.setRiceId(null);

		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(3, violations.size(), "エラーの数が異なります。");
	}

	@Test
	public void failureTest2() {

		itemForm.setSize("");
		itemForm.setRiceId(null);

		Set<ConstraintViolation<ItemForm>> violations = validator.validate(itemForm);

		assertEquals(2, violations.size(), "エラーの数が異なります。");
	}
}
