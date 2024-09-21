package com.yetaidev.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yetaidev.model.Users;
import com.yetaidev.repository.UsersDao;

import lombok.AllArgsConstructor;

/***
 * User Authentication service class iplementation
 */

@Service

public class AuthServiceImpl implements AuthService {
	@Autowired
	private UsersDao mUserDao;
	
	
	//create a new user
	@Override
	public Users newUser(Users newUser) {
		return mUserDao.save(newUser) ;	

	}
	
	

	//this method is for if user connect using email
	@Override
	public Optional<Users> getUserByEmail(String email) {
		return mUserDao.findByEmail(email);
	}
	
	
	//this method is for if user connect using username
	@Override
	public Optional<Users> getUserByUsername(String username) {
		return mUserDao.findByUsername(username);

		//return optionalName.get();
	}

	@Override
	public boolean hasUserWithUsername(String username) {
		return mUserDao.existsByUsername(username);
	}

	@Override
	public boolean hasUserWithEmail(String email) {
		return mUserDao.existsByEmail(email);
	}





	
	

}
