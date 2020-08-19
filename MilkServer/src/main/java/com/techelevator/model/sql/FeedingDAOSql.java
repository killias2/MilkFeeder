package com.techelevator.model.sql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Baby;
import com.techelevator.model.Feeding;
import com.techelevator.model.FeedingDAO;
import com.techelevator.model.User;

@Service
public class FeedingDAOSql implements FeedingDAO {

	private JdbcTemplate jdbcTemplate;
	
	public FeedingDAOSql(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Feeding> getFeedingsByBaby(Baby baby) {
		String sqlQuery = "SELECT feedingid, babyid, userid, isbreastfeeding, bottleouncesamount, feedingdate, feedingtime " +
				"FROM feedings WHERE babyid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, baby.getBabyId());
		List<Feeding> feedingList = new ArrayList<>();
		while(results.next()) {
			feedingList.add(mapRowToFeeding(results));
		}
		return feedingList;
	}

	@Override
	public List<Feeding> getFeedingsByUser(User user) {
		String sqlQuery = "SELECT feedingid, babyid, userid, isbreastfeeding, bottleouncesamount, feedingdate, feedingtime " +
				"FROM feedings WHERE userid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getUserId());
		List<Feeding> feedingList = new ArrayList<>();
		while(results.next()) {
			feedingList.add(mapRowToFeeding(results));
		}
		return feedingList;
	}

	@Override
	public Feeding addFeeding(Feeding newFeeding) {
		String sqlInsert = "INSERT INTO feedings(feedingid, babyid, userid, isbreastfeeding, bottleouncesamount, feedingdate, " +
			"feedingtime) VALUES (?, ?, ?, ?, ?, ?, ?)";
		newFeeding.setFeedingId(getNextFeedingId());
		jdbcTemplate.update(sqlInsert, newFeeding.getFeedingId(), newFeeding.getBabyId(), newFeeding.getUserId(), 
				newFeeding.isBreastFeeding(), newFeeding.getBottleOuncesAmount(), newFeeding.getFeedingDate(), 
				newFeeding.getFeedingTime());
		return newFeeding;
	}

	@Override
	public void deleteFeeding(Feeding oldFeeding) {
		String sqlDelete = "DELETE FROM feedings WHERE feedingid = ?";
		jdbcTemplate.update(sqlDelete, oldFeeding.getFeedingId());
	}

	@Override
	public Feeding updateFeeding(Feeding oldFeeding) {
		String sqlUpdate = "UPDATE feedings SET (feedingid, babyid, userid, isbreastfeeding, bottleouncesamount, feedingdate, " +
				"feedingtime) = (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlUpdate, oldFeeding.getFeedingId(), oldFeeding.getBabyId(), oldFeeding.getUserId(), 
				oldFeeding.isBreastFeeding(), oldFeeding.getBottleOuncesAmount(), oldFeeding.getFeedingDate(), 
				oldFeeding.getFeedingTime());
		return oldFeeding;
	}
	
	private int getNextFeedingId(){
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('feedings_feedingid_seq')");
		if(nextIdResult.next() ) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException ("Something went wrong while getting a new ID");
		}
	}
	
	private Feeding mapRowToFeeding(SqlRowSet results) {
		Feeding newFeeding = new Feeding();
		newFeeding.setFeedingId(results.getInt("feedingid"));
		newFeeding.setBabyId(results.getInt("babyid"));
		newFeeding.setUserId(results.getInt("userid"));
		newFeeding.setBreastFeeding(results.getBoolean("isbreastfeeding"));
		newFeeding.setBottleOuncesAmount(results.getDouble("bottleouncesamount"));
		newFeeding.setFeedingDate(results.getDate("feedingdate").toLocalDate());
		newFeeding.setFeedingTime(results.getTime("feedingtime").toLocalTime());
		return newFeeding;
	}

}
