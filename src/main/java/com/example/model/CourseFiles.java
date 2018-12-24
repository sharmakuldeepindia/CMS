package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courseFiles")
public class CourseFiles {
	
	private long id;	
	private long courseId;
	private String fileType;
	private String fileLocation;
	private String fileName;
	public CourseFiles(){}
	
	public CourseFiles(long courseId, String fileType, String fileLocation, String fileName) {
		super();
		this.courseId = courseId;
		this.fileType = fileType;
		this.fileLocation = fileLocation;
		this.fileName = fileName;
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

	



}
