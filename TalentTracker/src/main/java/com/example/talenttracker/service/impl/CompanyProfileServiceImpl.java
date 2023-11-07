package com.example.talenttracker.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.talenttracker.entity.CompanyProfile;
import com.example.talenttracker.repository.CompanyProfileRepository;
import com.example.talenttracker.service.CompanyProfileService;

@Service
public class CompanyProfileServiceImpl implements CompanyProfileService{

	@Autowired
	private CompanyProfileRepository companyProfileRepository;
	
	@Override
	public CompanyProfile createCompanyProfile(CompanyProfile companyProfile) {
		// TODO Auto-generated method stub
		return companyProfileRepository.save(companyProfile);
	}

	@Override
	public Optional<CompanyProfile> getCompanyProfileById(Long id) {
		// TODO Auto-generated method stub
		return companyProfileRepository.findById(id);
	}

}
