package com.example.talenttracker.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.talenttracker.dto.ApplicantJobInterviewDTO;
import com.example.talenttracker.dto.AppliedApplicantInfo;
import com.example.talenttracker.entity.Alerts;
import com.example.talenttracker.entity.Applicant;
import com.example.talenttracker.entity.ApplicantStatusHistory;
import com.example.talenttracker.entity.ApplyJob;
import com.example.talenttracker.entity.CompanyProfile;
import com.example.talenttracker.entity.Job;
import com.example.talenttracker.entity.Recruiter;
import com.example.talenttracker.repository.AlertsRepository;
import com.example.talenttracker.repository.ApplicantStatusHistoryRepository;
import com.example.talenttracker.repository.ApplyJobRepository;
import com.example.talenttracker.repository.CompanyProfileRepository;
import com.example.talenttracker.repository.JobRepository;
import com.example.talenttracker.repository.RegisterRepository;
import com.example.talenttracker.repository.ScheduleInterviewRepository;
import com.example.talenttracker.service.ApplyJobService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class ApplyJobServiceImpl implements ApplyJobService{

	@Autowired
	private RegisterRepository applicantRegisterRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ApplyJobRepository applyJobRepository;
	
	@Autowired
	private ScheduleInterviewRepository scheduleInterviewRepository;
	
	@Autowired
	private ApplicantStatusHistoryRepository statusHistoryRepository;
	
	@Autowired
	private AlertsRepository alertsRepository;
	
	@Autowired
	private CompanyProfileRepository companyProfileRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public String applyJobForApplicant(long applicantId, long jobId) {
		// TODO Auto-generated method stub
		Applicant applicant=applicantRegisterRepository.findById(applicantId);
		Job job=jobRepository.findById(jobId).orElse(null);
		if(applicant==null || job==null) {
			return "Applicant Id or Job Id not found";
		}
		else {
			if(!applyJobRepository.existsByApplicantAndJob(applicant,job)) {
				ApplyJob applyJob=new ApplyJob();
				applyJob.setApplicant(applicant);
				applyJob.setJob(job);
				applyJobRepository.save(applyJob);
				
				// Increment alert count
		        incrementAlertCount(applyJob.getApplicant());
		        
		        //SaveStatusHistory
				saveStatusHistory(applyJob, applyJob.getApplicationStatus());
//				CompanyProfile cp=new CompanyProfile();
//				sendAlerts(applyJob,applyJob.getApplicationStatus(),cp.getCompanyName());
				Job applyJobJob = applyJob.getJob();
	            if (applyJobJob != null) {
	                Recruiter recruiter = applyJobJob.getRecruiter();
	                if (recruiter != null) {
	                    String companyName = recruiter.getCompanyName();
	                    if (companyName != null) {
	                        String cN = recruiter.getCompanyName();
	                        //SendAlerts
	                        sendAlerts(applyJob, applyJob.getApplicationStatus(), cN);
	                        return "Job applied successfully";
	                    }
	                }
	            }return "Company information not found for the given ApplyJob";
	        } else {
				return "Job has already been applied";
			}
		}
	}

	private void sendAlerts(ApplyJob applyJob, String applicationStatus, String companyName) {
		// TODO Auto-generated method stub
		Alerts alerts=new Alerts();
		alerts.setApplyJob(applyJob);
		alerts.setCompanyName(companyName);
		alerts.setStatus(applicationStatus);
		alerts.setChangeDate(LocalDate.now());
		alertsRepository.save(alerts);
		// Send email to the applicant
        sendEmailToApplicant(applyJob.getApplicant().getEmail(), companyName, applicationStatus);
	}

	private void sendEmailToApplicant(String toEmail, String companyName, String applicationStatus) {
		// TODO Auto-generated method stub
		try {
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
			SimpleMailMessage message=new SimpleMailMessage();
			// Set email properties
			message.setTo(toEmail);
			message.setSubject("Job Alert Notification");
			// Customize your email content
            String content = "Dear Applicant,\n\n"
                    + "Your job application status has been updated to: " + applicationStatus + "\n"
                    + "Company: " + companyName + "\n\n"
                    + "Thank you.\n\n"
                    + "Best regards,\n"
                    + "Your Company Name";

            message.setText(content);
            
            // Send the email
            javaMailSender.send(message);
        } catch (Exception e) {
            // Handle exceptions, log, and consider appropriate error handling
        	e.printStackTrace();
        }
    }
		

	private void saveStatusHistory(ApplyJob applyJob, String applicationStatus) {
		// TODO Auto-generated method stub
		ApplicantStatusHistory statusHistory=new ApplicantStatusHistory();
		statusHistory.setApplyJob(applyJob);
		statusHistory.setStatus(applicationStatus);
		statusHistory.setChangeDate(LocalDateTime.now());
		statusHistoryRepository.save(statusHistory);
	}

	@Override
	public List<Job> getAppliedJobsForApplicant(long applicantId) {
		// TODO Auto-generated method stub
		List<Job> result=new ArrayList<>();
		try {
	          List<ApplyJob> appliedJobs = applyJobRepository.findByApplicantId(applicantId);

	          for (ApplyJob appliedJobs1 : appliedJobs) {
	              result.add(appliedJobs1 .getJob());
	          }

	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }

	      return result;
		
	}

	@Override
	public List<AppliedApplicantInfo> getAppliedApplicants(long jobRecruiterId) {
		// TODO Auto-generated method stub
		List<AppliedApplicantInfo> appliedApplicants=applyJobRepository.findAppliedApplicantsInfo(jobRecruiterId);
		
		return appliedApplicants;
		
	}

	@Override
	public String updateApplicantStatus(long applyJobId, String newStatus) {
		// TODO Auto-generated method stub
		ApplyJob applyJob=applyJobRepository.findById(applyJobId).orElseThrow(()->new EntityNotFoundException("Apply job not found"));
		
		Job job = applyJob.getJob();
	    if (job != null) {
	        Recruiter recruiter = job.getRecruiter();
	        if (recruiter != null) {
	            String companyName = recruiter.getCompanyName();
	            if (companyName != null) {
		applyJob.setApplicationStatus(newStatus);
		applyJobRepository.save(applyJob);
		//Increment alert count
		incrementAlertCount(applyJob.getApplicant());
		// Save status history
		saveStatusHistory(applyJob, applyJob.getApplicationStatus());
		//Send alerts
		sendAlerts(applyJob,applyJob.getApplicationStatus(),companyName);
		return "Applicant status updated to: "+newStatus;
	            }}}
	    return "Company information not found for the given ApplyJob";
	}

	private void incrementAlertCount(Applicant applicant) {
		// TODO Auto-generated method stub
		if (applicant != null) {
            int currentAlertCount = applicant.getAlertCount();
            applicant.setAlertCount(currentAlertCount + 1);
            applicantRegisterRepository.save(applicant);
        }
	}

	@Override
	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewInfoForRecruiterAndStatus(long applicantId,
			String status) {
		// TODO Auto-generated method stub
		return scheduleInterviewRepository.getApplicantJobInterviewInfoByRecruiterAndStatus(applicantId,
			status);
	}
	
	@Override
	public List<ApplicantJobInterviewDTO> getApplicantJobInterviewStatus(long applicantId) {
		// TODO Auto-generated method stub
		return scheduleInterviewRepository.getApplicantJobInterviewStatus(applicantId);
	}

	@Override
	public String checkInterviewStatus(long applicantId) {
		// TODO Auto-generated method stub
		List<ApplyJob> applyJobs=applyJobRepository.findByApplicantId(applicantId);
		String interviewStatus=checkInterviewStatus(applyJobs);
		return interviewStatus;
	}

	private String checkInterviewStatus(List<ApplyJob> applyJobs) {
		// TODO Auto-generated method stub
		if(!applyJobs.isEmpty()) {
			return applyJobs.get(0).getApplicationStatus();
		}
		return "No Interview Status Found";
	}

	@Override
	public List<ApplicantStatusHistory> getApplicantStatusHistory(long applyJobId) {
		// TODO Auto-generated method stub
		return statusHistoryRepository.findByApplyJob_ApplyJobIdOrderByChangeDateDesc(applyJobId);
	}

	@Override
	public List<Alerts> getAlerts(long applyJobId) {
		// TODO Auto-generated method stub
		return alertsRepository.findByApplyJob_ApplyJobIdOrderByChangeDateDesc(applyJobId);
	}

	@Override
	public void resetAlertCount(long applyJobId) {
		// TODO Auto-generated method stub
		try {
	        ApplyJob applyJob = applyJobRepository.findById(applyJobId)
	                .orElseThrow(() -> new EntityNotFoundException("Apply job not found"));

	        applyJob.getApplicant().setAlertCount(0);
	        applyJobRepository.save(applyJob);
	    } catch (Exception e) {
	        // Handle exceptions, log, and consider appropriate error handling
	    	e.printStackTrace();
	    }
	}
	
	
	
	
	
	

}
