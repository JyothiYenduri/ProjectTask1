package com.example.talenttracker.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class XclassDetails {

	@NotBlank
	private String xSchoolName;
	@NotBlank
	private String xBoard;
	@NotBlank
	private String xPercentage;
	@NotBlank
	private String xYearOfPassing;
	@NotBlank
	private String xCity;
	@NotBlank
	private String xState;
	@NotBlank
	private String xPincode;
}
