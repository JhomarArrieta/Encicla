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
import model.Bicycle;
import model.Persona;

/**
 *
 * @author Geraldine Acevedo Restrepo
 */
public class OperacionesPrestamo {
    private final String insertar = "INSERT INTO prestamo(Nombre,Cedula,Telefono,Direccion,Email,Ciudad,Codigo,Acopio,Year,Color,Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    Conexion bd = new Conexion();

    public void Registrar(Persona persona, Bicycle bicicleta) {
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement(insertar);
            ps.setString(1, persona.getName());
            ps.setInt(2, persona.getCedula());
            ps.setInt(3, persona.getTelefono());
            ps.setString(4, persona.getDireccion());
            ps.setString(5, persona.getEmail());
            ps.setString(6, persona.getCiudad());
            // Aquí puedes agregar los campos correspondientes a la bicicleta
            ps.setInt(7, bicicleta.getCode());
            ps.setString(8, bicicleta.getAcopio());
            ps.setInt(9, bicicleta.getYear());
            ps.setString(10, bicicleta.getColor());
            ps.setString(11, bicicleta.getEstado());
            ps.executeUpdate();
            bd.desconectar();
            JOptionPane.showMessageDialog(null, "Registro Guardado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cliente ya Registrado");
            System.out.println(ex);
        }
    }
}
