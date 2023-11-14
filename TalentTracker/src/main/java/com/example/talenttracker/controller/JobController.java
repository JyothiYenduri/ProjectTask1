package com.example.talenttracker.controller;

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

import com.example.talenttracker.entity.Job;
import com.example.talenttracker.entity.Recruiter;
import com.example.talenttracker.repository.RecruiterRegisterRepository;
import com.example.talenttracker.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobService jobService;
	
	@Autowired
	private RecruiterRegisterRepository recruiterRepository;
	
	@PostMapping("/recruiter/postjob/{recruiterid}")
	public ResponseEntity<String> postJob(@RequestBody @Valid Job job, @PathVariable Long recruiterid){
		Recruiter recruiter=recruiterRepository.findByRecruiterId(recruiterid);
		if(recruiter!=null) {
			job.setRecruiter(recruiter);
			jobService.postJob(job);
			return ResponseEntity.status(HttpStatus.OK).body("Job posted successfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("recruiter with id "+ recruiterid +" not found");
		}
	}
	
	@GetMapping("/recruiter/viewJobs")
	public ResponseEntity<List<Job>> getAllJobs(){
		List<Job> jobs=jobService.getAllJobs();
		return ResponseEntity.ok(jobs);
	}
	
	@GetMapping("/recruiter/getJobBy/JobId/{jobId}")
	public ResponseEntity<Job> getJobById(@PathVariable Long jobId){
		Job job=jobService.getJobById(jobId);
		if(job!=null) {
			return ResponseEntity.ok(job);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/recruiter/viewJob/RecruiterId/{jobRecruiterId}")
	public ResponseEntity<List<Job>> getJobByRecruiterID(@PathVariable Long jobRecruiterId){
		List<Job> jobs=jobService.getJobByRecruiterID(jobRecruiterId);
		if(jobs.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(jobs);
		}
	}
	
}
