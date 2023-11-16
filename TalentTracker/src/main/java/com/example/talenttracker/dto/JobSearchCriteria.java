package com.example.talenttracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobSearchCriteria {

	private String skillName;
    private String jobTitle;
    private String location;
    private String industryType;
    private String employeeType;
    private String minimumQualification;
    private String specialization;
}
