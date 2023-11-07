package com.example.talenttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.entity.CompanyProfile;
import com.example.talenttracker.entity.Recruiter;
import com.example.talenttracker.repository.RecruiterRegisterRepository;
import com.example.talenttracker.service.CompanyProfileService;

@RestController
public class CompanyProfileController {

	@Autowired
	private RecruiterRegisterRepository recruiterRegisterRepository;
	@Autowired
	private CompanyProfileService companyProfileService;

	@PostMapping("/create/company-profile/{recruiterId}")
	public ResponseEntity<String> createCompanyProfile(@RequestBody CompanyProfile companyProfile,
			@PathVariable Long recruiterId) {
		Recruiter recruiter = recruiterRegisterRepository.findByRecruiterId(recruiterId);
		if (recruiter != null) {
			companyProfile.setRecruiter(recruiter);
			companyProfileService.createCompanyProfile(companyProfile);
			return ResponseEntity.status(HttpStatus.OK).body("Company profile created successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("JobRecruiter with ID " + recruiterId + " not found.");
		}

	}
}
