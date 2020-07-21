package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * ユーザ登録をする.
	 * 
	 * @param user ユーザ情報
	 * 
	 * @author kohei eto
	 */
	public void insert(User user) {
		userRepository.insert(user);
	}

	/**
	 * ユーザIDからユーザ情報を取得する.
	 * 
	 * @return ユーザ情報
	 * @param id ユーザid
	 * @author shoya fujisawa
	 */
	public User getUserById(Integer id) {
		User user = userRepository.findById(id);
		String zipCode=user.getZipcode();
		String zipCodeFirst=zipCode.substring(0,3);
		String zipCodeLast=zipCode.substring(3);
		user.setZipcodefirst(zipCodeFirst);
		user.setZipcodelast(zipCodeLast);
		return user;

	}

	/**
	 * メールアドレスが既に登録されているかを判定する.
	 * 
	 * @param email メールアドレス
	 * @return 既に登録されているメールアドレスならtrueを、そうでないならfalseを返す
	 * 
	 * @author yumi takahashi
	 */
	public boolean existByEmail(String email) {
		return userRepository.existByEmail(email);
	}

	/**
	 * メールアドレスからユーザ情報を取得する.
	 * 
	 * @param email メールアドレス
	 * @return ユーザ情報
	 * 
	 * @author kohei eto
	 */
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
