package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Item;
import com.example.service.ItemService;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

	@Autowired
	private ItemService itemService;

	/**
	 * 検索において商品名オートコンプリートする.
	 * 
	 * @param searchName 検索キー
	 * @return 商品名リスト
	 * 
	 * @author kohei eto
	 */
	@RequestMapping("/findName")
	public List<String> findName(String searchName) {

		List<Item> itemList = itemService.findByItemName(searchName);
		List<String> nameList = itemList.stream().map(item -> item.getName()).collect(Collectors.toList());

		return nameList;
	}
}
