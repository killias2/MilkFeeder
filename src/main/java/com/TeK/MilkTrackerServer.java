package com.TeK;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class MilkTrackerServer {

	public static void main(String[] args) {
		SpringApplication.run(MilkTrackerServer.class, args);
	}

}
