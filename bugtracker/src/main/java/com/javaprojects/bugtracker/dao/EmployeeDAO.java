package com.javaprojects.bugtracker.dao;

import java.util.List;

import com.javaprojects.bugtracker.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();
	
	// CRUD methods
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
	
	Employee findByUserName(String userName);
	
	

}
