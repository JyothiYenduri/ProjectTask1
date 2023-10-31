package com.example.talenttracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpVerificationRequest {
	
	private String email;
	private String otp;
}
