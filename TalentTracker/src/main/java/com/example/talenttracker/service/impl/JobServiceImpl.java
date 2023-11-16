package com.example.talenttracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.talenttracker.dto.JobSearchCriteria;
import com.example.talenttracker.dto.JobSpecifications;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.repository.JobRepository;
import com.example.talenttracker.service.JobService;
@Service
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public Job postJob(Job job) {

		return jobRepository.save(job);
	}

	@Override
	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		return jobRepository.findAll();
	}

	@Override
	public Job getJobById(Long jobId) {
		// TODO Auto-generated method stub
		return jobRepository.findById(jobId).orElse(null);
	}

	@Override
	public List<Job> getJobByRecruiterID(Long jobRecruiterId) {
		// TODO Auto-generated method stub
		List<Job> jobs=jobRepository.findByJobRecruiterId(jobRecruiterId);
		return jobs;
	}

	@Override
	public List<Job> searchJobs(JobSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		Page<Job> jobPage = jobRepository.findAll(
	            JobSpecifications.searchJobs(
	                searchCriteria.getSkillName(),
	                searchCriteria.getJobTitle(),
	                searchCriteria.getLocation(),
	                searchCriteria.getIndustryType(),
	                searchCriteria.getEmployeeType(),
	                searchCriteria.getMinimumQualification(),
	                searchCriteria.getSpecialization()
	                
	            ),
	            Pageable.unpaged()
	        );
	        
	       
	 
	        return jobPage.getContent();
	}

//	@Override
//	public List<Job> searchJobs(String jobTitle, String location, String industryType, String empType,
//	        String minimumQualification, String specialization, String skillName, Integer minimumExperience) {
//	    return jobRepository.searchJobsByFilters(
//	            jobTitle,
//	            location,
//	            industryType,
//	            empType,
//	            minimumQualification,
//	            specialization,
//	            skillName,
//	            minimumExperience);
//	}
	
	


}
