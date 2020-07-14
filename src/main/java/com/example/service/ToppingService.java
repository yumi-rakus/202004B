package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Topping;
import com.example.repository.ToppingRepository;

/**
 * トッピング情報を操作するサービス.
 * 
 * @author yumi takahashi
 *
 */

@Service
@Transactional
public class ToppingService {

	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 
	 * トッピング情報を全件取得する.
	 * 
	 * @return トッピング情報全件
	 */
	public List<Topping> showToppingList() {
		return toppingRepository.findAll();
	}

}
