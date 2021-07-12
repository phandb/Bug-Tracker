package com.javaprojects.bugtracker.dao;

import com.javaprojects.bugtracker.entity.Role;

public interface RoleDao {
	
	public Role findRoleByName(String theRoleName);

}
