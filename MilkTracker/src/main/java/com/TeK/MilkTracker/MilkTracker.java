package com.TeK.MilkTracker;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import com.TeK.MilkTracker.model.Baby;
import com.TeK.MilkTracker.model.BabyDAO;
import com.TeK.MilkTracker.model.Feeding;
import com.TeK.MilkTracker.model.FeedingDAO;
import com.TeK.MilkTracker.model.User;
import com.TeK.MilkTracker.model.UserDAO;
import com.TeK.MilkTracker.model.jdbc.JDBCBabyDAO;
import com.TeK.MilkTracker.model.jdbc.JDBCFeedingDAO;
import com.TeK.MilkTracker.model.jdbc.JDBCUserDAO;

public class MilkTracker {
	
	private static BabyDAO babyDao = null;
	private static UserDAO userDao = null;
	private static FeedingDAO feedingDao = null;
	private MenuService service;
	
	public MilkTracker(MenuService service){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/milktracker");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		babyDao = new JDBCBabyDAO(dataSource);
		userDao = new JDBCUserDAO(dataSource);
		feedingDao = new JDBCFeedingDAO(dataSource);
		this.service = service;
	}
	
	public static void main(String[] args) {
		MilkTracker app = new MilkTracker(new MenuService(System.in, System.out));
		app.initialMenu();
	}
	
	public void initialMenu() {
		service.print("Welcome to MilkTracker!");
		boolean initialChecker = false;
		while (!initialChecker){
	
			String name = service.getUserInput("Please enter your username");
			User thisUser = userDao.getUserByUserName(name);
			if (thisUser != null) {
				initialChecker = true;
				babyMenu(thisUser);
			}
			else {
				service.print("I'm sorry, please double-check your username");
			}
		}
		
	}
	
	public void babyMenu(User thisUser) {
		boolean babyChecker = false;
		int input = 0;
		while (!babyChecker){
	
			input = service.getUserInputInteger("Welcome " + thisUser.getUserName() + "\nWould you like to (1) add a baby, (2) see the "
					+ "babies associated with your account, or (3) see all feedings associated with your account?");
			if (input == 1 || input == 2 || input == 3) {
				babyChecker = true;
				
			}
			else {
				service.print("I'm sorry, please choose 1, 2, or 3.");
			}
		}
		if (input == 1) {
			babyMenuAdd(thisUser);
		}
		else if (input == 2)
		{
			List<Baby> babyList = babyDao.getBabiesByParentId(thisUser.getUserId());
			Baby thisBaby = service.getChoiceFromBabies(babyList);
			babyMenuFeed(thisBaby, thisUser);
		}
		else if (input == 3) {
			userFeedMenu(thisUser);
		}

	}
	
	public void babyMenuAdd(User thisUser) {
		boolean babyChecker = false;
		int input = 0;
		while (!babyChecker){
		}
	}
	
	public void babyMenuFeed(Baby thisBaby, User thisUser) {
		boolean feedChecker = false;
		int input = 0;
		while (!feedChecker){
			input = service.getUserInputInteger("You are at the page for Baby" + thisBaby.getFirstName() + "\nWould you like to (1) add a feeding, "
				+ "\n(2) see the feedings associated with this baby, \n(3) select a feeding associated with "
				+ "this baby to delete or modify or \n(4) calculate an aggregate of feedings over a period of time?");
			if (input == 1 || input == 2 || input == 3) {
				feedChecker = true;
			}
			else {
				service.print("I'm sorry, please choose 1, 2, or 3.");
			}
		}
		if (input == 1) {
			//add a feeding
		}
		else if (input == 2) {
			service.displayFeedingOptions(feedingDao.getFeedingsByBaby(thisBaby));
		}
		else if (input == 3) {
			Feeding thisFeeding = service.getChoiceFromFeeding(feedingDao.getFeedingsByBaby(thisBaby));
		}
		else if (input == 4) {
			//aggregate options
		}
	}
	
	public void userFeedMenu(User thisUser) {
		//get a list of feedings, organized by baby
	}
}
