package com.example.repository;

import java.util.List;

import com.example.domain.Favorite;
import com.example.domain.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    Item item = new Item();
    item.setId(rs.getInt("item_id"));
    item.setName(rs.getString("item_name"));
    item.setDescription(rs.getString("item_description"));
    item.setPriceM(rs.getInt("item_price_m"));
    item.setPriceL(rs.getInt("item_price_l"));
    item.setImagePath(rs.getString("item_image_path"));

    favorite.setItem(item);

    return favorite;
  };

  public boolean save(Favorite favorite) {
    String selectSql = "SELECT COUNT(*) FROM favorites WHERE user_id = :userId AND item_id = :itemId";
    SqlParameterSource selectParam = new MapSqlParameterSource().addValue("userId", favorite.getUserId()).addValue("itemId",
        favorite.getItemId());
    Integer count = template.queryForObject(selectSql, selectParam, Integer.class);

    if (count == 0) {
      String insertSql = "INSERT INTO favorites(user_id, item_id, added_date) VALUES(:userId, :itemId, :addedDate)";
      SqlParameterSource insertParam = new BeanPropertySqlParameterSource(favorite);
      template.update(insertSql, insertParam);
      return true;
    }
    return false;
  }

  public List<Favorite> findAllByUserId(Integer userId) {
    String sql = "SELECT fav.id AS id, fav.user_id AS user_id, fav.added_date AS added_date, ite.id AS item_id, ite.name AS item_name, ite.description AS item_description, ite.price_m AS item_price_m, ite.price_l AS item_price_l, ite.image_path AS item_image_path FROM favorites AS fav JOIN items AS ite ON  fav.item_id = ite.id WHERE fav.user_id = :userId ORDER BY fav.added_date";
    SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
    List<Favorite> favoriteList = template.query(sql, param, FAVORITE_ROW_MAPPER);
    return favoriteList;
  }

  public boolean delete(Favorite favorite) {
    String sql = "DELETE FROM favorites WHERE user_id = :userId AND item_id = :itemId";
    SqlParameterSource param = new MapSqlParameterSource().addValue("userId", favorite.getUserId()).addValue("itemId",
        favorite.getItemId());
    Integer count = template.update(sql, param);
    if(count == 1){
      return true;
    }
    return false;
  }

}