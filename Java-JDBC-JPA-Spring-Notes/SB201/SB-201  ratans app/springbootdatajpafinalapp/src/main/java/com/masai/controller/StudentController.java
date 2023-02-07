package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.StudentException;
import com.masai.model.Student;
import com.masai.model.StudentDTO;
import com.masai.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@PostMapping("/students")
	public ResponseEntity<Student> registerStudentHandler(@RequestBody Student student) {
		
		Student savedStudent= studentService.saveStudent(student);
		
		
		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
		
	}
	
	
	
	@GetMapping("/students/{roll}")
	public ResponseEntity<Student> getStudentByRollHandler(@PathVariable("roll") Integer roll) throws StudentException{
		
		Student student= studentService.getStudentByRoll(roll);
		
		return new ResponseEntity<Student>(student, HttpStatus.OK);
		
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudentDetailsHandler() throws StudentException{
		
		List<Student> students= studentService.getAllStudentDetails();
		
		
		return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/students/{roll}")
	public ResponseEntity<Student> deleteStudentByRollHandler(@PathVariable("roll") Integer roll) throws StudentException{
		
		Student student= studentService.deleteStudentByRoll(roll);
		
		return new ResponseEntity<Student>(student,HttpStatus.OK);
		
	}
	
	@PutMapping("/students")
	public ResponseEntity<Student> updateStudentHandler(@RequestBody Student student) throws StudentException{
		
		Student updatedStudent= studentService.updateStudentDetails(student);
		
		
		return new ResponseEntity<Student>(updatedStudent,HttpStatus.OK);
		
	}
	//http://localhost:8888/students/2?gmarks=100
	@PutMapping("/students/{roll}")
	public ResponseEntity<Student> updateStudentMarksHandler(@PathVariable("roll") Integer roll, @RequestParam("gmarks") Integer gmarks) throws StudentException{
		
		Student updatedStudent= studentService.updateStudentMarks(roll, gmarks);
		
		return new ResponseEntity<Student>(updatedStudent,HttpStatus.OK);
		
	}
	
	@GetMapping("/getstudentbyname/{name}")
	public List<Student> getStudentByNameHandler(@PathVariable String name) throws StudentException {
	
		return  studentService.getStudentByName(name);
		
	}
	
	
	@GetMapping("/getStudent2")
	public List<StudentDTO> getStudentNameAndMarksByRollHandler() throws StudentException {
		
		
	return  studentService.getStudentNameAndMarks();
		
		
	}
	 
	
	
	
	
	
	
}
