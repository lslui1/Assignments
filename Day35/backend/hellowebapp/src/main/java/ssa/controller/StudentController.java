package ssa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ssa.entity.Student;
import ssa.service.IStudentService;

@CrossOrigin
@RestController
//@Controller
@RequestMapping("/")
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@RequestMapping(value= "/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> student = studentService.getAllStudents();
        return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
    }
	
	@RequestMapping(value= "/student/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

	@RequestMapping(value= "/deletestudent/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<Student>(HttpStatus.OK);
    }
	
		
	@RequestMapping(value= "/addstudent/{fname}/{lname}/{sat}/{gpa}/{majorid}", method = RequestMethod.GET)
    public ResponseEntity<Student> addStudent(@PathVariable("fname") String fname,
    		@PathVariable("lname") String lname, @PathVariable("sat") int sat,
    		@PathVariable("gpa") double gpa, @PathVariable("majorid") int majorid) {
		
		Student student = new Student();
		student.setFirst_name(fname);
		student.setLast_name(lname);
		student.setGpa(gpa);
		student.setSat(sat);
		student.setMajor_id(majorid);
		System.out.println("Instantiated:" + student);

        boolean condition = studentService.addStudent(student);
        System.out.println(condition);
        
        if (condition == true) {
        	return new ResponseEntity<Student>(student, HttpStatus.OK);
        } else {
        	return new ResponseEntity<Student>(student, HttpStatus.CONFLICT);
        }       
    }
	
	@RequestMapping(value= "/updatestudent/{id}/{fname}/{lname}/{sat}/{gpa}/{majorid}", method = RequestMethod.GET)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id,@PathVariable("fname") String fname,
    		@PathVariable("lname") String lname, @PathVariable("sat") int sat,
    		@PathVariable("gpa") double gpa, @PathVariable("majorid") int majorid) {
		
		Student student = studentService.getStudentById(id);
		student.setFirst_name(fname);
		student.setLast_name(lname);
		student.setGpa(gpa);
		student.setSat(sat);
		student.setMajor_id(majorid);
		System.out.println("Modified instance:" + student);

        studentService.updateStudent(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
	
}
