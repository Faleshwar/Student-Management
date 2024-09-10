package com.example.studentmanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	@NotNull
	@NotEmpty(message = "username is required")
	@Size(min = 4, max = 10, message = "Username must be between 4 to 10 characters")
	private String username;
	
	@NotNull
	@NotEmpty(message = "password is required")
	@Size(min = 3, max = 12, message = "Password must be between 3 to 12 character")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
