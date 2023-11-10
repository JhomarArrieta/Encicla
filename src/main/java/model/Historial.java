/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Usuario
 */
public class Historial {
    private int codigoBicicleta;
    private String usuario;
    private Timestamp fechaPrestamo;
    private Timestamp fechaDevolucion;

    // Getters y setters para cada campo...

    public int getCodigoBicicleta() {
        return codigoBicicleta;
    }

    public void setCodigoBicicleta(int codigoBicicleta) {
        this.codigoBicicleta = codigoBicicleta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Timestamp getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Timestamp fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Timestamp getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Timestamp fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
