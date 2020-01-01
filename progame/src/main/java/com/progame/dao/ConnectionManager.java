
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
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/progame?serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true&useSSL=false";
	private static final String DB_USER = "progame";
	private static final String DB_PASSWORD = "progame";
//	
	/*
	 * * ######## PRODUÇÃO #########################################################
	 * *
	 */ 
//	private static final String DB_CONNECTION = "jdbc:mysql://bf445f272712fd:610adfc6@us-cdbr-iron-east-05.cleardb.net/heroku_7e0ed9b0e3f50c0?reconnect=true";
//	private static final String DB_USER = "bf445f272712fd";
//	private static final String DB_PASSWORD = "610adfc6";




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
