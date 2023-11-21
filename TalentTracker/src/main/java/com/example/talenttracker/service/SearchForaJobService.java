package com.example.talenttracker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.talenttracker.entity.Job;

public interface SearchForaJobService {

	public Page<Job> searchJobsBySkillAndApplicant(long applicantId, String skillName, Pageable pageable);
}
