package com.example.talenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talenttracker.entity.Job;

public interface JobRepository extends JpaRepository<Job,Long>{

}
