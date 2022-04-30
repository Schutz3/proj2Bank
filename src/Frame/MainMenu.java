/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frame;

import com.mysql.jdbc.PreparedStatement;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author RIG-1
 */
public class MainMenu extends javax.swing.JFrame {
public Statement st;
public ResultSet rs;
Connection con = Connect.Connect.Connect();

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }
    
    String UserName;
    int UserRek;
    int Balance;
    public MainMenu(int AccountRek, String AccountName) {
        initComponents();
        UserRek = AccountRek;
        UserName = AccountName;
        LBName.setText(UserName);
        LBRek.setText(""+UserRek);
    }
public void getBalance(){
    String Query = "SELECT * FROM tblcustacc WHERE AccID='"+UserRek+"'";
    try {
            st = con.createStatement();
            rs = st.executeQuery(Query);
            rs.next();
            Balance = rs.getInt(6);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
} 


public void getCashInfo() {
        getBalance();
        JOptionPane.showMessageDialog(null, "No Rekening: #" + UserRek + "\nWaktu: " + new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()) + "\nSisa Saldo Anda Saat Ini (Rp): " + Balance, "Cek Saldo", JOptionPane.INFORMATION_MESSAGE);
    }

public void depositBalance(){
    getBalance();
    int amount = 0;
        String s = JOptionPane.showInputDialog(null, "Masukan Jumlah uang yang ingin di Deposit (Rp)");
        if (onlyContainsNumbers(s)) {
            amount = Integer.parseInt(s);           
            try {
            String Query = "UPDATE tblcustacc SET Balance=? WHERE AccID=?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(Query);
            ps.setInt(1, Balance+Integer.valueOf(amount));
            ps.setInt(2, UserRek);
            if(ps.executeUpdate() == 1){
              JOptionPane.showMessageDialog(null, "Tunai Berhasil Di Depositkan!", "Deposit Tunai ", JOptionPane.PLAIN_MESSAGE);
              getBalance();
            int ab = JOptionPane.showConfirmDialog(null, "Apakah anda ingin mencetak struk??", "Deposit Tunai", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
              if (ab == 0) {
                JOptionPane.showMessageDialog(null, "No Rekening #" + UserRek + "\nWaktu: " + new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()) + "\nJumlah Tunai Yang Di Depositkan (Rp): " + amount + "\nSisa Saldo Anda Saat Ini (Rp): " + Balance, "Deposit Tunai", JOptionPane.INFORMATION_MESSAGE);
            }  
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Deposti! \nKesalahan Sistem", "Deposit Tunai ", JOptionPane.PLAIN_MESSAGE);  
            }
             
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Tolong Masukan Angka Saja!", "Deposit Tunai", JOptionPane.ERROR_MESSAGE);
        }
    
}

public void withdrawBalance(){
    getBalance();
    int amount = 0;
        String s = JOptionPane.showInputDialog(null, "Masukan Jumlah uang yang ingin di tarik (Rp)");
        if (onlyContainsNumbers(s)) {
            amount = Integer.parseInt(s);
            if (amount < 7000000 && amount < Balance) {
                try {
            String Query = "UPDATE tblcustacc SET Balance=? WHERE AccID=?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(Query);
            ps.setInt(1, Balance-Integer.valueOf(amount));
            ps.setInt(2, UserRek);
            if(ps.executeUpdate() == 1){
              JOptionPane.showMessageDialog(null, "Berhasil", "Tarik Tunai", JOptionPane.PLAIN_MESSAGE);
              getBalance();
            int ab = JOptionPane.showConfirmDialog(null, "Apakah anda ingin mencetak struk??", "Tarik Tunai", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
              if (ab == 0) {
                JOptionPane.showMessageDialog(null, "No Rekening #" + UserRek + "\nWaktu: " + new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()) + "\nJumlah Tunai Yang Di Tarik (Rp): " + amount + "\nSisa Saldo Anda Saat Ini (Rp): " + Balance, "Tarik Tunai", JOptionPane.INFORMATION_MESSAGE);
            }  
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Deposit! \nKesalahan Sistem", "Tarik Tunai ", JOptionPane.PLAIN_MESSAGE);  
            }
            
            }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            }
            }else{
                JOptionPane.showMessageDialog(null, "Anda Tidak Bisa Tarik Tunai Lebih Dari Rp.7.000.000 /ndalam sekali transaksi", "Tarik Tunai", JOptionPane.ERROR_MESSAGE);
            }      
        }else{
            JOptionPane.showMessageDialog(null, "Tolong Masukan Angka Saja!", "Tarik Tunai", JOptionPane.ERROR_MESSAGE);
        }
    
}

public void transferFunc(){
    getBalance();
    String toName;
    int amount = 0;
    int amounttmp = 0;
    int account = 0;
    String s;
    do {
         s = JOptionPane.showInputDialog(null, "Masukan Jumlah uang yang ingin di Transfer (Rp)");
            if (onlyContainsNumbers(s)) {
                amount = Integer.parseInt(s);
            } else {
                JOptionPane.showMessageDialog(null, "Tolong Masukan Angka Saja!", "Transfer Tunai", JOptionPane.ERROR_MESSAGE);
            }
        } while (!onlyContainsNumbers(s));
    if (amount < Balance) {
            String s1;
            do {
                s1 = JOptionPane.showInputDialog(null, "Masukan No Rekening Tujuan: ");
                if (onlyContainsNumbers(s1)) {
                    account = Integer.parseInt(s1);
                } else {
                    JOptionPane.showMessageDialog(null, "Tolong Masukan Angka Saja!", "Transfer Tunai", JOptionPane.ERROR_MESSAGE);
                }
            } while (!onlyContainsNumbers(s1));   
        String Query = "SELECT * FROM tblcustacc WHERE AccID='"+account+"'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(Query);
            if(rs.next()){
            toName = rs.getString(2);
            amounttmp = rs.getInt(6);
            int ab = JOptionPane.showConfirmDialog(null, "Anda Akan Mentransfer Sebesar Rp.  " +amount+ " Ke Nomor Rek  "+account+" Atas nama "+ toName, "Transfer Tunai", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (ab == 0) {
                            try {
                                String Update = "UPDATE tblcustacc SET Balance=? WHERE AccID=?";
                                PreparedStatement ps = (PreparedStatement) con.prepareStatement(Update);
                                ps.setInt(1, amounttmp+Integer.valueOf(amount));
                                ps.setInt(2, account);
                                ps.executeUpdate();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                            }
                            try {
                                String mainUp = "UPDATE tblcustacc SET Balance=? WHERE AccID=?";
                                PreparedStatement ps = (PreparedStatement) con.prepareStatement(mainUp);
                                ps.setInt(1, Balance-Integer.valueOf(amount));
                                ps.setInt(2, UserRek);                                
                                if(ps.executeUpdate() == 1){
                                    getBalance();
                                    JOptionPane.showMessageDialog(null, "Transfer Berhasil!\n", "Transfer Uang", JOptionPane.INFORMATION_MESSAGE);
                                int ab1 = JOptionPane.showConfirmDialog(null, "Apakah anda ingin mencetak struk?", "Transfer Uang", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                                // 0=yes ; 1=no
                                if (ab1 == 0) {
                                    JOptionPane.showMessageDialog(null, "Dari: "+UserName+"/"+UserRek+"\nKe Rekening #" + account+ "\nAtas Nama: "+toName+ "\nWaktu: " + new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()) + "\nJumlah Yang Di Transfer (Rp):  " + amount + "\nSisa Saldo (Rp): " + Balance, "Transfer Uang", JOptionPane.INFORMATION_MESSAGE);
                                }  
                                }else {
                                    JOptionPane.showMessageDialog(null, "Gagal Transfer! \nKesalahan Sistem", "Transfer Tunai ", JOptionPane.PLAIN_MESSAGE);  
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());
                            }
                        }
            
            }else{
                JOptionPane.showMessageDialog(this, "Rekening Tidak Terdaftar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }else{
        getBalance();
        JOptionPane.showMessageDialog(null, "Saldo kamu tidak cukup!\nSaldo kamu saat ini adalah (Rp) " + Balance, "Transfer Tunai", JOptionPane.ERROR_MESSAGE);
    }
    
}

private boolean onlyContainsNumbers(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
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

        LBName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnDepo = new javax.swing.JButton();
        btnKeluar1 = new javax.swing.JButton();
        btnCekSaldo = new javax.swing.JButton();
        btnTrf = new javax.swing.JButton();
        btnTrkTunai = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LBRek = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LBName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LBName.setForeground(new java.awt.Color(0, 0, 255));
        LBName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LBName.setText("Name Goes Here!");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ATM BANK EZFARE");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SILAHKAN PILIH MENU:");

        btnDepo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDepo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/depo.png"))); // NOI18N
        btnDepo.setText("Deposit");
        btnDepo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepoActionPerformed(evt);
            }
        });

        btnKeluar1.setBackground(new java.awt.Color(255, 102, 102));
        btnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/KELUAR.png"))); // NOI18N
        btnKeluar1.setText("KELUAR");
        btnKeluar1.setIconTextGap(15);
        btnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluar1ActionPerformed(evt);
            }
        });

        btnCekSaldo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCekSaldo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/WALLET.png"))); // NOI18N
        btnCekSaldo.setText("Cek Saldo");
        btnCekSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekSaldoActionPerformed(evt);
            }
        });

        btnTrf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTrf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Trf.png"))); // NOI18N
        btnTrf.setText("Transfer");
        btnTrf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrfActionPerformed(evt);
            }
        });

        btnTrkTunai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnTrkTunai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/WD.png"))); // NOI18N
        btnTrkTunai.setText("Tarik Tunai");
        btnTrkTunai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrkTunaiActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("SELAMAT DATANG,");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("No Rekening Anda:");

        LBRek.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LBRek.setForeground(new java.awt.Color(0, 153, 0));
        LBRek.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LBRek.setText("Rekening Goes Here");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(238, 238, 238))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTrkTunai, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(btnCekSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKeluar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(234, 234, 234)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDepo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTrf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LBName, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(LBRek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LBName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LBRek))
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCekSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrf, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrkTunai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDepo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(btnKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepoActionPerformed
        // TODO add your handling code here:
        depositBalance();
    }//GEN-LAST:event_btnDepoActionPerformed

    private void btnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluar1ActionPerformed
        // TODO add your handling code here:      
        new LoginFrame().setVisible(true);
        this.dispose();  
    }//GEN-LAST:event_btnKeluar1ActionPerformed

    private void btnCekSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekSaldoActionPerformed
        // TODO add your handling code here:
        getCashInfo();
    }//GEN-LAST:event_btnCekSaldoActionPerformed

    private void btnTrfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrfActionPerformed
        // TODO add your handling code here:
        transferFunc();
    }//GEN-LAST:event_btnTrfActionPerformed

    private void btnTrkTunaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrkTunaiActionPerformed
        // TODO add your handling code here:
        withdrawBalance();
    }//GEN-LAST:event_btnTrkTunaiActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBName;
    private javax.swing.JLabel LBRek;
    private javax.swing.JButton btnCekSaldo;
    private javax.swing.JButton btnDepo;
    private javax.swing.JButton btnKeluar1;
    private javax.swing.JButton btnTrf;
    private javax.swing.JButton btnTrkTunai;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
