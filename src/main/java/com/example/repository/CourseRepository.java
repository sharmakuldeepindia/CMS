package com.example.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Course;

@Repository("courseRepository")
@Transactional
public interface CourseRepository extends JpaRepository<Course, Long>{

		
}
