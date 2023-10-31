package com.example.talenttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
public class TalentTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentTrackerApplication.class, args);
		//System.out.println("Hey I learn how to push, pull pack & commit changes again using GitHub");
	}

}
