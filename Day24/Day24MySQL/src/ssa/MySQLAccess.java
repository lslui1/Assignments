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

// Get database connection properties
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

// Establish JDBC connection to database
public void getConnection() throws Exception {
	try {
		getDBProperties();
    	String url = dburl + host + dbCatalog + dbOptions;
    	Class.forName ("com.mysql.jdbc.Driver").newInstance ();
    	connect = DriverManager.getConnection (url, user, passwd);
	}	catch(Exception e) {
		throw e;
	}
	
}

// SQL to read entire student table and call print statement for table
public void readStudentTable() throws Exception {
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

// Pad names for table formatting
public String padName(String aName) {
	String padded = String.format("%1$-20s", aName);
	return padded;
}

// Pad numbers for table formatting
public String padNumber(String aNumber) {
	String padded = String.format("%1$-7s", aNumber);
	return padded;
}

// Print Student table results
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

// SQL to check if major meets minimum requirements based on student SAT scores
public boolean checkUpdateMajor(String fname, String lname, String aMajor) throws Exception {
	int studentGPA = 0;
	int requiredGPA = 0;
	 try {	
	    	getConnection();
	    			
	    	String updateSQL = "select student.sat, major.req_sat from student join major on major.description = ? ";
	    	String updateSQL2 = "where student.first_name = ? and student.last_name = ?";
	    	preparedStatement = connect.prepareStatement(updateSQL + updateSQL2);
	    	preparedStatement.setString(1, aMajor);
	    	preparedStatement.setString(2, fname);
	    	preparedStatement.setString(3, lname);
	    	resultSet = preparedStatement.executeQuery();
	    	
	    	try {
				while (resultSet.next()) {
					studentGPA = resultSet.getInt("sat");
					requiredGPA = resultSet.getInt("req_sat");
				}
			} catch (Exception e) {
				throw e;
			}
	    } catch (Exception e) {
	    	throw e;
	    } finally {
	    	close();
	    }
	 
	 return (studentGPA >= requiredGPA) ? true : false;
}

// SQL to Update major in student table
public void updateMajor(String fname, String lname, String aMajor) throws Exception {
	 try {	
	    	getConnection();
	    	
	    	String updateSQL = "update student join major on major.description = ? set student.major_id = major.id ";
	    	String updateSQL2 = "where student.first_name = ? and student.last_name = ?";
	    	preparedStatement = connect.prepareStatement(updateSQL + updateSQL2);
	    	preparedStatement.setString(1, aMajor);
	    	preparedStatement.setString(2, fname);
	    	preparedStatement.setString(3, lname);
	    	preparedStatement.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }
}

// Call methods to verify and update student major
public void addStudentMajor(String fname, String lname, String aMajor) {
	boolean isValid = false;
	
	try {
		isValid = checkUpdateMajor(fname, lname, aMajor);
		if (isValid) {updateMajor(fname, lname, aMajor);}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	if (!isValid) {
		System.out.printf("\n%s %s: Invalid selection for major. SAT requirements do not meet the standard.\n\n", fname, lname);
	}
}

// Test method called from main program
public void requestAddStudentMajor() {
		try {
			addStudentMajor("Adam","Zapel","Finance");
			addStudentMajor("Graham","Krakir","General Studies");
			addStudentMajor("Ella","Vader","Accounting");
			addStudentMajor("Stanley","Kupp","Engineering");
			addStudentMajor("Lou","Zar","Education");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}

// SQL to insert student
public void insertStudent(String fname, String lname, int aSat, double aGpa) throws Exception {
	 try {
	    getConnection();
	    
	    preparedStatement = connect.prepareStatement("insert into student values (default, ?, ?, ?, ?, default)");
    	
    	preparedStatement.setString(1, fname);
    	preparedStatement.setString(2, lname);
    	preparedStatement.setInt(3, aSat);
    	preparedStatement.setDouble(4, aGpa);
    	preparedStatement.executeUpdate();
	 } catch (Exception e) {
	      throw e;
	 } finally {
	      close();
	 }
}

// Test method to insert students
public void addStudentsToDB() throws Exception {
    	insertStudent("Adam", "Zapel", 1200, 3.0);
    	insertStudent("Graham", "Krakir", 500, 2.5);
    	insertStudent("Ella", "Vader", 800, 3.0);
    	insertStudent("Stanley", "Kupp", 1350, 3.3);
    	insertStudent("Lou", "Zar", 950, 3.0);
  }

// Close everything
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

// SQL to Add classes for a student
public void addClassToStudent(String fname, String lname, int classId) throws Exception {
	try {
		getConnection();

		String sql1 = "insert into student_class_relationship (student_id, class_id) ";
		String sql2 = "select id, ? from student where first_name = ? and last_name = ?";
		preparedStatement = connect.prepareStatement(sql1 + sql2);
		
    	preparedStatement.setInt(1, classId);
    	preparedStatement.setString(2, fname);
    	preparedStatement.setString(3, lname);
    	preparedStatement.executeUpdate();
	}	catch (Exception e) {
		throw e;
	} finally {
	      close();
	}
}

// Test method for adding classes for a student
public void InsertToDBClasses() {
		try {
			addClassToStudent("Adam", "Zapel", 10102);
			addClassToStudent("Adam", "Zapel", 10103);
			addClassToStudent("Adam", "Zapel", 20401);
			addClassToStudent("Adam", "Zapel", 20201);
	    	
			addClassToStudent("Graham", "Krakir", 10101);
			addClassToStudent("Graham", "Krakir", 30101);
			addClassToStudent("Graham", "Krakir", 20201);
			addClassToStudent("Graham", "Krakir", 20404);
	    	
			addClassToStudent("Ella", "Vader", 20201);
			addClassToStudent("Ella", "Vader", 30101);
			addClassToStudent("Ella", "Vader", 20402);
			addClassToStudent("Ella", "Vader", 20202);
	    	
			addClassToStudent("Stanley", "Kupp", 10103);
			addClassToStudent("Stanley", "Kupp", 10101);
			addClassToStudent("Stanley", "Kupp", 20202);
			addClassToStudent("Stanley", "Kupp", 20403);
	    	
			addClassToStudent("Lou", "Zar", 10102);
			addClassToStudent("Lou", "Zar", 10103);
			addClassToStudent("Lou", "Zar", 20201);
			addClassToStudent("Lou", "Zar", 20203);
		} catch (Exception e) {
			e.printStackTrace();
		}	
}

// SQL to format and print a student report
public void printStudentReport(String fname, String lname) throws Exception{
	int listNum = 0;
	try {
		getConnection();

    	preparedStatement = connect.prepareStatement("select * from student where first_name = ? and last_name = ?");
    	preparedStatement.setString(1, fname);
    	preparedStatement.setString(2, lname);
    	
    	resultSet = preparedStatement.executeQuery();	
    	int aSat = 0;
    	int aMajor = 0;
    	String fullName = null;
		System.out.printf("\nEducation System - Enrollment Process\n");
		System.out.printf("=====================================\n\n");
    	
		try {
			while (resultSet.next()) {
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
						System.out.printf("Enrolled %s in the following classes:\n", fullName);
					}
				} catch (Exception e) {
					throw e;
				}
				// Display statement for class that are required for major
				String sql1 = "select c.subject, c.section, c.id " +
						"from student s " +
						"join major_class_relationship mc " +
						"on mc.major_id = s.major_id " +
						"join student_class_relationship sc " +
						"on sc.class_id = mc.class_id and s.id = sc.student_id " +
						"join class c " +
						"on c.id = sc.class_id " +
						"join major m " +
						"on m.id = mc.major_id " +
						"where first_name = ? and last_name = ?";
				
				preparedStatement = connect.prepareStatement(sql1);
				preparedStatement.setString(1, fname);
				preparedStatement.setString(2, lname);
    	
				resultSet = preparedStatement.executeQuery();	
				try {
					while (resultSet.next()) {
						listNum++;
						int aClassId = resultSet.getInt("id");
						String aSubject = resultSet.getString("subject");
						String aSection = resultSet.getString("section");
						System.out.printf("%d. %s %s %d required for major.\n", listNum, aSubject, aSection, aClassId);
					}
				} catch (Exception e) {
					throw e;
				}

                // Display statements for classes that are not in their major	
				String sql2 = "select c1.subject, c1.section, c1.id from student s1 join student_class_relationship sc1 "
								+ "on s1.id = sc1.student_id join class c1 on c1.id = sc1.class_id "
								+ "where first_name = ? and last_name = ? and c1.id not in ("
								+ "select c.id from student s join major_class_relationship mc "
								+ "on mc.major_id = s.major_id join student_class_relationship sc "
								+ "on sc.class_id = mc.class_id and s.id = sc.student_id join class c "
								+ "on c.id = sc.class_id join major m on m.id = mc.major_id "
								+ "where first_name = ? and last_name = ?)";
			    preparedStatement = connect.prepareStatement(sql2);
			    preparedStatement.setString(1, fname);
			    preparedStatement.setString(2, lname);
			    preparedStatement.setString(3, fname);
			    preparedStatement.setString(4, lname);
			    	
			    resultSet = preparedStatement.executeQuery();	
			    try {
			    	while (resultSet.next()) {
			    		listNum++;
			    		int aClassId = resultSet.getInt("id");
			    		String aSubject = resultSet.getString("subject");
			    		String aSection = resultSet.getString("section");

						System.out.printf("%d. %s %s %d elective (not required for major).\n", listNum, aSubject, aSection, aClassId);
					}
				} catch (Exception e) {
					throw e;
				}								
			}
		} catch (Exception e) {
			throw e;
		}
	}	catch (Exception e) {
		throw e;
	} finally {
	      close();
	}
}

// Test method to call for reports on individual students
public void generateReport() throws Exception{
	try {
		printStudentReport("Adam", "Zapel");
		printStudentReport("Graham", "Krakir");
		printStudentReport("Ella", "Vader");
		printStudentReport("Stanley", "Kupp");
		printStudentReport("Lou", "Zar");
	}	catch (Exception e) {
		throw e;
	 } finally {
	      close();
	 }
}
}