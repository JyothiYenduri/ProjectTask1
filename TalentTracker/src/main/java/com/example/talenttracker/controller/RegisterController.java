package com.example.talenttracker.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.dto.OtpVerificationRequest;
import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.service.EmailService;
import com.example.talenttracker.service.OtpService;
import com.example.talenttracker.service.RegisterService;

@RestController
public class RegisterController {
	@Autowired
	private OtpService otpService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RegisterService registerService;
	
	private Map<String, Boolean> otpVerificationMap=new HashMap<>();

	@PostMapping("/applicant/sendotp")
	public ResponseEntity<String> sendOtp(@RequestBody Applicant request){
		String userEmail=request.getEmail();
		Applicant applicant=registerService.findByEmailAddress(userEmail);
		if(applicant==null) {
			String otp=otpService.generateOtp(userEmail);
			emailService.sendOtpEmail(userEmail, otp);
			otpVerificationMap.put(userEmail, true);
			return ResponseEntity.ok("OTP sent to your email");
		}
		else {
			return ResponseEntity.badRequest().body("Email is already registered");
		}
	}
	
	@PostMapping("/applicant/verifyotp")
	public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest verificationRequest){
		String otp=verificationRequest.getOtp();
		String email=verificationRequest.getEmail();
		if(otpService.validateOtp(email, otp)) {
			return ResponseEntity.ok("OTP verified successfully");
		}else {
			return ResponseEntity.badRequest().body("Incorrect OTP");
		}
		
	}
	
	@PostMapping("/applicant/registration")
	public ResponseEntity<String> register(@RequestBody Applicant applicant){
		return registerService.saveApplicant(applicant);
	}
	
}
