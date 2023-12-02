package com.example.talenttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.talenttracker.dto.ApplicantJobInterviewDTO;
import com.example.talenttracker.entity.ScheduleInterview;

public interface ScheduleInterviewRepository extends JpaRepository<ScheduleInterview, Long>{

//	@Query("SELECT NEW com.example.talenttracker.dto.ApplicantJobInterviewDTO(" +
//	        "a.name, a.email, a.mobilenumber, j.jobTitle, si.dateAndTime, si.location, si.modeOfInterview, si.round, si.interviewLink, si.interviewPerson) " +
//	        "FROM ScheduleInterview si " +
//	        "INNER JOIN si.ApplyJob aj " +
//	        "INNER JOIN aj.Applicant a " +
//	        "INNER JOIN aj.Job j " +
//	        "INNER JOIN j.Recruiter r " +
//	        "WHERE r.recruiterId = :recruiterId " +
//	        "AND aj.applicationStatus = :status")
//	List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoByRecruiterAndStatus(@Param("recruiterId") long recruiterId, @Param("status") String status);

	@Query("SELECT NEW com.example.talenttracker.dto.ApplicantJobInterviewDTO(" +
	        "a.name, a.email, a.mobilenumber, j.jobTitle, si.dateAndTime, si.location, si.modeOfInterview, si.round, si.interviewLink, si.interviewPerson) " +
	        "FROM ScheduleInterview si " +
	        "INNER JOIN si.applyJob aj " + // Corrected here
	        "INNER JOIN aj.applicant a " + // Corrected here
	        "INNER JOIN aj.job j " + // Corrected here
	        "INNER JOIN j.recruiter r " + // Corrected here
	        "WHERE a.id = :applicantId " +
	        "AND aj.applicationStatus = :status")
	List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoByRecruiterAndStatus(@Param("applicantId") long applicantId, @Param("status") String status);

	@Query("SELECT NEW com.example.talenttracker.dto.ApplicantJobInterviewDTO(" +
	        "a.name, a.email, a.mobilenumber, j.jobTitle, si.dateAndTime, si.location, si.modeOfInterview, si.round, si.interviewLink, si.interviewPerson) " +
	        "FROM ScheduleInterview si " +
	        "INNER JOIN si.applyJob aj " + // Corrected here
	        "INNER JOIN aj.applicant a " + // Corrected here
	        "INNER JOIN aj.job j " + // Corrected here
//	        "INNER JOIN j.recruiter r " + // Corrected here
	        "WHERE a.id = :applicantId ")
	List<ApplicantJobInterviewDTO> getApplicantJobInterviewStatus(long applicantId);

}
