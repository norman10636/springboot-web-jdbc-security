package com.norman.jdbcweb.boot.init;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value={"classpath:student.properties"},ignoreResourceNotFound=true)
@Component
public class DBInitial {

	private static Connection con = null;
	
	@Value("${db.path}")
	private String dbPath;
	
	private String CREATE_TABLE_STUDENT = ""+
			"CREATE TABLE IF NOT EXISTS "+"student "+
			"("+
			"id TEXT, "+
			"name TEXT, "+
			"address TEXT);";
	private String CREATE_TABLE_TEACHER = ""+
			"CREATE TABLE IF NOT EXISTS "+"teacher "+
			"("+
			"name TEXT, "+
			"address TEXT);";
	public void initConnection(){
		if (con==null){
			try {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:"+dbPath+"/student.db");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	@PostConstruct
	public void initDB(){
		initConnection();
		createStudentTable();
		createTeacherTable();
	}
	public void createStudentTable(){
		if (con!=null){
			Statement stmt = null;		
			try {
				stmt = con.createStatement();
				stmt.executeUpdate(CREATE_TABLE_STUDENT);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if (stmt!=null){
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}
	public void createTeacherTable(){
		if (con!=null){
			Statement stmt = null;		
			try {
				stmt = con.createStatement();
				stmt.executeUpdate(CREATE_TABLE_TEACHER);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if (stmt!=null){
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}
	public Connection getConnection(){
		if (con==null){
			throw new RuntimeException("no connection");
		}
		return con;
	}
}
