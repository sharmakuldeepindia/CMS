package com.example.kuldeep.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Course;
import com.example.model.User;
import com.example.repository.CourseRepository;
import com.example.service.UserService;

@RestController
@RequestMapping("/api")

public class CourseRestController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	@Autowired
	UserService userService;
	@Autowired
	CourseRepository courseRepo;
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ResponseEntity<List<Course>> listAllCourses() {
		List<Course> courses = courseRepo.findAll();
		if (courses.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCourse(@PathVariable("id") long id) {
		logger.info("Fetching Course with id {}", id);
		Course course = courseRepo.findById(id);
		if (course == null) {
			logger.error("Course with id {} not found.", id);
			return new ResponseEntity(new String("Course with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

}
