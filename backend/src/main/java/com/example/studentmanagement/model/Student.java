package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Document
public class Student {
	
	@Id
	private String studentId;
	
	@NotEmpty(message = "Name can not be empty")
	private String name;
	
	@NotEmpty(message = "Email can not be empty")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotEmpty(message = "Phone number can not be empty")
	private String phoneNo;
	
	private Address address;
	
	

    public String getStudentId() {
		return studentId;
	}



	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNo() {
		return phoneNo;
	}



	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public Student(String studentId, String name, String email, String phoneNo, Address address) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
	}



	public Student() {
		
	}

}
