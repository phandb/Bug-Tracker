package com.javaprojects.bugtracker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// Add mapping for adding bug
	@GetMapping("/showFormForAdd")  //  This appears in URL
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Bug theBug = new Bug();
		
		// Thymeleaf template will access "new-bug" data for binding form data
		theModel.addAttribute("bug", theBug);
		
		// src/main/resources/templates/bug-tracker/bug-form.html
		return "view/bug-form";  // this is location of html file
	}
	
	// Processing to save bug
	@PostMapping("/save")
	public String saveBug(@ModelAttribute("bug") Bug theBug) {
		// data binding "bug" passed from bug-form
		
		// save the bug
		bugService.save(theBug);
		
		
		// use a redirect to prevent duplicate submission
		return "redirect:/bug-tracker/bugs";
		
	}
	

}
