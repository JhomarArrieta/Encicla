/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Bicycle;
import model.Historial;
import model.Vehiculo;

/**
 *
 * @author Milton Cuervo
 * 
 * Clase que contiene los metodos para la generacion del historial con los JSON
 * 
 */
public class HistorialOperaciones {

   private final String insertarPrestamo = "INSERT INTO Historial (CodigoBicicleta, Usuario, FechaPrestamo) VALUES (?, ?, NOW())";
    private final String insertarDevolucion = "UPDATE Historial SET FechaDevolucion = NOW() WHERE CodigoBicicleta = ? AND FechaDevolucion IS NULL";
    private PreparedStatement ps = null;
    Conexion bd = new Conexion();
    List<Historial> historial = new ArrayList<>();

    public void registrarPrestamo(int codigo, String usuario) {
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement(insertarPrestamo);
            ps.setInt(1, codigo);
            ps.setString(2, usuario);
            ps.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void registrarDevolucion(String codigo) {
        Connection cn = bd.Conectar();
        try {
            ps = cn.prepareStatement(insertarDevolucion);
            ps.setString(1, codigo);
            ps.executeUpdate();
            bd.desconectar();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public String obtenerHistorialJSON() throws SQLException {
        String sql = "SELECT * FROM Historial";
        Connection cn = bd.Conectar();
        ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); // Ejecutar la consulta y obtener el resultado...
        while (rs.next()) {
            Historial h = new Historial();
            h.setCodigoBicicleta(rs.getInt("CodigoBicicleta"));
            h.setUsuario(rs.getString("Usuario"));
            h.setFechaPrestamo(rs.getTimestamp("FechaPrestamo"));
            h.setFechaDevolucion(rs.getTimestamp("FechaDevolucion"));
            historial.add(h);
        }
        Gson gson = new Gson();
        return gson.toJson(historial);
    }

    public void escribirHistorialJSON() throws SQLException, IOException {
        String json = obtenerHistorialJSON();

        // Escribir el JSON en un archivo
        try (FileWriter file = new FileWriter("historial.json")) {
            file.write(json);
            System.out.println("Historial escrito con éxito en historial.json");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo historial.json");
            e.printStackTrace();
        }
    }
}
