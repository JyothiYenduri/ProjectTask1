package com.example.talenttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.dto.TeamMemberDTO;
import com.example.talenttracker.entity.TeamMember;
import com.example.talenttracker.exception.CustomException;
import com.example.talenttracker.service.TeamMemberService;

@RestController
public class TeamMemberController {

	@Autowired
	private TeamMemberService teamMemberService;
	
	@PostMapping("/team-members/{recruiterId}")
	public ResponseEntity<TeamMember> addTeamMemberToRecruiter(@RequestBody TeamMemberDTO teamMember,@PathVariable long recruiterId){
		try {
			TeamMember savedTeamMember=teamMemberService.addTeamMemberToRecruiter(recruiterId,teamMember);
			return new ResponseEntity<>(savedTeamMember,HttpStatus.CREATED);
		}catch (CustomException e) {
	        return ResponseEntity.notFound().build();
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
