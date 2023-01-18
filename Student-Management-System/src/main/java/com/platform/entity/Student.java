package com.platform.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "STUDENT_TABLE")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer unique_student_code;
	
	private String studentName;
	
	private String fatherName;
	
	private String motherName;
	
	private String email;
	
	private String mobile;
	
	private LocalDate dateOfBirth;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "STUDENT_COURSE_TABLE",
		joinColumns = @JoinColumn(referencedColumnName = "unique_student_code",name = "Student_Id"),
		inverseJoinColumns = @JoinColumn(referencedColumnName = "unique_course_code",name = "Course_Id")
			)
	
	private Set<Course> courses = new HashSet<>();
	
}
