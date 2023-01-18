package com.platform.payloads.request;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.platform.entity.Topic;
import com.platform.entity.enums.CourseTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Integer unique_course_code;
	private String courseName;

	private String description;
	@JsonProperty("Duration in Months")
	private Integer duration;
	
	@Enumerated(EnumType.STRING)
	private CourseTypeEnum courseType;
	
	private List<Topic> topics = new ArrayList<>();
	
}
