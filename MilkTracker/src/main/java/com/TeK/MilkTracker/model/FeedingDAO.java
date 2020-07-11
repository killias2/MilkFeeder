package com.TeK.MilkTracker.model;

import java.util.List;

public interface FeedingDAO {
	
	List<Feeding> getFeedingsByBaby(Baby baby);
	
	List<Feeding> getFeedingsByUser(User user);
	
	Feeding addFeeding(Feeding newFeeding);
	
	void deleteFeeding(Feeding oldFeeding);
	
	Feeding updateFeeding(Feeding oldFeeding);
	

}
