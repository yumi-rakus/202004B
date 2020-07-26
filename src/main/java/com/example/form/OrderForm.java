package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrderForm {
	private Integer userId;

	private Integer status;

	private Integer totalPrice;
	@NotBlank(message = "配達日時を入力してください")
	private String orderDate;

	@NotBlank(message = "名前を入力してください")
	@Size(max = 20, message = "20文字以内で入力してください")
	private String name;
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が不正です")
	private String email;
	@Pattern(regexp = "^[0-9]{3}", message = "郵便番号（上3桁）:3桁で入力してください")
	private String zipcodefirst;
	@Pattern(regexp = "^[0-9]{4}$", message = "郵便番号（下4桁）:4桁で入力してください")
	private String zipcodelast;
	@NotBlank(message = "住所を入力してください")
	private String address;
	@NotBlank(message = "配達日時を入力してください")
	private String time;
	@Size(min=10, max=12, message="電話番号を入力してください")
	private String telephone;
	@NotNull(message = "支払い方法を選択してください")
	private Integer paymentMethod;
	
	private Integer discountPrice;

	private Integer usedPoints;
	// getter setter
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcodefirst() {
		return zipcodefirst;
	}

	public void setZipcodefirst(String zipcodefirst) {
		this.zipcodefirst = zipcodefirst;
	}

	public String getZipcodelast() {
		return zipcodelast;
	}

	public void setZipcodelast(String zipcodelast) {
		this.zipcodelast = zipcodelast;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getUsedPoints() {
		return usedPoints;
	}

	public void setUsedPoints(Integer usedPoints) {
		this.usedPoints = usedPoints;
	}

	// toString
	@Override
	public String toString() {
		return "OrderForm [userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice + ", orderDate="
				+ orderDate + ", name=" + name + ", mailAddress=" + email + ", zipCodeFirst=" + zipcodefirst + ", zipCodeLast=" + zipcodelast + ", address="
				+ address + ", time=" + time + ", telephone=" + telephone + ", paymentMethod=" + paymentMethod + "]";
	}

}
