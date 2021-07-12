package com.javaprojects.bugtracker.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.javaprojects.bugtracker.customUser.CustomUser;
import com.javaprojects.bugtracker.entity.Employee;

public class UserServiceImpl implements UserService {

	
	//Need to inject user dao
	@Autowired
	private 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CustomUser customUser) {
		// TODO Auto-generated method stub

	}

}
