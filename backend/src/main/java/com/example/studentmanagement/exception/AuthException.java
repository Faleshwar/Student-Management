package com.example.studentmanagement.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class AuthException extends BadCredentialsException{
	public AuthException(String msg) {
		super(msg);
	}
}
