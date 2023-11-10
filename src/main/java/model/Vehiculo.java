/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Geraldine Acevedo Restrepo
 */
public class Vehiculo {
    private String acopio;
    private String color;
    private int code;
    private int year;
    private String estado;

    public Vehiculo(String acopio, String color, int code, int year, String estado) {
        this.acopio = acopio;
        this.color = color;
        this.code = code;
        this.year = year;
        this.estado = estado;
    }
    public Vehiculo(){
        
    }

    
    public String getAcopio() {
        return acopio;
    }

    public void setAcopio(String acopio) {
        this.acopio = acopio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "acopio=" + acopio + ", color=" + color + ", code=" + code + ", year=" + year + ", estado=" + estado + '}';
    }
    
}
