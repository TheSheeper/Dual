/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mauri
 */
public class BDSentences {

    private final Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "appDual", "appDual");;
    private boolean connected = false;
    
    private String numControl;
    private String nombre_alumno;
    private String alumno_apellido_pat;
    private String alumno_apellido_mat;
    private String alumno_matricula;
    private String alumno_institucion;

    public BDSentences(String username, String password) throws SQLException {
        String sql = "SELECT * FROM USUARIOS WHERE Nombre_Usuario=? AND Clave_Acceso=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            connected = true;
        }
    }
    
    public void Busqueda(String numControl) throws SQLException, IOException{
        //Sentencias de ejecucion
            String sql = "SELECT * FROM Alumno WHERE Numero_Control=?";
            
            //Preparacion y realizacion de las sentencias 
            PreparedStatement pst = con.prepareStatement(sql);     
            pst.setString(1,numControl);
            ResultSet rs = pst.executeQuery();
            
            if(rs==null){
                throw new IOException("Alumno no encontrado");
            }
            
            while(rs.next()){
             this.numControl = numControl;
             nombre_alumno = rs.getString("Nombres");
             alumno_apellido_pat = rs.getString("Apellido_Paterno");
             alumno_apellido_mat = rs.getString("Apellido_Materno");
             alumno_matricula = rs.getString("Numero_Control");
             alumno_institucion = rs.getString("Dependencia");
            }
            
            
    }

    public boolean isConnected() {
        return connected;
    }

    /**
     * @return the numControl
     */
    public String getNumControl() {
        return numControl;
    }

    /**
     * @return the nombre_alumno
     */
    public String getNombre_alumno() {
        return nombre_alumno;
    }

    /**
     * @return the alumno_apellido_pat
     */
    public String getAlumno_apellido_pat() {
        return alumno_apellido_pat;
    }

    /**
     * @return the alumno_apellido_mat
     */
    public String getAlumno_apellido_mat() {
        return alumno_apellido_mat;
    }

    /**
     * @return the alumno_matricula
     */
    public String getAlumno_matricula() {
        return alumno_matricula;
    }

    /**
     * @return the alumno_institucion
     */
    public String getAlumno_institucion() {
        return alumno_institucion;
    }
}
