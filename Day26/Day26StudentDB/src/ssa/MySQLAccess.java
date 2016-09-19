package ssa;

import java.util.Properties;
import java.io.FileInputStream;
import java.sql.*;

public class MySQLAccess {
	
	private String host = null;
	private String user = null;
	private String pwd = null;
	private String dburl = null;
	private String dbCatalog = null;
	private String dbOptions = null;
	public Connection conn = null;
	
	public MySQLAccess() throws SQLException {
		getDBProperties();
    	String url = dburl + host + dbCatalog + dbOptions;
    	try {
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(url, user, pwd);
	}
	
	// Get database connection properties
	public void getDBProperties() {
	    try {
	    	Properties dbProp = new Properties();
	   	 	dbProp.load(new FileInputStream("lib/db.properties"));
	   	 
	   	 	user = dbProp.getProperty("user");
	   	 	pwd = dbProp.getProperty("pwd");
	   	 	host = dbProp.getProperty("host");
	   	 	dburl = dbProp.getProperty("url");
	   	 	dbCatalog = dbProp.getProperty("catalog");
	   	 	dbOptions = dbProp.getProperty("options");
	    } 	catch(Exception e) { e.printStackTrace(); }
	}
	
	// Execute a SQL Update and return the count of records modified.
	public int execSQLUpdate(String sqlString) throws SQLException {
		Statement statement = conn.createStatement();
		
		try {
			int recCount = statement.executeUpdate(sqlString);
			return recCount;
		} catch(SQLException e) { throw e; }
	}
	
	// Execute a SQL query and return the resultSet
	public ResultSet execSQLQuery(String sqlString) throws SQLException {
		Statement statement = conn.createStatement();
		try {
			ResultSet rs = statement.executeQuery(sqlString);
			return rs;
		} catch(SQLException e) { throw e; }
	}
	
	public void close() throws SQLException {
		if(conn != null) { conn.close(); conn = null; }
	}
}
