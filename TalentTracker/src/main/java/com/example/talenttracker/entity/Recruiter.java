package com.example.talenttracker.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Recruiter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recruiterId;
	@Column(nullable = false)
	private String companyName;
	@Column(nullable = false,unique = true)
	private String mobileNum;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String pwd;
	private String role="Job_Recruiter";
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Job> jobs;
	
}
