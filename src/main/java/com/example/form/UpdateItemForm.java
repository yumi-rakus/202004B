package com.example.form;

import javax.validation.constraints.*;

public class UpdateItemForm {

	@NotBlank(message = "商品名を入力してください")
	@Size(min = 0, max = 30, message = "商品名は30文字以内で入力してください")
	private String name;

	@NotBlank(message = "商品説明を入力してください")
	@Size(min = 0, max = 140, message = "商品説明は140文字以内で入力してください")
	private String description;

	@NotNull(message = "Mサイズの価格を入力してください")
	private Integer priceM;

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
