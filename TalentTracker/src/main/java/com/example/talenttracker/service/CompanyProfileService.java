package com.example.talenttracker.service;

import java.util.Optional;

import com.example.talenttracker.entity.CompanyProfile;

public interface CompanyProfileService {

	public CompanyProfile createCompanyProfile(CompanyProfile companyProfile);

	public Optional<CompanyProfile> getCompanyProfileById(Long id);

}
