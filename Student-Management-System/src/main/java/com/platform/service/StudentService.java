package com.platform.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.platform.entity.Course;
import com.platform.exception.CourseException;
import com.platform.exception.StudentException;
import com.platform.payloads.request.StudentAuthenticationRequest;
import com.platform.payloads.request.StudentDTO;
import com.platform.payloads.request.StudentUpdateRequest;
import com.platform.payloads.response.CourseWithStudentsResponse;
import com.platform.payloads.response.StudentWithCoursesResponse;
@Service
public interface StudentService {

	StudentDTO registerStudent(StudentDTO studentDto)throws StudentException;
	StudentDTO updateStudent(StudentUpdateRequest studentDTO,StudentAuthenticationRequest studentAuth) throws StudentException;
	List<StudentDTO> getStudentByName(String name)throws StudentException;
	StudentDTO getStudentByStudentCode(Integer studentCode)throws StudentException;
	StudentWithCoursesResponse getCoursesAssigned(StudentAuthenticationRequest studentAuthenticationRequest)throws StudentException,CourseException;
	StudentWithCoursesResponse removeCourseFromStudent(Integer courseId, Integer studentCode, LocalDate dateOfBirth)throws StudentException,CourseException;
	
}
