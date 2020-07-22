package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
	 * 
	 * @author yumi takahashi
	 */
	public Item showDetail(Integer id) {
		return itemRepository.load(id);
	}

	/**
	 * (削除フラグのたっていない)全商品情報を取得する.
	 * 
	 * @return 商品情報一覧
	 * 
	 * @author kohei eto
	 */
	public List<Item> findAllNonDeleted() {
		return itemRepository.findAllNonDeleted();
	}

	/**
	 * 商品の名前で曖昧検索する.
	 * 
	 * @param name 検索キー
	 * @return 検索された商品情報一覧
	 * 
	 * @author kohei eto
	 */

	public Page<Item> showListPaging(int page, int size, List<Item> itemList) {
		// 表示させたいページ数を-1しなければうまく動かない
		page--;
		// どの商品から表示させるかと言うカウント値
		int startItemCount = page * size;
		// 絞り込んだ後の商品リストが入る変数
		List<Item> list = new ArrayList<>();

		if (itemList.size() < startItemCount) {
			// (ありえないが)もし表示させたい商品カウントがサイズよりも大きい場合は空のリストを返す list = Collections.emptyList();

		} else {
			// 該当ページに表示させる商品一覧を作成
			int toIndex = Math.min(startItemCount + size, itemList.size());
			list = itemList.subList(startItemCount, toIndex);
		}

		// 上記で作成した該当ページに表示させる商品一覧をページングできる形に変換して返す
		Page<Item> itemPage = new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());
		return itemPage;
	}

	public List<Item> findAllByPrice() {
		return itemRepository.findAllByPrice();
	}

	public List<Item> findAllByPrice2() {
		return itemRepository.findAllByPrice2();
	}

	public List<Item> findAllByPrice3() {
		return itemRepository.findAllByPrice3();
	}

	public List<Item> findByItemName(String searchName) {
		return itemRepository.findByItemName(searchName);
	}

	public List<Item> findByItemName2(String searchName) {
		return itemRepository.findByItemName2(searchName);
	}

	public List<Item> findByItemName3(String searchName) {
		return itemRepository.findByItemName3(searchName);
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

	/**
	 * 全商品情報を取得する.
	 * 
	 * @return 商品情報一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	/**
	 * 商品の削除フラグを更新する.
	 * 
	 * @param deleted 削除フラグ(true:商品一覧画面から除去、false:商品一覧画面に表示)
	 * @param itemId  商品ID
	 * 
	 * @author yumi takahashi
	 */
	public void updateDeleteFlag(Boolean deleted, Integer itemId) {
		itemRepository.updateDeleteFlag(deleted, itemId);
	}

	/**
	 * 商品情報を更新する.
	 * 
	 * @param item 商品情報
	 * 
	 * @author yumi takahashi
	 */
	public void updateItem(Item item) {
		itemRepository.updateItem(item);
	}

}
