package com.javaprojects.bugtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login-page")
	public String getLoginPage() {
		
		return "login";
	}
	
	// add request mapping for /register
	@GetMapping("/register")
	public String getRegisterPage() {
		
		return "register";
	}
	
	// add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String getAccessDenied() {
		return "access-denied";
	}
}
