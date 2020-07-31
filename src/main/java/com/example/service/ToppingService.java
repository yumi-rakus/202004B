package com.example.service;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
	 * 
	 * @author yumi takahashi
	 */
	public List<Topping> showToppingList() {

		List<Topping> toppingList = toppingRepository.findAll();

		List<Topping> sorted = toppingList.stream().sorted((str1, str2) -> {
			Collator collator = Collator.getInstance(Locale.JAPANESE);
			return collator.compare(str1.getName(), str2.getName());
		}).collect(Collectors.toList());

		return sorted;
	}

	/**
	 * トッピング情報を取得する.
	 * 
	 * @param id ID
	 * @return トッピング情報
	 * 
	 * @author yumi takahashi
	 */
	public Topping findById(Integer id) {
		return toppingRepository.load(id);
	}

}
