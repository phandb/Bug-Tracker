package com.javaprojects.bugtracker.dao;

import java.util.List;

import com.javaprojects.bugtracker.entity.Bug;

public interface BugDAO {
	public List<Bug> findAll();
	
	// CRUD methods
	public Bug findById(int id);
	
	public void save(Bug bug);
	
	public void deleteById(int id);
}
