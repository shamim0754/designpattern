package com.javaaround.dpattern.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
	private static Dbconnection instance = new Dbconnection();
	public static final String URL = "jdbc:mysql://localhost/jdbcdb";
	public static final String USER = "YOUR_DATABASE_USERNAME";
	public static final String PASSWORD = "YOUR_DATABASE_PASSWORD";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	//make the constructor private so that this class cannot be
   //instantiated
   private Dbconnection(){
   	
   } 
}