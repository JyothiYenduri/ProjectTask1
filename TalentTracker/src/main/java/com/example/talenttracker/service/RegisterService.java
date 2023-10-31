package com.example.talenttracker.service;

import org.springframework.http.ResponseEntity;

import com.example.talenttracker.entity.Applicant;

public interface RegisterService {

	public Applicant findByEmailAddress(String userEmail);

	public ResponseEntity<String> saveApplicant(Applicant applicant);
}
