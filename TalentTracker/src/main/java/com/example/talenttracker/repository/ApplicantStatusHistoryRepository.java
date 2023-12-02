package com.example.talenttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talenttracker.entity.ApplicantStatusHistory;

public interface ApplicantStatusHistoryRepository extends JpaRepository<ApplicantStatusHistory, Long>{

	List<ApplicantStatusHistory> findByApplyJob_ApplyJobIdOrderByChangeDateDesc(long applyJobId);

}
