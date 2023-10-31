package com.example.talenttracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.event.PrintJobAttributeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.talenttracker.dto.Login;
import com.example.talenttracker.dto.NewPwdRequest;
import com.example.talenttracker.dto.OtpVerificationRequest;
import com.example.talenttracker.dto.SendOtpDetails;
import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.repository.RegisterRepository;
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
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RegisterRepository appliRepository;
	
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
	
	@PostMapping("/applicant/forgotpassword/sendotp")
	public ResponseEntity<String> forgotPwd(@RequestBody SendOtpDetails request){
		String userEmail=request.getEmail();
		Applicant applicant=registerService.findByEmailAddress(userEmail);
		if(applicant!=null) {
			String otp=otpService.generateOtp(userEmail);
			emailService.sendOtpEmail(userEmail, otp);
			otpVerificationMap.put(userEmail, true);
			return ResponseEntity.ok("OTP sent successfully");
		}
		else
		return ResponseEntity.badRequest().body("Applicant not registered yet");
		
	}
	
	@PostMapping("/applicant/reset-password")
	public ResponseEntity<String> setNewPwd(@RequestBody NewPwdRequest request){
		String email=request.getEmail();
		String newPassword=request.getNewPassword();
		String confirmPassword=request.getConfirmPassword();
		if(email==null) {
			return ResponseEntity.badRequest().body("Email not found");
		}
		if (!newPassword.equals(confirmPassword)) {
	        return ResponseEntity.badRequest().body("New password and confirm password do not match");
	    }
		Applicant applicant=registerService.findByEmailAddress(email);
		if(applicant==null) {
			return ResponseEntity.badRequest().body("User not found");
		}
		applicant.setPassword(passwordEncoder.encode(newPassword));
		appliRepository.save(applicant);
		return ResponseEntity.ok("New Password set successfully");
		
	}
	
	
	@PostMapping("/applicant/login")
	public ResponseEntity<Object> login(@RequestBody Login loginRequest) {
	    
	    Applicant applicant = appliRepository.findByEmail(loginRequest.getEmail());

	    if (applicant == null || !passwordEncoder.matches(loginRequest.getPassword(), applicant.getPassword())) {
	        return ResponseEntity.badRequest().body("Invalid username or password");
	    }

	    	return ResponseEntity.ok("Login successful");
	}
	
	@GetMapping("/showAllApplicants")
	public ResponseEntity<List<Applicant>> getAllApplicants(){
		List<Applicant> applicants=registerService.getApplicants();
		return ResponseEntity.ok(applicants);
	}

	@PostMapping("/applicant/logout")
    public ResponseEntity<String> logout() {
//        if (currentUser != null) {
//            currentUser = null;
//            return ResponseEntity.ok("Logged out successfully");
//        } else {
//            return ResponseEntity.badRequest().body("No user is logged in");
//        }
		return ResponseEntity.ok("Logged out successfully");
    }
}
