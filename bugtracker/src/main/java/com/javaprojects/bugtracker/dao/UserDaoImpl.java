package com.javaprojects.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaprojects.bugtracker.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	

	private EntityManager entityManager;
	
	@Autowired
	public UserDaoImpl(EntityManager entityManager) {
	
		this.entityManager = entityManager;
	}

	@Override
	public List<User> findAll() {
		
		// gwt the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Create a query using native Hibernate
		Query<User> query = currentSession.createQuery("from Employee", User.class);
		
		// Execute the query and get result list
		List<User> employees = query.getResultList();
		
		// Return the results
		return employees;
	}

	@Override
	public User findById(int id) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		User employee = currentSession.get(User.class, id);
		
		//return the employee
		return employee;
	}

	@Override
	public void save(User employee) {
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

	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Now retrieve/and read from database using username
		Query<User> query = currentSession.createQuery(
				"from Employee where userName=:username", User.class);
		query.setParameter("username", theUserName);
		
		User employee = null;
		try {
			employee = query.getSingleResult();
			
		} catch (Exception e) {
			employee = null;
		}
				
		return employee;
	}

}
