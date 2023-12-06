package com.example.talenttracker.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.talenttracker.dto.TeamMemberDTO;
import com.example.talenttracker.entity.Recruiter;
import com.example.talenttracker.entity.TeamMember;
import com.example.talenttracker.exception.CustomException;
import com.example.talenttracker.repository.RecruiterRegisterRepository;
import com.example.talenttracker.repository.TeamMemberRepository;
import com.example.talenttracker.service.TeamMemberService;

@Service
public class TeamMemberServiceImpl implements TeamMemberService{

	@Autowired
	private RecruiterRegisterRepository recruiterRepository;
	@Autowired
	private TeamMemberRepository teamMemberRepository;
	
	@Override
	public TeamMember addTeamMemberToRecruiter(long recruiterId, TeamMemberDTO teamMemberDTO) {
		// TODO Auto-generated method stub
		Recruiter recruiter = recruiterRepository.findById(recruiterId)
	            .orElseThrow(() -> new CustomException("Recruiter with ID " + recruiterId + " not found.", HttpStatus.INTERNAL_SERVER_ERROR));

	    TeamMember teamMember = new TeamMember();
	    teamMember.setName(teamMemberDTO.getName());
	    teamMember.setRole(teamMemberDTO.getRole());
	    teamMember.setEmail(teamMemberDTO.getEmail());
	    // Map other fields as needed

	    teamMember.setRecruiter(recruiter);

	    return teamMemberRepository.save(teamMember);

	}

}
