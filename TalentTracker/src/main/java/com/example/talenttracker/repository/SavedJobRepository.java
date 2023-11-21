package com.example.talenttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.entity.SavedJob;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long>{

	boolean existsByApplicantAndJob(Applicant applicant, Job job);

	List<SavedJob> findByApplicantId(long applicantId);

}
