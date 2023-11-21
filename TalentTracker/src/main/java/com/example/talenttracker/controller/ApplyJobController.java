package com.example.talenttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.service.ApplyJobService;

@RestController
@RequestMapping("/applyJob")
public class ApplyJobController {

	@Autowired
	private ApplyJobService applyJobService;
	
	@PostMapping("/applicant/applyJob/{applicantId}/{jobId}")
	public String applyJobForApplicant(@PathVariable long applicantId, @PathVariable long jobId) {
		return applyJobService.applyJobForApplicant(applicantId,jobId);
	}
}
