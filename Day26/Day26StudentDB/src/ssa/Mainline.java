package ssa;

import java.util.ArrayList;

public class Mainline {

	public static void main(String[] args) {
	
		Students students = new Students();
		// retrieve a single student with id = 1
	    Student aStudent = students.getById(100);
	    // display the student
	    System.out.println(aStudent); // displays the student data in a formatted way
	    // retrieve all the students into a collection
	    ArrayList<Student> allStudents = students.getAll();
	    // iterate through the collection and display each student data in a formatted way
	    for(Student student : allStudents) {
	        System.out.println(student); 
	    }
	    
	    // Test Insert student
	    Student bStudent = new Student();
	    bStudent.setStudentData(400, "John", "Smith", 4.0, 1500, 2);
	    students.insertStudent(bStudent);
	    Student cStudent = students.getById(400);
	    System.out.println("******** Test output from Insert to Student Table ********");
	    System.out.println(cStudent);
	    
	    // Test delete student
	    students.DeleteStudent(cStudent);
	    
	    
	}
}
