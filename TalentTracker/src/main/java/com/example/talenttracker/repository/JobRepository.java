package com.example.talenttracker.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.talenttracker.entity.Job;

public interface JobRepository extends JpaRepository<Job,Long>{

	@Query("SELECT j FROM Job j WHERE j.recruiter.id=:jobRecruiterId")
	List<Job> findByJobRecruiterId(@Param("jobRecruiterId")  Long jobRecruiterId);
	
//	@Query("SELECT j FROM Job j " +
//            "WHERE (:jobTitle IS NULL OR LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%'))) " +
//            "AND (:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) " +
//            "AND (:industryType IS NULL OR LOWER(j.industryType) LIKE LOWER(CONCAT('%', :industryType, '%'))) " +
//            "AND (:empType IS NULL OR LOWER(j.empType) LIKE LOWER(CONCAT('%', :empType, '%'))) " +
//            "AND (:minimumQualification IS NULL OR LOWER(j.minimumQualification) LIKE LOWER(CONCAT('%', :minimumQualification, '%'))) " +
//            "AND (:specialization IS NULL OR LOWER(j.specialization) LIKE LOWER(CONCAT('%', :specialization, '%'))) " +
//            "AND (:skillName IS NULL OR LOWER(j.skillsRequired.skillName) LIKE LOWER(CONCAT('%', :skillName, '%'))) " +
//            "AND (:minimumExperience IS NULL OR j.skillsRequired.minimumExperience >= :minimumExperience) ")
//	
//    List<Job> searchJobsByFilters(
//            @Param("jobTitle") String jobTitle,
//            @Param("location") String location,
//            @Param("industryType") String industryType,
//            @Param("empType") String empType,
//            @Param("minimumQualification") String minimumQualification,
//            @Param("specialization") String specialization,
//            @Param("skillName") String skillName,
//            @Param("minimumExperience") Integer minimumExperience);
	
	@Query("SELECT DISTINCT j FROM Job j JOIN j.skillsRequired s WHERE " +
	        "(:skillName is null or s.skillName = :skillName) or " +
	        "(:jobTitle is null or j.jobTitle = :jobTitle) or " +
	        "(:location is null or j.location = :location) or " +
	        "(:industryType is null or j.industryType = :industryType) or " +
	        "(:employeeType is null or j.employeeType = :employeeType) or " +
	        "(:minimumQualification is null or j.minimumQualification = :minimumQualification) or " +
	        "(:specialization is null or j.specialization = :specialization)")
	List<Job> searchJobs(
	    @Param("skillName") String skillName,
	    @Param("jobTitle") String jobTitle,
	    @Param("location") String location,
	    @Param("industryType") String industryType,
	    @Param("employeeType") String employeeType,
	    @Param("minimumQualification") String minimumQualification,
	    @Param("specialization") String specialization
	    
	);

	Page<Job> findAll(Specification<Job> searchJobs, Pageable unpaged);
	
	@Query("SELECT DISTINCT j FROM Job j " +
	           "JOIN j.skillsRequired r " +
	           "WHERE LOWER(r.skillName) IN :skillNames")
	List<Job> findBySkillsRequiredIgnoreCaseAndSkillNameIn(Set<String> skillNames);

	@Query("SELECT DISTINCT j FROM Job j JOIN j.skillsRequired s WHERE s.skillName = :skillName")
	Page<Job> findJobsBySkillName(String skillName, Pageable pageable);

}
