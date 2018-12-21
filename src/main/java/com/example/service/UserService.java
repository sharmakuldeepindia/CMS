package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> findActiveUser();
	public User findUserById(Long id);
	
	public User findUserByEmailAndPassword(String email, String password);
}
