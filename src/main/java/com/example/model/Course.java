package com.example.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {

	private Long id;
	private String courseName;
	private String courseDescription;

	public Course() {
	}

	public Course(String courseName, String courseDescription) {
		this.courseName = courseName;
		this.courseDescription = courseDescription;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="COURSE_ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="courseName", nullable=false)
	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	
	

}