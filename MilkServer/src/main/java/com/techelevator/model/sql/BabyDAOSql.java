package com.techelevator.model.sql;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Baby;
import com.techelevator.model.BabyDAO;

@Service
public class BabyDAOSql implements BabyDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public BabyDAOSql(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Baby getBabyById(int babyId) {
		String sqlQuery = "SELECT babyid, babyfirstname, babymiddleinitial, babylastname FROM " +
				"babies WHERE babyId = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, babyId);
		if(results.next()) {
			return mapRowToBaby(results);
		}
		return null;
	}

	@Override
	public Baby addBaby(Baby newBaby) {
		String sqlInsert = "INSERT INTO babies(babyid, babyfirstname, babymiddleinitial, babylastname) VALUES " +
				"(?, ?, ?, ?)";
		newBaby.setBabyId(getNextBabyId());
		jdbcTemplate.update(sqlInsert, newBaby.getBabyId(), newBaby.getFirstName(), newBaby.getMiddleInitial(),
				newBaby.getLastName());
		return newBaby;
	}

	@Override
	public List<Baby> getBabiesByParentId(int parentId) {
		String sqlQuery = "SELECT b.babyid, babyfirstname, babymiddleinitial, babylastname FROM " +
				"babies b JOIN user_baby ub on b.babyid = ub.babyid JOIN users u ON ub.userid = u.userid " +
				"WHERE u.userid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, parentId);
		List<Baby> babyList = new ArrayList<>();
		while(results.next()) {
			babyList.add(mapRowToBaby(results));
		}
		return babyList;
	}
	
	private Baby mapRowToBaby(SqlRowSet results) {
		Baby newBaby = new Baby();
		newBaby.setBabyId(results.getInt("babyid"));
		newBaby.setFirstName(results.getString("babyfirstname"));
		newBaby.setMiddleInitial(results.getString("babymiddleinitial").charAt(0));
		newBaby.setLastName(results.getString("babylastname"));
		return newBaby;
	}
	
	private int getNextBabyId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('babies_babyid_seq')");
		if(nextIdResult.next() ) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException ("Something went wrong while getting a new ID");
		}
	}
	
	

}
