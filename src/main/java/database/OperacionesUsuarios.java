/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Persona;

/**
 *
 * @author Geraldine Acevedo Restrepo
 */
//Esta parte del codigo esta prevista a ser la que me permita registrar los usuarios del sistema de encicla
//Se conecta con ValidacionesUsuarios, que recibe la informacion de la ventana userRegister
//Hace las debidas validaciones y obtiene la informacion que luego llegará hasta aquí que es donde
//se encontrara la conexion con la base de datos que procedera a hacer las respectivas funciones
// de guardar

public class OperacionesUsuarios {
    
    private final String insertar = "INSERT INTO usuarios(Nombre,Cedula,Telefono,Direccion,Email,Ciudad) VALUES (?, ?, ?, ?, ?,?)";
    private PreparedStatement ps = null;
    Conexion bd = new Conexion();

    public void Registrar(Persona dato) {
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement(insertar);
            ps.setString(1, dato.getName());
            ps.setInt(2, dato.getCedula());
            ps.setInt(3, dato.getTelefono());
            ps.setString(4, dato.getDireccion());
            ps.setString(5, dato.getEmail());
            ps.setString(6, dato.getCiudad());
            ps.executeUpdate();
            bd.desconectar();
            JOptionPane.showMessageDialog(null, "Registro Guardado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cliente ya Registrado");
            System.out.println(ex);
        }
    }
}
