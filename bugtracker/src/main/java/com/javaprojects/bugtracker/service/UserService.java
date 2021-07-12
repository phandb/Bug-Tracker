package com.javaprojects.bugtracker.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.javaprojects.bugtracker.customUser.CustomUser;
import com.javaprojects.bugtracker.entity.Employee;

public interface UserService extends UserDetailsService {

	Employee findByUserName(String userName);
	
	void save(CustomUser customUser);
}
