package com.javaprojects.bugtracker.service;

import java.util.List;
import java.util.Set;

import com.javaprojects.bugtracker.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);

}
