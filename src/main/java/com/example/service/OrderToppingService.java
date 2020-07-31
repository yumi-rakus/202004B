package com.example.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.domain.OrderTopping;
import com.example.repository.OrderToppingRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注文トッピング情報を操作するサービス.
 * 
 * @author yumi takahashi
 *
 */
@Service
@Transactional
public class OrderToppingService {

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	/**
	 * 注文トッピング情報をカートに入れる.
	 * 
	 * @param orderTopping 注文トッピング情報
	 * 
	 * @author yumi takahashi
	 */
	public void insertOrderTopping(OrderTopping orderTopping) {
		orderToppingRepository.insertOrderTopping(orderTopping);
	}

	/**
	 * 注文トッピングIDリストを取得する.
	 * 
	 * @param orderItemId 注文商品ID
	 * @return 注文トッピングIDリスト
	 * 
	 * @author yumi takahashi
	 */
	public List<Integer> getToppingIdListByOrderItemId(Integer orderItemId) {
		return orderToppingRepository.getToppingIdListByOrderItemId(orderItemId);
	}

}
