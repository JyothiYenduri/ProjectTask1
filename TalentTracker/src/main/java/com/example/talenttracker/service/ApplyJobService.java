package com.example.talenttracker.service;

import java.util.List;

import com.example.talenttracker.dto.ApplicantJobInterviewDTO;
import com.example.talenttracker.dto.AppliedApplicantInfo;
import com.example.talenttracker.entity.Alerts;
import com.example.talenttracker.entity.ApplicantStatusHistory;
import com.example.talenttracker.entity.Job;

public interface ApplyJobService {

	String applyJobForApplicant(long applicantId, long jobId);

	List<Job> getAppliedJobsForApplicant(long applicantId);

	List<AppliedApplicantInfo> getAppliedApplicants(long jobRecruiterId);

	String updateApplicantStatus(long applyJobId, String newStatus);

	List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForRecruiterAndStatus(long recruiterId, String status);

	List<ApplicantJobInterviewDTO> getApplicantJobInterviewStatus(long applicantId);

	String checkInterviewStatus(long applicantId);

	List<ApplicantStatusHistory> getApplicantStatusHistory(long applyJobId);

	List<Alerts> getAlerts(long applyJobId);

}
