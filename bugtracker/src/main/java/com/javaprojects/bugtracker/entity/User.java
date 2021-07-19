package com.javaprojects.bugtracker.entity;



import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="user")
public class User {
	
	// Define field
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
	@Column(name="first_name")	
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="position")
	private String position;
	
		
	@Column(name="username")
	private String userName;
	
	
	@Column(name="password")
	private String password;
	
	
	//@DateTimeFormat(pattern = "MM-dd-yyyy")
	
	
	

	// Configure Many to Many relationship with Bug class
	@ManyToMany(mappedBy="employees",
				fetch=FetchType.LAZY,
			    cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			    		  CascadeType.DETACH, CascadeType.REFRESH })
	private Set<Bug> bugs = new HashSet<>();
	
	// Configure many to many with role
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	// Define constructor
	
	public User() {
		
	}

	public User(String firstName, String lastName, String position, String userName, String password,
			Collection<Role> roles) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public User(String firstName, String lastName, String position, String userName, String password) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.userName = userName;
		this.password = password;
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

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public Set<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(Set<Bug> bugs) {
		this.bugs = bugs;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	// Add a convenience method
	public void addBugToEmployee(Bug bug) {
		if (bugs == null) {
			bugs = new HashSet<>();
		}
		
		bugs.add(bug);
	}

	
	// toString


	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", position=" + position
				+ ", username=" + userName + ", password=" + password + ", roles=" + roles + "]";
	}
	
	
	
	
	

}
