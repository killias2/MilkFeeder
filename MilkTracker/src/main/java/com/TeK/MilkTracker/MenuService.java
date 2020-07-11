package com.TeK.MilkTracker;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import com.TeK.MilkTracker.model.Baby;
import com.TeK.MilkTracker.model.Feeding;

public class MenuService {
	
	private PrintWriter out;
	private Scanner in;

	public MenuService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}
	
	
	public void startMenu() {
		
	}
	
	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}
	
	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println("\n*** " + userInput + " is not valid ***\n");
			}
		} while(result == null);
		return result;
	}
	
	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	
	private void displayBabyOptions(List<Baby> options) {
		out.println();
		for (int i = 0; i < options.size(); i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") BabyID: " + options.get(i).getBabyId() + "\nName: " +
				options.get(i).getFirstName() + " " + options.get(i).getMiddleInitial() + ". " + 
				options.get(i).getLastName());
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	
	public void displayFeedingOptions(List<Feeding> options) {
		out.println();
		for (int i = 0; i < options.size(); i++) {
			int optionNum = i + 1;
			if (options.get(i).isBreastFeeding()) {
				out.println(optionNum + ") FeedingID: " + options.get(i).getFeedingId() + " Breast Feeeding (cannot report ounces)" + " Date: " + 
						options.get(i).getFeedingDate() + " Time: " + options.get(i).getFeedingTime());
			}
			else {
				out.println(optionNum + ") FeedingID: " + options.get(i).getFeedingId() + " Bottle Amount " +
					options.get(i).getBottleOuncesAmount() + " Date: "  + options.get(i).getFeedingDate() + 
					" Time: " + options.get(i).getFeedingTime());
			}	
		}
	}
	
	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}
	
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}
	
	public Baby getChoiceFromBabies(List<Baby> options) {
		Baby choice = null;
		while (choice == null) {
			displayBabyOptions(options);
			choice = getBabyFromUserInput(options);
		}
		out.println();
		return choice;
	}
	
	private Baby getBabyFromUserInput(List<Baby> options) {
		Baby choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.size()) {
				choice = options.get(selectedOption - 1);
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}
	
	public Feeding getChoiceFromFeeding(List<Feeding> options) {
		Feeding choice = null;
		while (choice == null) {
			displayFeedingOptions(options);
			out.print("\nPlease choose an option >>> ");
			out.flush();
			choice = getFeedingFromUserInput(options);
		}
		out.println();
		return choice;
	}
	
	private Feeding getFeedingFromUserInput(List<Feeding> options) {
		Feeding choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.size()) {
				choice = options.get(selectedOption - 1);
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}
	
	public void print(String output) {
		out.println(output);
	}

}
