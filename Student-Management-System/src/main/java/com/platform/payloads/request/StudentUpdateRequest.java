package com.platform.payloads.request;

import java.time.LocalDate;

import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentUpdateRequest {
	@JsonProperty(access = Access.READ_ONLY)
	private Integer unique_student_code;
	

	private String studentName;
	
	private String fatherName;
	
	private String motherName;
	
	private String email;
	
	private String mobile;
	@Past
	private LocalDate dateOfBirth;
}
