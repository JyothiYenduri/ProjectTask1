package com.example.talenttracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talenttracker.dto.AppliedApplicantInfo;
import com.example.talenttracker.dto.AppliedApplicantInfoDTO;
import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.ApplyJob;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.repository.ApplyJobRepository;
import com.example.talenttracker.repository.JobRepository;
import com.example.talenttracker.repository.RegisterRepository;
import com.example.talenttracker.service.ApplyJobService;

import jakarta.persistence.EntityNotFoundException;
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
			if(!applyJobRepository.existsByApplicantAndJob(applicant,job)) {
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

	@Override
	public List<Job> getAppliedJobsForApplicant(long applicantId) {
		// TODO Auto-generated method stub
		List<Job> result=new ArrayList<>();
		try {
	          List<ApplyJob> appliedJobs = applyJobRepository.findByApplicantId(applicantId);

	          for (ApplyJob appliedJobs1 : appliedJobs) {
	              result.add(appliedJobs1 .getJob());
	          }

	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }

	      return result;
		
	}

	@Override
	public List<AppliedApplicantInfo> getAppliedApplicants(long jobRecruiterId) {
		// TODO Auto-generated method stub
		List<AppliedApplicantInfo> appliedApplicants=applyJobRepository.findAppliedApplicantsInfo(jobRecruiterId);
		
		return appliedApplicants;
		
	}

	@Override
	public String updateApplicantStatus(long applyJobId, String newStatus) {
		// TODO Auto-generated method stub
		ApplyJob applyJob=applyJobRepository.findById(applyJobId).orElseThrow(()->new EntityNotFoundException("Apply job not found"));
		applyJob.setApplicationStatus(newStatus);
		applyJobRepository.save(applyJob);
		return "Applicant status updated to: "+newStatus;
	}
	
	
	
	

}
