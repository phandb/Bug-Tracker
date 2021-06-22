package com.javaprojects.bugtracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaprojects.bugtracker.dao.BugDAO;
import com.javaprojects.bugtracker.entity.Bug;

@RestController
@RequestMapping("/api")
public class BugRestController {
	
	

	private BugDAO bugDAO;
	
	// inject bug dao without using a service layer
	public BugRestController(BugDAO bugDAO) {
		
		this.bugDAO = bugDAO;
	}
	
	// expose "/bugs" and return list of bugs
	@GetMapping("/bugs")
	public List<Bug> findAll() {
		return bugDAO.findAll();
	}

}
