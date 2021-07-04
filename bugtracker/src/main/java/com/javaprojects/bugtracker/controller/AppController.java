package com.javaprojects.bugtracker.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaprojects.bugtracker.entity.Bug;
import com.javaprojects.bugtracker.service.BugService;


@Controller
@RequestMapping("/bug-tracker")
public class AppController {
	
	private BugService bugService;
	
	public AppController(BugService bugService) {
		
		this.bugService = bugService;
	}
	
	//Redirect to index.html
	@GetMapping("/")
	public String getHomePage() {
		return "index";
	}
	
	/****************************************/
	// Add mapping for get list of bugs
	@GetMapping("/bugs")
	public String getListBug(Model theModel) {
		
		// Get list of bugs from db
		List<Bug> bugs = bugService.findAll();
		
		// Add the list to model
		theModel.addAttribute("bugs", bugs);
		
		return "view/bugs";  // view is sub-directory of templates
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
		// view is sub-directory of templates
	}
	
	// Get Update Form
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bugId") int theId,
			                         Model theModel){
		// Get the bug from service
		Bug theBug = bugService.findById(theId);
		
		// Set bug as a model attribute to pre-populate into the form
		theModel.addAttribute("bug", theBug);
		
		//  Send over to the bug-form created in add section
		return "view/bug-form"; // view is sub-directory of templates
	}
	
	
	// Processing to save bug
	@PostMapping("/save")
	public String saveBug(@ModelAttribute("bug") Bug theBug) {
		// data binding "bug" passed from bug-form
		
		// save the bug
		bugService.save(theBug);
		
		
		// use a redirect to prevent duplicate submission
		// Go straight back to the main page: bug-tracker/bugs
		// This is URL not a file
		return "redirect:bugs";
		
	}
	
	// Delete bug
	@GetMapping("/deleteBug")
	public String deleteBug(@RequestParam("bugId") int theId) {
		// Delete the bug
		bugService.deleteById(theId);
		
		// Redirect back to /bug-tracker/bugs
		return "redirect:bugs";
	}

}
