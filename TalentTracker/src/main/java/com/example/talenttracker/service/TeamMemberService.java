package com.example.talenttracker.service;

import com.example.talenttracker.dto.TeamMemberDTO;
import com.example.talenttracker.entity.TeamMember;

public interface TeamMemberService {

	public TeamMember addTeamMemberToRecruiter(long recruiterId, TeamMemberDTO teamMember);

}
