package com.example.talenttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.talenttracker.entity.Job;

public interface JobRepository extends JpaRepository<Job,Long>{

	@Query("SELECT j FROM Job j WHERE j.recruiter.id=:jobRecruiterId")
	List<Job> findByJobRecruiterId(@Param("jobRecruiterId")  Long jobRecruiterId);

}
