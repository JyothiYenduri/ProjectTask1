package com.example.talenttracker.service;

import java.util.List;

import com.example.talenttracker.entity.Job;

public interface SavedJobService {

	public void saveJobForApplicant(long applicantId, long jobId) throws Exception;

	public List<Job> getSavedJobForApplicant(long applicantId);
}
