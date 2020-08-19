package com.techelevator.model.sql;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Service
public class UserDAOSql implements UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public UserDAOSql(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User addUser(User newUser) {
		String sqlInsert = "INSERT INTO users(userid, username) VALUES (?, ?)";
		newUser.setUserId(getNextUserId());
		jdbcTemplate.update(sqlInsert, newUser.getUserId(), newUser.getUserName());
		return newUser;
	}

	@Override
	public User getUserByUserName(String userName) {
		String sqlQuery = "SELECT userid, username FROM users where username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, userName);
		if(results.next()) {
			return mapRowToUser(results);
		}
		return null;
	}
	
	private User mapRowToUser(SqlRowSet results) {
		User newUser = new User();
		newUser.setUserId(results.getInt("userid"));
		newUser.setUserName(results.getString("username"));
		return newUser;
	}
	
	private int getNextUserId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('users_userid_seq')");
		if(nextIdResult.next() ) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException ("Something went wrong while getting a new ID");
		}
	}

}
