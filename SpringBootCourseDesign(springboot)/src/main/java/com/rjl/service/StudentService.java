package com.rjl.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rjl.bean.Student;
import com.rjl.respository.StudentRespository;

@Service
public class StudentService {

	@Resource
	private StudentRespository studentRespository;
	
	public Student addStudent(Student student) {
		if (student==null) {
			System.out.println("====================student is null"+student.toString());
		}
		return studentRespository.save(student);
	}
	
	public Student alterStudent(Student student) {
		return studentRespository.save(student);
	}
	
	
	public Student selectStudent(int sno) {
		return studentRespository.findOne(sno);
	}
	
	public List<Student> selectAllStudents() {
		
		return (List<Student>) studentRespository.findAll();
	}
	
	
}
