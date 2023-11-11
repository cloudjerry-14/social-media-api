package com.api.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.socialmedia.dao.UserDao;
import com.api.socialmedia.entity.UserEntity;
import com.api.socialmedia.exception.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	//return a list of all Users
	public List<UserEntity> getAllUsers() {
		return (List<UserEntity>) userDao.findAll();
	}

	public UserEntity getUserById(Long id) {
		if(!userDao.existsById(id)) {
			throw new UserNotFoundException("id: "+id);
		}
		return userDao.findById(id).get();
	}

	public UserEntity saveUser(UserEntity user) {
		return userDao.save(user);
	}

	public void deleteUserById(Long id) {
		if(!userDao.existsById(id)) {
			throw new UserNotFoundException("id: "+id);
		}
		userDao.deleteById(id);
	}
	
}
