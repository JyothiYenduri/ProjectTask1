package com.example.talenttracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.ApplicantProfile;
import com.example.talenttracker.repository.ApplicantProfileRepository;
import com.example.talenttracker.service.ApplicantProfileService;
@Service
public class ApplicantProfileServiceImpl implements ApplicantProfileService{

	@Autowired
	private ApplicantProfileRepository apr;
	@Override
	public ApplicantProfile createApplicantProfile(ApplicantProfile ap) {
		// TODO Auto-generated method stub
		return apr.save(ap);
	}

}
