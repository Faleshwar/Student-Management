package com.example.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.response.StudentResponse;
import com.example.studentmanagement.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/add")
	public ResponseEntity<StudentResponse> registerStudent(@Valid @RequestBody Student student, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			StudentResponse errorResponse = new StudentResponse();
			errorResponse.setStatus("error");
			errorResponse.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		StudentResponse response= studentService.register(student);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> students = studentService.getAllStudent();
		return ResponseEntity.ok(students);
	}
	
	@PutMapping("/update")
	public ResponseEntity<StudentResponse> updateStudent(@Valid @RequestBody Student student, @RequestParam String studentId, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			StudentResponse errorResponse = new StudentResponse();
			errorResponse.setStatus("error");
			errorResponse.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		StudentResponse response = studentService.updateStudent(studentId, student);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<StudentResponse> deleteStudent(@RequestParam String studentId) throws Exception{
		StudentResponse response= studentService.deleteStudent(studentId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Student> getStudentById(@RequestParam String studentId) throws Exception{
		Student student = studentService.getStudentById(studentId);
		if(student == null) {
			throw new StudentNotFoundException("Student not found with id "+studentId);
		}
		return ResponseEntity.ok(student);
	}
		
}
