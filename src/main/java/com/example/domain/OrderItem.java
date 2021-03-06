package com.example.domain;

import java.util.List;

/**
 * 注文商品を表すドメイン.
 * 
 * @author yumi takahashi
 *
 */
public class OrderItem {

	/** ID */
	private Integer id;

	/** 商品ID */
	private Integer itemId;

	/** オーダーID */
	private Integer orderId;

	/** 数量 */
	private Integer quantity;

	/** サイズ */
	private Character size;

	/** 商品情報 */
	private Item item;

	/** トッピングリスト */
	private List<Topping> orderToppingList;

	// constructor
	public OrderItem() {

	}

	public OrderItem(Integer id, Integer itemId, Integer orderId, Integer quantity, Character size, Item item,
			List<Topping> orderToppingList) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.size = size;
		this.item = item;
		this.orderToppingList = orderToppingList;
	}

	// getter setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Topping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<Topping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	// toString
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	// method
	public int getSubTotal() {

		int subTotal = 0;

		return subTotal;
	}

}
