package ssa;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Students extends HashMap<Integer, Student>{

	private static final long serialVersionUID = 1L;
	MySQLAccess myDB = null;
	
	// Default Constructor
	public Students() {
		super();
		makeSQLConn();
	}
	
	// Establish SQL DB connection
	private void makeSQLConn() {
		try {
			myDB = new MySQLAccess();
		} catch (Exception e) { e.printStackTrace(); }
	}

	// Get the student object by id
	public Student getById(int id) {
		ResultSet rs = null;
		
		String sql = String.format("select * from student where id = %s", id);
		try {
			rs = myDB.execSQLQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int aPosition = 0;
		Student aStudent = getAStudentFromRS(rs, aPosition);
		return aStudent;
	}

	public ArrayList<Student> getAll() {
		ResultSet rs = null;
		
		String sql = "select * from student";
		try {
			rs = myDB.execSQLQuery(sql);
		} catch (SQLException e) { e.printStackTrace(); }

		return getStudentsFromRS(rs);
	}
	
	// Insert a student to student DB
	public int insertStudent(Student aStudent) {
		int updateCount = 0;
		
		int aId = aStudent.getId();
		String afName = aStudent.getfName();
		String alName = aStudent.getlName();
		double aGpa = aStudent.getGpa();
		int aSat = aStudent.getSat();
		int aMajor = aStudent.getMajor();
		
		String sql = "insert into student values " +
				String.format("(%4d, '%s', '%s', %4d, %4.2f , %4d)", aId, afName, alName, aSat, aGpa, aMajor);

		try {
			updateCount = myDB.execSQLUpdate(sql);
		} catch (SQLException e) { e.printStackTrace(); }
		
		return updateCount;
	}
		 
	// Delete a student to student DB
	public int DeleteStudent(Student aStudent) {
		int updateCount = 0;
		
		int aId = aStudent.getId();
		String afName = aStudent.getfName();
		String alName = aStudent.getlName();
		double aGpa = aStudent.getGpa();
		int aSat = aStudent.getSat();
		int aMajor = aStudent.getMajor();
		
		String sql = "delete from student where id = " +
				String.format("(%4d)", aId);

		try {
			updateCount = myDB.execSQLUpdate(sql);
		} catch (SQLException e) { e.printStackTrace(); }
		
		return updateCount;
	}
	
	// Extract a single student from the result set
	private Student getAStudentFromRS(ResultSet rs, int aPosition) {
		List<Student> someStudents = getStudentsFromRS(rs);
		
		if (!(someStudents.isEmpty())) {
			return (someStudents.get(aPosition));
		} else { return null; }
	}


	// Get all students from the result set
	private ArrayList<Student> getStudentsFromRS(ResultSet rs) {	
		
		ArrayList<Student> someStudents = new ArrayList<Student>();

		try {
			while (rs.next()) {
				Student aStudent = new Student();
				aStudent.setId(rs.getInt("id"));
				aStudent.setfName(rs.getString("first_name"));
				aStudent.setlName(rs.getString("last_name"));
				aStudent.setGpa(rs.getDouble("gpa"));
				aStudent.setSat(rs.getInt("sat"));
				aStudent.setMajor(rs.getInt("major_id"));
				someStudents.add(aStudent);
			}
		} catch (SQLException ex) {ex.printStackTrace();};
		
		return someStudents.isEmpty() ? null : someStudents;
	}

	
	
}
		
