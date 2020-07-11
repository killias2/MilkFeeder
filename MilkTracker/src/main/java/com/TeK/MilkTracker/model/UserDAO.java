package com.TeK.MilkTracker.model;

public interface UserDAO {
	
	User addUser(User newUser);
	
	User getUserByUserName(String userName);

}
