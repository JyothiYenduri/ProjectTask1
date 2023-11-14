package com.example.talenttracker.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"recruiters"})
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String jobTitle;
	@Column(nullable = false)
	private int minimumExperience;
	@Column(nullable = false)
	private int maximumExperience;
	@Column(nullable = false)
	private double minSalary;
	@Column(nullable = false)
	private double maxSalary;
	@Column(nullable = false)
	private String location;
	@Column(nullable = false)
	private String empType;
	@Column(nullable = false)
	private String industryType;
	@Column(nullable = false)
	private String minimumQualification;
	@Column(nullable = false)
	private String specialization;
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "job_skills", joinColumns = @JoinColumn(name="job_id"),inverseJoinColumns = @JoinColumn(name="skill_id"))
	private Set<RecruiterSkills> skillsRequired=new HashSet<>();
	
	@ManyToOne
	private Recruiter recruiter;
	
	private String jobHighlights;
	@Column(nullable = false,length = 2000)
	private String description;
	@Lob
	@Column
	private byte[] uploadDocument;
}
