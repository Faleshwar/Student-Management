package com.example.studentmanagement.response;

public class ErrorResponse {

	private String error;
	private String message;
	
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


	public ErrorResponse() {
		
	}
	public ErrorResponse(String error, String message, String advice) {

		this.error = error;
		this.message = message;
		
	}
	
	
	
	
	
}
