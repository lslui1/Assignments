package ssa;


public class Main {
  public static void main(String[] args) throws Exception {
    MySQLAccess dao = new MySQLAccess();
    
    System.out.println("*******************Inserting students to the student table*******************");
    dao.insertToDBStudent();
//    dao.readStudentDataBase();
    System.out.println("*******************Updating student's major**********************************");
    dao.updateDBMajor();
    dao.readStudentDataBase();   
    System.out.println("*******************Insert student's classes**********************************");
//    dao.InsertToDBClasses();
//    dao.readStudentDataBase();   
//    System.out.println("*****************Deleting student from the student table*******************");
//    dao.deleteFromDataBase();
//    dao.readStudentDataBase();  
    System.out.println("*******************Generating student report**********************************");
    dao.generateReport();
  }
}
