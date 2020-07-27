package com.example.form;

/**
 * 商品検索の際に使用するフォーム.
 * 
 * @author yumi takahashi
 *
 */
public class ItemSearchForm {

	private String id;

	private String searchName;

	// getter setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	// toString
	@Override
	public String toString() {
		return "ItemSearchForm [id=" + id + ", searchName=" + searchName + "]";
	}

}