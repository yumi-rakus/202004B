package com.example.service;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

	/**
	 * 価格が安い順に並び替えられた商品一覧を取得する.
	 * 
	 * @return 商品一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Item> findAllByPrice() {

		List<Item> itemList = itemRepository.findAllNonDeleted();

		// Comparator作成
		Comparator<Item> comparator = Comparator.comparing(Item::getPriceM).thenComparing((str1, str2) -> {
			Collator collator = Collator.getInstance(Locale.JAPANESE);
			return collator.compare(str1.getName(), str2.getName());
		});
		// ソート処理
		List<Item> sorted = itemList.stream().sorted(comparator).collect(Collectors.toList());

		return sorted;
	}

	/**
	 * 価格が高い順に並び替えられた商品一覧を取得する.
	 * 
	 * @return 商品一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Item> findAllByPrice2() {

		List<Item> itemList = itemRepository.findAllNonDeleted();

		// Comparator作成
		Comparator<Item> comparator = Comparator.comparing(Item::getPriceM).reversed().thenComparing((str1, str2) -> {
			Collator collator = Collator.getInstance(Locale.JAPANESE);
			return collator.compare(str1.getName(), str2.getName());
		});
		// ソート処理
		List<Item> sorted = itemList.stream().sorted(comparator).collect(Collectors.toList());

		return sorted;
	}

	/**
	 * ID順に並び替えられた商品一覧を取得する.
	 * 
	 * @return 商品一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Item> findAllByPrice3() {
		return itemRepository.findAllNonDeleted();
	}

	/**
	 * 商品情報を商品名で曖昧検索し、検索された商品情報を取得する（価格が安い順）.
	 * 
	 * @param name 検索キー
	 * @return 検索された商品情報
	 * 
	 * @author yumi takahashi
	 */
	public List<Item> findByItemName(String searchName) {

		List<Item> itemList = itemRepository.findByItemName(searchName);
		// Comparator作成
		Comparator<Item> comparator = Comparator.comparing(Item::getPriceM).thenComparing((str1, str2) -> {
			Collator collator = Collator.getInstance(Locale.JAPANESE);
			return collator.compare(str1.getName(), str2.getName());
		});
		// ソート処理
		List<Item> sorted = itemList.stream().sorted(comparator).collect(Collectors.toList());

		return sorted;
	}

	/**
	 * 商品情報を商品名で曖昧検索し、検索された商品情報を取得する（価格が高い順）.
	 * 
	 * @param name 検索キー
	 * @return 検索された商品情報
	 * 
	 * @author yumi takahashi
	 */
	public List<Item> findByItemName2(String searchName) {

		List<Item> itemList = itemRepository.findByItemName(searchName);
		// Comparator作成
		Comparator<Item> comparator = Comparator.comparing(Item::getPriceM).reversed().thenComparing((str1, str2) -> {
			Collator collator = Collator.getInstance(Locale.JAPANESE);
			return collator.compare(str1.getName(), str2.getName());
		});
		// ソート処理
		List<Item> sorted = itemList.stream().sorted(comparator).collect(Collectors.toList());

		return sorted;
	}

	/**
	 * 商品情報を商品名で曖昧検索し、検索された商品情報を取得する（ID順）.
	 * 
	 * @param name 検索キー
	 * @return 検索された商品情報
	 * 
	 * @author yumi takahashi
	 */
	public List<Item> findByItemName3(String searchName) {
		return itemRepository.findByItemName(searchName);
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
