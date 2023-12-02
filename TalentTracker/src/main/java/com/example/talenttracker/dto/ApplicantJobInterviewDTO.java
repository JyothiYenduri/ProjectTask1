package com.example.talenttracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantJobInterviewDTO {

	private String name;
    private String email;
    private String mobilenumber;
    private String jobTitle;
    private String dateAndTime;
    private String location;
    private String modeOfInterview;
    private int round;
    private String interviewLink;
    private String interviewPerson;
}
