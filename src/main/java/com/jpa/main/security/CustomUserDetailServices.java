package com.jpa.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jpa.main.entity.UserAdmin;
import com.jpa.main.repository.UserAdminRepository;


public class CustomUserDetailServices implements UserDetailsService{
	
	@Autowired
	private UserAdminRepository userAdminRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserAdmin user = userAdminRepo.findByUsername(username);	
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetail(user);
	}

}
