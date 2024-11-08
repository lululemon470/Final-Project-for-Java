/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pawnsystem;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author macbookpro14
 */
public class PawnHistory extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection conn;
    String tempPawnid,tempItem; 
    /**
     * Creates new form PawnHistory
     */
    public PawnHistory() {
        initComponents();
        connectDatabase();
        getData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtpawn = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPawn = new javax.swing.JTable();
        btnclear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Baloo Bhai 2", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(75, 84, 92));
        jLabel1.setText("Pawn History");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 330, 50));

        txtpawn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpawnKeyReleased(evt);
            }
        });
        jPanel1.add(txtpawn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 240, 35));

        tblPawn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pawn ID", "Customer Name", "Phone", "Loan Amount", "Item", "Start Date", "Redeem Date", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPawn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPawnMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblPawn);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 720, 540));

        btnclear.setText("Clear");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });
        jPanel1.add(btnclear, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 110, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void connectDatabase() {
        try {
            // Adjust with your database details
            String url = "jdbc:mysql://localhost:3308/pawnshop_db";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void getData(){
        model = (DefaultTableModel) tblPawn.getModel();
        model.setRowCount(0);
        try {
            Connection con = Connector.getConnection();
            Statement st = con.createStatement();
            String query = "Select *, (loan_amount * interest_rate)+loan_amount as totalloan from \n" +
                    "Pawn, Item, Loan where \n" +
                    "Pawn.item_id = Item.item_id and \n" +
                    "Pawn.pawn_id = Loan.pawn_id and \n" +
                    "loan_status = 'closed'";
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                String id = rs.getString("pawn_id");
                String customerName = rs.getString("customer_name");
                String telephone = rs.getString("telephone");
                String item = rs.getString("item_name");
                String startDate = rs.getString("start_date");
                String dueDate = rs.getString("loan_due");
                String totalLoan = String.valueOf(rs.getDouble("totalloan"));
                String paidCount = String.valueOf(rs.getInt("paid_count"));
                
                
                
                Object data[] = {id,customerName,telephone,totalLoan,item,startDate,dueDate,paidCount};
                
                DefaultTableModel tblmodel = (DefaultTableModel) tblPawn.getModel();
                
                JTable table = new JTable(tblmodel);
                
                tblmodel.addRow(data);
                
                
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void txtpawnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpawnKeyReleased
        model = (DefaultTableModel) tblPawn.getModel();
        String searchText = txtpawn.getText().trim();
        model.setRowCount(0);
        String query = "select *, (loan_amount * interest_rate)+loan_amount as totalloan from Pawn\n" +
        "left join Loan on pawn.pawn_id = loan.pawn_id\n" +
        "left join Item on item.item_id = pawn.item_id\n" +
        "where loan_status = 'closed' and customer_name like ? or telephone like ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, searchText + "%");
            stmt.setString(2,searchText + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("pawn_id");
                String customerName = rs.getString("customer_name");
                String telephone = rs.getString("telephone");
                String item = rs.getString("item_name");
                String startDate = rs.getString("start_date");
                String dueDate = rs.getString("loan_due");
                String totalLoan = String.valueOf(rs.getDouble("totalloan"));
                String paidCount = String.valueOf(rs.getInt("paid_count"));

                model.addRow(new Object[]{id,customerName,telephone,totalLoan,item,startDate,dueDate,paidCount});
                // Add row to table model

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtpawnKeyReleased

    private void tblPawnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPawnMousePressed
        //         TODO add your handling code here:
        int selectedRowIndex = tblPawn.getSelectedRow();

        // If no row is selected, selectedRowIndex will be -1
        if (selectedRowIndex != -1) {
            // Get the table model
            TableModel model = tblPawn.getModel();

            // Retrieve the values safely with proper casting
            Object idObj = model.getValueAt(selectedRowIndex, 0);   // Assuming 1st column is ID
            Object nameObj = model.getValueAt(selectedRowIndex, 1); // Assuming 2nd column is Name
            Object itemObj = model.getValueAt(selectedRowIndex, 3); // Assuming 3rd column is Username
            Object telephoneObj = model.getValueAt(selectedRowIndex, 2); // Assuming 3rd column is Username
            Object tempstartObj = model.getValueAt(selectedRowIndex,4);
            Object tempdueObj = model.getValueAt(selectedRowIndex,5);

            // Convert ID to string if it's an Integer or handle accordingly
            String pawnId = idObj != null ? idObj.toString() : "";  // Handle possible Integer
            tempPawnid = pawnId;
            String query = "SELECT * from Item, Pawn where Pawn.item_id = Item.item_id and pawn_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, pawnId);
              
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    tempItem = rs.getString("Item.item_id");
                    System.out.println();
            
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }     

            // Print the selected row data for debugging (optional)

        } else {
            System.out.println("No row selected.");
        }
    }//GEN-LAST:event_tblPawnMousePressed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        int selectedRowIndex = tblPawn.getSelectedRow();

        // If no row is selected, selectedRowIndex will be -1
        if (selectedRowIndex != -1) {
        int choice = JOptionPane.showConfirmDialog(null, "Are u sure u want to delete this record?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try (Connection con = Connector.getConnection()){
                       String query= "DELETE FROM Pawn WHERE pawn_id = ? ";
                       // If the user chose 'Yes', show a message indicating that changes are saved
                       PreparedStatement preparedStatement = con.prepareStatement(query);
                       preparedStatement.setString(1,tempPawnid);
                       preparedStatement.executeUpdate();                   
           } catch (SQLException e) {
               e.printStackTrace();
           }
            try (Connection con = Connector.getConnection()){

                       String query= "DELETE FROM Item WHERE item_id = ? ";
                       // If the user chose 'Yes', show a message indicating that changes are saved
                       PreparedStatement preparedStatement = con.prepareStatement(query);
                       preparedStatement.setString(1,tempItem);
                       preparedStatement.executeUpdate();

           } catch (SQLException e) {
               e.printStackTrace();
           }
            JOptionPane.showMessageDialog(null, "Record Deleted Successfully.");
                   getData();
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "Select row to delete","Failure", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnclearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PawnHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PawnHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PawnHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PawnHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         UIManager.setLookAndFeel(new FlatIntelliJLaf());
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PawnHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnclear;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPawn;
    private javax.swing.JTextField txtpawn;
    // End of variables declaration//GEN-END:variables
}