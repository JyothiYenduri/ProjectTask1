package com.example.talenttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talenttracker.entity.Alerts;

public interface AlertsRepository extends JpaRepository<Alerts, Long>{

	List<Alerts> findByApplyJob_ApplyJobIdOrderByChangeDateDesc(long applyJobId);

}
