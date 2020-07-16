package com.example.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.domain.OrderTopping;
import com.example.repository.OrderToppingRepository;

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

}
