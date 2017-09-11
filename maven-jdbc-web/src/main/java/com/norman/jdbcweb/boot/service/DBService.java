package com.norman.jdbcweb.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.norman.jdbcweb.boot.dao.StudentDao;
import com.norman.jdbcweb.boot.entity.Student;

@Service
public class DBService {

	@Autowired
	private StudentDao studentDao;
	
	public Student getStudent(String name){
		return studentDao.getStudent(name);
	}
	
	public List<Student> getStudentList(){
		return studentDao.getStudentList();
	}
	
	public String saveStudent(Student student){
		studentDao.saveStudent(student);
		return "sucessfully save a record!!!";
	}
	
	public String updateStudent(Student student){
		studentDao.updateStudent(student);
		return "sucessfully update a record!!!";
	}
	
	public String deleteStudent(String name){
		studentDao.deleteStudent(name);
		return "delete a record!!!!!!";
	}
	
}
