/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import ventanas.MainMenu;

/**
 *
 * @author mauri
 */
public class BDSentences {

    private Connection con;
    private boolean connected = false;

    public BDSentences(String username, String password) throws SQLException {
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "dualApp", "dualApp");
        String sql = "SELECT * FROM USUARIOS WHERE Nombre_Usuario=? AND Clave_Acceso=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            connected = true;
        }
    }

    public boolean isConnected() {
        return connected;
    }
}
