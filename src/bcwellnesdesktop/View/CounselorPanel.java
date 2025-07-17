/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package bcwellnesdesktop.View;
import bcwellnesdesktop.View.CounselorEdit;
import bcwellnesdesktop.Controller.CounselorController;
import bcwellnesdesktop.DBConnection;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author marku
 */
public class CounselorPanel extends javax.swing.JPanel {
    public JTable tblc;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    /**
     * Creates new form CounselorPanel
     */
    public CounselorPanel() {
        CounselorController councontroller = new CounselorController();
        String[] colnames={"ID","Counselor Name","Specialization","Availability"};
        Object[][] data = councontroller.cview().toArray(new Object[0][]);
        
         setBackground(new Color(60, 63, 65));
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Manage Counselors");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(70, 73, 75));
        centerPanel.setLayout(new GridBagLayout());

        tblc = new JTable(data,colnames); //creating the table
        tblc.setFillsViewportHeight(true); //table aesthetics
        tblc.setBackground(new Color(80, 80, 80));
        tblc.setForeground(Color.WHITE);
        tblc.setGridColor(Color.DARK_GRAY);
        tblc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblc.setRowHeight(24);
        
        JScrollPane scrp = new JScrollPane(tblc); //adding scroll to our table for better experience
        scrp.setPreferredSize(new Dimension(600,300));
        
        GridBagConstraints gbc = new GridBagConstraints(); //Otherwise the table is a small block, now we fit it into the grid
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        centerPanel.add(scrp, gbc); //adding to the panel
        
        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(60, 63, 65));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnAdd = createStyledButton("Add Counselor");
        btnAdd.addActionListener(e -> {
            CounselorInput form = new CounselorInput(this);
            form.setVisible(true);
        });
        btnEdit = createStyledButton("Edit Counselor");
        btnEdit.addActionListener(e -> {
        int selectedRow = tblc.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an appointment to edit.");
        return;
        }
        int id =  Integer.parseInt((String) tblc.getValueAt(selectedRow, 0)) ;
        String studentName = (String) tblc.getValueAt(selectedRow, 1);
        String specialization = (String) tblc.getValueAt(selectedRow, 2);
        String availability = (String) tblc.getValueAt(selectedRow, 3);
        
        CounselorEdit frame = new CounselorEdit(this);
        
        frame.txtName.setText(studentName);
        frame.txtSpecialization.setText(specialization);
        frame.cmbAvailability.setSelectedItem(availability);
        frame.setTitle("Edditing counselor for: " + id);
        frame.setVisible(true);
        });
        btnDelete = createStyledButton("Delete Counselor");
        btnDelete.addActionListener(e ->{
        int selectedRow = tblc.getSelectedRow(); 
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an counselor to delete.");
        return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this counselor?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);

         if (confirm != JOptionPane.YES_OPTION) return;
        
        String id = tblc.getValueAt(selectedRow, 0).toString();
            try {
                councontroller.deleteCounselor(id,this.tblc);
            } catch (ClassNotFoundException ex) {
                System.getLogger(CounselorPanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);
        
        /*controller.loadCounselors();*/
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
    public JTable getTableCoun(){
        return tblc;
    }
    public JButton getAddCoun(){
        return btnAdd;
    }
    public JButton getEditCoun(){
        return btnEdit;
    }
    public JButton getDeleteCoun(){
        return btnDelete;
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
