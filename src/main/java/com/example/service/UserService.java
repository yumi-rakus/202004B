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

	// ユーザ情報を登録
	public void insert(User user) {
		String zip = user.getZipcode();
		String rezip = zip.replace("-", "");
		user.setZipcode(rezip);
		userRepository.insert(user);
	}

	/**
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
}
