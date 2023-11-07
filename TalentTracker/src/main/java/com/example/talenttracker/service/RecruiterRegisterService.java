package com.example.talenttracker.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.talenttracker.entity.Recruiter;

public interface RecruiterRegisterService {

	public Recruiter findByEmailAddress(String recruiterEmail);

	public ResponseEntity<String> saveRecruiter(Recruiter recruiter);

	public List<Recruiter> getRecruiters();

}
