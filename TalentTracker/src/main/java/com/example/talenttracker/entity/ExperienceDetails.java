package com.example.talenttracker.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ExperienceDetails {

	private String company;
	private String position;
	private String startDate;
	private String endDate;
	
}
