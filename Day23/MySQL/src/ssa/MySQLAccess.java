package ssa;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MySQLAccess {
       
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  private String host = null;
  private String user = null;
  private String passwd = null;
  private String dburl = null;

  
public void getDBProperties() throws Exception {
    try {
    	Properties dbProp = new Properties();
   	 	dbProp.load(new FileInputStream("db.properties"));
   	 
   	 	user = dbProp.getProperty("user");
   	 	passwd = dbProp.getProperty("pwd");
   	 	host = dbProp.getProperty("host");
   	 	dburl = dbProp.getProperty("url");
    } 	catch(Exception e) {
    	throw e;
    }
}

public void readDataBase() throws Exception {
    try {
    	
    	getDBProperties();
    	String url = dburl + host;
    	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
    	Connection connect = DriverManager.getConnection (url, user, passwd);

    	connect.setCatalog("tiy");
    	preparedStatement = connect.prepareStatement("select * from student where first_name = ? and last_name = ?");
    	preparedStatement.setString(1, "George");
    	preparedStatement.setString(2, "Washington");
    	
    	resultSet = preparedStatement.executeQuery();
    	
    	printStudentResultSet();	
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }

public String padName(String aName) {
	String padded = String.format("%1$-20s", aName);
	return padded;
}

public String padNumber(String aNumber) {
	String padded = String.format("%1$-7s", aNumber);
	return padded;
}

public void printStudentResultSet() throws Exception {
	
	boolean emptyRS = true;
	System.out.println("ID     First Name          LastName            Major  GPA    SAT Score");
	System.out.println("----------------------------------------------------------------------");

	try {
		while (resultSet.next()) {
			emptyRS = false;
			int aId = resultSet.getInt("id");
			String fName = resultSet.getString("first_name");
			String lName = resultSet.getString("last_name");
			int aMajor = resultSet.getInt("major_id");
			double aGpa = resultSet.getDouble("gpa");
			int aSat = resultSet.getInt("sat");
			
			System.out.printf("%s", padNumber(Integer.toString(aId)));
			System.out.printf("%s", padName(fName));
			System.out.printf("%s", padName(lName));
			System.out.printf("%s", padNumber(Integer.toString(aMajor)));
			System.out.printf("%s", padNumber(String.format("%.2f", aGpa)));
			System.out.printf("%s\n\n", padNumber(Integer.toString(aSat)));
		}
	} catch (Exception e) {
		throw e;
	}
	if (emptyRS) {
		System.out.println("Query resulted in an empty result set");
	}
}
	
public void updateDataBase() throws Exception {
    try {
    
    	getDBProperties();
    	String url = dburl + host;
    	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
    	Connection connect = DriverManager.getConnection (url, user, passwd);
 
    	connect.setCatalog("tiy");
    	
    	String updateSQL = "update student join major on major.name = ? set student.major_id = major.id, ";
    	String updateSQL2 = "student.gpa = ?, sat = ? where student.first_name = ? and student.last_name = ?";
    	preparedStatement = connect.prepareStatement(updateSQL + updateSQL2);
    	preparedStatement.setString(1, "General Business");
    	preparedStatement.setDouble(2, 3.5);
    	preparedStatement.setInt(3, 1450);
    	preparedStatement.setString(4, "George");
    	preparedStatement.setString(5, "Washington");
    	preparedStatement.executeUpdate();
    	
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }

public void deleteFromDataBase() throws Exception {
    try {
    
    	getDBProperties();
    	String url = dburl + host;
    	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
    	Connection connect = DriverManager.getConnection (url, user, passwd);
 
    	connect.setCatalog("tiy");
    	
    	String deleteSQL = "delete from student where last_name = ? and sat = ?";
    	preparedStatement = connect.prepareStatement(deleteSQL);
    	preparedStatement.setString(1, "Washington");
    	preparedStatement.setInt(2, 1450);
    	preparedStatement.executeUpdate();
     
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }

public void insertToDataBase() throws Exception {
    try {
    
    	getDBProperties();
    	String url = dburl + host;
    	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
    	Connection connect = DriverManager.getConnection (url, user, passwd);

    	connect.setCatalog("tiy");

    	preparedStatement = connect.prepareStatement("insert into student values (default, ?, ?, default, ?, ?)");
    	preparedStatement.setString(1, "George");
    	preparedStatement.setString(2, "Washington");
    	preparedStatement.setDouble(3, 4.0);
    	preparedStatement.setInt(4, 1600);
    	
    	preparedStatement.executeUpdate();
    	
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }

  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
      
    } catch (Exception e) {

    }
  }
}