package com.TeK.MilkTracker.model;

import java.util.List;

public interface BabyDAO {
	
	Baby getBabyById(int babyId);
	
	Baby addBaby(Baby newBaby);

	List<Baby> getBabiesByParentId(int parentId);

}
