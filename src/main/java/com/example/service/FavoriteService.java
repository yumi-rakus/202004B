package com.example.service;

import com.example.domain.Favorite;
import com.example.repository.FavoriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FavoriteService {

  @Autowired
  private FavoriteRepository favoriteRepository;

  public String create(Favorite favorite){
    favoriteRepository.save(favorite);
    return "success!!";
  } 

}