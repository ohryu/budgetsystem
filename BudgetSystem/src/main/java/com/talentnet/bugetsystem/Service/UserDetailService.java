package com.talentnet.bugetsystem.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.talentnet.bugetsystem.Entity.BUser;
import com.talentnet.bugetsystem.Repository.UserRepo;


@Service
@Transactional
public class UserDetailService implements UserDetailsService{
	@Autowired
	private UserRepo userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BUser user = this.userDAO.findByUsername(username);
		if (user == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

		System.out.println("Found User: " + user);
		// [ROLE_USER, ROLE_ADMIN,..]
		String roleNames = user.getRole().getRolename();

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(roleNames);
		grantList.add(authority);

		UserDetails userDetails = (UserDetails) new User(user.getUsername(), //
				user.getPassword().trim(), grantList);

		return userDetails;
	}
}
