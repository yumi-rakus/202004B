package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品情報を操作するサービス.
 * 
 * @author yumi takahashi
 *
 */

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品情報を取得する.
	 * 
	 * @param id 商品ID
	 * @return 商品情報
	 */
	public Item showDetail(Integer id) {
		return itemRepository.load(id);
	}

	// 全商品情報を取得
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	// 商品の名前で曖昧検索
	public List<Item> findByItemName(String name) {
		return itemRepository.findByItmeName(name);
	}
}
