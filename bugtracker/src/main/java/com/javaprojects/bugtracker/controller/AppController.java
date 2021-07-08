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
import com.javaprojects.bugtracker.entity.Employee;
import com.javaprojects.bugtracker.service.BugService;
import com.javaprojects.bugtracker.service.EmployeeService;


@Controller
@RequestMapping("/bug-tracker")
public class AppController {
	
	private BugService bugService;
	
	private EmployeeService employeeService;
	
	public AppController(BugService bugService, EmployeeService employeeService) {
		
		this.bugService = bugService;
		this.employeeService = employeeService;
	}
	
	//Redirect to index.html
	@GetMapping("/")
	public String getHomePage() {
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
	@GetMapping("/bug-adding-form")  //  This appears in URL
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
	@GetMapping("/bug-updating-form")
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
	@PostMapping("/bug-save")
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
	@GetMapping("/bug-delete")
	public String deleteBug(@RequestParam("bugId") int theId) {
		// Delete the bug
		bugService.deleteById(theId);
		
		// Redirect back to /bug-tracker/bugs
		return "redirect:bugs";
	}
	
	/*********************Employee CRUD**********************************/
	// Get list of employee
	@GetMapping("/employees")
	public String getEmployees(Model theModel) {
		
		// Get list of employees from service
		List<Employee> employees = employeeService.findAll();
		
		//Add the list of employee to model attribute
		theModel.addAttribute("employees", employees);
		
		return "view/admin/employees.html";
		
	}
	
	// Adding employee form
	@GetMapping("/employee-adding-form")
	public String getEmployeeAddingForm(Model theModel) {
		// Create a model attribute to bind form data
		Employee employee = new Employee();
		
		//Access data for binding from employee attribute
		theModel.addAttribute("employee", employee);
		
		//
		return "view/admin/employee-form";
		
	}
	
	// Get update employee form
	@GetMapping("/employee-updating-form")
	public String getEmployeeUpdatingForm(@RequestParam("employeeId") int theId, Model theModel) {
		// Get the employee from service
		Employee employee = employeeService.findById(theId);
		
		// Set the employee as a model attribute and populate it to a form
		theModel.addAttribute("employee", employee);
		
		//  Send over to the employee created in adding section
		return "view/admin/employee-form"; // view/admin is sub-directory of templates
	}
	
	// Processing save employee
	@PostMapping("/employee-save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// "employee" data binding from the employee form
		
		//save employee info
		employeeService.save(employee);
		
		// Use redirect to prevent duplicate submission
		//go to URL bug-tracker/employees
		return "redirect:employees";
	}
	
	// Delete employee
	@GetMapping("/employee-delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		// Delete the employee
		employeeService.deleteById(theId);
		
		//redirect back to /bug-tracker/employees
		return "redirect:employees";
	}
}
