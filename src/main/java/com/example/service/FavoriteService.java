package com.example.service;

import java.util.List;

import com.example.domain.Favorite;
import com.example.repository.FavoriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FavoriteService {

  @Autowired
  private FavoriteRepository favoriteRepository;

  public boolean create(Favorite favorite) {
    return favoriteRepository.save(favorite);
  }

  public List<Favorite> showAll(Integer userId) {
    return favoriteRepository.findAllByUserId(userId);
  }

  public boolean delete(Favorite favorite) {
    return favoriteRepository.delete(favorite);
  }

  public String isRegistered(Integer userId, Integer itemId) {
    Integer count = favoriteRepository.countByUserIdAndItemId(userId, itemId);
    if (count == 1) {
      return "200"; // 存在する場合
    } else {
      return "400"; // 存在しない場合
    }
  }

}