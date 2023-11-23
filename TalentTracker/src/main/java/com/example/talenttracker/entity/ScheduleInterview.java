package com.example.talenttracker.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ScheduleInterview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String interviewTitle;
	private String interviewPerson;
	private String typeOfInterview;
	private int round;
	private String dateAndTime;
	private String modeOfInterview;
	private String location;
	private String interviewLink;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "apply_job_id")
	private ApplyJob applyJob;
	
}
