/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toptour;
import java.sql.*;
/**
 *
 * @author Michael Kuzyk
 */
public class MysqlConnect {
    


   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/toptour";
   static final String USER = "root";
   static final String PASS = "";
//static final String DB_URL = "jdbc:mysql://db3.ho.ua/toptour";
   /*
   static final String USER = "toptour";
   static final String PASS = "toptour";
   */
   public Connection bd( String sql,Connection conn,Statement stmt,ResultSet rs ) {
     
  conn = null;
   stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      //System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      /*System.out.println("Creating statement...");
      stmt = conn.createStatement();
      
      sql = "SELECT * FROM putivka";
       rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
       System.out.println(rs.getString(1) + " | " + rs.getString(2)+ " | " + rs.getString(3));
      }*/
      //STEP 6: Clean-up environment
     /* rs.close();
      stmt.close();
      conn.close();*/
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
      
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }
   
  /* 
   finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   */
  /* System.out.println("Goodbye!");*/
     return conn;
}//end main
}//end FirstExample

