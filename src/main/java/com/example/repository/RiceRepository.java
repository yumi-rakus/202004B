package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Rice;

/**
 * ricesテーブルを操作するリポジトリ.
 * 
 * @author yumi takahashi
 *
 */
@Repository
public class RiceRepository {

	/**
	 * Riceオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Rice> RICE_ROW_MAPPER = (rs, i) -> {

		Rice rice = new Rice();

		rice.setId(rs.getInt("id"));
		rice.setName(rs.getString("name"));
		rice.setDescription(rs.getString("description"));
		rice.setImagePath(rs.getString("image_path"));
		rice.setDeleted(rs.getBoolean("deleted"));

		return rice;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * （削除フラグのたっていない）全米情報を取得する.
	 * 
	 * @return 米情報一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Rice> findAllNonDeleted() {

		String sql = "SELECT id, name, description, image_path, deleted FROM rices WHERE deleted = false ORDER BY id";

		SqlParameterSource param = new MapSqlParameterSource();

		List<Rice> riceList = template.query(sql, param, RICE_ROW_MAPPER);

		return riceList;
	}

	/**
	 * 米情報を取得する.
	 * 
	 * @param id 米ID
	 * @return 米情報
	 * 
	 * @author yumi takahashi
	 */
	public Rice load(Integer id) {

		String sql = "SELECT id, name, description, image_path, deleted FROM rices WHERE id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		List<Rice> rice = template.query(sql, param, RICE_ROW_MAPPER);

		return rice.get(0);
	}

}
