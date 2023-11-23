package com.example.talenttracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppliedApplicantInfoDTO {

	private Long applyjobid;
	private String name;
    private String email;
    private String mobilenumber;
    private String jobTitle;
    private String applicantStatus;
    private int minimumExperience;
    private String skillName;
    private String minimumQualification;
    private String location;
}
