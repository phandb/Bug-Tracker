package com.javaprojects.bugtracker.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.javaprojects.bugtracker.customUser.CustomUser;
import com.javaprojects.bugtracker.entity.Employee;

public interface EmployeeService extends UserDetailsService {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
	
	public Employee findByUserName(String userName);
	
	void saveCustomUser(CustomUser customUser);

}
