package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Rice;
import com.example.repository.RiceRepository;

/**
 * 米情報を操作するサービス.
 * 
 * @author yumi takahashi
 *
 */
@Service
@Transactional
public class RiceService {

	@Autowired
	private RiceRepository riceRepository;

	/**
	 * (削除フラグのたっていない)全米情報を取得する.
	 * 
	 * @return 米情報一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Rice> findAllNonDeleted() {
		return riceRepository.findAllNonDeleted();
	}

	/**
	 * 米情報を取得する.
	 * 
	 * @param id 米ID
	 * @return 米情報
	 * 
	 * @author yumi takahashi
	 */
	public Rice findById(Integer id) {
		return riceRepository.load(id);
	}

	/**
	 * 全米情報を取得する.
	 * 
	 * @return 米情報一覧
	 * 
	 * @author yumi takahashi
	 */
	public List<Rice> findAll() {
		return riceRepository.findAll();
	}

	/**
	 * 削除フラグを更新する.
	 * 
	 * @param deleted 削除フラグ
	 * @param riceId  米ID
	 * 
	 * @author yumi takahashi
	 */
	public void updateDeleteFlag(Boolean deleted, Integer riceId) {
		riceRepository.updateDeleteFlag(deleted, riceId);
	}
}
