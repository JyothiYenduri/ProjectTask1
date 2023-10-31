package com.example.talenttracker.service;

public interface OtpService {
	public String generateOtp(String userEmail);
	public boolean validateOtp(String userEmail, String enteredOtp);
}
