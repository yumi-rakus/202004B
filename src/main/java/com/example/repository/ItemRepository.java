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
	 */
	public Item load(Integer id) {

		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);

		return item;
	}

	/**
	 * 全商品情報を取得する.
	 * 
	 * @return 商品情報一覧
	 * 
	 * @author kohei eto
	 */
	public List<Item> findAll() {

		String sql = "select id, name, description, price_m, price_l, image_path, deleted from items order by price_m";

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
	public List<Item> findByItemName(String name) {
		String sql = "select * from items where name like :name order by price_m";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;

	}

}
