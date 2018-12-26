package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.CourseFiles;

@Repository("courseFilesRepository")
@Transactional
public interface CourseFilesRepository extends JpaRepository<CourseFiles, Long>  {

		List<CourseFiles> findByCourseId(long id);
	
}
