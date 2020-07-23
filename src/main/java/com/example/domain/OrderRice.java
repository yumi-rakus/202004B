package com.example.domain;

/**
 * 注文米情報を表すドメイン.
 * 
 * @author yumi takahashi
 *
 */
public class OrderRice {

	/** ID */
	private Integer id;

	/** 米ID */
	private Integer riceId;

	/** 注文商品ID */
	private Integer orderItemId;

	/** 米情報 */
	private Rice rice;

	// constructor
	public OrderRice() {

	}

	public OrderRice(Integer id, Integer riceId, Integer orderItemId, Rice rice) {
		super();
		this.id = id;
		this.riceId = riceId;
		this.orderItemId = orderItemId;
		this.rice = rice;
	}

	// getter setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRiceId() {
		return riceId;
	}

	public void setRiceId(Integer riceId) {
		this.riceId = riceId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Rice getRice() {
		return rice;
	}

	public void setRice(Rice rice) {
		this.rice = rice;
	}

	// toString
	@Override
	public String toString() {
		return "OrderRice [id=" + id + ", riceId=" + riceId + ", orderItemId=" + orderItemId + ", rice=" + rice + "]";
	}

}
