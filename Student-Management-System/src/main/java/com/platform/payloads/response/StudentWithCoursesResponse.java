package com.platform.payloads.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.platform.payloads.request.CourseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithCoursesResponse {
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer unique_student_code;
	

	private String studentName;
	
	private String fatherName;
	
	private String motherName;
	
	private String email;
	
	private String mobile;
	
	private LocalDate dateOfBirth;
	
	private List<CourseDTO> courses = new ArrayList<>();
}
