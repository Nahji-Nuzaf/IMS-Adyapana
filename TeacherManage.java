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
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author HP
 */
public class TeacherManage extends javax.swing.JPanel {

    private static HashMap<String, String> subjectMap = new HashMap<>();

    public TeacherManage() {
        initComponents();
        theader();
        generateTeacherId();
        loadTeachers();
        init();
//        loadSubjects();

        roundButton2.setEnabled(false);

    }

    private void generateTeacherId() {
        long id = System.currentTimeMillis();
        jLabel6.setText(String.valueOf(id));
    }

    private void init() {
        roundTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Using Subject Name");
    }

    private void theader() {
        JTableHeader thead = jTable1.getTableHeader();
        thead.setForeground((new Color(255, 255, 255)));
        thead.setBackground(new Color(0, 0, 0));
        thead.setFont(new Font("Poppins", Font.BOLD, 12));
        TableColumn coll = jTable1.getColumnModel().getColumn(0);

        coll.setPreferredWidth(100);
    }

    private void loadTeachers() {

        try {

            String query = "SELECT * FROM `teacher`";
//  INNER JOIN `teacher_has_subject` ON `teacher_has_subject`.`teacher_no`=`teacher`.`no` INNER JOIN `subject` ON `subject`.`no`=`teacher_has_subject`.`subject_no`
            String search = roundTextField1.getText();

            if (search != null) {
                query += "WHERE `teacher`.`no` LIKE '%" + search + "%' OR `teacher`.`name` LIKE '%" + search + "%' ";
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
//                vector.add(rs.getString("subject.name"));

                model.addRow(vector);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new Components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        roundButton3 = new Components.RoundButton();
        roundTextField1 = new Components.RoundTextField();
        roundButton5 = new Components.RoundButton();
        roundButton6 = new Components.RoundButton();
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

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Name", "Mobile", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/teacher.png"))); // NOI18N
        jLabel1.setText("Teacher Management");

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

        roundButton6.setText("Assign Class");
        roundButton6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roundButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(roundButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        roundPanel2.setBackground(new java.awt.Color(204, 255, 255));

        roundButton1.setBackground(new java.awt.Color(0, 51, 255));
        roundButton1.setForeground(new java.awt.Color(255, 255, 255));
        roundButton1.setText("Add Teacher");
        roundButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton1ActionPerformed(evt);
            }
        });

        roundButton2.setBackground(new java.awt.Color(0, 255, 255));
        roundButton2.setText("Update Teacher");
        roundButton2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton2ActionPerformed(evt);
            }
        });

        roundButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/reset.png"))); // NOI18N
        roundButton4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Register Teacher");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel3.setText("Teacher Name");

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

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                            .addComponent(roundTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(roundButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1302, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton1ActionPerformed

        String tId = jLabel6.getText();
        String tName = roundTextField2.getText();
        String tMobile = roundTextField3.getText();
        String tAddress = roundTextField4.getText();
//        String tsub = String.valueOf(jComboBox1.getSelectedItem());

        try {

            if (tName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Teacher Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (tMobile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Teacher Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (tAddress.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Teacher Address", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `teacher` WHERE `mobile`='" + tMobile + "' AND `no`='" + tId + "'");

                if (resultSet.next()) {

                    JOptionPane.showMessageDialog(this, "Teacher Already Registered", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `teacher` (`no`,`name`,`address`,`mobile`) VALUES ('" + tId + "','" + tName + "','" + tAddress + "','" + tMobile + "')");

//                    MySQL.executeIUD("INSERT INTO `teacher_has_subject` (`teacher_no`,`subject_no`) VALUES ('" + tId + "','" + subjectMap.get(tsub) + "')");
                    reset();
                    loadTeachers();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_roundButton1ActionPerformed

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        loadTeachers();
    }//GEN-LAST:event_roundTextField1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {

            int row = jTable1.getSelectedRow();

            String tId = String.valueOf(jTable1.getValueAt(row, 0));
            jLabel6.setText(tId);
            String name = String.valueOf(jTable1.getValueAt(row, 1));
            roundTextField2.setText(name);
            String mobile = String.valueOf(jTable1.getValueAt(row, 2));
            roundTextField3.setText(mobile);
            String address = String.valueOf(jTable1.getValueAt(row, 3));
            roundTextField4.setText(address);
//            String subject = String.valueOf(jTable1.getValueAt(row, 4));
//            jComboBox1.setSelectedItem(subject);

            roundButton1.setEnabled(false);
            roundButton2.setEnabled(true);
            roundButton4.setEnabled(false);

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void roundButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton2ActionPerformed

        String tId = jLabel6.getText();
        String tName = roundTextField2.getText();
        String tMobile = roundTextField3.getText();
        String tAddress = roundTextField4.getText();
//        String tsub = String.valueOf(jComboBox1.getSelectedItem());

        try {

            if (tName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Teacher Name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (tMobile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Teacher Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (tAddress.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Teacher Address", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `teacher` WHERE `mobile`='" + tMobile + "' AND `no`='" + tId + "'");
                boolean canUpdate = false;
                if (resultSet.next()) {

                    if (resultSet.getString("no").equals(tId)) {
                        canUpdate = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Teacher Already Registered", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                } else {

                    canUpdate = true;

                }

                if (canUpdate) {

                    MySQL.executeIUD("UPDATE `teacher` SET `name`='" + tName + "', `mobile`='" + tMobile + "', `address`='" + tAddress + "' WHERE `no`='" + tId + "'");

//                    MySQL.executeIUD("UPDATE `teacher_has_subject` SET `subject_no`='" + subjectMap.get(tsub) + "' WHERE `teacher_no`='" + tId + "'");
                    reset();
                    loadTeachers();

                    roundButton2.setEnabled(false);
                    roundButton1.setEnabled(true);
                    roundButton4.setEnabled(true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_roundButton2ActionPerformed

    private void roundButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton6ActionPerformed

        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String tId = String.valueOf(jTable1.getValueAt(row, 0));
            String name = String.valueOf(jTable1.getValueAt(row, 1));
            String mobile = String.valueOf(jTable1.getValueAt(row, 2));
            String address = String.valueOf(jTable1.getValueAt(row, 3));
//            String subject = String.valueOf(jTable1.getValueAt(row,4));

            assignClass ac = new assignClass(tId, name, mobile, address);
            ac.setVisible(true);

        }

    }//GEN-LAST:event_roundButton6ActionPerformed

    private void roundButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton5ActionPerformed
        try {

            String rowcount = String.valueOf(jTable1.getRowCount());
//            jLabel11.setText(rowcount);

            String path = "src\\reports\\adyapanaTeacherManage.jasper";

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

            String tId = String.valueOf(jTable1.getValueAt(row, 0));
            String name = String.valueOf(jTable1.getValueAt(row, 1));
            String mobile = String.valueOf(jTable1.getValueAt(row, 2));
            String address = String.valueOf(jTable1.getValueAt(row, 3));
//            String subject = String.valueOf(jTable1.getValueAt(row,4));

            try {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `class` "
                        + "INNER JOIN `teacher` ON `teacher`.`no`=`class`.`teacher_no` "
                        + "INNER JOIN `invoice_item` ON `invoice_item`.`class_no`=`class`.`no`"
                        + "INNER JOIN `student_has_class` ON `student_has_class`.`class_no`=`class`.`no`"
                        + "INNER JOIN `invoice` ON `invoice`.`id`=`invoice_item`.`invoice_id`"
                        + "INNER JOIN `atd_entry` ON `student_has_class`.`id`=`atd_entry`.`student_has_class_id`"
                        + "WHERE `teacher`.`no`='"+tId+"'");

                while (rs.next()) {

                    String className = rs.getString("class.name");
                    String classNo = rs.getString("class.no");
                    String classtName = rs.getString("class.teacher_no");
                    String invoiceId = rs.getString("invoice.id");
                    String stuId = rs.getString("student.no");

                    MySQL.executeIUD("DELETE FROM `class` WHERE `class`.`name`='" + className + "' AND `class`.`teacher_no`='" + classtName + "'");
                    
                    MySQL.executeIUD("DELETE FROM `invoice_item` WHERE `invoice_item`.`class_no`='" + classNo + "' AND `invoice_item`.`invoice_id`='" + invoiceId + "'");
                    
                    MySQL.executeIUD("DELETE FROM `student_has_class` WHERE `student_has_class`.`class_no`='" + classNo + "' AND `student_has_class`.`student_no`='" + stuId + "'");

                    MySQL.executeIUD("DELETE FROM `teacher` WHERE `teacher`.`name`='" + className + "' AND `teacher`.`no`='" + tId + "'");

                    loadTeachers();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_roundButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private Components.RoundButton roundButton1;
    private Components.RoundButton roundButton2;
    private Components.RoundButton roundButton3;
    private Components.RoundButton roundButton4;
    private Components.RoundButton roundButton5;
    private Components.RoundButton roundButton6;
    private Components.RoundPanel roundPanel1;
    private Components.RoundPanel roundPanel2;
    private Components.RoundTextField roundTextField1;
    private Components.RoundTextField roundTextField2;
    private Components.RoundTextField roundTextField3;
    private Components.RoundTextField roundTextField4;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        generateTeacherId();
        roundTextField2.setText("");
        roundTextField3.setText("");
        roundTextField4.setText("");
//        jComboBox1.setSelectedIndex(0);

    }
}
