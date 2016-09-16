package ssa;


public class Main {
  public static void main(String[] args) throws Exception {
    MySQLAccess dao = new MySQLAccess();
    
    System.out.println("*******************Inserting students to the student table*******************");
    dao.addStudentsToDB();
    System.out.println("*******************Updating student's major**********************************");
    dao.requestAddStudentMajor();
    System.out.println("*******************Dispaying student table***********************************");
    dao.readStudentTable();   
    System.out.println("*******************Insert student classes************************************");
    dao.InsertToDBClasses(); 
    System.out.println("*******************Generating student report*********************************");
    dao.generateReport();
  }
}
