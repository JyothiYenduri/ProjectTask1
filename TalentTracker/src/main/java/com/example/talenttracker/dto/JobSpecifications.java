package com.example.talenttracker.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.talenttracker.entity.Job;

import jakarta.persistence.criteria.Predicate;

public class JobSpecifications {

	public static Specification<Job> searchJobs(
            String skillName,
            String jobTitle,
            String location,
            String industryType,
            String employeeType,
            String minimumQualification,
            String specialization
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
 
            if (skillName != null && !skillName.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.join("skillsRequired").get("skillName"), skillName));
            }
 
            if (jobTitle != null && !jobTitle.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("jobTitle"), jobTitle));
            }
 
            if (location != null && !location.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("location"), location));
            }
 
            if (industryType != null && !industryType.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("industryType"), industryType));
            }
 
            if (employeeType != null && !employeeType.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("employeeType"), employeeType));
            }
 
            if (minimumQualification != null && !minimumQualification.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("minimumQualification"), minimumQualification));
            }
 
            if (specialization != null && !specialization.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("specialization"), specialization));
            }
 
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
