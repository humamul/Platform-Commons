package com.platform.payloads.response;

import com.platform.payloads.request.CourseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseAssignedResponse {

	private CourseDTO courseDTO;
	private String activity;
}
