package com.example.domain;

/**
 * 注文トッピング情報を表すドメイン.
 * 
 * @author yumi takahashi
 *
 */
public class OrderTopping {

	/** ID */
	private Integer id;

	/** トッピングID */
	private Integer toppingId;

	/** 注文商品ID */
	private Integer orderItemId;

	/** トッピング情報 */
	private Topping topping;

	// constructor
	public OrderTopping() {

	}

	public OrderTopping(Integer id, Integer toppingId, Integer orderItemId, Topping topping) {
		super();
		this.id = id;
		this.toppingId = toppingId;
		this.orderItemId = orderItemId;
		this.topping = topping;
	}

	// getter setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getToppingId() {
		return toppingId;
	}

	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

	// toString
	@Override
	public String toString() {
		return "OrderTopping [id=" + id + ", toppingId=" + toppingId + ", orderItemId=" + orderItemId + ", topping="
				+ topping + "]";
	}

}
