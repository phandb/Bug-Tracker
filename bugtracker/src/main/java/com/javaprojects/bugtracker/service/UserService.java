package com.javaprojects.bugtracker.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.javaprojects.bugtracker.customUser.CustomUser;
import com.javaprojects.bugtracker.entity.User;

public interface UserService extends UserDetailsService {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User user);
	
	public void deleteById(int id);
	
	public User findByUserName(String userName);
	
	public void saveCustomUser(CustomUser customUser);

	public void addRoleAdminToEmployee(int id);

}
