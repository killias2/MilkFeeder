package com.TeK.MilkTracker.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.TeK.MilkTracker.model.Baby;
import com.TeK.MilkTracker.model.Feeding;
import com.TeK.MilkTracker.model.FeedingDAO;
import com.TeK.MilkTracker.model.User;

public class JDBCFeedingDAO implements FeedingDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCFeedingDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Feeding> getFeedingsByBaby(Baby thisBaby) {
		String sqlQuery = "SELECT feedingid, babyid, userid, isbreastfeeding, bottleouncesamount, " +
				"feedingdate, feedingtime FROM feedings WHERE babyid = ? ORDER BY feedingdate, feedingtime";
		List<Feeding> feedingList = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, thisBaby.getBabyId());
		while(results.next()) {
			Feeding newFeeding = mapRowToFeeding(results);
			feedingList.add(newFeeding);
		}
		return feedingList;
	}

	public List<Feeding> getFeedingsByUser(User user) {
		String sqlQuery = "SELECT feedingid, babyid, userid, isbreastfeeding, bottleouncesamount, " +
				"feedingdate, feedingtime FROM feedings WHERE userid = ? ORDER BY feedingdate, feedingtime";
		List<Feeding> feedingList = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getUserId());
		while(results.next()) {
			Feeding newFeeding = mapRowToFeeding(results);
			feedingList.add(newFeeding);
		}
		return feedingList;
	}

	public Feeding addFeeding(Feeding newFeeding) {
		String sqlInsert = "INSERT INTO feedings(feedingid, babyid, userid, isbreastfeeding, " +
				"bottleouncesamount, feedingdate, feedingtime) VALUES(?, ?, ?, ?, ?, ?, ?)";
		newFeeding.setFeedingId(getNextFeedingId());
		jdbcTemplate.update(sqlInsert, newFeeding.getFeedingId(), newFeeding.getBabyId(), newFeeding.getUserId(),
				newFeeding.isBreastFeeding(), newFeeding.getBottleOuncesAmount(), newFeeding.getFeedingDate(), 
				newFeeding.getFeedingTime());
		return newFeeding;
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
	
	private int getNextFeedingId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('feedings_feedingid_seq')");
			if(nextIdResult.next() ) {
				return nextIdResult.getInt(1);
			} else {
				throw new RuntimeException ("Something went wrong while getting a new ID");
			}
	}

	@Override
	public void deleteFeeding(Feeding oldFeeding) {
		// TODO Auto-generated method stub
	}

	@Override
	public Feeding updateFeeding(Feeding oldFeeding) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
