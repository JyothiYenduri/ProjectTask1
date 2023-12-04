package com.example.talenttracker.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
//
//import jakarta.persistence.Column;
//import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@JsonIgnoreType
@JsonIgnoreProperties
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
	
//	@Column(nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)

//	@CreationTimestamp
//	private Date applicationDate;
	
	private String applicationStatus="new";
	
//	@PrePersist
//	private void setApplicationDate() {
//	    applicationDate = new Date();
// 
//	}
	@OneToMany(mappedBy = "applyJob", cascade = CascadeType.ALL)
//	@JsonManagedReference
	@JsonIgnore
	private List<ScheduleInterview> scheduleInterviews;
	
	@OneToMany(mappedBy = "applyJob", cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<ApplicantStatusHistory> statusHistory;
	
	@OneToMany(mappedBy = "applyJob",cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Alerts> alerts;
	
	//private int alertCount=0;
}
