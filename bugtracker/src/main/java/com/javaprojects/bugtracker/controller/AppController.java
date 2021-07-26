package com.javaprojects.bugtracker.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaprojects.bugtracker.entity.Bug;
import com.javaprojects.bugtracker.entity.User;
import com.javaprojects.bugtracker.service.BugService;
import com.javaprojects.bugtracker.service.UserService;


@Controller
@RequestMapping("/bug-tracker")
public class AppController {
	
	private BugService bugService;
	
	private UserService userService;
	

	
	public AppController(BugService bugService, UserService userService) {
		
		this.bugService = bugService;
		this.userService = userService;
	}
	
	
	@GetMapping("/")
	public String showHome() {
		
		return "index";
	}
	
	/**************BUg CRUD**************************/
	// Add mapping for get list of bugs
	@GetMapping("/bugs")
	public String getBugs(Model theModel) {
		
		// Get list of bugs from db
		List<Bug> bugs = bugService.findAll();
		
		// Add the list to model
		theModel.addAttribute("bugs", bugs);
		
		return "view/bugs";  // view is sub-directory of templates
	}
	
	// Add mapping for adding bug
	@GetMapping("/bug/adding-form")  //  This appears in URL
	public String getBugAddingForm(Model theModel) {
		
		// create model attribute to bind form data
		Bug theBug = new Bug();
		
		// Thymeleaf template will access "new-bug" data for binding form data
		theModel.addAttribute("bug", theBug);
		
		// src/main/resources/templates/bug-tracker/bug-form.html
		return "view/bug-form";  // this is location of html file
		// view is sub-directory of templates
	}
	
	// Get Update Form
	@GetMapping("/bug/updating-form")
	public String getBugUpdatingForm(@RequestParam("bugId") int theId,
			                         Model theModel){
		// Get the bug from service
		Bug theBug = bugService.findById(theId);
		
		// Set the bug to a model attribute to pre-populate into the form
		theModel.addAttribute("bug", theBug);
		
		//  Send over to the bug-form created in add section
		return "view/bug-form"; // view is sub-directory of templates
	}
	
	
	// Processing to save bug
	@PostMapping("/bug/save")
	public String saveBug(@ModelAttribute("bug") Bug theBug) {
		// data binding "bug" passed from bug-form
		
		// save the bug
		bugService.save(theBug);
		
		
		// use a redirect to prevent duplicate submission
		// Go straight back to the main page: bug-tracker/bugs
		// This is URL not a file
		return "redirect:../bugs";
		
	}
	
	// Delete bug
	@GetMapping("/bug/delete")
	public String deleteBug(@RequestParam("bugId") int theId) {
		// Delete the bug
		bugService.deleteById(theId);
		
		// Redirect back to /bug-tracker/bugs
		return "redirect:../bugs";
	}
	
	/*********************User CRUD**********************************/
	// Get list of user
	@GetMapping("/users")
	public String getUsers(Model theModel) {
		
		// Get list of users from service
		List<User> users = userService.findAll();
		
		//Add the list of user to model attribute employee
		theModel.addAttribute("users", users);
		
		// return the list to users html
		return "view/admin/users.html";
		
	}
	
	// Adding employee form
	@GetMapping("/user/adding-form")
	public String getUserAddingForm(Model theModel) {
		// Create a model attribute to bind form data
		User user = new User();
		
		//Access data for binding from employee attribute
		theModel.addAttribute("user", user);
		
		//
		return "view/admin/user-form";
		
	}
	
	// Get update employee form
	@GetMapping("/user/updating-form")
	public String getUserUpdatingForm(@RequestParam("userId") int theId, Model theModel) {
		// Get the employee from service
		User user = userService.findById(theId);
		
		// Set the employee as a model attribute and populate it to a form
		theModel.addAttribute("user", user);
		
		//  Send over to the employee created in adding section
		return "view/admin/user-form"; // view/admin is sub-directory of templates
	}
	
	// Processing save user
	@PostMapping("/user/save")
	public String saveUser(@ModelAttribute("user") User user) {
		// "employee" data binding from the employee form
		

			
		//save employee info
		userService.save(user);
		
		// Use redirect to prevent duplicate submission
		//go to URL bug-tracker/users
		return "redirect:../users";
	}
	
	// Delete user
	@GetMapping("/user/delete")
	public String deleteUser(@RequestParam("userId") int theId) {
		// Delete the employee
		userService.deleteById(theId);
		
		//redirect back to /bug-tracker/users
		return "redirect:../users";
	}
}
