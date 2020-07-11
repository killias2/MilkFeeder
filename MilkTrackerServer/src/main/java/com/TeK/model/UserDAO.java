package com.TeK.model;

public interface UserDAO {
	
	User addUser(User newUser);
	
	User getUserByUserName(String userName);

}
