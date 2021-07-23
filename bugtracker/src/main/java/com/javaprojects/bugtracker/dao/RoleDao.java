package com.javaprojects.bugtracker.dao;

import java.util.List;

import com.javaprojects.bugtracker.entity.Role;

public interface RoleDao {
	
	public Role findRoleByName(String theRoleName);
	
	public List<Role> finAll();

}
