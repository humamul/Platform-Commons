package com.platform.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.entity.Course;
import com.platform.entity.Student;
import com.platform.exception.CourseException;
import com.platform.exception.StudentException;
import com.platform.payloads.request.CourseDTO;
import com.platform.payloads.request.StudentAuthenticationRequest;
import com.platform.payloads.request.StudentDTO;
import com.platform.payloads.request.StudentUpdateRequest;
import com.platform.payloads.response.CourseWithStudentsResponse;
import com.platform.payloads.response.StudentWithCoursesResponse;
import com.platform.repository.CourseRepo;
import com.platform.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private CourseRepo courseRepo;
	
	@Override
	public StudentDTO registerStudent(StudentDTO studentDto) throws StudentException {
		Student map = modelMapper.map(studentDto, Student.class);
		System.out.println(map);
		Student savedStudent = studentRepo.save(map);
		StudentDTO studentDTO2 = this.modelMapper.map(savedStudent, StudentDTO.class);
		// TODO Auto-generated method stub
		return studentDTO2;
	}

	@Override
	public StudentDTO updateStudent(StudentUpdateRequest studentDTO, StudentAuthenticationRequest studentAuth)
			throws StudentException {
		Student student = studentRepo.findById(studentAuth.getStudent_code()).orElseThrow(()-> new StudentException("Student doesn't exists with this id"));
		if(student.getDateOfBirth().equals(studentAuth.getDateOfBirth())) {
			System.out.println(studentDTO);
			if(studentDTO.getDateOfBirth()!=null) student.setDateOfBirth(studentDTO.getDateOfBirth());
			if(studentDTO.getEmail()!=null) student.setEmail(studentDTO.getEmail());
			if(studentDTO.getFatherName()!=null) student.setFatherName(studentDTO.getFatherName());
			if(studentDTO.getMotherName()!=null) student.setMotherName(studentDTO.getMotherName());
			if(studentDTO.getMobile()!=null) student.setMobile(studentDTO.getMobile());
			Student studentUpdated = studentRepo.save(student);
			StudentDTO updatedDTO = this.modelMapper.map(studentUpdated, StudentDTO.class);
			return updatedDTO;
		}else throw new StudentException("Date of Birth is Wrong");
		
	}

	@Override
	public List<StudentDTO> getStudentByName(String name) throws StudentException {
		// TODO Auto-generated method stub
		List<Student> students = this.studentRepo.findByStudentName(name);
		if(students.size()==0) {
			throw new StudentException("No Students find with this name");
		}
		List<Student> list = students.stream().filter(s1 -> s1.getStudentName().contains(name)).toList();
		List<StudentDTO> dtoList = list.stream().map(i-> modelMapper.map(i, StudentDTO.class)).toList();
		return dtoList;
	}

	@Override
	public StudentDTO getStudentByStudentCode(Integer studentCode) throws StudentException {
		Student student = studentRepo.findById(studentCode).orElseThrow(()-> new StudentException("Student not found with id: "+studentCode));
		// TODO Auto-generated method stub
		StudentDTO dto = modelMapper.map(student, StudentDTO.class);
		return dto;
	}

	@Override
	public StudentWithCoursesResponse getCoursesAssigned(StudentAuthenticationRequest studentAuthenticationRequest) throws StudentException,CourseException {
		Student student = this.studentRepo.findById(studentAuthenticationRequest.getStudent_code()).orElseThrow(()->new StudentException("Student not found with id: "+studentAuthenticationRequest.getStudent_code()));
		if(!student.getDateOfBirth().equals(studentAuthenticationRequest.getDateOfBirth())) throw new StudentException("Wrong Credentials");
		if(student.getCourses().size()==0) throw new CourseException("No courses assigned to "+student.getStudentName());
		List<CourseDTO> coursesDTO = student.getCourses().stream().map(i -> this.modelMapper.map(i, CourseDTO.class)).toList();
		StudentWithCoursesResponse res = new StudentWithCoursesResponse(student.getUnique_student_code(), student.getStudentName(), student.getFatherName(), student.getMotherName(), student.getEmail(), student.getMobile(), student.getDateOfBirth(), coursesDTO);
		
		
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public StudentWithCoursesResponse removeCourseFromStudent(Integer courseId, Integer studentCode,
			LocalDate dateOfBirth) throws StudentException, CourseException {
		Student student = this.studentRepo.findById(studentCode).orElseThrow(()->new StudentException("Student not found with id: "+studentCode));
		if(!student.getDateOfBirth().equals(dateOfBirth)) throw new StudentException("Wrong Credentials");
		if(student.getCourses().size()==0) throw new CourseException("No courses assigned to "+student.getStudentName());
		Course course = courseRepo.findById(courseId).orElseThrow(()-> new CourseException("Course with id "+courseId+" not found"));
		boolean contains = student.getCourses().contains(course);
		if(!contains)throw new CourseException("Course with id "+courseId+" hasn't been assigned to "+student.getStudentName());
		student.getCourses().remove(course);
		
		studentRepo.save(student);
		return null;
	}

	

}
