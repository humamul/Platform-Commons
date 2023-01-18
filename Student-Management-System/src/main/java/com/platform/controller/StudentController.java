package com.platform.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.exception.CourseException;
import com.platform.exception.StudentException;
import com.platform.payloads.request.StudentAuthenticationRequest;
import com.platform.payloads.request.StudentDTO;
import com.platform.payloads.request.StudentUpdateRequest;
import com.platform.payloads.response.CourseWithStudentsResponse;
import com.platform.payloads.response.StudentWithCoursesResponse;
import com.platform.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;;
	
	@PostMapping("/register")   //ad
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO student) throws StudentException{
		StudentDTO registeredStudent = studentService.registerStudent(student);
		return new ResponseEntity<StudentDTO>(registeredStudent,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")      
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentUpdateRequest student,@RequestParam Integer student_code,@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate dateOfBirth) throws StudentException{
		StudentDTO registeredStudent = studentService.updateStudent(student, new StudentAuthenticationRequest(student_code,dateOfBirth) );
		
		return new ResponseEntity<StudentDTO>(registeredStudent,HttpStatus.OK);
	}
	
	@GetMapping("/byName/{name}") 
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<StudentDTO>> getStudentsByName(@PathVariable("name") String n) throws StudentException{
		List<StudentDTO> studentByName = studentService.getStudentByName(n);
		return new ResponseEntity<List<StudentDTO>>(studentByName,HttpStatus.OK);
	}
	
	@GetMapping("/byCode")      //ad 
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentDTO> getStudentByStudentCode(@RequestParam Integer studentCode) throws StudentException{
		StudentDTO studentByName = studentService.getStudentByStudentCode(studentCode);
		return new ResponseEntity<StudentDTO>(studentByName,HttpStatus.OK);
	}
	
	@GetMapping("/getCourses")
	public ResponseEntity<StudentWithCoursesResponse> getCoursesAssigned(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,@RequestParam Integer studentCode) throws CourseException, StudentException{
		
		StudentWithCoursesResponse savedCourse = this.studentService.getCoursesAssigned(new StudentAuthenticationRequest(studentCode,dateOfBirth));
		return new ResponseEntity<StudentWithCoursesResponse>(savedCourse,HttpStatus.OK);
	}
	@DeleteMapping("/removeCourse/{courseId}")
    public ResponseEntity<StudentWithCoursesResponse> removeCourse(@PathVariable Integer courseId,@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,@RequestParam Integer studentCode) throws CourseException, StudentException{
		
		StudentWithCoursesResponse savedCourse = this.studentService.removeCourseFromStudent(courseId,studentCode,dateOfBirth);
//				.getCoursesAssigned(new StudentAuthenticationRequest(studentCode,dateOfBirth));
		return new ResponseEntity<StudentWithCoursesResponse>(savedCourse,HttpStatus.OK);
	}
	
	
}
