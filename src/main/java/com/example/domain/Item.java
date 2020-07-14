package com.example.domain;

import java.util.List;

/**
 * アイテム情報を表すドメイン.
 * 
 * @author yumi takahashi
 *
 */
public class Item {

	/** ID */
	private Integer id;

	/** 名前 */
	private String name;

	/** 説明 */
	private String description;

	/** Mの価格 */
	private Integer priceM;

	/** Lの価格 */
	private Integer priceL;

	/** 画像パス */
	private String imagePath;

	/** 削除フラグ */
	private boolean deleted;

	/** トッピングリスト */
	private List<Topping> toppingList;

	// constructor
	public Item() {

	}

	public Item(Integer id, String name, String description, Integer priceM, Integer priceL, String imagePath,
			boolean deleted, List<Topping> toppingList) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.priceM = priceM;
		this.priceL = priceL;
		this.imagePath = imagePath;
		this.deleted = deleted;
		this.toppingList = toppingList;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Topping> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

	// toString
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", priceM=" + priceM + ", priceL="
				+ priceL + ", imagePath=" + imagePath + ", deleted=" + deleted + ", toppingList=" + toppingList + "]";
	}

}
