package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * itemsテーブルを操作するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */
@Repository
public class ItemRepository {

	/**
	 * Itemオブジェクトを生成するRowMapper
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {

		Item item = new Item();

		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));

		return item;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 商品情報を取得する.
	 * 
	 * @param id 商品ID
	 * @return 商品情報
	 * 
	 * @author yumi takahashi
	 */
	public Item load(Integer id) {

		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);

		return item;
	}

	/**
	 * (削除フラグのたっていない)全商品情報を取得する.
	 * 
	 * @return 商品情報一覧
	 * 
	 * @author kohei eto
	 */
	public List<Item> findAllNonDeleted() {

		String sql = "select id, name, description, price_m, price_l, image_path, deleted from items where deleted = false order by price_m";

		List<Item> itemlist = template.query(sql, ITEM_ROW_MAPPER);

		return itemlist;
	}

	/**
	 * 商品情報を商品名で曖昧検索し、検索された商品情報を取得する.
	 * 
	 * @param name 検索キー
	 * @return 検索された商品情報
	 * 
	 * @author kohei eto
	 */

	// あいまい検索で価格安い順に取得
	public List<Item> findByItemName(String name) {
		String sql = "select * from items where name like :name and deleted = false order by price_m";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;

	}

	// あいまい検索で価格高い順に取得
	public List<Item> findByItemName2(String name) {
		String sql = "select * from items where name like :name and deleted = false  order by price_m desc";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;
	}

	// あいまい検索でid順で取得
	public List<Item> findByItemName3(String name) {
		String sql = "select * from items where name like :name and deleted = false  order by id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;
	}

	// 全商品情報を価格安い順で取得
	public List<Item> findAllByPrice() {

		String sql = "select id, name, description, price_m, price_l, image_path, deleted from items where deleted = false order by price_m, name";

		List<Item> itemlist = template.query(sql, ITEM_ROW_MAPPER);

		return itemlist;
	}

	// 全商品情報を価格高い順で取得
	public List<Item> findAllByPrice2() {

		String sql = "select id, name, description, price_m, price_l, image_path, deleted from items where deleted = false order by price_m desc, name";

		List<Item> itemlist = template.query(sql, ITEM_ROW_MAPPER);

		return itemlist;
	}

	// 全商品情報をid順で取得
	public List<Item> findAllByPrice3() {

		String sql = "select id, name, description, price_m, price_l, image_path, deleted from items where deleted = false order by id, name";

		List<Item> itemlist = template.query(sql, ITEM_ROW_MAPPER);

		return itemlist;
	}

	/**
	 * 全商品情報を取得する.
	 * 
	 * @return 商品情報一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Item> findAll() {

		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY id";

		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);

		return itemList;
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

		String sql = "UPDATE items SET deleted = :deleted WHERE id = :itemId";

		SqlParameterSource param = new MapSqlParameterSource().addValue("deleted", deleted).addValue("itemId", itemId);

		template.update(sql, param);
	}

	/**
	 * 商品情報を更新する.
	 * 
	 * @param item 商品情報
	 * 
	 * @author yumi takahashi
	 */
	public void updateItem(Item item) {

		String sql = "UPDATE items SET name = :name, description = :description, price_m = :priceM, price_l = :priceL WHERE id = :itemId;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", item.getName())
				.addValue("description", item.getDescription()).addValue("priceM", item.getPriceM())
				.addValue("priceL", item.getPriceL()).addValue("itemId", item.getId());

		template.update(sql, param);
	}
}
