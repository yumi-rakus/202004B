package com.example.domain;

/**
 * ユーザ情報を表すドメイン.
 * 
 * @author yumi takahashi
 *
 */
public class User {

	/** ID */
	private Integer id;

	/** 名前 */
	private String name;

	/** Eメール */
	private String email;

	/** パスワード */
	private String password;

	/** 郵便番号 */
	private String zipcode;

	/** 郵便番号（上3桁） */
	private String zipcodefirst;

	/** 郵便番号（下4桁） */
	private String zipcodelast;

	/** 住所 */
	private String address;

	/** 電話番号 */
	private String telephone;

	/** 管理者権限 */
	private Boolean isAdmin;

	// constructor
	public User() {

	}

	public User(Integer id, String name, String email, String password, String zipcode, String zipcodefirst,
			String zipcodelast, String address, String telephone, Boolean isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.zipcode = zipcode;
		this.zipcodefirst = zipcodefirst;
		this.zipcodelast = zipcodelast;
		this.address = address;
		this.telephone = telephone;
		this.isAdmin = isAdmin;
	}

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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	// toString
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", zipcode="
				+ zipcode + ", zipcodefirst=" + zipcodefirst + ", zipcodelast=" + zipcodelast + ", address=" + address
				+ ", telephone=" + telephone + ", isAdmin=" + isAdmin + "]";
	}

}
