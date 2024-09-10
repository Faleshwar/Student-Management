package com.example.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthRequest {
	
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 10, message = "Username must be between 3 to 10 characters")
	private String username;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Password is Required")
	@Size(min = 4, max = 12, message = "Password must be between 4 to 12 characters")
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

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AuthRequest(String username, String name, String password) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
	}

	public AuthRequest() {
		
	}
	
	

}
