package com.javaprojects.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javaprojects.bugtracker.entity.Bug;

@Repository
public class BugDAOHibernateImpl implements BugDAO {
	
	// define field for entity manager
	private EntityManager entityManager;
	
	// set up constructor injection
	@Autowired
	public BugDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Bug> findAll() {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query using native Hibernate
		Query<Bug> query = currentSession.createQuery("from Bug", Bug.class);
		
		// execute the query and get result list
		List<Bug> bugs = query.getResultList();
		
		// return the results
		return bugs;
	}

}
