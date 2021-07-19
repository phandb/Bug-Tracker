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
	private UserDao employeeDAO;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	//--------------  Constructor -----------------
	
	@Autowired
	public UserServiceImpl(UserDao employeeDAO,
								RoleDao roleDao	) {
		
		this.employeeDAO = employeeDAO;
		this.roleDao = roleDao;
	}

	// -----------Authorization  -----------------
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Find the employee
		User employee = employeeDAO.findByUserName(username);
		
		// Check valid employee
		if (employee == null) {
			throw new UsernameNotFoundException("Invalid username or password");
			
		}
		return new org.springframework.security.core.userdetails.User(employee.getUserName(), employee.getPassword(),
					mapRolesToAuthorities(employee.getRoles())) ;
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		// TODO Auto-generated method stub
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the employee already exist
		
		return employeeDAO.findByUserName(userName);
	}
	
	// Save new employee via registration

	@Override
	@Transactional
	public void saveCustomUser(CustomUser customUser) {
		
		User employee = new User();
		
		// Assign employee details to the employee object
		employee.setUserName(customUser.getUserName());
		employee.setPassword(passwordEncoder.encode(customUser.getPassword()));
		employee.setFirstName(customUser.getFirstName());
		employee.setLastName(customUser.getLastName());
		employee.setPosition(customUser.getPosition());
		
		// Assign default role of DEVELOPER
		employee.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_DEVELOPER")));
		
		// Save the employee in the database
		employeeDAO.save(employee);
		
	}

	// -----------CRUD------------
	
	@Override
	@Transactional
	public List<User> findAll() {
		
		return employeeDAO.findAll();
	}



	@Override
	@Transactional
	public User findById(int id) {
		// Delegate to DAO
		return employeeDAO.findById(id);
	}

	// might not need this method
	@Override
	@Transactional
	public void save(User employee) {
		// Delegate to DAO
		employeeDAO.save(employee);
		
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// Delegate to DAO
		employeeDAO.deleteById(id);
		
	}

	

}
