/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcwellnesdesktop.Controller;

import bcwellnesdesktop.DBConnection;
import bcwellnesdesktop.View.CounselorPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marku
 */
public class CounselorController {
    
    Connection con;
    DBConnection db = new DBConnection();
    public CounselorController(){
        
        try{
        db.connect();
         con = db.getcon();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
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
    public void updateCounselor(int id, String name, String special, String availability){
        try{
            PreparedStatement ps = con.prepareStatement(
            "UPDATE Counselors SET "+
            "name = ?,"+
            "specialization = ?, " +
            "availability = ? " +
            "WHERE id = ?");
            ps.setString(1, name);
            ps.setString(2, special);
            ps.setString(3, availability);
            ps.setInt(4, id);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
         ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
         // Handles bad date/time formatting
        JOptionPane.showMessageDialog(null,
                "Invalid date or time format. Please use YYYY-MM-DD for date and HH:MM:SS for time.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
