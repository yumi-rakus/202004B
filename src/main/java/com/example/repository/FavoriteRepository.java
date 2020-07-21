package com.example.repository;

import com.example.domain.Favorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteRepository {

  @Autowired
	private NamedParameterJdbcTemplate template;

  private static final RowMapper<Favorite> FAVORITE_ROW_MAPPER = (rs, i) -> {
		Favorite favorite = new Favorite();
		favorite.setId(rs.getInt("id"));
		favorite.setUserId(rs.getInt("user_id"));
		favorite.setItemId(rs.getInt("item_id"));
		favorite.setAddedDate(rs.getDate("added_date"));
    return favorite;
  };
  
  public void save(Favorite favorite){
    String sql = "INSERT INTO favorites(user_id, item_id, added_date) VALUES(:userId, :itemId, :addedDate)";
    SqlParameterSource param = new BeanPropertySqlParameterSource(favorite);
    template.update(sql, param);
  }

}           