package com.example.talenttracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.talenttracker.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	public void sendOtpEmail(String to, String otp) {
		// TODO Auto-generated method stub
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("OTP to verify your Email");
		message.setText("Your OTP is: "+otp);
		javaMailSender.send(message);
	}

}
