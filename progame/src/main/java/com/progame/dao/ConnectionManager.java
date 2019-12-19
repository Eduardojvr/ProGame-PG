
package com.progame.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";



	/*
	 * * ######## BANCO LOCAL #########################################################
	 * *
	 */ 
		private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/heroku_7e0ed9b0e3f50c0?serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true&useSSL=false";
		private static final String DB_USER = "progame";
		private static final String DB_PASSWORD = "progame";

	Connection dbConnection = null;
	
	public static Connection getDBConnection() throws Exception {
		return getDBConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
	}

	public static Connection getDBConnection(String conn, String user, String pass) throws Exception {
		Connection dbConnection = null;
		Class.forName(DB_DRIVER);
		dbConnection = DriverManager.getConnection(conn, user, pass);
		return dbConnection;
	}
}
