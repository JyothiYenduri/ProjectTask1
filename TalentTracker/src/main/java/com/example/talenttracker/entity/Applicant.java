package com.example.talenttracker.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
@JsonIgnoreProperties
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Please enter valid Mobile number")
	private String mobilenumber;
	@Email(message = "Please enter valid mail address")
	private String email;
	private String password;
	private String roles="Job_Applicant";
	
	@OneToMany(mappedBy = "applicant")
	@JsonIgnore
	private Set<SavedJob> savedJobs=new HashSet<>();
	
	@OneToMany(mappedBy = "applicant")
	@JsonIgnore
	private Set<ApplyJob> appliedJobs=new HashSet<>();
	
}
