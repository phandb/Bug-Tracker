package com.javaprojects.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaprojects.bugtracker.dao.BugDAO;
import com.javaprojects.bugtracker.entity.Bug;
import com.javaprojects.bugtracker.service.BugService;

@RestController
@RequestMapping("/api")
public class BugRestController {
	
	

	//private BugDAO bugDAO;
	private BugService bugService;
	
	// inject bug dao without using a service layer
	/*
	 * @Autowired
	 * public BugRestController(BugDAO bugDAO) {
		
		this.bugDAO = bugDAO;
	}
	*/
	
	@Autowired
	public BugRestController(BugService bugService) {
		
		this.bugService = bugService;
	}
	
	// expose "/bugs" and return list of bugs
	@GetMapping("/bugs")
	public List<Bug> findAll() {
		return bugService.findAll();
	}

}
