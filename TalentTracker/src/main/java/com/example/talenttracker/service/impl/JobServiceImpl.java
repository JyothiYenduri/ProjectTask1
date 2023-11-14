package com.example.talenttracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
