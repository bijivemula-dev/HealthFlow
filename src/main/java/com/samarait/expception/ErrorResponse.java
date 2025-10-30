package com.samarait.expception;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private LocalDateTime timestamp;
	
	private int status;
	
	private String error;
	
	private String message;
	
	private String type;
	
	

	public ErrorResponse() {
	
		
	}

	public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String type) {
		
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.type = type;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
