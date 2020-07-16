package com.example.controller;

import java.util.ArrayList;
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

	@RequestMapping("/findName")
	public List<String> findName(String searchName) {

		List<Item> itemList = itemService.findByName(searchName);

		//		List<String> nameList = new ArrayList<String>();
//		
//		for ( : nameList) {
//			nameList.add(itemList.get(i).getName());
//		}
	    
		List<String> nameList = itemList.stream().map(item -> item.getName()).collect(Collectors.toList());

		
		return nameList;
	}
}
