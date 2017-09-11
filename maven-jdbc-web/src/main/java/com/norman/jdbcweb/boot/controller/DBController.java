package com.norman.jdbcweb.boot.controller;

import org.codehaus.groovy.runtime.StringGroovyMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.norman.jdbcweb.boot.entity.Student;
import com.norman.jdbcweb.boot.service.DBService;

@RestController
@RequestMapping("/student")
public class DBController {
	
	@Autowired
	private DBService dbService;
	
	// get one student
	@RequestMapping(value="/{name}")
	public String getStudent(@PathVariable("name") String name){
		return  JSONObject.toJSONString(dbService.getStudent(name));
	}
	
	// get student list
	@RequestMapping(value="/list")
	public String getStudentList(){
		return JSONObject.toJSONString(dbService.getStudentList());
	}
	
	// create a student
	@RequestMapping(value="/save/{name}",method = RequestMethod.POST)
	public String saveStudent(@PathVariable("name") String name,@RequestBody String student){
		return dbService.saveStudent(JSON.parseObject(student, Student.class));
	}
	// update a student
	@RequestMapping(value="/update/{name}",method = RequestMethod.PUT)
	public String updateStudent(@PathVariable("name") String name,@RequestBody String student){
		return dbService.updateStudent(JSON.parseObject(student, Student.class));
	}
	
	// delete a student
	@RequestMapping(value="/delete/{name}",method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable("name") String name){
		return dbService.deleteStudent(name);
	}
}
