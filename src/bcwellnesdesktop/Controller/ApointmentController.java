/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcwellnesdesktop.Controller;

import bcwellnesdesktop.View.AppointmentPanel;
import bcwellnesdesktop.View.AppointmentInput;
import javax.swing.*;
/**
 *
 * @author marku
 */
public class ApointmentController {
    protected AppointmentPanel view;
    
    public ApointmentController(AppointmentPanel view){
        this.view = view;
        this.view.getEditApp().addActionListener(e -> handleEdit());
    }
    
    private void handleEdit(){
        JTable table = view.getTableApp();
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(view, "Please select an appointment to edit.");
        return;
        }
        int id = (Integer) table.getValueAt(selectedRow, 0);
        String studentNumber = (String) table.getValueAt(selectedRow, 1);
        String counselor = (String) table.getValueAt(selectedRow, 2);
        String reason = (String) table.getValueAt(selectedRow, 3);
        String date = (String) table.getValueAt(selectedRow, 4);
        String time = (String) table.getValueAt(selectedRow, 5);
        String status = (String) table.getValueAt(selectedRow, 6);
        
        AppointmentInput frame = new AppointmentInput();
        
        frame.txtStudentNum.setText(studentNumber);
        frame.txtCounselor.setText(counselor);
        frame.txtReason.setText(reason);
        frame.txtDate.setText(date);
        frame.txtTime.setText(time);
        frame.cmbStatus.setSelectedItem(status);
        
        frame.setVisible(true);
    }
}
