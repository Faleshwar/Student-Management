package com.example.studentmanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dto.AuthRequest;
import com.example.studentmanagement.dto.AuthResponse;
import com.example.studentmanagement.dto.LoginRequest;
import com.example.studentmanagement.exception.AuthException;
import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public AuthResponse register(AuthRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		String password = encoder.encode(request.getPassword());
		user.setPassword(password);
		user.setName(request.getName());
		userRepository.save(user);
		String token = jwtService.generateToken(user.getUsername());
		return new AuthResponse(token, "Success");
	}
	
	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		Optional<User> opUser = userRepository.findByUsername(request.getUsername());
		if(!opUser.isPresent()) {
			throw new AuthException("User not found");
		}
		User user = opUser.get();
		String token = jwtService.generateToken(user.getUsername());
		return new AuthResponse(token, "Success");
	}
	

}
