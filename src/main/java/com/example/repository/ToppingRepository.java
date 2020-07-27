package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.example.domain.Topping;

/**
 * toppingsテーブルを操作するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */

@Repository
public class ToppingRepository {

	/**
	 * Toppingオブジェクトを生成するRowMapper
	 */
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {

		Topping topping = new Topping();

		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));

		return topping;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * トッピング情報を全件取得する.
	 * 
	 * @return トッピング情報全件
	 * 
	 * @author yumi takahashi
	 */
	public List<Topping> findAll() {

		String sql = "SELECT id, name, price_m, price_l FROM toppings ORDER BY name";

		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);

		return toppingList;
	}

	/**
	 * トッピング情報を取得する.
	 * 
	 * @param id ID
	 * @return トッピング情報
	 * 
	 * @author yumi takahashi
	 */
	public Topping load(Integer id) {

		String sql = "SELECT id, name, price_m, price_l FROM toppings WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Topping topping = template.queryForObject(sql, param, TOPPING_ROW_MAPPER);

		return topping;
	}
}
