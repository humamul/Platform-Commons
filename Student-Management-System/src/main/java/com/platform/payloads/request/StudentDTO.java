package com.platform.payloads.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.platform.entity.Address;
import com.platform.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Integer unique_student_code;
	

	private String studentName;
	
	private String fatherName;
	
	private String motherName;
	
	private String email;
	
	private String mobile;
	
	private LocalDate dateOfBirth;
	
	private List<AddressDTO> addresses = new ArrayList<>();
	
	
}
