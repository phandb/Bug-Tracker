package com.javaprojects.bugtracker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaprojects.bugtracker.entity.Bug;
import com.javaprojects.bugtracker.service.BugService;


@Controller
@RequestMapping("/bug-tracker")
public class AppController {
	
	private BugService bugService;
	
	public AppController(BugService bugService) {
		
		this.bugService = bugService;
	}
	
	/****************************************/
	// Add mapping for get list of bugs
	@GetMapping("/bugs")
	public String getListBug(Model theModel) {
		
		// Get list of bugs from db
		List<Bug> bugs = bugService.findAll();
		
		// Add the list to model
		theModel.addAttribute("bugs", bugs);
		
		return "view/bugs.html";
	}
	

}
