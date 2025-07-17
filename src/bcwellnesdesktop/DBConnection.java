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
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/welnessDB"; //start of db name: WelllnessDB is for Markus, welnessDB is for Iwan
    
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
        new String[]{"STUDENTNAME", "COUNSELORNAME", "APPOINTMENTDATE", "APPOINTMENTTIME", "STATUS"}, 0
        );
        try{
            String sql = "SELECT * FROM APPOINTMENTS";
            connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                mdl.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("STUDENTNAME"),
                    rs.getString("COUNSELORNAME"),
                    rs.getDate("APPOINTMENTDATE").toString(),
                    rs.getTime("APPOINTMENTTIME").toString(),
                    rs.getString("STATUS")                    
                });
            }
            tbl.setModel(mdl);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void reloadc(JTable tbl) throws ClassNotFoundException{
            DefaultTableModel mdl = new DefaultTableModel(
        new String[]{"NAME", "SPECIALIZATION", "AVAILABILITY"}, 0
        );
        try{
            String sql = "SELECT * FROM COUNSELORS";
            connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                mdl.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("NAME"),
                    rs.getString("SPECIALIZATION"),
                    rs.getString("AVAILABILITY")                   
                });
            }
            tbl.setModel(mdl);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void reloadf(JTable tbl) throws ClassNotFoundException{
        DefaultTableModel mdl = new DefaultTableModel(
        new String[]{"ID","STUDENTNAME", "RATING", "COMMENTS"}, 0
        );
        try{
            String sql = "SELECT * FROM FEEDBACK";
            connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                mdl.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("STUDENTNAME"),
                    rs.getInt("RATING"),
                    rs.getString("COMMENTS")                    
                });
            }
            tbl.setModel(mdl);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
   
   

