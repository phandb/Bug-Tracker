package com.javaprojects.bugtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaprojects.bugtracker.dao.BugDAO;
import com.javaprojects.bugtracker.entity.Bug;


@Service
public class BugServiceImpl implements BugService {
	
	
	private BugDAO bugDAO;
	
	@Autowired
	public BugServiceImpl(BugDAO bugDAO) {
	
		this.bugDAO = bugDAO;
	}

		

	@Override
	
	public List<Bug> findAll() {
		
		return bugDAO.findAll();
	}

	@Override
	@Transactional
	public Bug findById(int theId) {
		
		return bugDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Bug theBug) {
		
		bugDAO.save(theBug);

	}

	@Override
	@Transactional
	public void deleteById(int theId) {

		bugDAO.deleteById(theId);

	}

}
