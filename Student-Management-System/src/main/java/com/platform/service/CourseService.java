package com.platform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.platform.entity.Course;
import com.platform.exception.CourseException;
import com.platform.exception.StudentException;
import com.platform.payloads.request.CourseDTO;
import com.platform.payloads.request.StudentAuthenticationRequest;
import com.platform.payloads.response.CourseAssignedResponse;
import com.platform.payloads.response.CourseWithStudentsResponse;
import com.platform.payloads.response.StudentWithCoursesResponse;

@Service
public interface CourseService {

	CourseDTO uploadCourse(CourseDTO courseDto)throws CourseException;
	CourseDTO updateCourse(Integer course_id,CourseDTO courseDto)throws CourseException;
	CourseDTO deleteCourse(Integer courseId)throws CourseException;
	StudentWithCoursesResponse getCoursesAssigned(StudentAuthenticationRequest studentAuth)throws StudentException,CourseException;
	List<String> getAllTopicsAssigned(StudentAuthenticationRequest studentAuth) throws CourseException,StudentException;
	CourseAssignedResponse assignCourseToStudent(Integer courseId, Integer studentCode)throws StudentException,CourseException;
	CourseWithStudentsResponse getStudentByCourseId(Integer courseId)throws CourseException,StudentException;
}
