package com.example.talenttracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.ApplyJob;
import com.example.talenttracker.entity.ScheduleInterview;
import com.example.talenttracker.repository.ApplyJobRepository;
import com.example.talenttracker.repository.ScheduleInterviewRepository;
import com.example.talenttracker.service.scheduleInterviewService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class scheduleInterviewServiceImpl implements scheduleInterviewService {

	@Autowired
	private ApplyJobRepository applyJobRepository;
	@Autowired
	private ScheduleInterviewRepository scheduleInterviewRepository;
	
	@Override
	public ScheduleInterview scheduleInterview(long applyJobId, ScheduleInterview scheduleInterview) {
		// TODO Auto-generated method stub
		ApplyJob applyJob=applyJobRepository.findById(applyJobId).orElseThrow(()->new EntityNotFoundException("Apply Job not found"));
		scheduleInterview.setApplyJob(applyJob);
		return scheduleInterviewRepository.save(scheduleInterview);
	}

	

}
