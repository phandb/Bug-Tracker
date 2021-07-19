package com.javaprojects.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaprojects.bugtracker.entity.User;
import com.javaprojects.bugtracker.service.UserService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private UserService employeeService;
	
	@Autowired
	public EmployeeRestController(UserService employeeService) {
		
		this.employeeService = employeeService;
	}
	
	/*************************************************************/
	
	// expose /employees and return list of employees
	@GetMapping("/employees")
	public List<User> findAll() {
		return employeeService.findAll();
	}
	
	// add mapping for /employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public User getEmployee(@PathVariable int employeeId) {
		User employee = employeeService.findById(employeeId);
		if (employee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		return employee;
	}
	
	// Map for adding new employee
	@PostMapping("/employees")
	public User addEmployee(@RequestBody User employee) {
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}
	
	//  Map for updating employee info
	@PutMapping("/employees")
	public User updateEmployee(@RequestBody  User employee) {
		
		employeeService.save(employee);
		
		return employee;
	}
	
	// Map for deleting employee
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		User tempEmployee = employeeService.findById(employeeId);
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
			
		}
		employeeService.deleteById(employeeId);
		return "Delete employee id - " + employeeId;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
