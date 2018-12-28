package com.example.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorResponse {
	long timestamp;
	int status;
	// String error;
	String message;
	String path;

	List<String> errors;

	public ErrorResponse() {

	}

	public ErrorResponse(long timestamp, int status, String error, String message, String path) {
		super();
		errors = new ArrayList<String>();

		this.timestamp = timestamp;
		this.status = status;
		this.errors.add(error);
		this.message = message;
		this.path = path;
	}

	public ErrorResponse(long timestamp, int status, String error, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.errors.add(error);
		this.message = message;
	}

	public ErrorResponse(long timestamp, int status, String message, List<ObjectError> allErrors, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		this.path = path;
		errors = new ArrayList<String>();
		for (ObjectError oe : allErrors) {
			FieldError fieldError = (FieldError) oe;
			errors.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
		}
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ErrorResponse [timestamp=" + timestamp + ", status=" + status + ", message=" + message + ", path="
				+ path + ", errors=" + errors + "]";
	}
	
}
