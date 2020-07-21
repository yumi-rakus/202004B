package com.example.domain;

import java.util.Date;

public class Favorite {

  private Integer id;

  private Integer userId;

  private Integer itemId;

  private Date addedDate;

  private Item item;

  public Favorite() {
  }

  public Favorite(Integer userId, Integer itemId, Date addedDate) {
    this.userId = userId;
    this.itemId = itemId;
    this.addedDate = addedDate;
  }

  public Favorite(Integer id, Integer userId, Integer itemId, Date addedDate, Item item) {
    this.id = id;
    this.userId = userId;
    this.itemId = itemId;
    this.addedDate = addedDate;
    this.item = item;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return this.userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getItemId() {
    return this.itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public Date getAddedDate() {
    return this.addedDate;
  }

  public void setAddedDate(Date addedDate) {
    this.addedDate = addedDate;
  }

  public Item getItem() {
    return this.item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", userId='" + getUserId() + "'" + ", itemId='" + getItemId() + "'"
        + ", addedDate='" + getAddedDate() + "'" + "}";
  }

}