/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcwellnesdesktop.Controller;


import bcwellnesdesktop.View.AppointmentPanel;
import bcwellnesdesktop.View.AppointmentInput;
import javax.swing.*;
import javax.swing.table.*;
import bcwellnesdesktop.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;


/**
 *
 * @author marku
 */
public class ApointmentController {

    protected AppointmentPanel view;
    Connection con;
    DBConnection db = new DBConnection();
    public ApointmentController(){
       
        try {
            db.connect();
            con = db.getcon();
        } catch (ClassNotFoundException ex) {
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

    public void updateAppointment(
    int id,
    String studentName,
    String counselorName,
    String dateStr,
    String timeStr,
    String status
    ) {
        try {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE Appointments SET " +
                "studentName = ?, " +
                "counselorName = ?, " +
                "appointmentDate = ?, " +
                "appointmentTime = ?, " +
                "status = ? " +
                "WHERE id = ?"
        );

            java.sql.Date sqlDate = java.sql.Date.valueOf(dateStr);
            java.sql.Time sqlTime = java.sql.Time.valueOf(timeStr);

            ps.setString(1, studentName);
            ps.setString(2, counselorName);
            ps.setDate(3, sqlDate);
            ps.setTime(4, sqlTime);
            ps.setString(5, status);
            ps.setInt(6, id);

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
