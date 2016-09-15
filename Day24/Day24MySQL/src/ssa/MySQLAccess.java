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
  private String dbCatalog = null;
  private String dbOptions = null;


public void getDBProperties() throws Exception {
    try {
    	Properties dbProp = new Properties();
   	 	dbProp.load(new FileInputStream("lib/db.properties"));
   	 
   	 	user = dbProp.getProperty("user");
   	 	passwd = dbProp.getProperty("pwd");
   	 	host = dbProp.getProperty("host");
   	 	dburl = dbProp.getProperty("url");
   	 	dbCatalog = dbProp.getProperty("catalog");
   	 	dbOptions = dbProp.getProperty("options");
    } 	catch(Exception e) {
    	throw e;
    }
}

public void getConnection() throws Exception {
	try {
		getDBProperties();
    	String url = dburl + host + dbCatalog + dbOptions;
    	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
    	connect = DriverManager.getConnection (url, user, passwd);

//    	connect.setCatalog(dbCatalog);
		
	}	catch(Exception e) {
		throw e;
	}
	
}
public void readStudentDataBase() throws Exception {
    try {   	
    	getConnection();
    	
    	preparedStatement = connect.prepareStatement("select * from student");
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
			int aSat = resultSet.getInt("sat");
			double aGpa = resultSet.getDouble("gpa");
			int aMajor = resultSet.getInt("major_id");
			
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
	
public void updateDBMajor() throws Exception {
    try {	
    	getConnection();
    	
    	String updateSQL = "update student join major on major.description = ? set student.major_id = major.id ";
    	String updateSQL2 = "where student.first_name = ? and student.last_name = ?";
    	preparedStatement = connect.prepareStatement(updateSQL + updateSQL2);
    	preparedStatement.setString(1, "Finance");
    	preparedStatement.setString(2, "Adam");
    	preparedStatement.setString(3, "Zapel");
    	preparedStatement.executeUpdate();
    	
    	preparedStatement.setString(1, "General Studies");
    	preparedStatement.setString(2, "Graham");
    	preparedStatement.setString(3, "Krakir");
    	preparedStatement.executeUpdate();

    	preparedStatement.setString(1, "Accounting");
    	preparedStatement.setString(2, "Ella");
    	preparedStatement.setString(3, "Vader");
    	preparedStatement.executeUpdate();

    	preparedStatement.setString(1, "Engineering");
    	preparedStatement.setString(2, "Stanley");
    	preparedStatement.setString(3, "Kupp");
    	preparedStatement.executeUpdate();

    	preparedStatement.setString(1, "Education");
    	preparedStatement.setString(2, "Lou");
    	preparedStatement.setString(3, "Zar");
    	preparedStatement.executeUpdate();
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }

public void deleteFromDataBase() throws Exception {
    try {
    	getConnection();
    	
    	String deleteSQL = "delete from student where first_name = ? and last_name = ?";
    	preparedStatement = connect.prepareStatement(deleteSQL);
    	preparedStatement.setString(1, "Adam");
    	preparedStatement.setString(2, "Zapel");
    	preparedStatement.executeUpdate();  
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }

public void insertToDBStudent() throws Exception {
    try {
    	getConnection();

    	preparedStatement = connect.prepareStatement("insert into student values (default, ?, ?, ?, ?, default)");
    	
    	preparedStatement.setString(1, "Adam");
    	preparedStatement.setString(2, "Zapel");
    	preparedStatement.setInt(3, 1200);
    	preparedStatement.setDouble(4, 3.0);
    	preparedStatement.executeUpdate();
    	
    	preparedStatement.setString(1, "Graham");
    	preparedStatement.setString(2, "Krakir");
    	preparedStatement.setInt(3, 500);
    	preparedStatement.setDouble(4, 2.5);
    	preparedStatement.executeUpdate();

    	preparedStatement.setString(1, "Ella");
    	preparedStatement.setString(2, "Vader");
    	preparedStatement.setInt(3, 800);
    	preparedStatement.setDouble(4, 3.0);
    	preparedStatement.executeUpdate();

    	preparedStatement.setString(1, "Stanley");
    	preparedStatement.setString(2, "Kupp");
    	preparedStatement.setInt(3, 1350);
    	preparedStatement.setDouble(4, 3.3);
    	preparedStatement.executeUpdate();

    	preparedStatement.setString(1, "Lou");
    	preparedStatement.setString(2, "Zar");
    	preparedStatement.setInt(3, 950);
    	preparedStatement.setDouble(4, 3.0);
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

public void InsertToDBClasses() throws Exception{
	try {
		getConnection();

		String sql1 = "insert into student_class_relationship (student_id, class_id) ";
		String sql2 = "select id, ? from student where first_name = ? and last_name = ?";
		preparedStatement = connect.prepareStatement(sql1 + sql2);
		
    	preparedStatement.setInt(1, 10102);
    	preparedStatement.setString(2, "Adam");
    	preparedStatement.setString(3, "Zapel");
    	preparedStatement.executeUpdate();
    	preparedStatement.setInt(1, 10103);
    	preparedStatement.setString(2, "Adam");
    	preparedStatement.setString(3, "Zapel");
    	preparedStatement.executeUpdate();
    	preparedStatement.setInt(1, 20401);
    	preparedStatement.setString(2, "Adam");
    	preparedStatement.setString(3, "Zapel");
    	preparedStatement.executeUpdate();
    	preparedStatement.setInt(1, 20201);
    	preparedStatement.setString(2, "Adam");
    	preparedStatement.setString(3, "Zapel");
    	preparedStatement.executeUpdate();
    	
	}	catch (Exception e) {
		throw e;
	 } finally {
	      close();
	 }
}

public void printStudentReport(String fname, String lname) throws Exception{
	boolean emptyRS = true;
	
	try {
		getConnection();

    	preparedStatement = connect.prepareStatement("select * from student where first_name = ? and last_name = ?");
    	preparedStatement.setString(1, fname);
    	preparedStatement.setString(2, lname);
    	
    	resultSet = preparedStatement.executeQuery();	
    	int aId = 0;
    	int aSat = 0;
    	int aMajor = 0;
    	String fullName = null;
    	int aClass_id = 0;
		System.out.println("Education System - Enrollment Process");
		System.out.printf("=====================================\n\n");
    	
		try {
			while (resultSet.next()) {
				emptyRS = false;
				aId = resultSet.getInt("id");
				String fName = resultSet.getString("first_name");
				String lName = resultSet.getString("last_name");
				aSat = resultSet.getInt("sat");
				aMajor = resultSet.getInt("major_id");
				fullName = fName + " " + lName;
				
				System.out.println("Enrolled " + fullName + " as a new student.");
				System.out.printf("%s has an SAT score of %d.\n", fullName, aSat);
				
				preparedStatement = connect.prepareStatement("select description from major where major.id = ?");
				preparedStatement.setInt(1, aMajor);
		    	resultSet = preparedStatement.executeQuery();
				try {
					while (resultSet.next()) {
						String theMajor = resultSet.getString("description");

						System.out.printf("Assigned %s to the %s Major which requires a required SAT score of %d.\n", fullName, theMajor, aSat);
						System.out.printf("Enrolled %s in the following four classes:\n", fullName);
					}
				} catch (Exception e) {
					throw e;
				}
				
//				1. [class name] required for major
//				3. [class name] elective (not required for major)
				
				preparedStatement = connect.prepareStatement("select class_id from student_class_relationship where student_class_relationship.student_id = ?");
				preparedStatement.setInt(1, aId);
		    	resultSet = preparedStatement.executeQuery();
		    	int aIndex = 0;
		    	try {
					while (resultSet.next()) {
						aClass_id = resultSet.getInt("class_id");
						aIndex++;
						System.out.printf(" %d. %d is an enrolled class.\n", aIndex, aClass_id);
					}
				} catch (Exception e) {
					throw e;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		if (emptyRS) {
			System.out.println("Query resulted in an empty result set");
		}
		
		
	}	catch (Exception e) {
		throw e;
	} finally {
	      close();
	}
}

public void generateReport() throws Exception{
	try {
		printStudentReport("Adam", "Zapel");
		
    	
	}	catch (Exception e) {
		throw e;
	 } finally {
	      close();
	 }
}
}