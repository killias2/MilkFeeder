package com.techelevator.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class UserController {
	
	private UserDAO userDAO;
	
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User addUser(@RequestBody User newUser) {
		userDAO.addUser(newUser);
		return newUser;
	}

}