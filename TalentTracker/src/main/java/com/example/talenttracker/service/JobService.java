package com.example.talenttracker.service;

import java.util.List;

import com.example.talenttracker.dto.JobSearchCriteria;
import com.example.talenttracker.entity.Job;


public interface JobService {

	public Job postJob(Job job);

	public List<Job> getAllJobs();

	public Job getJobById(Long jobId);

	public List<Job> getJobByRecruiterID(Long jobRecruiterId);
	
//	public List<Job> searchJobs(String jobTitle, String location, String industryType, String empType,
//	        String minimumQualification, String specialization, String skillName, Integer minimumExperience);
	public List<Job> searchJobs(JobSearchCriteria searchCriteria);
}
