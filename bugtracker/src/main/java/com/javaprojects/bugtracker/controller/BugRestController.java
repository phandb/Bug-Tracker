package com.javaprojects.bugtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// add mapping for GET /bug/{bugId}
	@GetMapping("/bugs/{bugId}")
	public Bug getBug(@PathVariable int bugId) {
		
		Bug bug = bugService.findById(bugId);
		
		if (bug == null) {
			throw new RuntimeException("Bug id not found - " + bugId);
		}
		
		return bug;
	}
	
	// add mapping for POST /bugs  - add new bug
	@PostMapping("/bugs")
	public Bug addBug(@RequestBody Bug bug) {
		
		// Also just in case they pass an id in JSON... set id to 0
		// This is to force a save of new item... instead of update
		
		bug.setId(0);
		
		bugService.save(bug);
		
		return bug;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
