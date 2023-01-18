package com.platform.payloads.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAuthenticationRequest {

	private Integer student_code;
	private LocalDate dateOfBirth;
}
