package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanagement.dto.AuthRequest;
import com.example.studentmanagement.dto.AuthResponse;
import com.example.studentmanagement.dto.LoginRequest;
import com.example.studentmanagement.exception.AuthException;
import com.example.studentmanagement.serviceImpl.AuthService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> createAuthenticationToken(@Valid @RequestBody AuthRequest request, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
			throw new AuthException(msg);
		}
		AuthResponse response = authService.register(request);
		return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
			throw new AuthException(msg);
		}
		AuthResponse response = authService.login(request);
		return ResponseEntity.ok(response);
	}

}
