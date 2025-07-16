/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcwellnesdesktop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author marku
 */
public class DBConnection {
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/welnessDB";
    
    Connection con;
    
    public DBConnection(){
        
    }
    public void connect() throws ClassNotFoundException{
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(JDBC_URL,"admin1","1101");
            if(this.con != null){
                System.out.println("Connected to the database!");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public ArrayList<String[]> appview(){
        ArrayList<String[]> dlist = new ArrayList<>();
        try{
            String qry = "SELECT * FROM APPOINTMENTS";
            ResultSet table = this.con.createStatement().executeQuery(qry);
            while(table.next()){
               String ID = table.getString("ID");
               String stdname = table.getString("STUDENTNAME");
               String cname = table.getString("COUNSELORNAME");
               String date = table.getString("APPOINTMENTDATE");
               String time = table.getString("APPOINTMENTTIME");
               String status = table.getString("STATUS");
               
               String[] row = {ID,stdname,cname,date,time,status};
               dlist.add(row);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return dlist;
    }
    public ArrayList<String[]> cview(){
        ArrayList<String[]> dlist = new ArrayList<>();
        try{
            String qry = "SELECT * FROM COUNSELORS";
            ResultSet table = this.con.createStatement().executeQuery(qry);
            while(table.next()){
               String ID = table.getString("ID");
               String stdname = table.getString("NAME");
               String cname = table.getString("SPECIALIZATION");
               String date = table.getString("AVAILABILITY");
               String[] row = {ID,stdname,cname,date};
               dlist.add(row);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return dlist;
    }
    public ArrayList<String[]> fview(){
        ArrayList<String[]> dlist = new ArrayList<>();
        try{
            String qry = "SELECT * FROM FEEDBACK";
            ResultSet table = this.con.createStatement().executeQuery(qry);
            while(table.next()){
               String ID = table.getString("ID");
               String stdname = table.getString("STUDENTNAME");
               String cname = table.getString("RATING");
               String date = table.getString("COMMENTS");
               
               String[] row = {ID,stdname,cname,date};
               dlist.add(row);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return dlist;
    }
       
}
   
   

