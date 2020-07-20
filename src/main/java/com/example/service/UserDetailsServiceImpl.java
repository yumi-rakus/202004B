package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private HttpSession session;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String) DBから検索をし、ログイン情報を構成して返す。
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません。");
		} else {

			// ログインーログアウトでカートの中身を統合する処理
			if (Objects.nonNull((Integer) session.getAttribute("userId"))) {

				Integer uuid = (Integer) session.getAttribute("userId");
				List<Order> order = orderService.getOrderListByUserIdAndStatus0(user.getId());

				if (!order.isEmpty()) {

					if (orderService.status0ExistByUserId(uuid)) {

						Integer uuidOrderId = orderService.getOrderIdByUserId(uuid);
						Integer userOrderId = orderService.getOrderIdByUserId(user.getId());

						orderService.updateOrderId(uuidOrderId, userOrderId);
						orderService.deleteUuidRecordByUuid(uuid);

						

						if (!(order.get(0).getOrderItemList().get(0).getItem().getId() == 0)) {

							Integer totalPrice = order.get(0).getCalcTotalPrice();
							orderService.updateTotalPriceByUserId(user.getId(), totalPrice);
						}

					}
				} else {

					orderService.updateUserId(user.getId(), uuid);
				}
			}

			session.setAttribute("userId", user.getId());
		}
		// 権限付与の例
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER")); // ユーザ権限付与
		// if(member.isAdmin()) {
		// authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 管理者権限付与
		// }
		return new LoginUser(user, authorityList);
	}
}