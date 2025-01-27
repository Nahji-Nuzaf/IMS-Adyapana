/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import model.MySQL;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class studentManagement extends javax.swing.JPanel {

    JpanelLoader panelLoader = new JpanelLoader();

    public studentManagement() {
        initComponents();
        theader();
        init();
        generateStudentId();
        loadStudents();
        roundButton2.setEnabled(false);
    }

    private void generateStudentId() {
        long id = System.currentTimeMillis();
        jLabel6.setText(String.valueOf(id));
    }

    private void init() {
        roundTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Using Student Name or Student ID");
    }

    private void theader() {
        JTableHeader thead = jTable1.getTableHeader();
        thead.setForeground((new Color(255, 255, 255)));
        thead.setBackground(new Color(0, 0, 0));
        thead.setFont(new Font("Poppins", Font.BOLD, 12));
        TableColumn coll = jTable1.getColumnModel().getColumn(0);

        coll.setPreferredWidth(100);
    }

    private void loadStudents() {

        try {

            String query = "SELECT * FROM `student`";

            String search = roundTextField1.getText();

            if (search != null) {
                query += "WHERE `student`.`no` LIKE '%" + search + "%' OR `student`.`name` LIKE '%" + search + "%' ";
            }

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("no"));
                vector.add(rs.getString("name"));
                vector.add(rs.getString("mobile"));
                vector.add(rs.getString("address"));
                vector.add(rs.getString("dob"));
//                vector.add(rs.getString("no"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new Components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        roundButton3 = new Components.RoundButton();
        roundTextField1 = new Components.RoundTextField();
        roundButton5 = new Components.RoundButton();
        roundButton7 = new Components.RoundButton();
        roundPanel2 = new Components.RoundPanel();
        roundButton1 = new Components.RoundButton();
        roundButton2 = new Components.RoundButton();
        roundButton4 = new Components.RoundButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roundTextField2 = new Components.RoundTextField();
        jLabel4 = new javax.swing.JLabel();
        roundTextField3 = new Components.RoundTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        roundTextField4 = new Components.RoundTextField();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Name", "Mobile", "Address", "DOB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/student.png"))); // NOI18N
        jLabel1.setText("Student Management");

        roundButton3.setText("Delete");
        roundButton3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton3ActionPerformed(evt);
            }
        });

        roundTextField1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roundTextField1KeyReleased(evt);
            }
        });

        roundButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/print.png"))); // NOI18N
        roundButton5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton5ActionPerformed(evt);
            }
        });

        roundButton7.setText("Enroll Student");
        roundButton7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                        .addComponent(roundButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        roundPanel2.setBackground(new java.awt.Color(204, 255, 255));

        roundButton1.setBackground(new java.awt.Color(0, 51, 255));
        roundButton1.setForeground(new java.awt.Color(255, 255, 255));
        roundButton1.setText("Add Student");
        roundButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton1ActionPerformed(evt);
            }
        });

        roundButton2.setBackground(new java.awt.Color(0, 255, 255));
        roundButton2.setText("Update Student");
        roundButton2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton2ActionPerformed(evt);
            }
        });

        roundButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/reset.png"))); // NOI18N
        roundButton4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Register Student");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel3.setText("Student Name");

        roundTextField2.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel4.setText("Mobile");

        roundTextField3.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        jLabel5.setText("Index No :");

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("0187954620");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel7.setText("Address");

        roundTextField4.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel8.setText("D.O.B");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(roundButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(roundTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(25, 25, 25)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton1ActionPerformed

//        roundTextField2.grabFocus();
        String stuId = jLabel6.getText();
        String name = roundTextField2.getText();
        String mobile = roundTextField3.getText();
        String address = roundTextField4.getText();
        String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate()) == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Date Of Birth", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `student` WHERE `mobile`='" + mobile + "' AND `no`='" + stuId + "'");

                if (rs.next()) {

                    JOptionPane.showMessageDialog(this, "Student Already Registered", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {
                    String dob = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());

                    MySQL.executeIUD("INSERT INTO `student` (`no`,`name`,`address`,`dob`,`admission_date`,`mobile`) "
                            + "VALUES ('" + stuId + "','" + name + "','" + address + "','" + dob + "','" + dateTime + "','" + mobile + "')");

                    reset();
                    loadStudents();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_roundButton1ActionPerformed

    private void roundButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton7ActionPerformed

        int row = jTable1.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this, "Please Select a Row", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            String stuId = String.valueOf(jTable1.getValueAt(row, 0));
            String name = String.valueOf(jTable1.getValueAt(row, 1));
            String mobile = String.valueOf(jTable1.getValueAt(row, 2));

            enrollStudent es = new enrollStudent(stuId, name, mobile);
            es.setVisible(true);

        }

    }//GEN-LAST:event_roundButton7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {

            int row = jTable1.getSelectedRow();

            String stuId = String.valueOf(jTable1.getValueAt(row, 0));
            jLabel6.setText(stuId);
            String name = String.valueOf(jTable1.getValueAt(row, 1));
            roundTextField2.setText(name);
            String mobile = String.valueOf(jTable1.getValueAt(row, 2));
            roundTextField3.setText(mobile);
            String address = String.valueOf(jTable1.getValueAt(row, 3));
            roundTextField4.setText(address);
            String dobb = String.valueOf(jTable1.getValueAt(row, 4));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // Define the date format
            Date dob = null;
            try {
                dob = format.parse(dobb); // Parse the String into a Date
                jDateChooser1.setDate(dob);
            } catch (ParseException ex) {
                Logger.getLogger(studentManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

            roundButton1.setEnabled(false);
            roundButton2.setEnabled(true);
            roundButton4.setEnabled(false);

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void roundButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton2ActionPerformed

        String stuId = jLabel6.getText();
        String name = roundTextField2.getText();
        String mobile = roundTextField3.getText();
        String address = roundTextField4.getText();
        String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student Address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate()) == null) {
            JOptionPane.showMessageDialog(this, "Please Enter Date Of Birth", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `student` WHERE `mobile`='" + mobile + "' AND `no`='" + stuId + "'");
                boolean canUpdate = false;
                if (rs.next()) {

                    if (rs.getString("no").equals(stuId)) {
                        canUpdate = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Student Already Registered", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                } else {

                    canUpdate = true;
                }

                if (canUpdate) {
                    String dob = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());

                    MySQL.executeIUD("UPDATE `student` SET `name`='" + name + "', `mobile`='" + mobile + "', `address`='" + address + "' WHERE `no`='" + stuId + "'");

                    reset();
                    loadStudents();

                    roundButton2.setEnabled(false);
                    roundButton1.setEnabled(true);
                    roundButton4.setEnabled(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_roundButton2ActionPerformed

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        loadStudents();
    }//GEN-LAST:event_roundTextField1KeyReleased

    private void roundButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton4ActionPerformed
        reset();
    }//GEN-LAST:event_roundButton4ActionPerformed

    private void roundButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton5ActionPerformed
        try {

            String rowcount = String.valueOf(jTable1.getRowCount());
//            jLabel11.setText(rowcount);

            String path = "src\\reports\\adyapanaStudentManage.jasper";

            HashMap<String, Object> para = new HashMap<>();
            para.put("Parameter1", rowcount);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, para, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_roundButton5ActionPerformed

    private void roundButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton3ActionPerformed

        int row = jTable1.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this, "Please Select a Row", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            String stuId = String.valueOf(jTable1.getValueAt(row, 0));
            String name = String.valueOf(jTable1.getValueAt(row, 1));
            String mobile = String.valueOf(jTable1.getValueAt(row, 2));

            try {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `class` "
                        + "INNER JOIN `teacher` ON `teacher`.`no`=`class`.`teacher_no` "
                        + "INNER JOIN `invoice_item` ON `invoice_item`.`class_no`=`class`.`no`"
                        + "INNER JOIN `student_has_class` ON `student_has_class`.`class_no`=`class`.`no`"
                        + "INNER JOIN `student` ON `student_has_class`.`student_no`=`student`.`no`"
                        + "INNER JOIN `invoice` ON `invoice`.`id`=`invoice_item`.`invoice_id`"
                        + "INNER JOIN `atd_entry` ON `student_has_class`.`id`=`atd_entry`.`student_has_class_id`"
                        + "WHERE `student`.`no`='" + stuId + "' AND `student`.`name`='" + name + "'");

                if (rs.next()) {

                    MySQL.executeIUD("DELETE FROM `invoice` WHERE `invoice`.`student_no`='" + stuId + "'");
                    MySQL.executeIUD("DELETE FROM `invoice_item` WHERE `invoice_item`.`invoice_id`='" + rs.getString("invoice_item.invoice_id") + "'");

                    MySQL.executeIUD("DELETE FROM `student_has_class` WHERE `student_has_class`.`student_no`='" + stuId + "'");
                    MySQL.executeIUD("DELETE FROM `atd_entry` WHERE `atd_entry`.`student_has_class_id`='" + rs.getString("atd_entry.student_has_class_id") + "'");

                    MySQL.executeIUD("DELETE FROM `invoice` WHERE `invoice`.`student_no`='" + stuId + "'");

                }

                loadStudents();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_roundButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private Components.RoundButton roundButton1;
    private Components.RoundButton roundButton2;
    private Components.RoundButton roundButton3;
    private Components.RoundButton roundButton4;
    private Components.RoundButton roundButton5;
    private Components.RoundButton roundButton7;
    private Components.RoundPanel roundPanel1;
    private Components.RoundPanel roundPanel2;
    private Components.RoundTextField roundTextField1;
    private Components.RoundTextField roundTextField2;
    private Components.RoundTextField roundTextField3;
    private Components.RoundTextField roundTextField4;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        roundTextField2.setText("");
        roundTextField3.setText("");
        roundTextField4.setText("");
        jDateChooser1.setDate(null);
        generateStudentId();
//        loadStudents();

    }
}
