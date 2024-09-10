package com.example.studentmanagement.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.model.Address;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.response.StudentResponse;
import com.example.studentmanagement.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentResponse register(Student Student) {
		studentRepository.save(Student);
		return new StudentResponse("success", "Student added successfully");
	}

	@Override
	public Student getStudentById(String studentId) throws Exception {
		Optional<Student> opStudent = studentRepository.findById(studentId);
		
		if(!opStudent.isPresent()) {
			return null;
		}
		Student student = opStudent.get();
		return student;
	}

	@Override
	public List<Student> getAllStudent() {
		
		return studentRepository.findAll();
	}

	@Override
	public StudentResponse updateStudent(String studentId, Student student) throws Exception {
		Optional<Student> opStudent = studentRepository.findById(studentId);
		if(!opStudent.isPresent()) {
			return new StudentResponse("error", "Student not found with id "+studentId);
		}
		Student oldStudent = opStudent.get();
		if(student.getName() != null) {
			oldStudent.setName(student.getName());
		}
		if(student.getEmail() != null) {
			oldStudent.setEmail(student.getEmail());
		}
		if(student.getPhoneNo() != null) {
			oldStudent.setPhoneNo(student.getPhoneNo());
		}
		if(student.getAddress() != null) {
			Address oldAddress = oldStudent.getAddress();
			Address newAddress = student.getAddress();
			if(newAddress.getCountry() != null) {
				oldAddress.setCountry(newAddress.getCountry());
			}
			
			if(newAddress.getState()!=null) {
				oldAddress.setState(newAddress.getState());
			}
			if(newAddress.getCity() !=null) {
				oldAddress.setCity(newAddress.getCity());
			}
			if(newAddress.getPincode() != null) {
				oldAddress.setPincode(newAddress.getPincode());
			}
		}
		
	
		studentRepository.save(oldStudent);
		
		return new StudentResponse("updated", "Student updated successfully");
	}

	@Override
	public StudentResponse deleteStudent(String studentId) throws Exception {
		Optional<Student> deletedStudent =studentRepository.findById(studentId);
		if(!deletedStudent.isPresent()) {
			return new StudentResponse("error", "Student not found with id "+studentId);
		}
		studentRepository.delete(deletedStudent.get());
		return new StudentResponse("deleted", "Student deleted successfully");
	}

	
}
