package com.javaprojects.bugtracker.dao;

import java.util.List;

import com.javaprojects.bugtracker.entity.User;

public interface UserDao {
	
	public List<User> findAll();
	
	// CRUD methods
	public User findById(int id);
	
	public void save(User employee);
	
	public void deleteById(int id);
	
	public User findByUserName(String userName);
	
	

}
