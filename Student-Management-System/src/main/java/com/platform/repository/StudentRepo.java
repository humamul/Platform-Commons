package com.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.entity.Student;
import com.platform.exception.StudentException;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	List<Student> findByStudentName(String studentName);
}
