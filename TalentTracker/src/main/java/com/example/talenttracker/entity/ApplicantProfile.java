package com.example.talenttracker.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "applicant_profile_skills_required",joinColumns = @JoinColumn(name = "profileid"),inverseJoinColumns = @JoinColumn(name = "applicantskill_id"))
	private Set<ApplicantSkills> skillsRequired=new HashSet<>();
	
	@ElementCollection
	private List<ExperienceDetails> experienceDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "applicantid",referencedColumnName = "id")
	private Applicant applicant;
	
}
