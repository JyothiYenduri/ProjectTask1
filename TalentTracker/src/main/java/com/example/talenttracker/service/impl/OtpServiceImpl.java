package com.example.talenttracker.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.example.talenttracker.service.OtpService;

@Service
public class OtpServiceImpl implements OtpService{
	
	private static final long OTP_VALID_DURATION_MS = 120 * 1000;
	
	private Map<String, OtpData> otpMap=new HashMap<>();

	@Override
	public String generateOtp(String userEmail) {
		// TODO Auto-generated method stub
		String otp = generateRandomOtp();
		otpMap.put(userEmail, new OtpData(otp));
		return otp;
	}
	
	@Override
	public boolean validateOtp(String userEmail, String enteredOtp) {
		// TODO Auto-generated method stub
		OtpData otpData=otpMap.get(userEmail);
		if(otpData!=null && otpData.isValid(enteredOtp)) {
			otpMap.remove(userEmail);
			return true;
		}
		return false;
	}

	private String generateRandomOtp() {
		// TODO Auto-generated method stub
		return RandomStringUtils.randomNumeric(6);
	}
	
	private static class OtpData{
		private String otp;
		private long creationTime;
		
		public OtpData(String otp) {
			this.otp = otp;
			this.creationTime = System.currentTimeMillis();
		}
		
		public boolean isValid(String enteredOtp) {
            long currentTime = System.currentTimeMillis();
            return currentTime - creationTime <= OTP_VALID_DURATION_MS && otp.equals(enteredOtp);
        }

        /*public long getCreationTime() {
            return creationTime;
        }*/
		
	}

	

}
