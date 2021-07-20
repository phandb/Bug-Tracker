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
		Query<User> query = currentSession.createQuery("from User", User.class);
		
		// Execute the query and get result list
		List<User> users = query.getResultList();
		
		// Return the results
		return users;
	}

	@Override
	public User findById(int id) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee
		User user = currentSession.get(User.class, id);
		
		//return the employee
		return user;
	}

	@Override
	public void save(User user) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		// save the bug.  
		// method saveOrUpdate() means
		// if id=0 then save/insert;  otherwise update
		currentSession.saveOrUpdate(user);
		
	}

	@Override
	public void deleteById(int id) {
		//gwt the current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete the employee with primary key
		Query query = currentSession.createQuery(
				"delete from User where id=:userId");
		
		query.setParameter("userId", id);
		
		query.executeUpdate();
		
	}

	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Now retrieve/and read from database using username
		Query<User> query = currentSession.createQuery(
				"from User where userName=:username", User.class);
		query.setParameter("username", theUserName);
		
		User user = null;
		try {
			user = query.getSingleResult();
			
		} catch (Exception e) {
			user = null;
		}
				
		return user;
	}

}
