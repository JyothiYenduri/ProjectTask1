package com.example.talenttracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.example.talenttracker.entity.Recruiter;
import com.example.talenttracker.repository.RecruiterRegisterRepository;
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
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RecruiterRegisterService recruiterRegisterService;
	@Autowired
	private RecruiterRegisterRepository recruiterRegisterRepository;
	
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
	
	@PostMapping("/recruiter/reset-password")
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
		Recruiter recruiter=recruiterRegisterService.findByEmailAddress(email);
		if(recruiter==null) {
			return ResponseEntity.badRequest().body("User not found");
		}
		recruiter.setPwd(passwordEncoder.encode(newPassword));
		recruiterRegisterRepository.save(recruiter);
		return ResponseEntity.ok("New Password set successfully");
		
	}
	
	@PostMapping("/recruiter/login")
	public ResponseEntity<Object> login(@RequestBody Login loginRequest) {
	    
	    Recruiter recruiter = recruiterRegisterRepository.findByEmail(loginRequest.getEmail());

	    if (recruiter == null || !passwordEncoder.matches(loginRequest.getPassword(), recruiter.getPwd())) {
	        return ResponseEntity.badRequest().body("Invalid username or password");
	    }

	    	return ResponseEntity.ok("Login successful");
	}
	
	@GetMapping("/showAllRecruiters")
	public ResponseEntity<List<Recruiter>> getAllRecriters(){
		List<Recruiter> recruiters=recruiterRegisterService.getRecruiters();
		return ResponseEntity.ok(recruiters);
	}
	
	@PostMapping("/recruiter/logout")
    public ResponseEntity<String> logout() {
		return ResponseEntity.ok("Logged out successfully");
    }
	
}
