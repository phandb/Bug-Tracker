package com.javaprojects.bugtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaprojects.bugtracker.dao.EmployeeDAO;
import com.javaprojects.bugtracker.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		return employeeDAO.findAll();
	}



	@Override
	@Transactional
	public Employee findById(int id) {
		// Delegate to DAO
		return employeeDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		// Delegate to DAO
		employeeDAO.save(employee);
		
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// Delegate to DAO
		employeeDAO.deleteById(id);
		
	}

	

}
