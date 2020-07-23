package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.OrderRice;
import com.example.repository.OrderRiceRepository;

/**
 * 注文米情報を操作するサービス.
 * 
 * @author yumi takahashi
 *
 */
@Service
@Transactional
public class OrderRiceService {

	@Autowired
	private OrderRiceRepository orderRiceRepository;

	/**
	 * 注文米情報をカートに入れる.
	 * 
	 * @param orderRice 注文米情報
	 * 
	 * @author yumi takahashi
	 */
	public void insertOrderRice(OrderRice orderRice) {
		orderRiceRepository.insertOrderRice(orderRice);
	}

	/**
	 * 注文IDから米IDを取得する.
	 * 
	 * @param orderItemId 注文ID
	 * @return 米ID（ない場合は0を返す）
	 * 
	 * @author yumi takahashi
	 */
	public Integer getRiceIdByOrderItemId(Integer orderItemId) {
		return orderRiceRepository.getRiceIdByOrderItemId(orderItemId);
	}

}
