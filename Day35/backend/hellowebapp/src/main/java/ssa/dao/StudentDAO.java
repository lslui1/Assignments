package ssa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ssa.entity.Student;

@Transactional
@Repository
public class StudentDAO implements IStudentDAO{

	@Autowired
	private HibernateTemplate  hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudents() {
	    String hql = "FROM Student as s ORDER BY s.id";
	    return (List<Student>) hibernateTemplate.find(hql);
	}

	@Override
	public Student getStudentById(int studentId) {
	    return (Student) hibernateTemplate.get(Student.class,studentId);
	}

	@Override
	public boolean addStudent(Student student) {
		try {
			hibernateTemplate.saveOrUpdate(student);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;		
	}

	@Override
	public void updateStudent(Student student) {
		hibernateTemplate.saveOrUpdate(student);
	}

	@Override
	public void deleteStudent(int studentId) {
	    Student student = (Student) hibernateTemplate.load(Student.class, studentId);
	    hibernateTemplate.delete(student);
//		hibernateTemplate.delete(hibernateTemplate.get(Student.class, studentId));;
	}

}
