package com.javaprojects.bugtracker.service;

import java.util.Set;

import com.javaprojects.bugtracker.entity.Employee;

public interface EmployeeService {
	
	public Set<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);

}
