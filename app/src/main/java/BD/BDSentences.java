/*
 * Copyright 2022 Chipa & Alan G.
 */
package BD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauri
 */
public class BDSentences {

    private final Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "appDual", "appDual");
    ;
    private boolean connected = false;

    private String nombre_alumno;
    private String alumno_apellido_pat;
    private String alumno_apellido_mat;
    private String alumno_carrera;
    private String alumno_matricula;
    private String alumno_institucion;
    private String alumno_num_seguro;

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

    public void insertar(String numControl, Object idCarrera, Object idGenero, String nombres,
            String apPaterno, String apMaterno, String correo, String dependencia, String numSeguroS) throws SQLException {
        String sql = "INSERT INTO ALUMNO (Numero_Control, ID_Carrera, ID_Genero, Nombres, Apellido_Paterno, Apellido_Materno, Correo, Dependencia, Num_SeguroSocial) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, numControl);
        pst.setInt(2, generar_ID_Carrera(idCarrera));
        pst.setInt(3, generar_ID_Genero(idGenero));
        pst.setString(4, nombres);
        pst.setString(5, apPaterno);
        pst.setString(6, apMaterno);
        pst.setString(7, correo);
        pst.setString(8, dependencia);
        pst.setString(9, numSeguroS);
        pst.executeUpdate();
    }

    public void Busqueda(String numControl) throws SQLException, IOException {
        //Sentencias de ejecucion
        String sql = "SELECT NUMERO_CONTROL, NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, CORREO, DEPENDENCIA, NUM_SEGUROSOCIAL, GENERO, CARRERA FROM ALUMNO A, "
                + "GENERO G, CARRERA C WHERE A.NUMERO_CONTROL = ? AND A.ID_CARRERA=C.ID_CARRERA AND A.ID_GENERO=G.ID_GENERO ORDER BY NUMERO_CONTROL";

        //Preparacion y realizacion de las sentencias 
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, numControl);
        ResultSet rs = pst.executeQuery();

        if (rs == null) {
            throw new IOException("Alumno no encontrado");
        }

        while (rs.next()) {
            nombre_alumno = rs.getString("NOMBRES");
            alumno_apellido_pat = rs.getString("APELLIDO_PATERNO");
            alumno_apellido_mat = rs.getString("APELLIDO_MATERNO");
            alumno_carrera = rs.getString("CARRERA");
            alumno_matricula = rs.getString("NUMERO_CONTROL");
            alumno_institucion = rs.getString("DEPENDENCIA");
            alumno_num_seguro = rs.getString("NUM_SEGUROSOCIAL");
        }

    }

    public void rellenarTabla(DefaultTableModel tblModel) throws SQLException {
        String sql = "SELECT NUMERO_CONTROL, NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, CORREO, DEPENDENCIA, NUM_SEGUROSOCIAL, GENERO, CARRERA FROM ALUMNO A, "
                + "GENERO G, CARRERA C WHERE A.ID_CARRERA=C.ID_CARRERA AND A.ID_GENERO=G.ID_GENERO ORDER BY NUMERO_CONTROL";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);
        tblModel.setRowCount(0);
        
        while (rs.next()) {
            String numControl = String.valueOf(rs.getInt("NUMERO_CONTROL"));
            String genero = rs.getString("GENERO");
            String carrera = rs.getString("CARRERA");
            String nombre = rs.getString("NOMBREs");
            String apePaterno = rs.getString("APELLIDO_PATERNO");
            String apeMaterno = rs.getString("APELLIDO_MATERNO");
            String correo = rs.getString("CORREO");
            String dependencia = rs.getString("DEPENDENCIA");
            String seguroSocial = rs.getString("NUM_SEGUROSOCIAL");

            String tbData[] = {numControl, carrera, genero, nombre + " " + apePaterno + " " + apeMaterno, correo, dependencia, seguroSocial};

            tblModel.addRow(tbData);
        }
    }

    public void rellenarComboCarrera(JComboBox jcombo) {
        try {
            //Preparacion y realizacion de las sentencias 
            PreparedStatement pst = con.prepareStatement("SELECT CARRERA FROM Carrera");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String carrera = String.valueOf(rs.getString("CARRERA"));
                jcombo.addItem(carrera);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void rellenarComboGenero(JComboBox jcombo) {
        try {
            //Preparacion y realizacion de las sentencias 
            PreparedStatement pst = con.prepareStatement("SELECT Genero FROM Genero");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String genero = String.valueOf(rs.getString("Genero"));
                jcombo.addItem(genero);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private int generar_ID_Genero(Object jcombo) {
        int id = 0;
        try {
            String sql = "SELECT ID_GENERO FROM GENERO WHERE GENERO=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, jcombo.toString());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("ID_GENERO"));
            }
            return id;
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return id;
    }

    private int generar_ID_Carrera(Object jcombo) {
        int id = 0;
        try {
            String sql = "SELECT ID_CARRERA FROM CARRERA WHERE CARRERA='" + jcombo + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("ID_CARRERA"));
            }
            return id;
        } catch (Exception ex) {
            System.out.print(ex);
        }
        return id;
    }

    public boolean isConnected() {
        return connected;
    }

    /**
     * @return the numControl
     */
    public String getSeguroSocial() {
        return alumno_num_seguro;
    }

    /**
     * @return the numControl
     */
    public String getCarrera() {
        return alumno_carrera;
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
