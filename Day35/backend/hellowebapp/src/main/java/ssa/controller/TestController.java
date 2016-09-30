package ssa.controller;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssa.entity.Student;
//import eddb.Student;

@CrossOrigin
@RestController
public class TestController {
	    
		@RequestMapping("/students")
	    public List<Student> students() {
			ArrayList<Student> students = new ArrayList<Student>();
			students.add(new Student("Peter","Piper", 1250, 3.5, 1));
			students.add(new Student("Johnny","Applecakes", 1250, 3.5, 2));
			students.add(new Student("Sam","Buca", 1250, 3.5, 3));
	        return students;
		}
	    

		
}