package com.example.talenttracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.dto.AppliedApplicantInfo;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.entity.ScheduleInterview;
import com.example.talenttracker.service.ApplyJobService;
import com.example.talenttracker.service.scheduleInterviewService;

@RestController
@RequestMapping("/applyJob")
public class ApplyJobController {

	@Autowired
	private ApplyJobService applyJobService;
	@Autowired
	private scheduleInterviewService scheduleInterviewService;
	
	@PostMapping("/applicant/applyJob/{applicantId}/{jobId}")
	public String applyJobForApplicant(@PathVariable long applicantId, @PathVariable long jobId) {
		return applyJobService.applyJobForApplicant(applicantId,jobId);
	}
	
	@GetMapping("/getAppliedJobs/{applicantId}")
	public ResponseEntity<List<Job>> getAppliedJobsForApplicant(@PathVariable long applicantId){
		try {
	         List<Job> AppliedJobs = applyJobService.getAppliedJobsForApplicant(applicantId);
		            return ResponseEntity.ok(AppliedJobs);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
		 }
	}
	
	@GetMapping("/recruiter/{jobRecruiterId}/appliedapplicants")
	public ResponseEntity<List<AppliedApplicantInfo>> getAppliedApplicantsForRecruiter(@PathVariable long jobRecruiterId){
		List<AppliedApplicantInfo> appliedApplicants=applyJobService.getAppliedApplicants(jobRecruiterId);
		return ResponseEntity.ok(appliedApplicants);
	}
	
	@PostMapping("/recruiter/scheduleInterview/{applyJobId}")
	public ResponseEntity<Void> scheduleInterview(@PathVariable long applyJobId, @RequestBody ScheduleInterview scheduleInterview){
		ScheduleInterview isCreated=scheduleInterviewService.scheduleInterview(applyJobId,scheduleInterview);
		if(isCreated!=null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/recruiters/applyjob-update-status/{applyJobId}/{newStatus}")
	public ResponseEntity<String> updateApplicantStatus(@PathVariable long applyJobId, @PathVariable String newStatus){
		String updateStatus=applyJobService.updateApplicantStatus(applyJobId,newStatus);
		return ResponseEntity.ok(updateStatus);
	}
	
	
	
}
