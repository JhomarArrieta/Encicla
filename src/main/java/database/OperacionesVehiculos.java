/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Bicycle;
import model.Vehiculo;

/**
 *
 * @author Geraldine Acevedo Restrepo
 */
public class OperacionesVehiculos {
    private final String insertar = "INSERT INTO bicicletas(Codigo,Acopio,Year,Color,Estado) VALUES (?, ?, ?, ?, ?)";
    private PreparedStatement ps = null;
    Conexion bd = new Conexion();

    public void Registrar(Bicycle dato) {
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement(insertar);
            ps.setInt(1, dato.getCode());
            ps.setString(2, dato.getAcopio());           
            ps.setInt(3, dato.getYear());
            ps.setString(4, dato.getColor());
            ps.setString(5, dato.getEstado());
            ps.executeUpdate();
            bd.desconectar();
            JOptionPane.showMessageDialog(null, "Registro Guardado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Bicicleta ya Registrada con este codigo");
            System.out.println(ex);
        }
    }

    public void prestarBicicleta(int codigo, String usuario) {
        Connection cn = bd.Conectar();
        String sql = "UPDATE bicicletas SET Estado = 'Prestada', Usuario = ? WHERE Codigo = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setInt(2, codigo);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bicicleta prestada con éxito");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al prestar bicicleta");
            System.out.println(ex);
        }
    }

    public void devolverBicicleta(int codigo) {
        Connection cn = bd.Conectar();
        String sql = "UPDATE bicicletas SET Estado = 'Disponible', Usuario = NULL WHERE Codigo = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(2, codigo);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bicicleta devuelta con éxito");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al devolver bicicleta");
            System.out.println(ex);
        }
    }
}
