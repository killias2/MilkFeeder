package com.techelevator.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.Feeding;
import com.techelevator.model.FeedingDAO;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class FeedingController {
	
	private FeedingDAO feedingDAO;
	
	public FeedingController(FeedingDAO feedingDAO) {
		this.feedingDAO = feedingDAO;
	}
	
	@RequestMapping(value = "/feedings", method = RequestMethod.POST)
	public Feeding addFeeding(@RequestBody Feeding newFeeding) {
		feedingDAO.addFeeding(newFeeding);
		return newFeeding;
	}

}
