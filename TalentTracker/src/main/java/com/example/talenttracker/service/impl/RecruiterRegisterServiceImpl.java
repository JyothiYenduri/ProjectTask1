package com.example.talenttracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.Recruiter;
import com.example.talenttracker.repository.RecruiterRegisterRepository;
import com.example.talenttracker.service.RecruiterRegisterService;

@Service
public class RecruiterRegisterServiceImpl implements RecruiterRegisterService{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RecruiterRegisterRepository recruiterRepo;

	@Override
	public Recruiter findByEmailAddress(String recruiterEmail) {
		// TODO Auto-generated method stub
		return recruiterRepo.findByEmail(recruiterEmail);
	}

	@Override
	public ResponseEntity<String> saveRecruiter(Recruiter recruiter) {
		// TODO Auto-generated method stub
		if(recruiterRepo.existsByEmail(recruiter.getEmail())) {
			return ResponseEntity.badRequest().body("Email already registered");
		}
		recruiter.setPwd(passwordEncoder.encode(recruiter.getPwd()));
		recruiterRepo.save(recruiter);
		return ResponseEntity.ok("Recruiter registered successfully");
	}

	@Override
	public List<Recruiter> getRecruiters() {
		// TODO Auto-generated method stub
		return recruiterRepo.findAll();
	}

}
