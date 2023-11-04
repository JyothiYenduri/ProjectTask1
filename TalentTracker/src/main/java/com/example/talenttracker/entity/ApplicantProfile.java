package com.example.talenttracker.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class ApplicantProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long profileId;
	private String role="Job_Applicant";
	@Embedded
	private BasicDetails basicDetails;
	@Embedded
	private XclassDetails xClassDetails;
	@Embedded
	private IntermediateDetails interDetails;
	@Embedded
	private GraduationDetails gradDetails;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "applicantid",referencedColumnName = "id")
	private Applicant applicant;
	
}
