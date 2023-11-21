package com.example.talenttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.entity.Job;
import com.example.talenttracker.service.FindRecommendedJobService;

@RestController
@RequestMapping("/recommendedjob")
public class FindRecommendedJobController {

	@Autowired
	private FindRecommendedJobService findRecommendedJobService;
	
	@GetMapping("/findrecommendedjob/{applicantId}")
	public List<Job> recommendedJobsForApplicant(@PathVariable long applicantId){
		return findRecommendedJobService.findJobsMatchingApplicantSkills(applicantId);
	}
}
