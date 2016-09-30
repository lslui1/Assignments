package ssa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssa.dao.IStudentDAO;
import ssa.entity.Student;

@Service
public class StudentService implements IStudentService{

	@Autowired
	private IStudentDAO studentDAO;
	
	@Override
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}

	@Override
	public Student getStudentById(int studentId) {
		return studentDAO.getStudentById(studentId);
	}

	@Override
	public boolean addStudent(Student student) {
		return studentDAO.addStudent(student);
	}

	@Override
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}

	@Override
	public void deleteStudent(int studentId) {
		studentDAO.deleteStudent(studentId);
	}

}
