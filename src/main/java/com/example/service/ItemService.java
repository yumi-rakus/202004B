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

	/**
	 * 全商品情報を取得する.
	 * 
	 * @return 商品情報一覧
	 * 
	 * @author kohei eto
	 */
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	/**
	 * 商品の名前で曖昧検索する.
	 * 
	 * @param name 検索キー
	 * @return 検索された商品情報一覧
	 * 
	 * @author kohei eto
	 */
	public List<Item> findByItemName(String name) {
		return itemRepository.findByItemName(name);
	}

	/**
	 * 商品名の文字列を取得する.
	 * 
	 * @param itemList 商品情報リスト
	 * @return 商品名の文字列
	 * 
	 * @author kohei eto
	 */
	public StringBuilder getItemListForAutocomplete(List<Item> itemList) {
		StringBuilder itemListForAutocomplete = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			if (i != 0) {
				itemListForAutocomplete.append(",");
			}
			Item item = itemList.get(i);
			itemListForAutocomplete.append("\"");
			itemListForAutocomplete.append(item.getName());
			itemListForAutocomplete.append("\"");
		}
		return itemListForAutocomplete;
	}
}
