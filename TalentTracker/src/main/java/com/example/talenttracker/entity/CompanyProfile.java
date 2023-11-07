package com.example.talenttracker.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"jobRecruiters"})
public class CompanyProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{3,19}$",message = "Please enter valid company name")
	private String companyName;
	@NotBlank
	private String website;
	@NotBlank
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Please enter valid Mobile number")
	private String phoneNumber;
	@NotBlank
	@Email(message = "Please enter valid mail address")
	private String email;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{3,19}$",message = "Please enter valid city name")
	private String headOffice;
	
	@OneToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;
	
	@ElementCollection
	@CollectionTable(name = "social_profiles",joinColumns = @JoinColumn(name="company_profile_id"))
	private List<String> socialProfiles;
}
