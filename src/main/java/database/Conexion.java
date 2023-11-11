/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Geraldine Aecvedo Restrepo
 * 
 * Clase con las conexines necesarias para l abase de datos y su estructuracion
 * 
 * 
 */
public class Conexion {
    public static Connection conexion = null;
    static String driver = "org.mariadb.jdbc.Driver";  
    static String host = "127.0.0.1";
    static String bd = "bd_prueba";//nombre de la base de datos
    static String usuario = "root";
    static String contrasenia = "12345"; 
    static String puerto = "3308";  //si no funciona utiliza el puerto 3306  
    static String servidor = "jdbc:mariadb://" + host + ":" + puerto + "/" + bd;
    
    /**
     *
     * @return
     */
    public  Connection Conectar(){

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(servidor, usuario, contrasenia);
            System.out.println("Conectado");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se conecto a la base de datos");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se conecto a la base de datos");
        }
        return conexion;
    }
    public void desconectar() {
        conexion = null;
        if (conexion == null) {
            System.out.println("Conexion terminada");
        }
    }
}
