package com.javaprojects.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaprojects.bugtracker.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
	
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		
		// gwt the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Create a query using native Hibernate
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		
		// Execute the query and get result list
		List<Employee> employees = query.getResultList();
		
		// Return the results
		return employees;
	}

	@Override
	public Employee findById(int id) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		Employee employee = currentSession.get(Employee.class, id);
		
		//return the employee
		return employee;
	}

	@Override
	public void save(Employee employee) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// save the bug.  
		// method saveOrUpdate() means
		// if id=0 then save/insert;  otherwise update
		currentSession.saveOrUpdate(employee);
		
	}

	@Override
	public void deleteById(int id) {
		//gwt the current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete the employee with primary key
		Query query = currentSession.createQuery(
				"delete from Employee where id=:employeeId");
		
		query.setParameter("employeeId", id);
		
		query.executeUpdate();
		
	}

}
