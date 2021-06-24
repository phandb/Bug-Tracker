package com.javaprojects.bugtracker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	
	// Define field
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private int id;
	
	@Column(name="first_name")	
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="position")
	private String position;
	
	@Column(name="role")
	private String role;
	
	// Configure Many to Many relationship with Bug class
	@ManyToMany(fetch=FetchType.LAZY,
			    cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			    		  CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(
			name="bug_employee",
			joinColumns=@JoinColumn(name="employee_id"),
			inverseJoinColumns=@JoinColumn(name="bug_id")
			)
	private List<Bug> bugs;
	
	// Define constructor
	
	public Employee() {
		
	}

	public Employee(String firstName, String lastName, String position, String role) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.role = role;
	}
	
	// Getter Setter
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	public List<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}

	// Add a convenience method
	public void addBug(Bug bug) {
		if (bugs == null) {
			bugs = new ArrayList<>();
		}
		
		bugs.add(bug);
	}

	
	// toString


	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", position=" + position
				+ ", role=" + role + "]";
	}
	
	
	
	
	

}
