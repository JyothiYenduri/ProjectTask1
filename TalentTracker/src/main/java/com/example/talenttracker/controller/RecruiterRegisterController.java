package com.example.talenttracker.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.dto.OtpVerificationRequest;
import com.example.talenttracker.dto.SendOtpDetails;
import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.Recruiter;
import com.example.talenttracker.service.EmailService;
import com.example.talenttracker.service.OtpService;
import com.example.talenttracker.service.RecruiterRegisterService;

@RestController
public class RecruiterRegisterController {

	@Autowired
	private OtpService otpService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RecruiterRegisterService recruiterRegisterService;
	
	private Map<String, Boolean> otpVerificationMap=new HashMap<>();
	
	@PostMapping("/recruiter/sendotp")
	public ResponseEntity<String> sendOtp(@RequestBody Recruiter request){
		String recruiterEmail=request.getEmail();
		Recruiter recruiter=recruiterRegisterService.findByEmailAddress(recruiterEmail);
		if(recruiter==null) {
			String otp=otpService.generateOtp(recruiterEmail);
			emailService.sendOtpEmail(recruiterEmail, otp);
			otpVerificationMap.put(recruiterEmail, true);
			return ResponseEntity.ok("OTP sent successfully");
		}
		else {
			return ResponseEntity.badRequest().body("Email is already registered");
		}
	}
	
	@PostMapping("/recruiter/verifyotp")
	public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest verificationRequest){
		String otp=verificationRequest.getOtp();
		String email=verificationRequest.getEmail();
		if(otpService.validateOtp(email, otp)) {
			return ResponseEntity.ok("OTP verified successfully");
		}else {
			return ResponseEntity.badRequest().body("Incorrect OTP");
		}
		
	}
	
	@PostMapping("/recruiter/registration")
	public ResponseEntity<String> recruiterRegister(@RequestBody Recruiter recruiter){
		return recruiterRegisterService.saveRecruiter(recruiter);
	}
	
	@PostMapping("/recruiter/forgotpassword/sendotp")
	public ResponseEntity<String> forgotPwd(@RequestBody SendOtpDetails request){
		String recruiterEmail=request.getEmail();
		Recruiter recruiter=recruiterRegisterService.findByEmailAddress(recruiterEmail);
		if(recruiter!=null) {
			String otp=otpService.generateOtp(recruiterEmail);
			emailService.sendOtpEmail(recruiterEmail, otp);
			otpVerificationMap.put(recruiterEmail, true);
			return ResponseEntity.ok("OTP sent successfully");
		}
		else
		return ResponseEntity.badRequest().body("Recruiter not registered yet");
		
	}
	
	
}
