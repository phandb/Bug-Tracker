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
	private Date issuedOn;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="status")
	private String status;
	
	@Column(name="description")
	private String description;
	
	@Column(name="tips")
	private String tips;
	
	@Column(name="solved_by")
	private String solvedBy;
	
	// Configure Many to Many relationship with Employee class
	@ManyToMany(fetch=FetchType.EAGER,
				    cascade= {CascadeType.PERSIST, CascadeType.MERGE,
				    		  CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(
				name="bug_employee",
				joinColumns=@JoinColumn(name="bug_id"),
				inverseJoinColumns=@JoinColumn(name="employee_id")
				)
	private Set<Employee> employees = new HashSet<>();; 
	
	// define constructors
	// No-argument constructor required by Hibernate
	public Bug() {
		
	}


	public Bug(String type, Date issuedOn, String createdBy, String status, String description, String tips,
			String solvedBy) {

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


	

	
	public Set<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	// Add a convenience method
		public void addEmployeeToBug(Employee employee) {
			if (employees == null) {
				employees = new HashSet<>();
			}
			
			employees.add(employee);
		}

		


	@Override
	public String toString() {
		return "Bug [id=" + id + ", type=" + type + ", issuedOn=" + issuedOn + ",  status="
				+ status + ", description=" + description + ", tips=" + tips  + "]";
	}
	
	
	
}
