package com.example.form;

/**
 * 商品検索の際に使用するフォーム.
 * 
 * @author yumi takahashi
 *
 */
public class ItemSearchForm {

	/**
	 * 並び替え方法のキー名
	 */
	private String id;

	/**
	 * 曖昧検索する文字列
	 */
	private String searchName;

	public ItemSearchForm() {
		this.id = "1";
		this.searchName = "";
	}

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