package com.platform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.entity.Course;
import com.platform.entity.Student;
import com.platform.entity.Topic;
import com.platform.exception.CourseException;
import com.platform.exception.StudentException;
import com.platform.payloads.request.CourseDTO;
import com.platform.payloads.request.StudentAuthenticationRequest;
import com.platform.payloads.request.StudentDTO;
import com.platform.payloads.response.CourseAssignedResponse;
import com.platform.payloads.response.CourseWithStudentsResponse;
import com.platform.payloads.response.StudentWithCoursesResponse;
import com.platform.repository.CourseRepo;
import com.platform.repository.StudentRepo;

@Service
public class CourseServiceImpl implements CourseService{
//implements CourseService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private StudentRepo studentRepo ;
	@Override
	public CourseDTO uploadCourse(CourseDTO courseDto) throws CourseException {
		// TODO Auto-generated method stub
		Course course = modelMapper.map(courseDto, Course.class);
		System.out.println(course+"**********************************************************************************");
		Course savedCourse = courseRepo.save(course);
		
		return this.modelMapper.map(savedCourse, CourseDTO.class);
	}
	@Override
	public CourseDTO updateCourse(Integer course_id, CourseDTO courseDto) throws CourseException {
		// TODO Auto-generated method stub
		Course course = courseRepo.findById(course_id).orElseThrow(()-> new CourseException("Course not found with id:"+course_id));
		if(courseDto.getCourseName()!=null) course.setCourseName(courseDto.getCourseName());
		if(courseDto.getCourseType()!=null) course.setCourseType(courseDto.getCourseType());
		if(courseDto.getDescription()!=null) course.setDescription(courseDto.getDescription());
		if(courseDto.getDuration()!=null) course.setCourseDuration(courseDto.getDuration());
		if(courseDto.getTopics()!=null) { 
			//no need to change this 
		}
		courseRepo.save(course);
		CourseDTO updatedDto = this.modelMapper.map(course, CourseDTO.class);
		return updatedDto;
	}
	@Transactional
	@Override
	public CourseDTO deleteCourse(Integer courseId) throws CourseException {
		Course course = courseRepo.findById(courseId).orElseThrow(()-> new CourseException("Course not found with id:"+courseId));
		List<Student> students = course.getStudents();
		for(Student i:students) {
			i.getCourses().remove(course);
			studentRepo.save(i);
			System.out.println("**************************************************************************");
		}
		courseRepo.delete(course);
//		this.courseRepo.delete(course);
		CourseDTO res = modelMapper.map(course, CourseDTO.class);
		
		return res;
	}
	@Override
	public StudentWithCoursesResponse getCoursesAssigned(StudentAuthenticationRequest studentAuth)
			throws StudentException, CourseException {
		Student student = this.studentRepo.findById(studentAuth.getStudent_code()).orElseThrow(()-> new StudentException("Student not found with id:"+studentAuth.getStudent_code()));
		if(!student.getDateOfBirth().equals(studentAuth.getDateOfBirth())) {
			throw new StudentException("Wrong Credentials");
		}
		StudentWithCoursesResponse response = new StudentWithCoursesResponse();
		
		StudentWithCoursesResponse map = modelMapper.map(student, StudentWithCoursesResponse.class);
		// TODO Auto-generated method stub
		return map;
	}
	@Override
	public List<String> getAllTopicsAssigned(StudentAuthenticationRequest studentAuth)
			throws CourseException, StudentException {
		Student student = this.studentRepo.findById(studentAuth.getStudent_code()).orElseThrow(()-> new StudentException("Student not found with id:"+studentAuth.getStudent_code()));
		if(!student.getDateOfBirth().equals(studentAuth.getDateOfBirth())) {
			throw new StudentException("Wrong Credentials");
		}
		List<String> resp = new ArrayList<>();
		Set<Course> courses = student.getCourses();
		for(Course i:courses) {
			List<Topic> topics = i.getTopics();
			topics.forEach(topic-> resp.add("topic :"+topic.getTopicName() +" course :"+ i.getCourseName()));
		}
		
		// TODO Auto-generated method stub
		return resp;
	}
	@Override
	public CourseAssignedResponse assignCourseToStudent(Integer courseId, Integer studentCode)
			throws StudentException, CourseException {
		Course course = courseRepo.findById(courseId).orElseThrow(()-> new CourseException("course id not found: "+ courseId));
		Student student = studentRepo.findById(studentCode).orElseThrow(()-> new StudentException("Student does not exists with code:"+studentCode));
		course.getStudents().add(student);
		student.getCourses().add(course);
		courseRepo.save(course);
		CourseAssignedResponse response = new CourseAssignedResponse();
		response.setCourseDTO(this.modelMapper.map(course, CourseDTO.class));
		response.setActivity("Student "+student.getStudentName()+" with id: "+student.getUnique_student_code() +"has been assigned with the course requested");
		
		
		// TODO Auto-generated method stub
		return response;
	}
	@Override
	public CourseWithStudentsResponse getStudentByCourseId(Integer courseId) throws CourseException, StudentException {
		Course course = this.courseRepo.findById(courseId).orElseThrow(()-> new CourseException("course not found with id : "+courseId));
		List<StudentDTO> list = course.getStudents().stream().map(i-> this.modelMapper.map(i, StudentDTO.class)).toList();		
		CourseWithStudentsResponse response = new CourseWithStudentsResponse(courseId, course.getCourseName(),course.getDescription(),
				courseId,course.getCourseType() , course.getTopics(), list);
//		CourseWithStudentsResponse response = new CourseWithStudentsResponse(courseId, null, null, courseId, null, null, list)
		return response;
	}

	
	

}
