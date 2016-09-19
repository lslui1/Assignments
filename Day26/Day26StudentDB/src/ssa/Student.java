package ssa;

public class Student {
	private int id = 0;
	private String fName = null;
	private String lName = null;
	private double gpa = 0;
	private int sat = 0;
	private int major = 0;
	
	@Override
	public String toString() {
		return (String.format("%4d %-30s %4.2f %4d",this.id, this.fName + " " + this.lName,this.gpa,this.sat));
	}

	public void setStudentData(int aId, String afname, String alname, double aGpa, int aSat, int aMajor) {
		setId(aId);
		setfName(afname);
		setlName(alname);
		setGpa(aGpa);
		setSat(aSat);
		setMajor(aMajor);
	}
	
	public Student() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = sat;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}
}
