package com.platform.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.entity.enums.CourseTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer unique_course_code;
	private String courseName;

	private String description;
	
	
	private Integer courseDuration=0;
	
	@Enumerated(EnumType.STRING)
	private CourseTypeEnum courseType;
	
	@Embedded
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Topic> topics = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.PERSIST,mappedBy = "courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<>();
}
