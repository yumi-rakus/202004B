package com.example.domain;

/**
 * 米情報を表すドメイン.
 * 
 * @author yumi takahashi
 *
 */
public class Rice {

	/** ID */
	private Integer id;

	/** 名前 */
	private String name;

	/** 説明 */
	private String description;

	/** 画像パス */
	private String imagePath;

	/** 削除フラグ */
	private Boolean deleted;

	// constructor
	public Rice() {

	}

	public Rice(Integer id, String name, String description, String imagePath, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
		this.deleted = deleted;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	// toString
	@Override
	public String toString() {
		return "Rice [id=" + id + ", name=" + name + ", description=" + description + ", imagePath=" + imagePath
				+ ", deleted=" + deleted + "]";
	}

}
