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
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marku
 */
public class DBConnection {
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/WelllnessDB"; //start of db name: WelllnessDB is for Markus, welnessDB is for Iwan
    
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
    public Connection getcon(){
        return con;
    }
    
    public void reloadapp(JTable tbl) throws ClassNotFoundException{
            DefaultTableModel mdl = new DefaultTableModel(
        new String[]{"studentName", "counselorName", "appointmentDate", "appointmentTime", "status"}, 0
        );
        try{
            String sql = "SELECT * FROM Appointments";
            connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                mdl.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("studentName"),
                    rs.getString("counselorName"),
                    rs.getDate("appointmentDate").toString(),
                    rs.getTime("appointmentTime").toString(),
                    rs.getString("status")                    
                });
            }
            tbl.setModel(mdl);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void reloadc(JTable tbl) throws ClassNotFoundException{
            DefaultTableModel mdl = new DefaultTableModel(
        new String[]{"name", "specialization", "availability"}, 0
        );
        try{
            String sql = "SELECT * FROM Counselors";
            connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                mdl.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("availability")                   
                });
            }
            tbl.setModel(mdl);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void reloadf(JTable tbl) throws ClassNotFoundException{
        DefaultTableModel mdl = new DefaultTableModel(
        new String[]{"id","studentName", "rating", "comments"}, 0
        );
        try{
            String sql = "SELECT * FROM Feedback";
            connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                mdl.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("studentName"),
                    rs.getInt("rating"),
                    rs.getString("comments")                    
                });
            }
            tbl.setModel(mdl);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
   
   

