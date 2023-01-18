package com.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.entity.Course;
@Repository
public interface CourseRepo extends JpaRepository<Course, Integer>{

}
