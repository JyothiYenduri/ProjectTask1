package com.example.talenttracker.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talenttracker.dto.ApplicantJobInterviewDTO;
import com.example.talenttracker.dto.AppliedApplicantInfo;
import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.ApplicantStatusHistory;
import com.example.talenttracker.entity.ApplyJob;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.repository.ApplicantStatusHistoryRepository;
import com.example.talenttracker.repository.ApplyJobRepository;
import com.example.talenttracker.repository.JobRepository;
import com.example.talenttracker.repository.RegisterRepository;
import com.example.talenttracker.repository.ScheduleInterviewRepository;
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
	
	@Autowired
	private ScheduleInterviewRepository scheduleInterviewRepository;
	
	@Autowired
	private ApplicantStatusHistoryRepository statusHistoryRepository;
	
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
				saveStatusHistory(applyJob, applyJob.getApplicationStatus());
				return "Job applied successfully";
			}else {
				return "Job has already been applied";
			}
		}
	}

	private void saveStatusHistory(ApplyJob applyJob, String applicationStatus) {
		// TODO Auto-generated method stub
		ApplicantStatusHistory statusHistory=new ApplicantStatusHistory();
		statusHistory.setApplyJob(applyJob);
		statusHistory.setStatus(applicationStatus);
		statusHistory.setChangeDate(LocalDateTime.now());
		statusHistoryRepository.save(statusHistory);
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
		saveStatusHistory(applyJob, applyJob.getApplicationStatus());
		return "Applicant status updated to: "+newStatus;
	}

	@Override
	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForRecruiterAndStatus(long applicantId,
			String status) {
		// TODO Auto-generated method stub
		return scheduleInterviewRepository.getApplicantJobInterviewInfoByRecruiterAndStatus(applicantId,
			status);
	}
	
	@Override
	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewStatus(long applicantId) {
		// TODO Auto-generated method stub
		return scheduleInterviewRepository.getApplicantJobInterviewStatus(applicantId);
	}

	@Override
	public String checkInterviewStatus(long applicantId) {
		// TODO Auto-generated method stub
		List<ApplyJob> applyJobs=applyJobRepository.findByApplicantId(applicantId);
		String interviewStatus=checkInterviewStatus(applyJobs);
		return interviewStatus;
	}

	private String checkInterviewStatus(List<ApplyJob> applyJobs) {
		// TODO Auto-generated method stub
		if(!applyJobs.isEmpty()) {
			return applyJobs.get(0).getApplicationStatus();
		}
		return "No Interview Status Found";
	}

	@Override
	public List<ApplicantStatusHistory> getApplicantStatusHistory(long applyJobId) {
		// TODO Auto-generated method stub
		return statusHistoryRepository.findByApplyJob_ApplyJobIdOrderByChangeDateDesc(applyJobId);
	}
	
	
	
	
	
	

}
