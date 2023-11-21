package com.example.talenttracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.entity.SavedJob;
import com.example.talenttracker.repository.JobRepository;
import com.example.talenttracker.repository.RegisterRepository;
import com.example.talenttracker.repository.SavedJobRepository;
import com.example.talenttracker.service.SavedJobService;

@Service
public class SavedJobServiceImpl implements SavedJobService {
	@Autowired
	private RegisterRepository applicantRegisterRepository;
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private SavedJobRepository savedJobRepository;
	

	@Override
	public void saveJobForApplicant(long applicantId, long jobId) throws Exception {
		// TODO Auto-generated method stub
		Applicant applicant=applicantRegisterRepository.findById(applicantId);
		Job job=jobRepository.findById(jobId).orElse(null);
		if(applicant==null || job==null) {
			throw new Exception("Applicant Id or Job Id not found");
		}
		else {
			if (!savedJobRepository.existsByApplicantAndJob(applicant, job)) {
                SavedJob savedJob = new SavedJob();
                savedJob.setApplicant(applicant);
                savedJob.setJob(job);
                savedJobRepository.save(savedJob);
            } else {
                throw new Exception("Job has already been saved by the applicant");
            }
		}
	}


	@Override
	public List<Job> getSavedJobForApplicant(long applicantId) {
		// TODO Auto-generated method stub
		List<Job> result=new ArrayList<>();
		try {
			List<SavedJob> savedJobs=savedJobRepository.findByApplicantId(applicantId);
			for(SavedJob savedJob:savedJobs) {
				result.add(savedJob.getJob());
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

}
