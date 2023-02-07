package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.StudentException;
import com.masai.model.Student;
import com.masai.model.StudentDTO;
import com.masai.repository.StudentDao;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentDao dao;

	@Override
	public Student saveStudent(Student student) {
		
		Student savedStudent= dao.save(student);
		
		return savedStudent;
	}

	@Override
	public Student getStudentByRoll(Integer roll) throws StudentException {
		
		
			Optional<Student> opt= dao.findById(roll);
			
//			if(opt.isPresent()) {
//				return opt.get();
//			}else
//				throw new StudentException("Student does not exist with roll :"+roll);
//		
		
		return opt.orElseThrow(() -> new StudentException("Student does not exist with Roll :"+roll) );
			
		
	}

	@Override
	public List<Student> getAllStudentDetails() throws StudentException {
		
		
		List<Student> students= dao.findAll();
		
		if(students.size() > 0)
			return students;
		else
			throw new StudentException("No student found..");
		
		
		
	}

	@Override
	public Student deleteStudentByRoll(Integer roll) throws StudentException {
		
//		Student existingStudent= dao.findById(roll).orElseThrow(() -> new StudentException("Student does not exist with Roll "+roll));;
//		
//		dao.delete(existingStudent);
//		
//		
//		return existingStudent;
		
		Optional<Student> opt= dao.findById(roll);
		
		if(opt.isPresent()) {
			
			 Student existingStudent= opt.get();
			 
			 dao.delete(existingStudent);
			
			 return existingStudent;
		}else
			throw new StudentException("Student does not exist with Roll :"+roll);
		
		
		
		
		
	}

	@Override
	public Student updateStudentDetails(Student student) throws StudentException {
		
		Optional<Student> opt= dao.findById(student.getRoll());
		
		
		if(opt.isPresent()) {
			
			return dao.save(student);
			
			
			//here save method will perform as saveOrUpdate based on Id field
			
		}
		else
			throw new StudentException("Invalid Student details");
		
		
		
		
		
		
	}

	@Override
	public Student updateStudentMarks(Integer roll, Integer graceMarks) throws StudentException {
		
		Optional<Student> opt= dao.findById(roll);
		
		if(opt.isPresent()) {
			
			Student existingStudent= opt.get();
			
			
			existingStudent.setMarks(existingStudent.getMarks()+graceMarks);
			
			return dao.save(existingStudent);
			
			
		}else
			throw new StudentException("Student does not exist with Roll :"+roll);
		
		
		
		
	}

	@Override
	public List<Student> getStudentByName(String name) throws StudentException {
		
		
		
		List<Student> students= dao.findByName(name);
		
		
		if(students.size() > 0)
			return students;
		else
			throw new StudentException("Student does not exist with Name "+name);
		
	
		
	}

	@Override
	public  List<StudentDTO> getStudentNameAndMarks() throws StudentException {
		
		List<StudentDTO> dtos= dao.getStudentNameAndMarks();
		
		if(dtos.size() >0)
			return dtos;
		else
			throw new StudentException("No record found");
		
		
	}

}
