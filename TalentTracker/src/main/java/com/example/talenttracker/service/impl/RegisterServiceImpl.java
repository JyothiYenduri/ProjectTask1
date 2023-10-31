package com.example.talenttracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.repository.RegisterRepository;
import com.example.talenttracker.service.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RegisterRepository applicantRepository;
	@Autowired
	public RegisterServiceImpl(PasswordEncoder passwordEncoder, RegisterRepository applicantRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.applicantRepository = applicantRepository;
	}

	@Override
	public Applicant findByEmailAddress(String userEmail) {
		return applicantRepository.findByEmail(userEmail);
	}

	@Override
	public ResponseEntity<String> saveApplicant(Applicant applicant) {
		// TODO Auto-generated method stub
		if (applicantRepository.existsByEmail(applicant.getEmail())) {
			return ResponseEntity.badRequest().body("Email already registered");
		}
		
			applicant.setPassword(passwordEncoder.encode(applicant.getPassword()));
			applicantRepository.save(applicant);
			return ResponseEntity.ok("Applicant registered successfully");
	}

}
