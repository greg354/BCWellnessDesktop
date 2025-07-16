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

    

}
