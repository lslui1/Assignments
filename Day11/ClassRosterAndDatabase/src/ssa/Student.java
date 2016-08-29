package ssa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;


public class Student {

//	public String searchItem = "001122";   // test first student
//	public String searchItem = "772223";   // test last student
//	public String searchItem = "010101";   // test bogus student
	public String searchItem = "306700";   // test on myself
	
    public String id; 
    public String firstName;
    public String lastName;
    public String eyeColor;
    public int monthsEmployed;
    
    //static ArrayList<Student> students = new ArrayList<Student>();
    //static Map<String, Student> unsortMap = new HashMap<>();
    //static NavigableMap<String, Student> sortedMap = new TreeMap<>(); // sorted tree map
    static ArrayList<Student> students = null;             // arraylist of students
    static Map<String, Student> unsortMap = null;          // unsorted hashmap of students
    static NavigableMap<String, Student> sortedMap = null; // sorted tree map of students
    
    private void Init() {}
    
    // default constructor
    public Student() { 
    	if (students == null) {
        	students = new ArrayList<Student>();
        	Init();
    	}
    	if (unsortMap == null) {
    		unsortMap = new HashMap<>();
    		Init();
    	}
    	if (sortedMap == null) {
    		sortedMap = new TreeMap<>();
    		Init();
    	}
    }

    // constructor passing in student information
	public Student(String aId, String fName, String lName, String eye, int months) {
    	this.id = aId;
    	this.firstName = fName;
    	this.lastName = lName;
    	this.eyeColor = eye;
    	this.monthsEmployed = months;
    }

	/*Comparator for sorting the list by Student firstName*/
    public static Comparator<Student> FNameComparator = new Comparator<Student>() {

	public int compare(Student st1, Student st2) {
	   String StudentName1 = st1.getStudentFname().toUpperCase();
	   String StudentName2 = st2.getStudentFname().toUpperCase();

	   //ascending order
	   return StudentName1.compareTo(StudentName2);
	   //descending order
	   //return StudentName2.compareTo(StudentName1);
	   }
	};

	// get student first name
	public String getStudentFname() {
		return firstName;
	}
	
	// input student information
	public void genStudents() {
		Student s1 = new Student("001122", "Michael", "Clair", "Other", 12);
		Student s2 = new Student("001212", "Stephen", "Rook", "Brown", 11);
		Student s3 = new Student("474143", "Johnathan", "Stafford,", "Brown", 13);
		Student s4 = new Student("005295", "Kyle", "Deen", "Blue", 2);
		Student s5 = new Student("004400", "Kevin", "Tran", "Other", 12);
		Student s6 = new Student("132617", "Reuben", "Turner", "Blue", 12);
		Student s7 = new Student("306700", "Li", "Lui", "Brown", 12);
		Student s8 = new Student("215296", "Joshua", "Franey", "Other", 27);
		Student s9 = new Student("523420", "Gabriel", "Hamilton", "Black", 10);
		Student s10 = new Student("004014", "Aisha", "Covington",  "Brown", 10);
		Student s11 = new Student("006789", "Arun", "Soma", "Brown", 2);
		Student s12 = new Student("009999", "Steve", "Ellwood", "Green", 2);
		Student s13 = new Student("343769", "Shaquil", "Timmons", "Brown", 11);
		Student s14 = new Student("001449", "Karen", "Reiter", "Blue", 10);
		Student s15 = new Student("267252", "Dax", "Richards", "Brown", 12);
		Student s16 = new Student("229949", "Mike", "Sykes", "Brown", 13);
		Student s17 = new Student("772223", "Daniel", "Kiros", "Brown", 3);
		Student s18 = new Student("004444", "Peter", "Choi", "Brown", 2);
		Student s19 = new Student("005255", "Joe", "Hill", "Brown", 13);
		Student s20 = new Student("008888", "Evan", "Tizard", "Brown", 12);
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		students.add(s5);
		students.add(s6);
		students.add(s7);
		students.add(s8);
		students.add(s9);
		students.add(s10);
		students.add(s11);
		students.add(s12);
		students.add(s13);
		students.add(s14);
		students.add(s15);
		students.add(s16);
		students.add(s17);
		students.add(s18);
		students.add(s19);
		students.add(s20);
		unsortMap.put(s1.id, s1);
		unsortMap.put(s2.id, s2);
		unsortMap.put(s3.id, s3);
		unsortMap.put(s4.id, s4);
		unsortMap.put(s5.id, s5);
		unsortMap.put(s6.id, s6);
		unsortMap.put(s7.id, s7);
		unsortMap.put(s8.id, s8);
		unsortMap.put(s9.id, s9);
		unsortMap.put(s10.id, s10);
		unsortMap.put(s11.id, s11);
		unsortMap.put(s12.id, s12);
		unsortMap.put(s13.id, s13);
		unsortMap.put(s14.id, s14);
		unsortMap.put(s15.id, s15);
		unsortMap.put(s16.id, s16);
		unsortMap.put(s17.id, s17);
		unsortMap.put(s18.id, s18);
		unsortMap.put(s19.id, s19);
		unsortMap.put(s20.id, s20);
	}
	
	// print roster information
	public void printClassRoster() {
		genStudents();
		
		sortByFname();
		printRosterFname();
		
		sortMap();
		print3Students();								
	}
	
	// sort the roster by first name
	public void sortByFname() {
		Collections.sort(students, Student.FNameComparator);		
	}

	// format name fields for output
	public String padName(String aName) {
		String padded = String.format("%1$-16s", aName);
		return padded;
	}
	
	// format eye color for output
	public String padEye(String eye) {
		String padded = String.format("%1$-9s", eye);
		return padded;
	}
	
	// print the roster
	public void printRosterFname() {
		System.out.println("Student class roster");
		System.out.println("Empl Id   First name         Last name          Eye color   Months at SSA");
		System.out.println("=======   ================   ================   =========   =============");
		for(Student str: students){
			printStudent(str);
		}
	}
	
	// print the student
	public void printStudent(Student str) {
		System.out.printf("%s    %s   %s   ", str.id, padName(str.getStudentFname()), padName(str.lastName));
		System.out.printf("%s         %d\n", padEye(str.eyeColor), str.monthsEmployed);
		
	}
	
	// sort the map
	public void sortMap() {
		unsortMap.entrySet().stream()
        .sorted(Map.Entry.<String, Student>comparingByKey())
        .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
	}
	
	// print the student before searchItem key, the searchItem student, and the student immediately after
	public void print3Students() {

		System.out.printf("\nStudents before and after me\n");
		System.out.println("Empl Id   First name         Last name          Eye color   Months at SSA");
		System.out.println("=======   ================   ================   =========   =============");
		
		Map.Entry<String, Student> result2 = null;
		for (Map.Entry<String, Student> e : sortedMap.entrySet()) {
			if (e.getKey() == searchItem) {
				result2 = e;                                // current or searchItem		
				Map.Entry<String, Student> result1 = sortedMap.lowerEntry(e.getKey());  // previous		
				Map.Entry<String, Student> result3 = sortedMap.higherEntry(e.getKey()); // next
				if (result1 == null) {
					System.out.println("result1 is null");
					printStudent(result2.getValue());
					printStudent(result3.getValue());
					Map.Entry<String, Student> result4 = sortedMap.higherEntry(result3.getKey());
					printStudent(result4.getValue());
				}
				if (result3 == null) {
					System.out.println("result3 is null");
					Map.Entry<String, Student> result0 = sortedMap.lowerEntry(result1.getKey());
					printStudent(result0.getValue());
					printStudent(result1.getValue());
					printStudent(result2.getValue());
				}
				if ((result1 != null) && (result3 != null)) {
				printStudent(result1.getValue());
				printStudent(result2.getValue());
				printStudent(result3.getValue());	
				}
			} else {
				if ((sortedMap.lastKey() == e.getKey()) && (result2 == null))
				System.out.println("Student not found");
			}
			
		}			    
	}
	
}
