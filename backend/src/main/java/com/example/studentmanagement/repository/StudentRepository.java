package com.example.studentmanagement.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.studentmanagement.model.Student;

public interface StudentRepository extends MongoRepository<Student, String>{

}
