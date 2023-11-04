package com.example.talenttracker.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class BasicDetails {

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{3,19}$",message = "Please enter valid name")
	private String firstName;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]{3,9}$",message = "Please enter valid name")
	private String lastName;
	@NotBlank
	private String DOB;
	@NotBlank
	private String address;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
	@NotBlank
	@Pattern(regexp = "^\\d{6}$",message = "Please enter valid PinCode")
	private String pincode;
	@NotBlank
	@Email(message = "Please enter valid mail address")
	private String email;
	@NotBlank
	@Pattern(regexp = "^[6-9]\\d{9}$",message = "Please enter valid Mobile number")
	private String alternatePhNum;
}

