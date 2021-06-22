package com.javaprojects.bugtracker.dao;

import java.util.List;

import com.javaprojects.bugtracker.entity.Bug;

public interface BugDAO {
	public List<Bug> findAll();
}
