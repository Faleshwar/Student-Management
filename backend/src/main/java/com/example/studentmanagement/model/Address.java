package com.example.studentmanagement.model;

import jakarta.validation.constraints.NotEmpty;

public class Address {

	@NotEmpty(message = "Country cannot be empty")
	private String country;
	
	@NotEmpty(message = "State cannot be empty")
	private String state;;
	
	@NotEmpty(message = "City cannot be empty")
	private String city;
	
	@NotEmpty(message = "Pincode cannot be empty")
	private String pincode;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Address(String country, String state, String city, String pincode) {
		
		this.country = country;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
	}

	public Address() {
		
	}
	
	
		
		
}
