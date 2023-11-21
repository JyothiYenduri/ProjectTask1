package com.example.talenttracker.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.ApplicantProfile;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.repository.ApplicantProfileRepository;
import com.example.talenttracker.repository.JobRepository;
import com.example.talenttracker.service.SearchForaJobService;
@Service
public class SearchForaJobServiceImpl implements SearchForaJobService{
	@Autowired
	private ApplicantProfileRepository applicantProfileRepository;
	@Autowired
	private JobRepository jobRepository;
	@Override
	public Page<Job> searchJobsBySkillAndApplicant(long applicantId, String skillName, Pageable pageable) {
		// TODO Auto-generated method stub
		Optional<ApplicantProfile> applicantOptional = applicantProfileRepository.findById(applicantId);
        
        if (applicantOptional.isPresent()) {
           return jobRepository.findJobsBySkillName(skillName, pageable);
        } else {
          List<Job> emptyJobList = Collections.emptyList();
            return new PageImpl<>(emptyJobList, pageable, 0);
        }
	}

}
