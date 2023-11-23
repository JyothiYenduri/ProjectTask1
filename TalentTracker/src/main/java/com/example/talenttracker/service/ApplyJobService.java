package com.example.talenttracker.service;

import java.util.List;

import com.example.talenttracker.dto.AppliedApplicantInfo;
import com.example.talenttracker.entity.Job;

public interface ApplyJobService {

	String applyJobForApplicant(long applicantId, long jobId);

	List<Job> getAppliedJobsForApplicant(long applicantId);

	List<AppliedApplicantInfo> getAppliedApplicants(long jobRecruiterId);

	String updateApplicantStatus(long applyJobId, String newStatus);

}
