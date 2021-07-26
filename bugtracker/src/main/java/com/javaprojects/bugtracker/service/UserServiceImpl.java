package com.javaprojects.bugtracker.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaprojects.bugtracker.customUser.CustomUser;
import com.javaprojects.bugtracker.dao.UserDao;
import com.javaprojects.bugtracker.dao.RoleDao;
import com.javaprojects.bugtracker.entity.User;
import com.javaprojects.bugtracker.entity.Role;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//--------------  Constructor -----------------
	
	@Autowired
	public UserServiceImpl(UserDao userDao,
								RoleDao roleDao	) {
		
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	// -----------Authorization  -----------------
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Find the user
		User user = userDao.findByUserName(username);
		
		// Check valid user
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
			
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					mapRolesToAuthorities(user.getRoles())) ;
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exist
		
		return userDao.findByUserName(userName);
	}
	
	// Save new user via registration

	@Override
	@Transactional
	public void saveCustomUser(CustomUser customUser) {
		
		User user = new User();
		
		// Assign user details to the user object
		user.setUserName(customUser.getUserName());
		user.setPassword(passwordEncoder.encode(customUser.getPassword()));
		user.setFirstName(customUser.getFirstName());
		user.setLastName(customUser.getLastName());
		user.setPosition(customUser.getPosition());
		
		// Assign default role of DEVELOPER
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_DEVELOPER")));
		
		// Enable the user
		
	
		// Save the custom user into  the User of database
		userDao.save(user);
		
	}
	
	@Override
	@Transactional
	public void addRoleAdminToEmployee(int id) {
		User updatingUser = findById(id);
		updatingUser.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_DEVELOPER")));
		Role tempRole = roleDao.findRoleByName("ROLE_ADMIN");
		tempRole.addRoleToEmployee(updatingUser);
		
		//return updatingUser;
	}

	// -----------CRUD------------
	
	@Override
	@Transactional
	public List<User> findAll() {
		
		return userDao.findAll();
	}



	@Override
	@Transactional
	public User findById(int id) {
		// Delegate to DAO
		return userDao.findById(id);
	}

	// might not need this method
	@Override
	@Transactional
	public void save(User user) {
		
		
		// Delegate to DAO
		userDao.save(user);
		

		if (user.getPosition().equals("Lead")) {
			addRoleAdminToEmployee(user.getId());
		}
		
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// Delegate to DAO
		userDao.deleteById(id);
		
	}

	

}
