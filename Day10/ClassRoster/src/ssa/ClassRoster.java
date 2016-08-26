package ssa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassRoster {

	private List<String> list = new ArrayList<String>();
	
	public ClassRoster() {}
	
	// add to the roster
	public void addName(String aName) {
		this.list.add(aName);
	}
	
	// print list
	public void printList() {
		for (String temp : list) {
			System.out.println(temp);
		}
	}
	
	//sort ascending and print
	public void printAsc() {
		Collections.sort(list);
		System.out.println("--------------------------------------\nClass roster in ascending order\n--------------------------------------\n");
		printList();
	}
	
	// sort descending and print
	public void printDesc() {
		Collections.sort(list, Collections.reverseOrder());
		System.out.println("--------------------------------------\nClass roster in descending order\n--------------------------------------\n");
		printList();
	}
	
	// get class size
	public int getClassSize() {
		return list.size();
	}
	
	// Generate class report
	public void classRosterReport() {
		this.addName("Li");
		this.addName("Joseph");
		this.addName("Michael S");
		this.addName("Joshua");
		this.addName("Stephen R");
		this.addName("Jarrett");
		this.addName("Shaquil");
		this.addName("Karen");
		this.addName("Reuben");
		this.addName("Dax");
		this.addName("Aisha");
		this.addName("Evan");
		this.addName("Daniel");
		this.addName("Stephen");
		this.addName("Peter");
		this.addName("Michael C");
		this.addName("Arun");
		this.addName("Gabriel");
		this.addName("Johnathan");
		this.addName("Kyle");
		this.addName("Daniel");
		
		printAsc();
		printDesc();
		System.out.printf("\nTotal number of students is %d.\n", getClassSize());
	}
}
