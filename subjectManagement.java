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
import java.util.HashMap;
import java.util.Vector;
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
public class subjectManagement extends javax.swing.JPanel {

    /**
     * Creates new form subjectManagement
     */
    public subjectManagement() {
        initComponents();
        theader();
        init();
        loadSubjects();
        roundButton2.setEnabled(false);
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

    private void loadSubjects() {

        try {

            String query = "SELECT * FROM `subject`";

            String search = roundTextField1.getText();

            if (search != null) {
                query += "WHERE `subject`.`name` LIKE '%" + search + "%' ";
            }

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("no"));
                vector.add(rs.getString("name"));
                vector.add(rs.getString("description"));
                vector.add(rs.getString("price"));

                model.addRow(vector);
            }

        } catch (Exception e) {
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

        roundPanel1 = new Components.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        roundButton3 = new Components.RoundButton();
        roundTextField1 = new Components.RoundTextField();
        roundButton5 = new Components.RoundButton();
        roundPanel2 = new Components.RoundPanel();
        roundButton1 = new Components.RoundButton();
        roundButton2 = new Components.RoundButton();
        roundButton4 = new Components.RoundButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roundTextField2 = new Components.RoundTextField();
        jLabel4 = new javax.swing.JLabel();
        roundTextField3 = new Components.RoundTextField();
        jLabel7 = new javax.swing.JLabel();
        roundTextField4 = new Components.RoundTextField();

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject Id", "Name", "Description", "Fee"
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
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/book.png"))); // NOI18N
        jLabel1.setText("Subject Management");

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

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(roundTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(roundButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        roundPanel2.setBackground(new java.awt.Color(204, 255, 255));

        roundButton1.setBackground(new java.awt.Color(0, 51, 255));
        roundButton1.setForeground(new java.awt.Color(255, 255, 255));
        roundButton1.setText("Add Subject");
        roundButton1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        roundButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundButton1ActionPerformed(evt);
            }
        });

        roundButton2.setBackground(new java.awt.Color(0, 255, 255));
        roundButton2.setText("Update Subject");
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
        jLabel2.setText("Register Subject");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel3.setText("Subject Name");

        roundTextField2.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel4.setText("Subject Description");

        roundTextField3.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 16)); // NOI18N
        jLabel7.setText("Subject Fee");

        roundTextField4.setFont(new java.awt.Font("Poppins", 0, 15)); // NOI18N

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
                    .addComponent(roundButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(roundButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(75, 75, 75)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
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

        String subName = roundTextField2.getText();
        String subDesc = roundTextField3.getText();
        String subFee = roundTextField4.getText();

        if (subName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Subject Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subDesc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Subject Description", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subFee.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Subject Fee", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Double fee = Double.parseDouble(subFee);

            try {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `subject` WHERE `name`='" + subName + "'");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Subject Already Exist", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.executeIUD("INSERT INTO `subject` (`name`,`description`,`price`) VALUES ('" + subName + "','" + subDesc + "','" + fee + "')");

                    reset();
                    loadSubjects();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_roundButton1ActionPerformed

    private void roundTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roundTextField1KeyReleased
        loadSubjects();
    }//GEN-LAST:event_roundTextField1KeyReleased

    private void roundButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton2ActionPerformed

        String subName = roundTextField2.getText();
        String subDesc = roundTextField3.getText();
        String subFee = roundTextField4.getText();

        if (subName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Subject Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subDesc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Subject Description", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (subFee.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Subject Fee", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            Double fee = Double.parseDouble(subFee);

            try {

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `subject` WHERE `name`='" + subName + "'");

                if (rs.next()) {

                    MySQL.executeIUD("UPDATE `subject` SET `description`='" + subDesc + "', `price`='" + fee + "' WHERE `name`='" + subName + "'");

                    reset();
                    loadSubjects();

                    roundButton1.setEnabled(true);
                    roundButton4.setEnabled(true);
                    roundButton2.setEnabled(false);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_roundButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {

            int row = jTable1.getSelectedRow();

            String name = String.valueOf(jTable1.getValueAt(row, 1));
            roundTextField2.setText(name);
            String desc = String.valueOf(jTable1.getValueAt(row, 2));
            roundTextField3.setText(desc);
            String fee = String.valueOf(jTable1.getValueAt(row, 3));
            roundTextField4.setText(fee);

            roundButton1.setEnabled(false);
            roundButton4.setEnabled(false);
            roundButton2.setEnabled(true);

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void roundButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton3ActionPerformed

        int row = jTable1.getSelectedRow();

        String name = String.valueOf(jTable1.getValueAt(row, 1));
        String desc = String.valueOf(jTable1.getValueAt(row, 2));
        String fee = String.valueOf(jTable1.getValueAt(row, 3));

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `class` "
                    + "INNER JOIN `teacher` ON `teacher`.`no`=`class`.`teacher_no` "
                    + "INNER JOIN `subject` ON `subject`.`no`=`class`.`subject_no` "
                    + "INNER JOIN `invoice_item` ON `invoice_item`.`class_no`=`class`.`no`"
                    + "INNER JOIN `student_has_class` ON `student_has_class`.`class_no`=`class`.`no`"
                    + "INNER JOIN `student` ON `student_has_class`.`student_no`=`student`.`no`"
                    + "INNER JOIN `invoice` ON `invoice`.`id`=`invoice_item`.`invoice_id`"
                    + "INNER JOIN `atd_entry` ON `student_has_class`.`id`=`atd_entry`.`student_has_class_id`"
                    + "WHERE `subject`.`name`='" + name + "'");

            if (rs.next()) {

                MySQL.executeIUD("DELETE FROM `invoice_item` WHERE `invoice_item`.`invoice_id`='" + rs.getString("invoice_item.invoice_id") + "'");
                MySQL.executeIUD("DELETE FROM `invoice` WHERE `invoice`.`student_no`='" + rs.getString("student.no") + "'");

                MySQL.executeIUD("DELETE FROM `atd_entry` WHERE `atd_entry`.`student_has_class_id`='" + rs.getString("atd_entry.student_has_class_id") + "'");
                MySQL.executeIUD("DELETE FROM `student_has_class` WHERE `student_has_class`.`class_no`='" + rs.getString("class.no") + "'");

                MySQL.executeIUD("DELETE FROM `class` WHERE `class`.`subject_no`='" + rs.getString("subject.no") + "'");
                loadSubjects();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }//GEN-LAST:event_roundButton3ActionPerformed

    private void roundButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton4ActionPerformed
        reset();
    }//GEN-LAST:event_roundButton4ActionPerformed

    private void roundButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundButton5ActionPerformed
        try {

            String rowcount = String.valueOf(jTable1.getRowCount());
//            jLabel11.setText(rowcount);

            String path = "src\\reports\\adyapanaSubjectManage.jasper";

            HashMap<String, Object> para = new HashMap<>();
            para.put("Parameter1", rowcount);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, para, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_roundButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private Components.RoundButton roundButton1;
    private Components.RoundButton roundButton2;
    private Components.RoundButton roundButton3;
    private Components.RoundButton roundButton4;
    private Components.RoundButton roundButton5;
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

    }
}
