package com.example.talenttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.talenttracker.dto.AppliedApplicantInfo;
import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.ApplyJob;
import com.example.talenttracker.entity.Job;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Long>{

	boolean existsByApplicantAndJob(Applicant applicant, Job job);

	List<ApplyJob> findByApplicantId(long applicantId);

//	@Query("SELECT NEW com.example.talenttracker.dto.AppliedApplicantInfo(" +
//		       " aj.applyjobid,a.name, a.email, a.mobilenumber, j.jobTitle, aj.applicantStatus, " +
//		       " j.minimumExperience, s.skillName, " +
//		       "j.minimumQualification, j.location) " +
//		       "FROM ApplyJob aj " +
//		       "INNER JOIN aj.applicant a " +
//		       "INNER JOIN aj.job j " +
//		       "INNER JOIN j.skillsRequired s " +
//		       "INNER JOIN j.recruiter r " +
//		       "WHERE r.id = :jobRecruiterId")
//	
	@Query("SELECT NEW com.example.talenttracker.dto.AppliedApplicantInfo(" +
			" aj.applyJobId, a.name, a.email, a.mobilenumber, j.jobTitle, aj.applicationStatus, "+ 
			"j.minimumExperience, s.skillName, "+ 
			"j.minimumQualification, j.location) "+
			"FROM ApplyJob aj "+ 
			"INNER JOIN aj.applicant a "+
			"INNER JOIN aj.job j "+ 
			"INNER JOIN j.skillsRequired s "+ 
			"INNER JOIN j.recruiter r "+
			"WHERE r.id = :jobRecruiterId ")
	
	List<AppliedApplicantInfo> findAppliedApplicantsInfo(@Param("jobRecruiterId") long jobRecruiterId);
	

}
