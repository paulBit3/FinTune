package com.yetaidev.app.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yetaidev.model.Users;
import com.yetaidev.repository.UsersDao;

import lombok.RequiredArgsConstructor;

/***
 * This class defines our custom UserDetails class called UserDetailsImpl. 
 * This is the class whose instances will be returned from our custom UserDetailsService. 
 * Spring Security will use the information stored in the UserDetailsImpl 
 * object to perform authentication and authorization.
 * 
 */
@RequiredArgsConstructor
@Service
public class UserDetailsserviceImpl implements UserDetailsService {
	@Autowired
	private UsersDao mUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = mUserDao.getUserByEmail(email)
				.orElseThrow(() -> new RuntimeException(String.format("User does not exist, email: %s", email)));
		//calling CustomUserDetails static method here
		return CustomUserDetails.build(user);
	}

}
