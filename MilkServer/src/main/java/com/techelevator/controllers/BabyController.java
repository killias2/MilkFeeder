package com.techelevator.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.Baby;
import com.techelevator.model.BabyDAO;


@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class BabyController {
	
	private BabyDAO babyDAO;
	
	public BabyController(BabyDAO babyDAO) {
		this.babyDAO = babyDAO;
	}
	
	@RequestMapping(value = "/babies", method = RequestMethod.POST)
	public Baby addBaby(@RequestBody Baby newBaby) {
		babyDAO.addBaby(newBaby);
		return newBaby;
	}

}
