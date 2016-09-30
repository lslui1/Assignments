package ssa.service;

import java.util.List;

import ssa.entity.Student;

public interface IStudentService {

	List<Student> getAllStudents();
	Student getStudentById(int studentId);
	boolean addStudent(Student student);
	void updateStudent(Student student);
	void deleteStudent(int studentId);
	
}
