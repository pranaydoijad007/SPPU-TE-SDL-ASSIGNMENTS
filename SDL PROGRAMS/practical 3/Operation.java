package com.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.common.Student;


public class Operation {
	public int addstudent(Student s) throws ClassNotFoundException, SQLException{
		Connection con =Dbconnection.getMySQL();
		PreparedStatement ps = con.prepareStatement("insert into Student(rollNo,name,marks)values(?,?,?)");
		ps.setInt(1,s.rollNo);
		ps.setString(2,s.name);
		ps.setDouble(3,s.marks);
		int rows = ps.executeUpdate();
		Dbconnection.close_mysql(con);
		return rows;
	}
	
	public Student search(int rollNo) throws ClassNotFoundException, SQLException{
		Student s =null;
		Connection con =Dbconnection.getMySQL();
		PreparedStatement ps = con.prepareStatement("select rollNo,name,marks from Student where rollNo=?");
		ps.setInt(1,rollNo);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			s=new Student();
			s.rollNo=rs.getInt("rollNo");
			s.name=rs.getString("name");
			s.marks=rs.getDouble("marks");
			
		}
		Dbconnection.close_mysql(con);
		
		return s;
	}
	public int delete(int rollNo) throws ClassNotFoundException, SQLException {
		Student s =null;
		
		Connection con =Dbconnection.getMySQL();
		PreparedStatement ps = con.prepareStatement("delete from Student where rollNo=?");
		ps.setInt(1,rollNo);
	
		int rows = ps.executeUpdate();
		Dbconnection.close_mysql(con);
		
		return rows;
		
		
	}
	public ArrayList<Student> failedstudent(double marks) throws ClassNotFoundException, SQLException{
		ArrayList<Student> failedlist= new ArrayList<Student>();
		Student s =null;
		Connection con =Dbconnection.getMySQL();
		PreparedStatement ps = con.prepareStatement("select rollNo,name,marks from Student where marks<?");
		ps.setDouble(1,marks);
		
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			s=new Student();
			s.rollNo=rs.getInt("rollNo");
			s.name=rs.getString("name");
			s.marks=rs.getDouble("marks");
			
			failedlist.add(s);
		

}
		return failedlist;
	}
}
