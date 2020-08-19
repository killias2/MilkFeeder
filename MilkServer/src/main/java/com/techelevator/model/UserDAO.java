package com.techelevator.model;

public interface UserDAO {
	
	User addUser(User newUser);
	
	User getUserByUserName(String userName);

}
