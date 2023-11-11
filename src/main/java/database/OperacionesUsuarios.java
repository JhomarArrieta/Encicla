/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Persona;

/**
 *
 * @author Geraldine Acevedo Restrepo
 * 
 * Clase con las operaciones que modifican los usuarion de la db
 */
//Esta parte del codigo esta prevista a ser la que me permita registrar los usuarios del sistema de encicla
//Se conecta con ValidacionesUsuarios, que recibe la informacion de la ventana userRegister
//Hace las debidas validaciones y obtiene la informacion que luego llegará hasta aquí que es donde
//se encontrara la conexion con la base de datos que procedera a hacer las respectivas funciones
// de guardar

public class OperacionesUsuarios {
    
    private final String insertar = "INSERT INTO usuarios(Nombre,Cedula,Telefono,Direccion,Email,Ciudad) VALUES (?, ?, ?, ?, ?,?)";
    private PreparedStatement ps = null;
    private ResultSet rs = null;
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
    public boolean Buscar(Persona dato){
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement("SELECT * FROM usuarios WHERE cedula = ?");
            ps.setInt(1, dato.getCedula());
            rs = ps.executeQuery();
            if(rs.next()){
                dato.setName(rs.getString("nombre"));
                dato.setTelefono(rs.getInt("telefono"));
                dato.setDireccion(rs.getString("direccion"));
                dato.setEmail(rs.getString("email"));
                dato.setCiudad(rs.getString("ciudad"));
                return true;
            }
            JOptionPane.showMessageDialog(null, "Se ejecuata la seleccion");
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la seleccion");
            System.out.println(ex);
            return false;
        }finally{
            try{
                cn.close();
            }catch(SQLException ex){
               System.out.println("ahhh"); 
            }
        }
    }
    public boolean modificar(Persona dato) {
        Connection cn = bd.Conectar();
        PreparedStatement ps = null;

        try {
            ps = cn.prepareStatement("UPDATE usuarios SET nombre=?, telefono=?, direccion=?, email=?, ciudad=? WHERE cedula=?");
            ps.setString(1, dato.getName());
            ps.setInt(2, dato.getCedula());
            ps.setInt(3, dato.getTelefono());
            ps.setString(4, dato.getDireccion());
            ps.setString(5, dato.getEmail());
            ps.setString(6, dato.getCiudad());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return true; // Se modificó exitosamente
            } else {
                return false; // No se encontró el registro o no se modificó
            }
        } catch (SQLException e) {
            System.err.println("Error al modificar el producto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public ArrayList<Persona> getPersonas() {
        ArrayList<Persona> personas = new ArrayList<Persona>();
        try {
            Connection cn = bd.Conectar();
            ps = cn.prepareStatement("SELECT * FROM usuarios");
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setName(rs.getString("nombre"));
                persona.setCedula(rs.getInt("cedula"));
                persona.setTelefono(rs.getInt("telefono"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setEmail(rs.getString("email"));
                persona.setCiudad(rs.getString("ciudad"));                
                personas.add(persona);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en obtener personas: " + e.getMessage());
        }


        return personas;
    }
}
