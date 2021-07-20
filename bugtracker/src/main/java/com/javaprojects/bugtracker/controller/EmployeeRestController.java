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
	
	private UserService userService;
	
	@Autowired
	public EmployeeRestController(UserService userService) {
		
		this.userService = userService;
	}
	
	/*************************************************************/
	
	// expose /employees and return list of employees
	@GetMapping("/users")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	// add mapping for /employees/{employeeId}
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		User user = userService.findById(userId);
		if (user == null) {
			throw new RuntimeException("Employee id not found - " + userId);
		}
		return user;
	}
	
	// Map for adding new employee
	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		user.setId(0);
		userService.save(user);
		return user;
	}
	
	//  Map for updating employee info
	@PutMapping("/users")
	public User updateUser(@RequestBody  User user) {
		
		userService.save(user);
		
		return user;
	}
	
	// Map for deleting employee
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId) {
		User tempUser = userService.findById(userId);
		if (tempUser == null) {
			throw new RuntimeException("Employee id not found - " + userId);
			
		}
		userService.deleteById(userId);
		return "Delete employee id - " + userId;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
