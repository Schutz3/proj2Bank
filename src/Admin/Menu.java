
package Admin;

import Frame.LoginFrame;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RIG-1
 */
public class Menu extends javax.swing.JFrame {
public DefaultTableModel tabModel;
public Statement st;
public ResultSet rs;
Connection con = Connect.Connect.Connect();

    public Menu() {
        initComponents();
        judul();
        tampilData("");
    }

    @SuppressWarnings("unchecked")
        
        PreparedStatement ps = null;


public void tambahData(){
    if(TFAccID.getText().isEmpty() || TFName.getText().isEmpty() || TFPhone.getText().isEmpty() || TFJob.getText().isEmpty() || TFPinCode.getText().isEmpty() || TFBalance.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Missing Some Data!!!");
        }else{
            try {
                st = con.createStatement();
                st.executeUpdate("INSERT INTO tblcustacc VALUES('" + Integer.valueOf(TFAccID.getText()) + "','"
                + TFName.getText() + "','"
                + TFPhone.getText() + "','"
                + TFJob.getText() + "','"
                + Integer.valueOf(TFPinCode.getText()) + "','"
                + Integer.valueOf(TFBalance.getText()) + "')");
    tampilData("");
                    JOptionPane.showMessageDialog(null, "Simpan Berhasil");
                hapus();
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}

public void clickTB(){
    TFAccID.setText(TBData.getValueAt(TBData.getSelectedRow(), 0).toString());
    TFName.setText(TBData.getValueAt(TBData.getSelectedRow(), 1).toString());
    TFPhone.setText(TBData.getValueAt(TBData.getSelectedRow(), 2).toString());
    TFJob.setText(TBData.getValueAt(TBData.getSelectedRow(), 3).toString());
    TFPinCode.setText(TBData.getValueAt(TBData.getSelectedRow(), 4).toString());
    TFBalance.setText(TBData.getValueAt(TBData.getSelectedRow(), 5).toString());
}

public void updateBtn(){
    try {
    st = con.createStatement();
    st.executeUpdate("UPDATE tblcustacc set " 
        + "AccID='"       + Integer.valueOf(TFAccID.getText()) + "', "
        + "Name='"      + TFName.getText() + "', "
        + "Phone='"   + TFPhone.getText() + "', "
        + "Job='"        + TFJob.getText() + "', "
        + "PinCode='"        + Integer.valueOf(TFPinCode.getText()) + "', "
        + "Balance='"    + Integer.valueOf(TFBalance.getText()) + "'");
    
    tampilData("");
    JOptionPane.showMessageDialog(null, "Update Berhasil");
    hapus();
  } catch (Exception e) {
    JOptionPane.showMessageDialog(this, e.getMessage());
  }
}
        
public void judul() {
  Object[] judul = {
    "AccID", "Name", "Phone", "Job", "PinCode","Balance"
  };
  tabModel = new DefaultTableModel(null, judul);
  TBData.setModel(tabModel);
}

public void tampilData(String where) {
  try {
    st = con.createStatement();
    tabModel.getDataVector().removeAllElements();
    tabModel.fireTableDataChanged();
    rs = st.executeQuery("SELECT * FROM tblcustacc " + where);
    
    while (rs.next()) {
      Object[] data = {
        rs.getString("AccID"),
        rs.getString("Name"),
        rs.getString("Phone"),
        rs.getString("Job"),
        rs.getString("PinCode"),
        rs.getString("Balance"),
      };
        
        tabModel.addRow(data);
    }
  } catch(Exception e) {
    JOptionPane.showMessageDialog(this, e.getMessage());
  }
}

public void deleteBtn(){
    try {
    int yon;
    
    if ((yon = JOptionPane.showConfirmDialog(null, "Ingin menghapus data?", "konfirmasi", JOptionPane.YES_NO_OPTION)) == 0) {
      st = con.createStatement();
      st.executeUpdate("DELETE FROM tblcustacc WHERE AccID='"
          + tabModel.getValueAt(TBData.getSelectedRow(), 0) + "'");
      tampilData("");
      hapus();
    }
  } catch (Exception e) {
    JOptionPane.showMessageDialog(this, e.getMessage());
  }
}
          
public void hapus(){
    TFAccID.setText("");
    TFName.setText("");
    TFJob.setText("");
    TFPhone.setText("");
    TFPinCode.setText("");
    TFBalance.setText("");
}
        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TFName = new javax.swing.JTextField();
        TFPhone = new javax.swing.JTextField();
        TFAccID = new javax.swing.JTextField();
        BTNAdd = new javax.swing.JButton();
        BTNDelete = new javax.swing.JButton();
        BTNUpdate = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        TFJob = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TFBalance = new javax.swing.JTextField();
        TFPinCode = new javax.swing.JTextField();
        BTNBack = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TBData = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel2.setText("Account ID");

        jLabel3.setText("Phone");

        jLabel4.setText("Job");

        TFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNameActionPerformed(evt);
            }
        });

        TFAccID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFAccIDActionPerformed(evt);
            }
        });

        BTNAdd.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BTNAdd.setText("Add +");
        BTNAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddActionPerformed(evt);
            }
        });

        BTNDelete.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BTNDelete.setText("Delete -");
        BTNDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDeleteActionPerformed(evt);
            }
        });

        BTNUpdate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BTNUpdate.setText("Update");
        BTNUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNUpdateActionPerformed(evt);
            }
        });

        jLabel5.setText("Name");

        jLabel7.setText("Pin Code");

        jLabel8.setText("Balance");

        BTNBack.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BTNBack.setText("<< Back");
        BTNBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFJob, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFAccID, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFPinCode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BTNBack, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TFAccID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TFPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TFJob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TFPinCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TFBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNAdd)
                    .addComponent(BTNDelete)
                    .addComponent(BTNUpdate))
                .addGap(18, 18, 18)
                .addComponent(BTNBack)
                .addContainerGap())
        );

        TBData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AccID", "Name", "Phone", "Job", "PinCode", "Balance"
            }
        ));
        TBData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBDataMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TBData);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setText("ADMIN MENU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(394, 394, 394))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddActionPerformed
        // TODO add your handling code here:
        tambahData();
    }//GEN-LAST:event_BTNAddActionPerformed

    private void BTNDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteActionPerformed
        // TODO add your handling code here:
        deleteBtn();
    }//GEN-LAST:event_BTNDeleteActionPerformed

    private void BTNUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdateActionPerformed
        // TODO add your handling code here:
        updateBtn();
    }//GEN-LAST:event_BTNUpdateActionPerformed

    private void TFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFNameActionPerformed

    private void TBDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBDataMouseClicked
        // TODO add your handling code here:
        clickTB();
    }//GEN-LAST:event_TBDataMouseClicked

    private void TFAccIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFAccIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFAccIDActionPerformed

    private void BTNBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBackActionPerformed
        // TODO add your handling code here:
        new LoginFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTNBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAdd;
    private javax.swing.JButton BTNBack;
    private javax.swing.JButton BTNDelete;
    private javax.swing.JButton BTNUpdate;
    private javax.swing.JTable TBData;
    private javax.swing.JTextField TFAccID;
    private javax.swing.JTextField TFBalance;
    private javax.swing.JTextField TFJob;
    private javax.swing.JTextField TFName;
    private javax.swing.JTextField TFPhone;
    private javax.swing.JTextField TFPinCode;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
