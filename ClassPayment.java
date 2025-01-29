/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import model.MySQL;
import model.invoiceClass;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class ClassPayment extends javax.swing.JPanel {

    HashMap<String, invoiceClass> invoiceClassMap = new HashMap<>();
    private static HashMap<String, String> subjectMap = new HashMap<>();
    HashMap<String, String> paymentMethodMap = new HashMap<>();

    public ClassPayment() {
        initComponents();
        init();
        theader();
        loadPaymentMethod();
        generateInvoiceId();
        roundTextField1.grabFocus();
    }

    private void init() {
        roundTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Student Name or Student ID");
    }

    private void generateInvoiceId() {
        long id = System.currentTimeMillis();
        jLabel20.setText(String.valueOf(id));
    }

    private void theader() {
        JTableHeader thead = jTable1.getTableHeader();
        thead.setForeground((new Color(255, 255, 255)));
        thead.setBackground(new Color(0, 0, 0));
        thead.setFont(new Font("Poppins", Font.BOLD, 12));
        TableColumn coll = jTable1.getColumnModel().getColumn(0);

        coll.setPreferredWidth(100);
    }

    private void loadPaymentMethod() {

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `payment_method`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {

                paymentMethodMap.put(rs.getString("name"), rs.getString("id"));
                vector.add(rs.getString("name"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadSubjects() {

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `student_has_class` "
                    + "INNER JOIN `class` ON `class`.`no`=`student_has_class`.`class_no` "
                    + "INNER JOIN `subject` ON `subject`.`no`=`class`.`subject_no` WHERE `student_has_class`.`student_no`='" + jLabel4.getText() + "'");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (rs.next()) {

                vector.add(rs.getString("subject.name"));

//                jLabel19.setText(rs.getString("subject.price"));
                subjectMap.put(rs.getString("subject.name"), rs.getString("subject.no"));

            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadTeacher() {
        try {

            String query = "SELECT * FROM `student_has_class` "
                    + "INNER JOIN `class` ON `class`.`no`=`student_has_class`.`class_no` "
                    + "INNER JOIN `student` ON `student`.`no`=`student_has_class`.`student_no` "
                    + "INNER JOIN `teacher` ON `teacher`.`no`=`class`.`teacher_no`"
                    + "INNER JOIN `subject` ON `subject`.`no`=`class`.`subject_no`";

            String selectedSubject = String.valueOf(jComboBox1.getSelectedItem());
//            System.out.println(subjectMap.get("subject.name"));

            String stuId = jLabel4.getText();

            if (subjectMap.containsKey(selectedSubject)) {
                query += "WHERE `class`.`subject_no`='" + subjectMap.get(selectedSubject) + "'  AND `student`.`no`='" + stuId + "'";
            }

            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {

                jLabel12.setText(rs.getString("teacher.name"));
                jLabel14.setText(rs.getString("subject.price"));
                jLabel22.setText(rs.getString("class.name"));
                jLabel27.setText(rs.getString("class.no"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPaymentTable() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        double totalfee = 0;

        for (invoiceClass invoiceClass : invoiceClassMap.values()) {

            Vector<String> vector = new Vector<>();
            vector.add(invoiceClass.getClassId());
            vector.add(invoiceClass.getClassName());
            vector.add(invoiceClass.getSubject());
            vector.add(invoiceClass.gettName());
            vector.add(invoiceClass.getMonth());
            vector.add(invoiceClass.getFee());

            double total = Double.parseDouble(invoiceClass.getFee());
            totalfee += total;

            model.addRow(vector);
        }
        jFormattedTextField4.setText(String.valueOf(totalfee));
    }

    private double totalfee = 0;
    private double payment = 0;
    private double balance = 0;
    private String paymentMethod = "Select";

    private void calculate() {

//        String itemstotal = ;
        if (jFormattedTextField4.getText().isEmpty()) {
            totalfee = 0;
        } else {
            totalfee = Double.parseDouble(jFormattedTextField4.getText());
        }

        if (jFormattedTextField2.getText().isEmpty()) {
            payment = 0;
        } else {
            payment = Double.parseDouble(jFormattedTextField2.getText());
        }

        paymentMethod = String.valueOf(jComboBox2.getSelectedItem());

        if (paymentMethod.equals("Select")) {

        } else {
            if (paymentMethod.equals("Cash")) {
//            //cash
                jFormattedTextField2.setEditable(true);
                balance = payment - totalfee;
//
                if (balance < 0) {
//                    roundedButton4.setEnabled(false);
//                    roundedButton7.setEnabled(true);
                } else {
//                    roundedButton4.setEnabled(true);
//                    roundedButton7.setEnabled(false);
                }
//
            } else {
//            //card
                payment = totalfee;
                balance = 0;
                jFormattedTextField2.setText(String.valueOf(payment));
                jFormattedTextField2.setEditable(false);
//                jButton3.setEnabled(true);
            }
        }
        jFormattedTextField3.setText(String.valueOf(balance));

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
        roundPanel1 = new Components.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        roundTextField1 = new Components.RoundTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        roundButton1 = new Components.RoundButton();
        roundButton2 = new Components.RoundButton();
        roundButton3 = new Components.RoundButton();
        jLabel17 = new javax.swing.JLabel();
        roundTextField2 = new Components.RoundTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        roundButton4 = new Components.RoundButton();
        roundButton5 = new Components.RoundButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fee.png"))); // NOI18N
        jLabel1.setText("Payment");

        roundTextField1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Student Name Here");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255), 2));

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Student ID :");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel5.setText("Mobile :");

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        jTable1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class ID", "Class Name", "Subject", "Teacher Name", "Month", "Fee"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel7.setText("Admission Date :");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel9.setText("Due Amount :");

        jFormattedTextField1.setEditable(false);
        jFormattedTextField1.setForeground(new java.awt.Color(255, 0, 0));
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextField1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel10.setText("Subject :");

        jComboBox1.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel11.setText("Teacher : ");

        jLabel12.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel13.setText("Subject Fee :");

        jLabel14.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        roundButton1.setBackground(new java.awt.Color(0, 0, 153));
        roundButton1.setForeground(new java.awt.Color(255, 255, 255));
        roundButton1.setText("Add");
        roundButton1.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        roundButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton1ActionPerformed(evt);
            }
        });

        roundButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/bin2.png"))); // NOI18N
        roundButton2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        roundButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton2ActionPerformed(evt);
            }
        });

        roundButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/reset.png"))); // NOI18N
        roundButton3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        roundButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton3ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel17.setText("Month :");

        roundTextField2.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jFormattedTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField2KeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Payment");

        jLabel18.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Balance");

        jFormattedTextField3.setEditable(false);
        jFormattedTextField3.setForeground(new java.awt.Color(255, 0, 0));
        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        roundButton4.setBackground(new java.awt.Color(51, 102, 255));
        roundButton4.setForeground(new java.awt.Color(255, 255, 255));
        roundButton4.setText("Pay");
        roundButton4.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        roundButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton4ActionPerformed(evt);
            }
        });

        roundButton5.setBackground(new java.awt.Color(204, 255, 255));
        roundButton5.setText("Cancel");
        roundButton5.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 153));
        jLabel19.setText("Invoice ID :");

        jLabel20.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel21.setText("Class Name : ");

        jLabel22.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("SubTotal");

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Payment Method");

        jComboBox2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel25.setText("Class ID : ");

        jLabel27.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N

        jFormattedTextField4.setEditable(false);
        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField4.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addGap(641, 641, 641)
                                .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(90, 90, 90))
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel25))
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(61, 61, 61)
                                                .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(roundButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGap(372, 372, 372)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(roundPanel1Layout.createSequentialGroup()
                                        .addGap(96, 96, 96)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roundButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased

        String search = roundTextField1.getText();

        try {

            if (search != null && search.trim().isEmpty()) {

                jLabel2.setText("Student Name Here");
                jLabel4.setText("");
                jLabel6.setText("");
                jComboBox1.setSelectedIndex(0);
                jLabel8.setText("");
                jLabel12.setText("");
                jLabel14.setText("");
                jLabel22.setText("");
                jFormattedTextField1.setText("0");
                roundTextField2.setText("");

//                loadInvoiceItem();
            } else {

                String query = "SELECT * FROM `student_has_class` "
                        + "INNER JOIN `student` ON `student`.`no`=`student_has_class`.`student_no` "
                        + "INNER JOIN `class` ON `class`.`no`=`student_has_class`.`class_no` "
                        + "INNER JOIN `subject` ON `subject`.`no`=`class`.`subject_no` "
                        + "INNER JOIN `teacher` ON `teacher`.`no`=`class`.`teacher_no` ";

                if (search != null) {
                    query += "WHERE `student`.`no` LIKE '%" + search + "%' OR `student`.`name` LIKE '%" + search + "%' ";
                }

                ResultSet rs = MySQL.executeSearch(query);

                while (rs.next()) {

                    jLabel2.setText(rs.getString("student.name"));
                    jLabel4.setText(rs.getString("student.no"));
                    jLabel6.setText(rs.getString("student.mobile"));
                    jLabel8.setText(rs.getString("student.admission_date"));

                    loadSubjects();

//                    jLabel12.setText("");
//                    jLabel14.setText("");
//                    jComboBox1.setSelectedIndex(0);
//                    jFormattedTextField1.setText("0");
//                    roundTextField2.setText("");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_roundTextField1KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        loadTeacher();

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void roundButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton1ActionPerformed

        String search = roundTextField1.getText();
        String stuId = jLabel4.getText();
        String classId = jLabel27.getText();
        String className = jLabel22.getText();
        String tName = jLabel12.getText();
        String month = roundTextField2.getText();
        String subFee = jLabel14.getText();
        String subject = String.valueOf(jComboBox1.getSelectedItem());

        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Search for a Student", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (stuId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Search for a Student", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subject.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Subject", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (month.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Payment Month", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            invoiceClass invoiceClass = new invoiceClass();
            invoiceClass.setClassId(classId);
            invoiceClass.setClassName(className);
            invoiceClass.setSubject(subject);
            invoiceClass.settName(tName);
            invoiceClass.setMonth(month);
            invoiceClass.setFee(subFee);

            if (invoiceClassMap.get(classId) == null) {
                invoiceClassMap.put(classId, invoiceClass);
            }

            loadPaymentTable();
//            reset();

        }

    }//GEN-LAST:event_roundButton1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        calculate();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jFormattedTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField2KeyReleased
        calculate();
    }//GEN-LAST:event_jFormattedTextField2KeyReleased

    private void roundButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton4ActionPerformed

        String invoiceId = jLabel20.getText();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String stuName = jLabel2.getText();
        String stuId = jLabel4.getText();
        String subTotal = String.valueOf(jFormattedTextField4.getText());
        String payment = String.valueOf(jFormattedTextField2.getText());
        String balance = String.valueOf(jFormattedTextField3.getText());
        String paymentMethod = String.valueOf(jComboBox2.getSelectedItem());

        String classId = jLabel27.getText();
//        String tName = jLabel12.getText();
        String month = roundTextField2.getText();
//        String subFee = jLabel14.getText();
//        String subject = String.valueOf(jComboBox1.getSelectedItem());
        try {

            if (subTotal.equals("SubTotal")) {
                JOptionPane.showMessageDialog(this, "Add a Subject", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (paymentMethod.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Payment Method", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (payment.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Payment Amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `invoice` "
                        + "INNER JOIN `invoice_item` ON `invoice`.`id`=`invoice_item`.`invoice_id`"
                        + "WHERE `invoice`.`student_no` = '" + stuId + "' "
                        + "AND `invoice_item`.`class_no` = '" + classId + "' AND `invoice`.`month`='" + month + "'");

                if (rs.next()) {

//                    jLabel2.setText("Student Already Paid");

                    JOptionPane.showMessageDialog(this, "Student Already Paid", "Warning", JOptionPane.WARNING_MESSAGE);
                    reset();
                    refresh();
                    
                } else {

                    if (balance.equals("0.0")) {

//                    JOptionPane.showMessageDialog(this, "0 Balance", "Warning", JOptionPane.WARNING_MESSAGE);
                        MySQL.executeIUD("INSERT INTO `invoice` (`id`,`month`,`value`,`student_no`,`date`,`payment_method_id`,`payment_status_id`) "
                                + "VALUES ('" + invoiceId + "','" + month + "','" + payment + "','" + stuId + "','" + date + "','" + paymentMethodMap.get(paymentMethod) + "','1')");

                    } else {

//                    JOptionPane.showMessageDialog(this, "Balance Left", "Warning", JOptionPane.WARNING_MESSAGE);
                        MySQL.executeIUD("INSERT INTO `invoice` (`id`,`month`,`value`,`due_amount`,`student_no`,`date`,`payment_method_id`,`payment_status_id`) "
                                + "VALUES ('" + invoiceId + "','" + month + "','" + payment + "','" + balance + "','" + stuId + "','" + date + "','" + paymentMethodMap.get(paymentMethod) + "','2')");

                    }

                    for (invoiceClass invoiceClass : invoiceClassMap.values()) {

                        MySQL.executeIUD("INSERT INTO `invoice_item` (`invoice_id`,`class_no`) VALUES ('" + invoiceId + "','" + invoiceClass.getClassId() + "')");

                    }

                    try {

                        String path = "src\\reports\\adyapanaInvoice.jasper";

                        HashMap<String, Object> para = new HashMap<>();
                        para.put("Parameter1", invoiceId);
                        para.put("Parameter2", stuId);
                        para.put("Parameter3", stuName);
                        para.put("Parameter4", "nahji@gmail.com");
                        para.put("Parameter5", subTotal);
                        para.put("Parameter6", payment);
                        para.put("Parameter7", balance);

                        JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

                        JasperPrint jasperPrint = JasperFillManager.fillReport(path, para, dataSource);

                        JasperViewer.viewReport(jasperPrint, false);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                    reset();
                    refresh();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_roundButton4ActionPerformed

    private void roundButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton3ActionPerformed
        reset();
    }//GEN-LAST:event_roundButton3ActionPerformed

    private void roundButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton2ActionPerformed

    }//GEN-LAST:event_roundButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private Components.RoundButton roundButton1;
    private Components.RoundButton roundButton2;
    private Components.RoundButton roundButton3;
    private Components.RoundButton roundButton4;
    private Components.RoundButton roundButton5;
    private Components.RoundPanel roundPanel1;
    private Components.RoundTextField roundTextField1;
    private Components.RoundTextField roundTextField2;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        roundTextField1.setText("");
        jLabel2.setText("Student Name Here");
        jLabel4.setText("");
        jLabel6.setText("");
        jLabel8.setText("");
        jComboBox1.setSelectedIndex(0);
        jLabel12.setText("");
        jLabel14.setText("");
        jLabel22.setText("");
        jLabel27.setText("");
        roundTextField2.setText("");
        roundTextField1.grabFocus();

    }

    private void refresh() {

        invoiceClassMap.clear();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        jFormattedTextField4.setText("");
        jComboBox2.setSelectedIndex(0);
        jFormattedTextField2.setText("");
        jFormattedTextField3.setText("");

        generateInvoiceId();

    }

}
