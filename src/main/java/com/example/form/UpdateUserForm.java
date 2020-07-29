package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * ゆーあ情報更新時に使用するフォーム
 * 
 * @author shoya
 *
 */
public class UpdateUserForm {
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
	@Size(min = 8, max = 16, message = "パスワードは８文字以上１６文字以内で設定してください")
	private String password;

	/** 郵便番号（上3桁） */
	@Pattern(regexp = "[0-9]{3}", message = "郵便番号（上3桁）：3桁で入力してください")
	private String zipcodefirst;

	/** 郵便番号（上4桁） */
	@Pattern(regexp = "[0-9]{4}", message = "郵便番号（下4桁）：4桁で入力してください")
	private String zipcodelast;
	
	/** 住所　*/
	@NotBlank(message = "住所を入力してください")
	private String address;

	/** 電話番号 */
	@Size(min=10, max=11, message="電話番号を入力してください")
	private String telephone;
	
	
	/** 確認用パスワード */
	private String conpassword;


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
					+ ", zipcodefirst=" + zipcodefirst + ", zipcodelast=" + zipcodelast + ", address=" + address
					+ ", telephone=" + telephone + ", conpassword=" + conpassword + "]";
		}
}
