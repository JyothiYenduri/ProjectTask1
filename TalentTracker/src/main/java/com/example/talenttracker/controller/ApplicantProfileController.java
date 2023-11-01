package com.example.talenttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.ApplicantProfile;
import com.example.talenttracker.repository.RegisterRepository;
import com.example.talenttracker.service.ApplicantProfileService;

@RestController
public class ApplicantProfileController {
	@Autowired
	private ApplicantProfileService aps;

	@Autowired
	private RegisterRepository registerRepository;
	
	@PostMapping("/applicant/createprofile/{applicantid}")
	public ResponseEntity<ApplicantProfile> createApplicantProfile(@PathVariable long applicantid,@RequestBody ApplicantProfile ap){
		Applicant applicant=registerRepository.getApplicantById(applicantid);
		if(applicant==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			ap.setApplicant(applicant);
			ApplicantProfile savedProfile=aps.createApplicantProfile(ap);
			return ResponseEntity.ok(savedProfile);
		}
		
		
	}
}
