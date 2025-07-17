/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package bcwellnesdesktop.View;
import bcwellnesdesktop.View.AppointmentEdit;
import bcwellnesdesktop.Controller.ApointmentController;
import bcwellnesdesktop.DBConnection;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marku
 */
public class AppointmentPanel extends javax.swing.JPanel {
    public JTable tblapp;
    private JButton btnEdit;
    private JButton btnAdd;
    private JButton btnDelete;
    
    /**
     * Creates new form AppointmentPanel
     */
    DBConnection db = new DBConnection();
    public AppointmentPanel() {

        ApointmentController ac = new ApointmentController();
        String[] colnames = {"ID", "Student Name", "Councelor Name", "Date", "Time", "Status"};
        Object[][] data = ac.appview().toArray(new Object[0][]);
        setBackground(new Color(60, 63, 65));
        setLayout(new BorderLayout(10, 10));

     
        JLabel titleLabel = new JLabel("Manage Appointments");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(70, 73, 75));
        centerPanel.setLayout(new GridBagLayout());

        tblapp = new JTable(data,colnames); //creating the table
        tblapp.setFillsViewportHeight(true); //table aesthetics 
        tblapp.setBackground(new Color(80, 80, 80));
        tblapp.setForeground(Color.WHITE);
        tblapp.setGridColor(Color.DARK_GRAY);
        tblapp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblapp.setRowHeight(24);

        
        JScrollPane scrp = new JScrollPane(tblapp); //adding scroll to our table for better experience
        scrp.setPreferredSize(new Dimension(600,300));
        
        GridBagConstraints gbc = new GridBagConstraints(); //Otherwise the table is a small block, now we fit it into the grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        centerPanel.add(scrp, gbc);//adding to the panel

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(60, 63, 65));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        
        
        btnAdd = createStyledButton("Add Appointment");
        btnAdd.addActionListener(e -> {
            AppointmentInput form = new AppointmentInput(this); //don't fucking put this outside the arrow function!
            form.setVisible(true);
        });
        btnEdit = createStyledButton("Edit Appointment");
        btnEdit.addActionListener(e -> {
            
        int selectedRow = tblapp.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an appointment to edit.");
        return;
        }
        int id =  Integer.parseInt((String) tblapp.getValueAt(selectedRow, 0)) ;
        String studentNumber = (String) tblapp.getValueAt(selectedRow, 1);
        String counselor = (String) tblapp.getValueAt(selectedRow, 2);
        String date = (String) tblapp.getValueAt(selectedRow, 3);
        String time = (String) tblapp.getValueAt(selectedRow, 4);
        String status = (String) tblapp.getValueAt(selectedRow, 5);
        
        AppointmentEdit frame = new AppointmentEdit();
        
        frame.setTitle("Edditing appointment for: " + id);
        
        frame.txtStudentNum.setText(studentNumber);
        frame.txtCounselor.setText(counselor);
        frame.txtDate.setText(date);
        frame.txtTime.setText(time);
        frame.cmbStatus.setSelectedItem(status);
        
        frame.setVisible(true);
        });
        btnDelete = createStyledButton("Delete Appointment");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        /*controller.loadAppointments();*/

        
    }
    public JTable getTableApp() {
    return tblapp;
    }
    public JButton getAddApp(){
        return btnAdd;
    }
    public JButton getEditApp(){
        return btnEdit;
    }
    public JButton getDeleteApp(){
        return btnDelete;
    }
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(100, 100, 100));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return button;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
