package com.example.studentmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.studentmanagement.response.ErrorResponse;
import com.example.studentmanagement.response.StudentResponse;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<StudentResponse> handleStudentNotfoundException(StudentNotFoundException exception){
		StudentResponse errorRes = new StudentResponse();
		errorRes.setStatus("error");
		errorRes.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorRes, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AuthException.class)
	public ResponseEntity<ErrorResponse> handleAuthException(AuthException exception){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setError("Authentication");
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ErrorResponse> handleJWTExpire(ExpiredJwtException exception){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setError("token expired");
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.REQUEST_TIMEOUT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleErrorException(Exception exception){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setError("Internal server error");
		errorResponse.setMessage(exception.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
