package ssa;


public class Main {
  public static void main(String[] args) throws Exception {
    MySQLAccess dao = new MySQLAccess();
    
    System.out.println("*******************Inserting George to the student table*******************");
    dao.insertToDataBase();
    dao.readDataBase();
    System.out.println("*******************Updating George's major, GPA, and SAT Score*************");
    dao.updateDataBase();
    dao.readDataBase();   
    System.out.println("*******************Deleting George to the student table********************");
    dao.deleteFromDataBase();
    dao.readDataBase();  
  }
}
