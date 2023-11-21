package com.example.talenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.ApplyJob;
import com.example.talenttracker.entity.Job;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Long>{

	boolean existsByApplicantandJob(Applicant applicant, Job job);

}
