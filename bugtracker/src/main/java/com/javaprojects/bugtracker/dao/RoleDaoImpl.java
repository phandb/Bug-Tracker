package com.javaprojects.bugtracker.dao;



import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaprojects.bugtracker.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	
	// Need to inject the session
	@Autowired
	private EntityManager entityManager;
	
	
	public RoleDaoImpl(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}


	@Override
	public Role findRoleByName(String theRoleName) {
		// get the current session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Retrieve/read from database using name
		Query<Role> query = currentSession.createQuery("from Role where name=:roleName", Role.class);
		
		query.setParameter("roleName", theRoleName);
		
		Role theRole = null;
		
		try
		{
			theRole = query.getSingleResult();
			
		}
		catch (Exception e) {
			theRole = null;
		}
		
		return theRole;
	}

}
