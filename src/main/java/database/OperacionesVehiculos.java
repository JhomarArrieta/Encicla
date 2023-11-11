/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Bicycle;
import model.Persona;

/**
 *
 * @author Geraldine Acevedo Restrepo
 */
public class OperacionesVehiculos {
    private final String insertar = "INSERT INTO bicicletas(Codigo,Acopio,Year,Color,Estado) VALUES (?, ?, ?, ?, ?)";
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    Conexion bd = new Conexion();

    
    public void RegistrarBicicletas() {
        Connection cn = bd.Conectar();
        try {
            // Preparando el statement fuera del bucle para optimizar
            ps = cn.prepareStatement(insertar);

            String[] acopios = {"Acopio1", "Acopio2", "Acopio3", "Acopio4", "Acopio5"};
            String[] colores = {"Rojo", "Azul", "Amarillo", "Verde", "Morado"};
            String estado = "Disponible";
            int year = 2023;

            for (int i = 0; i < 100; i++) {
                int codigo = i + 1;
                String acopio = acopios[i / 20];
                String color = colores[i / 20];

                Bicycle dato = new Bicycle(codigo, acopio, year, color, estado);

                ps.setInt(1, dato.getCode());
                ps.setString(2, dato.getAcopio());
                ps.setInt(3, dato.getYear());
                ps.setString(4, dato.getColor());
                ps.setString(5, dato.getEstado());
                ps.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "100 Registros Guardados");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar bicicleta");
            ex.printStackTrace();
        } finally {
            // Cerrar PreparedStatement y Connection aquí
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            bd.desconectar();
        }
    }

    public boolean Buscar(Bicycle dato){
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement("SELECT * FROM bicicletas WHERE codigo = ?");
            ps.setInt(1, dato.getCode());
            rs = ps.executeQuery();
            if(rs.next()){                 
                dato.setAcopio(rs.getString("acopio"));
                dato.setYear(rs.getInt("year"));
                dato.setColor(rs.getString("color"));               
                dato.setEstado(rs.getString("estado"));
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
    public boolean modificar(Bicycle dato) {
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement("UPDATE bicicletas SET acopio=?, year=?, color=?, estado=? WHERE codigo=?");
            ps.setInt(1, dato.getCode());
            ps.setString(2, dato.getAcopio());           
            ps.setInt(3, dato.getYear());
            ps.setString(4, dato.getColor());
            ps.setString(5, dato.getEstado());
            
            ps.executeUpdate();
            bd.desconectar();
            return false;
        } catch (SQLException e) {
            System.err.println("Error al modificar el producto: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Bicycle> getBicicletas() {
        ArrayList<Bicycle> bicicletas= new ArrayList<Bicycle>();
        try {
            Connection cn = bd.Conectar();
            ps = cn.prepareStatement("SELECT * FROM bicicletas");
            rs = ps.executeQuery();
            while (rs.next()) {
                Bicycle bicicleta = new Bicycle();
                bicicleta.setCode(rs.getInt("codigo"));
                bicicleta.setAcopio(rs.getString("acopio"));
                bicicleta.setYear(rs.getInt("year"));
                bicicleta.setColor(rs.getString("color"));
                bicicleta.setEstado(rs.getString("estado"));               
                bicicletas.add(bicicleta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en obtener bicicletas: " + e.getMessage());
        }
        return bicicletas;
    }
}
