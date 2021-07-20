package com.javaprojects.bugtracker.entity;

import java.util.Date;
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
@Table(name="bug")
public class Bug {
	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bug_id")
	private int id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="issued_on")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date issuedOn;
	

	
	@Column(name="status")
	private String status;
	
	@Column(name="description")
	private String description;
	
	@Column(name="tips")
	private String tips;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="solved_by")
	private String solvedBy;
	
	// private Set<Employee> createdBy = new HashSet<>();
	
	// private Set<Employee> solvedBy = new HashSet<>();
	
	// Configure Many to Many relationship with Employee class
	@ManyToMany(fetch=FetchType.LAZY,
				    cascade= {CascadeType.PERSIST, CascadeType.MERGE,
				    		  CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(
				name="bugs_users",
				joinColumns=@JoinColumn(name="bug_id"),
				inverseJoinColumns=@JoinColumn(name="user_id")
				)
	private Set<User> users = new HashSet<>();
	
	
	
	
	// define constructors
	// No-argument constructor required by Hibernate
	public Bug() {
		
	}


	public Bug(String type, Date issuedOn,  String status, String description, String tips) {

		this.type = type;
		this.issuedOn = issuedOn;
		this.status = status;
		this.description = description;
		this.tips = tips;
		
	}


	
	// define getter/setter

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getIssuedOn() {
		return issuedOn;
	}


	public void setIssuedOn(Date issuedOn) {
		this.issuedOn = issuedOn;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getTips() {
		return tips;
	}


	public void setTips(String tips) {
		this.tips = tips;
	}
	
	

/*
	
	public Set<Employee> getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Set<Employee> createdBy) {
		this.createdBy = createdBy;
	}


	public Set<Employee> getSolvedBy() {
		return solvedBy;
	}


	public void setSolvedBy(Set<Employee> solvedBy) {
		this.solvedBy = solvedBy;
	}
	
	*/


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getSolvedBy() {
		return solvedBy;
	}


	public void setSolvedBy(String solvedBy) {
		this.solvedBy = solvedBy;
	}


	


	


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	// Add a convenience method
	public void addEmployeeToBug(User user) {
			if (users == null) {
				users = new HashSet<>();
			}
			
			users.add(user);
		}
	
	

		


	@Override
	public String toString() {
		return "Bug [id=" + id + ", type=" + type + ", issuedOn=" + issuedOn + ",  status="
				+ status + ", description=" + description + ", tips=" + tips  + "]";
	}
	
	
	
}
