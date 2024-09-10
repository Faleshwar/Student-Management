package com.example.studentmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.studentmanagement.model.User;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String>{
	
	Optional<User> findByUsername(String username);

}
