/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pawnsystem;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author macbookpro14
 */
public class Pawn extends javax.swing.JFrame{
    String path="/Users/macbookpro14/Desktop/Fake Desktop/SETEC/Year 4/Java with IReport/PawnSystem/src/images/No_Image_Available.jpg";
    File selectedFile, imagesDir, destinationFile;
    int tempCreated = 0;
    String tempPawnID = "";
    String tempDueDate="";
    double tempTotalLoan = 0, tempInterestRate=0;
    String tempItem="", tempType="", tempName="", tempTelephone="",tempLoandue="",tempLoanstart="";
    /**
     * Creates new form Pawn
     */
    public Pawn(int createdBy) {
        initComponents();
        
          // Add the number-only document filter to the text field
        ((AbstractDocument) txtloan.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        ((AbstractDocument) txtphone.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        tempCreated = createdBy;
        btnpawn.setIcon(new FlatSVGIcon("images/add.svg",0.35f));
        imageLabel.setPreferredSize(new Dimension(547, 547)); // Set preferred size of JLabel
        
        txtitemname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter item name");
        txtdescription.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter item's detail");
        txtloan.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter amount ($)");
        
        txtstartdate.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "yyyy/mm/dd");
        ImageIcon imgbtn = new ImageIcon("/Users/macbookpro14/Desktop/Fake Desktop/SETEC/Year 4/Java with IReport/PawnSystem/src/images/icons8-calendar-36.png");
        btncalendar.setIcon(imgbtn);
        
        txtcname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Full Name");
        txtphone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Number");
       
        try {
            Connection con = Connector.getConnection();
            Statement st = con.createStatement();
            String query = "select * from Interest order by id";
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                String interest_rate = String.valueOf(rs.getInt("id"));
                cboInterest.addItem(interest_rate);
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        // Load and fit the image
        try {
            // Load the image from a file (can be from a URL or other source)
            BufferedImage img = ImageIO.read(new File("/Users/macbookpro14/Desktop/Fake Desktop/SETEC/Year 4/Java with IReport/PawnSystem/src/images/No_Image_Available.jpg"));
            
            // Resize the image to fit the JLabel's size
            Image scaledImage = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            
            // Set the scaled image as the icon for the JLabel
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
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
        jPanel2 = new javax.swing.JPanel();
        btnpawn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cboitemType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtitemname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescription = new javax.swing.JTextArea();
        txtloan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtstartdate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btncalendar = new javax.swing.JLabel();
        cboInterest = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        cboDuration = new javax.swing.JComboBox<>();
        txtcname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtphone = new javax.swing.JTextField();
        btnMoreInterest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(830, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Baloo Bhai 2", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(75, 84, 92));
        jLabel1.setText("Pawn");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 159, 50));

        btnpawn.setText("Pawn new Item");
        btnpawn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpawnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel3.setText("Item name:");

        cboitemType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Select Option ~", "Car", "Motorcycle", "Others" }));
        cboitemType.setPreferredSize(new java.awt.Dimension(72, 30));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel4.setText("Item Type: ");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel5.setText("Description:");

        txtdescription.setColumns(20);
        txtdescription.setRows(5);
        jScrollPane1.setViewportView(txtdescription);

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel6.setText("Loan Amount:");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel7.setText("Interest Rate:");

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel8.setText("Start Date:");

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel9.setText("Duration:");

        btncalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-calendar-36.png"))); // NOI18N
        btncalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btncalendarMousePressed(evt);
            }
        });

        cboInterest.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Interest Rate (%)" }));
        cboInterest.setPreferredSize(new java.awt.Dimension(72, 30));
        cboInterest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboInterestMousePressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item's image", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Helvetica Neue", 0, 16))); // NOI18N
        jPanel3.setToolTipText("Item Image");
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-image-100.png"))); // NOI18N
        imageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageLabelMousePressed(evt);
            }
        });
        jPanel3.add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 25, 165, 165));

        cboDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Duration (Months)", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cboDuration.setPreferredSize(new java.awt.Dimension(72, 30));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel10.setText("Customer Name: ");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel11.setText("Telephone:");

        btnMoreInterest.setText("+");
        btnMoreInterest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoreInterestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtstartdate)
                            .addComponent(cboInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncalendar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnpawn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtloan, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(170, 170, 170)
                                .addComponent(btnMoreInterest)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtcname, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtitemname, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboitemType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(49, 49, 49)
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtphone))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(16, 16, 16)))))
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboitemType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtitemname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtloan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(cboInterest, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnMoreInterest))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(cboDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btncalendar, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(txtstartdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(btnpawn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 770, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void printInvoice() {
//        DefaultTableModel model = (DefaultTableModel) tblsale.getModel();
//        int rowCount = model.getRowCount();
//
//        if (rowCount == 0) {
//            JOptionPane.showMessageDialog(this, "No items to print.", "Warning", JOptionPane.WARNING_MESSAGE);
//            return;
//        }

//        List<Map<String, Object>> reportData = new ArrayList<>();
        
//        for (int i = 0; i < rowCount; i++) {
//            Map<String, Object> row = new HashMap<>();
//            row.put("saleitem", model.getValueAt(i, 1));
//
//            Object qtyValue = model.getValueAt(i, 2);
//            row.put("qty", qtyValue != null ? qtyValue.toString() : "0"); // Handle null values
//
//            Object amountValue = model.getValueAt(i, 4);
//            row.put("amount", amountValue != null ? amountValue.toString() : "0"); // Handle null values
//            
//            System.out.println(amountValue);
//            reportData.add(row);
//        }
        

//        double totalPrice = Double.parseDouble(txttotalamount.getText().replaceAll("[^\\d.]", ""));
//        double cash = Double.parseDouble(txtpaid.getText().replaceAll("[^\\d.]", ""));
//        double change = Double.parseDouble(txtreturnmoney.getText().replaceAll("[^\\d.]", ""));

        // Create parameters map
        List<Map<String, Object>> reportData = new ArrayList<>();
        Map<String, Object> row = new HashMap<>();
        row.put("customer_name", txtcname.getText());
              
        row.put("description", txtdescription.getText()); // Handle null values

        row.put("telephone", txtphone.getText()); // Handle null values
            
        row.put("loan_start", txtstartdate.getText());
        
        row.put("loan_due", tempDueDate);
        
        row.put("item_name",txtitemname.getText());
        
        row.put("item_type", String.valueOf(cboitemType.getSelectedItem()));
        
        reportData.add(row);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("loan_amount",Double.valueOf(txtloan.getText()));
        parameters.put("interest_rate", String.valueOf(cboInterest.getSelectedItem()));
        parameters.put("duration", String.valueOf(cboDuration.getSelectedItem()));
        parameters.put("pawn_id", tempPawnID);

//         Create a data source for the report
         JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);

        try {
            File reportFile = new File("/Users/macbookpro14/Desktop/Fake Desktop/SETEC/Year 4/Java with IReport/PawnSystem/src/pawnsystem/report.jrxml");
            if (!reportFile.exists()) {
                JOptionPane.showMessageDialog(this, "Report file not found at: " + reportFile.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            InputStream reportStream = new FileInputStream(reportFile);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
            JasperViewer.viewReport(jasperPrint, false);
            // Print the report directly
            JasperPrintManager.printReport(jasperPrint, true); // 'true' to show print dialog before printing

        } catch (JRException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating or printing the report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void btnpawnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpawnActionPerformed
        // TODO add your handling code here:
        Date now = new Date();

        // Define the format you want for the ID, including milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        // Format the current date into a string
  
        
        int createdBy= tempCreated;
        
        if(cboitemType.getSelectedIndex()!=0 && !txtitemname.getText().equalsIgnoreCase("")
                && !txtdescription.getText().equalsIgnoreCase("") 
                && !txtloan.getText().equalsIgnoreCase("")
                && cboDuration.getSelectedIndex() != 0
                && !txtstartdate.getText().equalsIgnoreCase("")
                && cboInterest.getSelectedIndex() != 0
                && !txtcname.getText().trim().equalsIgnoreCase("")
                && !txtphone.getText().trim().equalsIgnoreCase("")
                )
        { 
            // For table item
            String itemId = "i" + sdf.format(now);   
            String itemType = String.valueOf(cboitemType.getSelectedItem());
            String itemName = txtitemname.getText();
            String description = txtdescription.getText();

            String sql = "INSERT INTO Item (item_id, item_name, type, description, image) "
            + "VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = Connector.getConnection()){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, itemId);
                ps.setString(2, itemName);
                ps.setString(3, itemType);
                ps.setString(4,description);
                ps.setString(5, path);
                ps.executeUpdate();
                if(selectedFile != null){
                   Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Pawn.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // For table Pawn included itemID
            String pawnId = "p" + sdf.format(now);
            tempPawnID = pawnId;
            
            String tempLoan = txtloan.getText();
            double loanAmount = Double.valueOf(tempLoan);
            
            String tempDuration = String.valueOf(cboDuration.getSelectedItem());
            int duration = Integer.valueOf(tempDuration);
            String startDate = txtstartdate.getText();
            
            String tempInterest = String.valueOf(cboInterest.getSelectedItem());
            double interestRate = Double.valueOf(tempInterest)/100;
            
            String loanStatus = "active";
            double debtAmount = (loanAmount * interestRate) + loanAmount;
            LocalDate date = LocalDate.parse(startDate);
            // Add one month to the date
            LocalDate newDate = date.plusMonths(1);
            
            // Format the new date back to string format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dueDate = newDate.format(formatter);
            tempDueDate = dueDate;
            String customerName = txtcname.getText();
            String telephone = txtphone.getText();
            sql = "INSERT INTO Pawn (pawn_id, item_id, loan_amount, interest_rate, loan_start, duration, loan_status, debt_amount, customer_name, telephone, loan_due) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = Connector.getConnection()){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, pawnId);
                ps.setString(2, itemId);
                ps.setDouble(3, loanAmount);
                ps.setDouble(4, interestRate);
                ps.setString(5,startDate);
                ps.setInt(6, duration);
                ps.setString(7, loanStatus);
                ps.setDouble(8,loanAmount);
                ps.setString(9, customerName);
                ps.setString(10, telephone);
                ps.setString(11, dueDate);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            
     
            double interest_month = loanAmount * interestRate;
            double principal = loanAmount/duration;
            sql = "INSERT INTO Loan (pawn_id, principal, interest_month,duration,start_date) "
                    + "VALUES(?, ?, ?, ?, ?)";
            try (Connection conn = Connector.getConnection()){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, pawnId);
                ps.setDouble(2, Math.round(principal));
                ps.setDouble(3, interest_month);
                ps.setInt(4, duration);
                ps.setString(5,startDate);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }             
            
            JOptionPane.showMessageDialog(null, "Record Added Successfully.");
            printInvoice();
            clearField();
        }else{
            System.out.println(createdBy);
            JOptionPane.showMessageDialog(null, "Fill in the textfield properly","Failure", JOptionPane.ERROR_MESSAGE);
        }
      
    }//GEN-LAST:event_btnpawnActionPerformed

    private void btncalendarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncalendarMousePressed
        // TODO add your handling code here:
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Format the current timestamp
        String formattedTimestamp = currentDateTime.format(formatter);
        
        txtstartdate.setText(formattedTimestamp);
    }//GEN-LAST:event_btncalendarMousePressed

    private void imageLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageLabelMousePressed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an image file");

        // Filter for image files only
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                String name = f.getName().toLowerCase();
                return f.isDirectory() || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif");
            }

            @Override
            public String getDescription() {
                return "Image files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });


        // Show the file dialog
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath(); // Store the path
            System.out.println("Selected file path: " + imagePath); // Print the path

            // Create the uploaded_images folder in the src directory if it doesn't exist
            File srcDir = new File("src");
            imagesDir = new File(srcDir, "images");
            if (!imagesDir.exists()) {
                imagesDir.mkdir();
            }

            // Copy the selected image to the uploaded_images folder
            destinationFile = new File(imagesDir, selectedFile.getName());

            try {

                imagePath = selectedFile.getAbsolutePath(); // Store the path
              
                // Read the image
                BufferedImage image = ImageIO.read(selectedFile);

                // Scale the image to fit the label
                Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);

                // Set the image as an icon on the label
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setText(null);
                imagePath = selectedFile.getAbsolutePath(); // Store the path
                System.out.println("Selected file path: " + imagePath); // Print the path
                path = imagePath;
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Could not load the image: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_imageLabelMousePressed

    private void cboInterestMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboInterestMousePressed
        // TODO add your handling code here:
        cboInterest.removeAllItems();
        cboInterest.addItem("Interest Rate (%)");
        cboInterest.setSelectedIndex(0);
        try {
            Connection con = Connector.getConnection();
            Statement st = con.createStatement();
            String query = "select * from Interest order by id";
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                String interest_rate = String.valueOf(rs.getInt("id"));
                cboInterest.addItem(interest_rate);
            }
            
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboInterestMousePressed

    private void btnMoreInterestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoreInterestActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        // TODO add your handling code here:
        Interest ir = new Interest();
        ir.setVisible(true);
    }//GEN-LAST:event_btnMoreInterestActionPerformed
    
    private void clearField(){
        txtcname.setText("");
        txtphone.setText("");
        txtitemname.setText("");
        txtdescription.setText("");
        txtloan.setText("");
        txtstartdate.setText("");
        cboitemType.setSelectedIndex(0);
        cboDuration.setSelectedIndex(0);
        cboInterest.setSelectedIndex(0);
        try {
            // Load the image from a file (can be from a URL or other source)
            BufferedImage img = ImageIO.read(new File("/Users/macbookpro14/Desktop/Fake Desktop/SETEC/Year 4/Java with IReport/PawnSystem/src/images/No_Image_Available.jpg"));
            
            // Resize the image to fit the JLabel's size
            Image scaledImage = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            
            // Set the scaled image as the icon for the JLabel
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Pawn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pawn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pawn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pawn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         try{
            FlatLaf.registerCustomDefaultsSource("images");
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        }catch(Exception e){
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pawn(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnMoreInterest;
    private javax.swing.JLabel btncalendar;
    private javax.swing.JButton btnpawn;
    private javax.swing.JComboBox<String> cboDuration;
    private javax.swing.JComboBox<String> cboInterest;
    private javax.swing.JComboBox<String> cboitemType;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtcname;
    private javax.swing.JTextArea txtdescription;
    private javax.swing.JTextField txtitemname;
    private javax.swing.JTextField txtloan;
    private javax.swing.JTextField txtphone;
    private javax.swing.JTextField txtstartdate;
    // End of variables declaration//GEN-END:variables
}

class NumberOnlyFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isValidNumber(string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidNumber(text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    // Helper method to check if the input is a valid number
    private boolean isValidNumber(String text) {
        // Check if the input is a valid digit (this excludes letters and symbols)
        return text.matches("\\d*");
    }
}