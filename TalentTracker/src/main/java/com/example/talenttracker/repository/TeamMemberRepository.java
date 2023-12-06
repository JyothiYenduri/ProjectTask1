package com.example.talenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.talenttracker.entity.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>{

}
