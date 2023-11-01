package com.example.talenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talenttracker.entity.ApplicantProfile;

public interface ApplicantProfileRepository extends JpaRepository<ApplicantProfile, Long>{

}
