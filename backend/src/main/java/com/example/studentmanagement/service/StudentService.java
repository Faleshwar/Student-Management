package com.example.studentmanagement.service;

import java.util.List;


import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.response.StudentResponse;

public interface StudentService {
	StudentResponse register(Student Student);
	
	Student getStudentById(String studentId) throws Exception;
	
	List<Student> getAllStudent();
	
	StudentResponse updateStudent(String studentId, Student student) throws Exception;
	
	StudentResponse deleteStudent(String studentId) throws Exception;
}
