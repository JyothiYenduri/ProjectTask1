package com.example.talenttracker.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.ApplicantProfile;
import com.example.talenttracker.entity.ApplicantSkills;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.repository.ApplicantProfileRepository;
import com.example.talenttracker.repository.JobRepository;
import com.example.talenttracker.service.FindRecommendedJobService;
@Service
public class FindRecommendedJobServiceImpl implements FindRecommendedJobService{
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private ApplicantProfileRepository applicantProfileRepository;

	@Override
	public List<Job> findJobsMatchingApplicantSkills(long applicantId) {
		// TODO Auto-generated method stub
		ApplicantProfile applicantProfile=applicantProfileRepository.findByApplicantId(applicantId);
		
		if(applicantProfile==null) {
			return Collections.emptyList();
		}
		Set<ApplicantSkills> applicantSkills=applicantProfile.getSkillsRequired();
		
		Set<String> lowercaseApplicantSkillNames = applicantSkills.stream()
                .map(skill -> skill.getSkillName().toLowerCase())
                .collect(Collectors.toSet());
		
		List<Job> matchingJobs = jobRepository.findBySkillsRequiredIgnoreCaseAndSkillNameIn(lowercaseApplicantSkillNames);
		return matchingJobs;
	}

}
