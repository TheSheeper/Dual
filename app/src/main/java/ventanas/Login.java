/*
 * Copyright 2022 Chipa & Alan G.
 * Pantalla de login
 */
package ventanas;

import BD.BDSentences;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author chipa
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Inicio_Sesion
     */
    public Login() {
        initComponents();
//        Cambiar locacion principal
        this.setLocationRelativeTo(null); 

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Header = new javax.swing.JPanel();
        logo_ITS = new javax.swing.JLabel();
        header_title = new javax.swing.JLabel();
        Fondo = new javax.swing.JPanel();
        InicioSesion = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        field_user = new javax.swing.JTextField();
        usuario1 = new javax.swing.JLabel();
        field_password = new javax.swing.JPasswordField();
        btn_enter = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Programa Dual");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(31, 61, 109));
        Header.setPreferredSize(new java.awt.Dimension(800, 80));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo_ITS.setIcon(new javax.swing.ImageIcon(getClass().getClassLoader().getResource("./images/icons/logo_its_60.png")));
        Header.add(logo_ITS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 60));

        header_title.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        header_title.setForeground(new java.awt.Color(255, 255, 255));
        header_title.setText("Programa Dual");
        Header.add(header_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InicioSesion.setBackground(new java.awt.Color(243, 243, 243));
        InicioSesion.setName(""); // NOI18N
        InicioSesion.setPreferredSize(new java.awt.Dimension(400, 350));
        InicioSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        titulo.setText("Iniciar Sesi�n");
        InicioSesion.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usuario.setText("Usuario");
        InicioSesion.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        field_user.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        InicioSesion.add(field_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 230, -1));

        usuario1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usuario1.setText("Contrase�a");
        InicioSesion.add(usuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));
        InicioSesion.add(field_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 230, -1));

        btn_enter.setBackground(new java.awt.Color(31, 61, 109));
        btn_enter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_enter.setForeground(new java.awt.Color(255, 255, 255));
        btn_enter.setText("Ingresar");
        btn_enter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_enterMouseClicked(evt);
            }
        });
        InicioSesion.add(btn_enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 230, 30));

        btn_exit.setBackground(new java.awt.Color(31, 61, 109));
        btn_exit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(255, 255, 255));
        btn_exit.setText("Salir");
        btn_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_exitMouseClicked(evt);
            }
        });
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });
        InicioSesion.add(btn_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 230, 30));

        Fondo.add(InicioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 80, 340, -1));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    Evento que permitirá iniciar sesión y cambiar de ventana
    private void btn_enterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_enterMouseClicked
        try {
            BDSentences db = new BDSentences(field_user.getText(), new String(field_password.getPassword()));
            if (db.isConnected()) {
                MainMenu menuPrincipal = new MainMenu(db);
                menuPrincipal.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_enterMouseClicked

    private void btn_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_exitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_exitMouseClicked

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
         System.exit(0);
    }//GEN-LAST:event_btn_exitActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel InicioSesion;
    private javax.swing.JButton btn_enter;
    private javax.swing.JButton btn_exit;
    private javax.swing.JPasswordField field_password;
    private javax.swing.JTextField field_user;
    private javax.swing.JLabel header_title;
    private javax.swing.JLabel logo_ITS;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel usuario;
    private javax.swing.JLabel usuario1;
    // End of variables declaration//GEN-END:variables
}
