package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;

@Document
public class User {
	
	@Id
	private String userId;
	
	@NotEmpty(message="Name cannot be empty")
	private String name;

	@NotEmpty(message = "Username cannot be empty")
	private String username;
	
	@NotEmpty(message = "Password cannot be empty")
	private String password;

	

	public User() {
		
	}
	
	

	public User(String userId, String name, String username, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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
