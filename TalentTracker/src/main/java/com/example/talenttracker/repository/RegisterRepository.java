package com.example.talenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.talenttracker.entity.Applicant;
import java.util.List;


public interface RegisterRepository extends JpaRepository<Applicant, Long>{

	Applicant findByEmail(String userEmail);

	boolean existsByEmail(String email);

	Applicant getApplicantById(long applicantid);

	Applicant findById(long id);
	

}
