package com.TeK.MilkTracker.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Feeding {
	
	private int feedingId;
	private int babyId;
	private int userId;
	private boolean isBreastFeeding;
	private double bottleOuncesAmount;
	private LocalDate feedingDate;
	private LocalTime feedingTime;
	
	public int getBabyId() {
		return babyId;
	}
	public void setBabyId(int babyId) {
		this.babyId = babyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFeedingId() {
		return feedingId;
	}
	public void setFeedingId(int feedingId) {
		this.feedingId = feedingId;
	}
	public boolean isBreastFeeding() {
		return isBreastFeeding;
	}
	public void setBreastFeeding(boolean isBreastFeeding) {
		this.isBreastFeeding = isBreastFeeding;
	}
	public double getBottleOuncesAmount() {
		return bottleOuncesAmount;
	}
	public void setBottleOuncesAmount(double bottleOuncesAmount) {
		this.bottleOuncesAmount = bottleOuncesAmount;
	}
	public LocalDate getFeedingDate() {
		return feedingDate;
	}
	public void setFeedingDate(LocalDate feedingDate) {
		this.feedingDate = feedingDate;
	}
	public LocalTime getFeedingTime() {
		return feedingTime;
	}
	public void setFeedingTime(LocalTime feedingTime) {
		this.feedingTime = feedingTime;
	}
	
	

}
