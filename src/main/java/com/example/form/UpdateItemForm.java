package com.example.form;

import javax.validation.constraints.*;

/**
 * 商品情報を更新する際に使用するフォーム.
 * 
 * @author yumi takahashi
 *
 */
public class UpdateItemForm {

	/** 商品名 */
	@NotBlank(message = "商品名を入力してください")
	@Size(min = 0, max = 30, message = "商品名は30文字以内で入力してください")
	private String name;

	/** 商品説明 */
	@NotBlank(message = "商品説明を入力してください")
	@Size(min = 0, max = 140, message = "商品説明は140文字以内で入力してください")
	private String description;

	/** Mの価格 */
	@NotNull(message = "Mサイズの価格を入力してください")
	private Integer priceM;

	/** Lの価格 */
	@NotNull(message = "Lサイズの価格を入力してください")
	private Integer priceL;

	// getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	// toString
	@Override
	public String toString() {
		return "UpdateItemForm [name=" + name + ", description=" + description + ", priceM=" + priceM + ", priceL="
				+ priceL + "]";
	}

}
