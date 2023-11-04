package com.example.talenttracker.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class IntermediateDetails {

	@NotBlank
	private String icollegeName;
	@NotBlank
	private String iBoard;
	@NotBlank
	private String iProgram;
	@NotBlank
	private String iYearOfPassing;
	@NotBlank
	private String iCity;
	@NotBlank
	private String iState;
	
	
}
