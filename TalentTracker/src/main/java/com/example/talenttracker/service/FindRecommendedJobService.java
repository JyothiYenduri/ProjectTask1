package com.example.talenttracker.service;

import java.util.List;

import com.example.talenttracker.entity.Job;

public interface FindRecommendedJobService {

	public List<Job> findJobsMatchingApplicantSkills(long applicantId);
}
