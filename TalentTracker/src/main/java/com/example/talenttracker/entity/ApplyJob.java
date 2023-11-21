package com.example.talenttracker.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class ApplyJob {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applyJobId;
	
	@ManyToOne
	@JoinColumn(name = "applicantId")
	private Applicant applicant;
	
	@ManyToOne
	@JoinColumn(name="jobId")
	private Job job;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date applicationDate;
	
	private String applicationStatus="new";
	
	@PrePersist
	private void setApplicationDate() {
	    applicationDate = new Date();
 
	}
}
