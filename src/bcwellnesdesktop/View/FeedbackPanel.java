/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package bcwellnesdesktop.View;
import bcwellnesdesktop.View.FeedbackEdit;
import bcwellnesdesktop.Controller.FeedbackController;
import bcwellnesdesktop.DBConnection;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author marku
 */
public class FeedbackPanel extends javax.swing.JPanel {
    public JTable tblf;
    private JButton btnView;
    private JButton btnEdit;
    private JButton btnDelete;
    
    /**
     * Creates new form FeedbackPanel
     */
    public FeedbackPanel() {
        DBConnection db = new DBConnection();
        FeedbackController fc = new FeedbackController();
        String[] colnames={"ID","Student Name","Rating","Comments"};
        Object[][] data = fc.fview().toArray(new Object[0][]);
        setBackground(new Color(60, 63, 65));
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Manage Feedback");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(70, 73, 75));
        centerPanel.setLayout(new GridBagLayout());

        tblf = new JTable(data,colnames); //creating the table
        tblf.setFillsViewportHeight(true); //table aesthetics
        tblf.setBackground(new Color(80, 80, 80));
        tblf.setForeground(Color.WHITE);
        tblf.setGridColor(Color.DARK_GRAY);
        tblf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tblf.setRowHeight(24);
        
        JScrollPane scrp = new JScrollPane(tblf); //adding scroll to our table for better experience
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

        btnView = createStyledButton("Add Feedback");
        btnView.addActionListener(e -> {
            FeedbackInput frame = new FeedbackInput(this);
            frame.setVisible(true);
        });
        btnEdit = createStyledButton("Edit Feedback");
        btnEdit.addActionListener(e -> {
            int selectedRow = tblf.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a comment to edit.");
                return;
            }
            int id =  Integer.parseInt((String) tblf.getValueAt(selectedRow, 0)) ;
            String studentName = (String) tblf.getValueAt(selectedRow, 1);
            String rating = (String) tblf.getValueAt(selectedRow, 2);
            String comments = (String) tblf.getValueAt(selectedRow, 3);
            
            FeedbackEdit frame = new FeedbackEdit(this);
            
            frame.txtFullName.setText(studentName);
            frame.cmbRating.setSelectedItem(Integer.parseInt(rating));
            frame.txtComments.setText(comments);
            frame.setTitle("Edditing appointment for: " + id);
            frame.setVisible(true);
        });
        btnDelete = createStyledButton("Delete Feedback");
        btnDelete.addActionListener(e ->{
        int selectedRow = tblf.getSelectedRow(); 
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a feedback to delete.");
        return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this feedback?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);

         if (confirm != JOptionPane.YES_OPTION) return;
        
        String id = tblf.getValueAt(selectedRow, 0).toString();
            try {
                fc.deleteFeedback(id,this.tblf);
            } catch (ClassNotFoundException ex) {
                System.getLogger(FeedbackPanel.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });

        buttonPanel.add(btnView);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        add(buttonPanel, BorderLayout.SOUTH);
    }
    public JTable getTableFeed() {
    return tblf;
    }
    public JButton getAddFeed(){
        return btnView;
    }
    public JButton getEditFeed(){
        return btnEdit;
    }
    public JButton getDeleteFeed(){
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
