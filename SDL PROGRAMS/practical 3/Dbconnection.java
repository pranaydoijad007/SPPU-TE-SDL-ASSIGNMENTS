package com.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class Dbconnection {
	public static Connection getMySQL() throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		
		Class.forName("com.mysql.jdbc.Driver");
	 con =DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","google");
		
	 
	 return con;
	}
	public static void close_mysql(Connection con) throws SQLException {
		con.close();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Dbconnection.getMySQL();
	}
}
