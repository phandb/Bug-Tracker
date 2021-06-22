package com.javaprojects.bugtracker.service;

import java.util.List;

import com.javaprojects.bugtracker.entity.Bug;

public interface BugService {
	
	public List<Bug> findAll();
	
	public Bug findById(int theId);
	
	public void save(Bug theBug);
	
	public void deleteById(int theId);
	

}
