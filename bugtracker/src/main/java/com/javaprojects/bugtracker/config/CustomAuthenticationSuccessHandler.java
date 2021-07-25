package com.javaprojects.bugtracker.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.javaprojects.bugtracker.entity.User;
import com.javaprojects.bugtracker.service.UserService;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private UserService userService;
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");
		
		String userName = authentication.getName();
		
		System.out.println("userName: " + userName);
		
		User theUser = userService.findByUserName(userName);
		
		// Place the login employee in the session
		HttpSession session = request.getSession();
		session.setAttribute("loggedInUser", theUser);
		session.setAttribute("userFullName", theUser.getFullName());
		
		
		// forward to home page
		response.sendRedirect(request.getContextPath() + "/");

	}

}
