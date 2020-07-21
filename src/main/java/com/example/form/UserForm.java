package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * ユーザ情報登録時に使用するフォーム.
 * 
 * @author yumi takahashi
 *
 */
public class UserForm {

	/** ID */
	private Integer id;

	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	@Size(min = 0, max = 50, message = "名前は50文字以内で入力してください")
	private String name;

	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が不正です")
	private String email;

	/** パスワード */
	@Size(min = 1, max = 16, message = "パスワードは８文字以上１６文字以内で設定してください")
	private String password;

	/** 郵便番号（上3桁） */
	@Pattern(regexp = "[0-9]{3}", message = "郵便番号（上3桁）：3桁で入力してください")
	private String zipcodeFirst;

	/** 郵便番号（上4桁） */
	@Pattern(regexp = "[0-9]{4}", message = "郵便番号（下4桁）：4桁で入力してください")
	private String zipcodeLast;

	/** 住所（都道府県市区町村） */
	@NotBlank(message = "住所(都道府県市区町村)を入力してください")
	private String addressFirst;

	/** 住所（番地以降） */
	@NotBlank(message = "住所（番地以降）を入力してください")
	private String addressLast;

	/** 電話番号 */
	@Size(min=10, max=12, message="電話番号を入力してください")
	private String telephone;

	/** 確認用パスワード */
	private String conpassword;

	// getter setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipcodeFirst() {
		return zipcodeFirst;
	}

	public void setZipcodeFirst(String zipcodeFirst) {
		this.zipcodeFirst = zipcodeFirst;
	}

	public String getZipcodeLast() {
		return zipcodeLast;
	}

	public void setZipcodeLast(String zipcodeLast) {
		this.zipcodeLast = zipcodeLast;
	}

	public String getAddressFirst() {
		return addressFirst;
	}

	public void setAddressFirst(String addressFirst) {
		this.addressFirst = addressFirst;
	}

	public String getAddressLast() {
		return addressLast;
	}

	public void setAddressLast(String addressLast) {
		this.addressLast = addressLast;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getConpassword() {
		return conpassword;
	}

	public void setConpassword(String conpassword) {
		this.conpassword = conpassword;
	}

	// toString
	@Override
	public String toString() {
		return "UserForm [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", zipcodeFirst=" + zipcodeFirst + ", zipcodeLast=" + zipcodeLast + ", addressFirst=" + addressFirst
				+ ", addressLast=" + addressLast + ", telephone=" + telephone + ", conpassword=" + conpassword + "]";
	}

}
