package com.example.talenttracker.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class GraduationDetails {

	@NotBlank
	private String gCollegeName;  
	@NotBlank 
	private String gBoard;
	@NotBlank
	private String gProgram;
	@NotBlank
	private String gPercentage;   
	@NotBlank
	private String gYearOfPassing;
	@NotBlank
	private String gCity;        
	@NotBlank
	private String gState;
}
