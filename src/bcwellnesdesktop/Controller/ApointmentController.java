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
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Statement;
import java.sql.ResultSet;


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
    public void deleteAppointment(String id,JTable tbl) throws ClassNotFoundException{
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM Appointments WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            db.reloadapp(tbl);
        }catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(view, "Error deleting appointment.");
        }
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
    public void appadd(
    String sname,
    String cname,
    String adate,
    String atime,
    String status){
        try{
            PreparedStatement ps = con.prepareStatement(
            "INSERT INTO Appointments (studentName,counselorName,appointmentDate,appointmentTime,status)"+
            "VALUES (?,?,?,?,?)");
            SimpleDateFormat tparse = new SimpleDateFormat("hh:mm a");
            Date parsed = tparse.parse(atime);
            java.sql.Date sqlDate = java.sql.Date.valueOf(adate);
            java.sql.Time sqlTime = new java.sql.Time(parsed.getTime());
            ps.setString(1, sname);
            ps.setString(2, cname);
            ps.setDate(3, sqlDate);
            ps.setTime(4, sqlTime);
            ps.setString(5,status);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,
            "Appointment saved successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException | ParseException ex){
            ex.printStackTrace();
        }catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null,
                "Invalid date or time format. Please use YYYY-MM-DD for date and HH:MM:SS for time.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
