/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcwellnesdesktop.Controller;

import bcwellnesdesktop.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Iwang
 */
public class FeedbackController {
    Connection con;
    DBConnection db = new DBConnection();
    public FeedbackController(){
        try{
        db.connect();
        con = db.getcon();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
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
    public void updateFeedback(int id, String name, int rating, String comments){
        
        try{
            PreparedStatement ps = con.prepareStatement(
            "UPDATE Feedback SET "+
            "studentName = ?,"+
            "rating = ?, " +
            "comments = ? " +
            "WHERE id = ?");
            ps.setString(1, name);
            ps.setInt(2, rating);
            ps.setString(3, comments);
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

