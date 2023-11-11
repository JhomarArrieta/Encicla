/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Geraldine Acevedo Restrepo
 * 
 * Clase con las Operaciones para devolucion de bicicletas
 * 
 */
public class OperacionesDevolucion {
    private final String insertar = "INSERT INTO devolucion(Nombre,Cedula,Codigo,Acopio,Estado) VALUES (?, ?, ?, ?, ?)";
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    Conexion bd = new Conexion();

    public void Devolucion(String Nombre,String Cedula, String Codigo, String Acopio,String Estado) {
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement(insertar);
            ps.setString(1, Nombre);
            ps.setString(2, Cedula);
            ps.setString(3, Codigo);
            ps.setString(4, Acopio);
            ps.setString(5, Estado);
            ps.executeUpdate();
            bd.desconectar();
            JOptionPane.showMessageDialog(null, "Devolucion registrada");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cliente ya Registrado");
            System.out.println(ex);
        }
    }
}
