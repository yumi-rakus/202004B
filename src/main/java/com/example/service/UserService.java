package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ユーザ情報を操作するサービス.
 * 
 * @author yumi takahahi
 *
 */

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	// ユーザ情報を登録
	public void insert(User user) {
		String zip = user.getZipcode();
		String rezip = zip.replace("-", "");
		user.setZipcode(rezip);
		userRepository.insert(user);
	}

	public User findByMail(String mail) {
		return userRepository.findByMail(mail);
	}
}
