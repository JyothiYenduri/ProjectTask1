package com.example.talenttracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPwdRequest {
 
	private String email;
	private String newPassword;
	private String confirmPassword;
}
