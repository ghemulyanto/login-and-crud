package com.trim.login.crud.service;

import com.trim.login.crud.model.User;

public interface UserService {
	
	public User findUserByEmail(String email);
	public User findUserByUsername(String username);
	public void saveUser(User user);

}
