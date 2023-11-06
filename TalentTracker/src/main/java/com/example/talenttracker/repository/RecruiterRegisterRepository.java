package com.example.talenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talenttracker.entity.Recruiter;

public interface RecruiterRegisterRepository extends JpaRepository<Recruiter, Long>{

	Recruiter findByEmail(String recruiterEmail);

	boolean existsByEmail(String email);

}
