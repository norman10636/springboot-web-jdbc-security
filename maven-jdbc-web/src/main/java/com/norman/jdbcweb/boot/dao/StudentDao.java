package com.norman.jdbcweb.boot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.norman.jdbcweb.boot.entity.Student;
import com.norman.jdbcweb.boot.init.DBInitial;

@Component
public class StudentDao {

	@Autowired
	private DBInitial dbInit;
	
	public Student getStudent(String name){
		String sql = "SELECT * FROM {0} WHERE name=\"{1}\"";
		sql = MessageFormat.format(sql, "student",name);
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = dbInit.getConnection().createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()){
				return transferToStudent(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	// get studentList
	public List<Student> getStudentList(){
		String sql = "SELECT * FROM "+"student";
		List<Student> list = new ArrayList<Student>();
		Statement statement = null;
		ResultSet rs = null; 
		try {
			statement = dbInit.getConnection().createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()){
				list.add(transferToStudent(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	// get a student

	// create a student
	public void saveStudent(Student student){
		String sql = "INSERT INTO {0} (id,name,address) "+"VALUES (\"{1}\",\"{2}\",\"{3}\")";
		sql = MessageFormat.format(sql,"student",student.getId(),student.getName(),student.getAddress());
		Statement statement = null;
		
		try {
			statement = dbInit.getConnection().createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	// update a student
	
	public void updateStudent(Student student){
		String sql = "UPDATE {0} SET id=\"{1}\", address=\"{2}\" "+
					 "WHERE name = \"{3}\"";
		sql = MessageFormat.format(sql, "student",student.getId(),student.getAddress(),student.getName());
		Statement statement = null;
		try {
			statement = dbInit.getConnection().createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	// delete a student
	public void deleteStudent(String name){
		String sql="DELETE FROM {0} WHERE name = \"{1}\"";
		sql = MessageFormat.format(sql,"student",name);
		Statement statement = null;
		try {
			statement = dbInit.getConnection().createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}
	
	public Student transferToStudent(ResultSet rs){
		Student student = new Student();
		try {
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setAddress(rs.getString("address"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
}
