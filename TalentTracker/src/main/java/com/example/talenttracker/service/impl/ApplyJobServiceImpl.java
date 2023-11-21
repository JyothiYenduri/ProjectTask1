package com.example.talenttracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.ApplyJob;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.repository.ApplyJobRepository;
import com.example.talenttracker.repository.JobRepository;
import com.example.talenttracker.repository.RegisterRepository;
import com.example.talenttracker.service.ApplyJobService;
@Service
public class ApplyJobServiceImpl implements ApplyJobService{

	@Autowired
	private RegisterRepository applicantRegisterRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ApplyJobRepository applyJobRepository;
	
	@Override
	public String applyJobForApplicant(long applicantId, long jobId) {
		// TODO Auto-generated method stub
		Applicant applicant=applicantRegisterRepository.findById(applicantId);
		Job job=jobRepository.findById(jobId).orElse(null);
		if(applicant==null || job==null) {
			return "Applicant Id or Job Id not found";
		}
		else {
			if(!applyJobRepository.existsByApplicantandJob(applicant,job)) {
				ApplyJob applyJob=new ApplyJob();
				applyJob.setApplicant(applicant);
				applyJob.setJob(job);
				applyJobRepository.save(applyJob);
				return "Job applied successfully";
			}else {
				return "Job has already been applied";
			}
		}
	}

}
