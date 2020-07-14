package com.example.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商品をカートに入れる際にに使用するフォーム.
 * 
 * @author yumi takahashi
 *
 */
public class ItemForm {

	@NotBlank(message = "サイズを選択してください")
	private String size;

	private List<Integer> toppingIdList;

	@NotNull(message = "数量を選択してください")
	private Integer quantity;
	
	private Integer itemId;

	// getter setter
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Integer> getToppingIdList() {
		return toppingIdList;
	}

	public void setToppingIdList(List<Integer> toppingIdList) {
		this.toppingIdList = toppingIdList;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	

}
