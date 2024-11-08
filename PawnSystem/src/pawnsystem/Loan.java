/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pawnsystem;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Loan extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection conn;
    String tempPawnid, tempCustomer, tempTelephone;
    String tempstart, tempdue;
    /**
     * Creates new form Loan
     */
    public Loan() {
        initComponents();
        
        txtpawn.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search by name or phone");
        txtpaid.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter the amount");
        txtpawn.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("images/search.svg",0.35f));
        txtprincipal.setForeground(Color.red);
        connectDatabase();
        getData();
    }
    
    
    private void getData(){
        model = (DefaultTableModel) tblLoan.getModel();
        model.setRowCount(0);
        try {
            Connection con = Connector.getConnection();
            Statement st = con.createStatement();
            String query = "Select * from Pawn, Item where Pawn.item_id = Item.item_id and debt_amount > 0";
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                String id = rs.getString("pawn_id");
                String customerName = rs.getString("customer_name");
                String telephone = rs.getString("telephone");
                String item = rs.getString("item_name");
                String startDate = rs.getString("loan_start");
                String dueDate = rs.getString("loan_due");
                String debtAmount = String.valueOf(rs.getDouble("debt_amount"));
                String paidCount = String.valueOf(rs.getInt("paid_count"));
                
                Object data[] = {id,customerName,telephone,item,startDate,dueDate,debtAmount,paidCount};
                
                DefaultTableModel tblmodel = (DefaultTableModel) tblLoan.getModel();
                
                JTable table = new JTable(tblmodel);
                
                tblmodel.addRow(data);
                
                
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cboSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboSort.getSelectedItem();
                if(cboSort.getSelectedIndex() == 0){
                    connectDatabase();
                    getData();
                }else if(cboSort.getSelectedIndex() == 1){
                    model = (DefaultTableModel) tblLoan.getModel();
                    model.setRowCount(0);
                    String query = "Select * from Pawn, Item where Pawn.item_id = Item.item_id and debt_amount > 0 and loan_due <= ?";
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    // Format the current timestamp
                    String formattedTimestamp = currentDateTime.format(formatter);
                    try 
                    (Connection conn = Connector.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, formattedTimestamp);
                    ResultSet rs = stmt.executeQuery();

                    while(rs.next()){
                        String id = rs.getString("pawn_id");
                        String customerName = rs.getString("customer_name");
                        String telephone = rs.getString("telephone");
                        String item = rs.getString("item_name");
                        String startDate = rs.getString("loan_start");
                        String dueDate = rs.getString("loan_due");
                        String debtAmount = String.valueOf(rs.getDouble("debt_amount"));
                        String paidCount = String.valueOf(rs.getInt("paid_count"));

                        Object data[] = {id,customerName,telephone,item,startDate,dueDate,debtAmount,paidCount};

                        DefaultTableModel tblmodel = (DefaultTableModel) tblLoan.getModel();

                        JTable table = new JTable(tblmodel);

                        tblmodel.addRow(data);


                    }

                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    model = (DefaultTableModel) tblLoan.getModel();
                    model.setRowCount(0);
                    String query = "Select * from Pawn, Item where Pawn.item_id = Item.item_id and debt_amount > 0 and extract(month from loan_due) = extract(month from ?);";
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    // Format the current timestamp
                    String formattedTimestamp = currentDateTime.format(formatter);
                    try 
                    (Connection conn = Connector.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, formattedTimestamp);
                    ResultSet rs = stmt.executeQuery();

                    while(rs.next()){
                        String id = rs.getString("pawn_id");
                        String customerName = rs.getString("customer_name");
                        String telephone = rs.getString("telephone");
                        String item = rs.getString("item_name");
                        String startDate = rs.getString("loan_start");
                        String dueDate = rs.getString("loan_due");
                        String debtAmount = String.valueOf(rs.getDouble("debt_amount"));
                        String paidCount = String.valueOf(rs.getInt("paid_count"));

                        Object data[] = {id,customerName,telephone,item,startDate,dueDate,debtAmount,paidCount};

                        DefaultTableModel tblmodel = (DefaultTableModel) tblLoan.getModel();

                        JTable table = new JTable(tblmodel);

                        tblmodel.addRow(data);


                    }

                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Additional actions can be performed here
            }
        });
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtpawn = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtprincipal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtinterest = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtpaid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnpaid1 = new javax.swing.JButton();
        txtduration = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtitem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnclear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoan = new javax.swing.JTable();
        cboSort = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Baloo Bhai 2", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(75, 84, 92));
        jLabel1.setText("Loan ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 14, 159, 50));

        txtpawn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpawnKeyReleased(evt);
            }
        });
        jPanel1.add(txtpawn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 240, 35));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtprincipal.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel3.setText("Principal:");

        txtinterest.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel4.setText("Interest Month:");

        txtamount.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel5.setText("Total Amount:");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel6.setText("Paid Amount:");

        btnpaid1.setText("Pay");
        btnpaid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpaid1ActionPerformed(evt);
            }
        });

        txtduration.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel7.setText("Duration:");

        txtitem.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel8.setText("item:");

        btnclear.setText("Clear");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(txtprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtpaid, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtinterest, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtitem, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtduration, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnpaid1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                    .addComponent(btnclear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(23, 23, 23)))))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtinterest, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtduration, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtpaid, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)
                        .addComponent(txtitem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnpaid1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 720, 180));

        tblLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pawn ID", "Customer Name", "Phone", "Item", "Loan Start", "Loan Due", "Debt Amount", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblLoanMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblLoan);
        if (tblLoan.getColumnModel().getColumnCount() > 0) {
            tblLoan.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblLoan.getColumnModel().getColumn(1).setPreferredWidth(110);
            tblLoan.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblLoan.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblLoan.getColumnModel().getColumn(5).setPreferredWidth(70);
            tblLoan.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 720, 380));

        cboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Check Loan ~", "Expired", "Due this month" }));
        jPanel1.add(cboSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 265, 150, 30));

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblLoanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoanMousePressed
//         TODO add your handling code here:
        int selectedRowIndex = tblLoan.getSelectedRow();

        // If no row is selected, selectedRowIndex will be -1
        if (selectedRowIndex != -1) {
            // Get the table model
            TableModel model = tblLoan.getModel();

            // Retrieve the values safely with proper casting
            Object idObj = model.getValueAt(selectedRowIndex, 0);   // Assuming 1st column is ID
            Object nameObj = model.getValueAt(selectedRowIndex, 1); // Assuming 2nd column is Name
            Object itemObj = model.getValueAt(selectedRowIndex, 3); // Assuming 3rd column is Username
            Object telephoneObj = model.getValueAt(selectedRowIndex, 2); // Assuming 3rd column is Username
            Object tempstartObj = model.getValueAt(selectedRowIndex,4);
            Object tempdueObj = model.getValueAt(selectedRowIndex,5);

            // Convert ID to string if it's an Integer or handle accordingly
            String pawnId = idObj != null ? idObj.toString() : "";  // Handle possible Integer
            tempCustomer = nameObj instanceof String ? (String) nameObj : "";
            String item = itemObj instanceof String ? (String) itemObj : "";
            tempTelephone = telephoneObj instanceof String? (String) telephoneObj: "";
            tempstart = tempstartObj instanceof String? (String) tempstartObj:"";
            tempdue = tempdueObj instanceof String? (String) tempdueObj:"";
            
            // Set the values to the respective text fields and combo box
            String query = "SELECT *, principal+interest_month as total_amount FROM Loan where pawn_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, pawnId);
              
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("pawn_id");
                    String principal = String.valueOf(rs.getDouble("principal"));
                    String interest = String.valueOf(rs.getDouble("interest_month"));
                    String totalAmount = String.valueOf(rs.getDouble("total_amount"));
                    String duration = rs.getString("duration");
                    
                    tempPawnid = id;
                    txtprincipal.setText(principal);
                    txtinterest.setText(interest);
                    txtamount.setText(totalAmount);
                    txtitem.setText(item);
                    txtduration.setText(duration);
            
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }        
            
            // Print the selected row data for debugging (optional)
            

        } else {
            System.out.println("No row selected.");
        }

    }//GEN-LAST:event_tblLoanMousePressed

    private void txtpawnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpawnKeyReleased
        model = (DefaultTableModel) tblLoan.getModel();
        String searchText = txtpawn.getText().trim();
        model.setRowCount(0);
        String query = "SELECT * FROM Pawn INNER JOIN Item on Pawn.item_id = Item.item_id\n" +
                    "where customer_name like ? or telephone like ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, searchText + "%");
            stmt.setString(2,searchText + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("pawn_id");
                String customerName = rs.getString("customer_name");
                String telephone = rs.getString("telephone");
                String item = rs.getString("item_name");
                String startDate = rs.getString("loan_start");
                String dueDate = rs.getString("loan_due");
                String debtAmount = String.valueOf(rs.getDouble("debt_amount"));
                String paidCount = String.valueOf(rs.getInt("paid_count"));
                
                model.addRow(new Object[]{id,customerName,telephone,item,startDate,dueDate,debtAmount,paidCount});
                // Add row to table model
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtpawnKeyReleased

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        clearData();
    }//GEN-LAST:event_btnclearActionPerformed

    private void btnpaid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpaid1ActionPerformed
        // TODO add your handling code here:
        if(!txtpaid.getText().equalsIgnoreCase("")){
            double paidAmount = Double.valueOf(txtpaid.getText());
            double totalAmount = Double.valueOf(txtamount.getText());
            double principalAmount = Double.valueOf(txtprincipal.getText());
            double returnMoney = paidAmount - totalAmount;
            returnMoney = Math.round(returnMoney);
            
            if(paidAmount >= totalAmount && paidAmount >0){
                String totalResult = "Check the loan details properly before proceeding!!"
                        + "\n\nPawn ID: " + tempPawnid
                        + "\nCustomer Name: "+ tempCustomer
                        + "\nTelephone: " + tempTelephone
                        + "\nItem: " + txtduration.getText()
                        + "\nTotal Amount: " + txtamount.getText() +" $"
                        + "\nPaid Amount: " + String.valueOf(paidAmount) +" $"
                        + "\nReturn Money: " + String.valueOf(returnMoney) + " $";
                String[] options = { "Cancel", "Pay" };
                   var selection = JOptionPane.showOptionDialog(null,totalResult, 
                           "Payment Verification", 0, 3, null, options, options[0]);
                   if(selection == 1){
                       String startDate = tempdue;
                        // Parse the string date to LocalDate
                        LocalDate date = LocalDate.parse(startDate);

                        // Add one month to the date
                        LocalDate newDate = date.plusMonths(1);

                        // Format the new date back to string format
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String dueDate = newDate.format(formatter);
                        
                       try(Connection con = Connector.getConnection()){
                        PreparedStatement st = con.prepareStatement("UPDATE Pawn SET debt_amount = debt_amount - ?, loan_start = ?, loan_due = ?, paid_count = paid_count+1 WHERE pawn_id = ?");
                        st.setDouble(1, principalAmount);
                        st.setString(2, startDate);
                        st.setString(3, dueDate);
                        st.setString(4, tempPawnid);
                        st.executeUpdate();
                        clearData();
                        }catch(SQLException e){
                            e.printStackTrace();
                        };
                        updateLoanStatus();
                        JOptionPane.showMessageDialog(null, "Record Added Successfully.");
                   }
            }else{
                JOptionPane.showMessageDialog(null, "The paid amount is insufficient","Failure", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
             JOptionPane.showMessageDialog(null, "Fill in the Paid Amount textfield","Failure", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnpaid1ActionPerformed
    
    public static void updateLoanStatus() {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            // Establish a database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/pawnshop_db", "root", "");

            // Prepare to call the stored procedure
            String sql = "{CALL UpdateLoanStatus()}";
            callableStatement = connection.prepareCall(sql);

            // Execute the stored procedure
            callableStatement.execute();
            System.out.println("Loan status updated successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void clearData(){
        txtprincipal.setText("");
        txtinterest.setText("");
        txtamount.setText("");
        txtduration.setText("");
        txtpaid.setText("");
        txtitem.setText("");
        getData();
    }
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
            java.util.logging.Logger.getLogger(Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnpaid1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboSort;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLoan;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtduration;
    private javax.swing.JTextField txtinterest;
    private javax.swing.JTextField txtitem;
    private javax.swing.JTextField txtpaid;
    private javax.swing.JTextField txtpawn;
    private javax.swing.JTextField txtprincipal;
    // End of variables declaration//GEN-END:variables
}

