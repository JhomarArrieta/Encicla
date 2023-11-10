/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import database.Conexion;
import database.OperacionesUsuarios;
import database.OperacionesVehiculos;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Bicycle;
import model.Persona;
import view.BicycleRegister;
import view.Menu;
import view.UserRegister;

/**
 *
 * @author Geraldine Acevedo Restrepo
 */
public class Controlador implements ActionListener {
    Menu formMenuRegister;
    UserRegister formUserRegister;
    BicycleRegister formBicycleRegister;
    static OperacionesUsuarios users = new OperacionesUsuarios();
    static OperacionesVehiculos vehicles = new OperacionesVehiculos();
    
    public Controlador() {
        this.formMenuRegister = new Menu();
        this.formUserRegister = new UserRegister();
        this.formBicycleRegister = new BicycleRegister();
    }
    public void Start(){
        //Conexion a la base de datos
        Conexion objetoconexion = new Conexion();
        objetoconexion.Conectar();
        //Hace el menu visible
        formMenuRegister.setVisible(true);
        //Es el escuchador para el boton de registrar usuario
        formMenuRegister.getR_User_Btn().addActionListener(this);
        //Es el escuchador para el boton de registrar bicicleta
        formMenuRegister.getR_Bycicle_Btn().addActionListener(this);
        //Es el escuchador para el boton de guardar usuarios
        formBicycleRegister.getSave_bicycle_btn().addActionListener(this);
        //Es el escuchador para el boton de guardar bicicletas
        formUserRegister.getUser_save_btn().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(formMenuRegister.getR_User_Btn())){
            formUserRegister.setLocationRelativeTo(null);
            formUserRegister.setVisible(true);
            int x = (formMenuRegister.getEscritorio().getWidth() / 2) - (formUserRegister.getWidth() / 2);
            int y = (formMenuRegister.getEscritorio().getHeight() / 2) - (formUserRegister.getHeight() / 2);
            if (formUserRegister.isShowing()) {
                formUserRegister.setLocation(x, y);
            } else {
                formMenuRegister.getEscritorio().add(formUserRegister );
                formUserRegister.setLocation(x, y);
                formUserRegister.show();
            }   
        }
        if(e.getSource().equals(formMenuRegister.getR_Bycicle_Btn())){
            formBicycleRegister.setLocationRelativeTo(null);
            formBicycleRegister.setVisible(true);
            int x = (formMenuRegister.getEscritorio().getWidth() / 2) - (formBicycleRegister.getWidth() / 2);
            int y = (formMenuRegister.getEscritorio().getHeight() / 2) - (formBicycleRegister.getHeight() / 2);
            if (formBicycleRegister.isShowing()) {
                formBicycleRegister.setLocation(x, y);
            } else {
                formMenuRegister.getEscritorio().add(formBicycleRegister );
                formBicycleRegister.setLocation(x, y);
                formBicycleRegister.show();
            }   
        }
        if(e.getSource().equals(formUserRegister.getUser_save_btn())){
            try{
                String name, direccion, email, ciudad;
                int cedula, telefono;
                //Aquí la idea es validar primero la información de los campos de la ventana antes de poder guardarla
                //if (formRegister_validado()) {
                name = formUserRegister.getRegister_name_txt().getText();
                cedula = Integer.parseInt(formUserRegister.getRegister_cedula_txt().getText());
                direccion = formUserRegister.getRegister_direc_txt().getText();
                telefono = Integer.parseInt(formUserRegister.getRegister_tel_txt().getText());
                email = formUserRegister.getRegister_email_txt().getText();
                ciudad = formUserRegister.getRegister_city_txt().getText();
                Persona usuario = new Persona(name,cedula,telefono,direccion,email,ciudad);
                JOptionPane.showMessageDialog(null, "Entra al menos");
                users.Registrar(usuario);
                //La idea de esto es que funcione para limpiar los campos de la ventana
                //Clean();
                //} else {

                //}
            }catch (NumberFormatException x) {
            JOptionPane.showMessageDialog(null, "Don't leave empty and unselected fields and use numbers for Force, Vision and EvilScale", "Error", JOptionPane.ERROR_MESSAGE);
            }catch (HeadlessException m) {
                JOptionPane.showMessageDialog(null, "Hay errores, revise sus números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource().equals(formBicycleRegister.getSave_bicycle_btn())){
            try{
                String acopio, color, estado;
                int codigo, year;
                //Aquí la idea es validar primero la información de los campos de la ventana antes de poder guardarla
                //if (formRegister_validado()) {
                acopio = formBicycleRegister.getRegister_Acopio_txt().getText();
                year = Integer.parseInt(formBicycleRegister.getRegister_year_txt().getText());
                color = formBicycleRegister.getRegister_color_txt().getText();
                codigo = Integer.parseInt(formBicycleRegister.getRegister_cod_txt().getText());
                if (formBicycleRegister.getRegister_disponible_txt().isSelected()){
                    estado = "Disponible";
                }else if (formBicycleRegister.getRegister_ocupada_txt().isSelected()){
                    estado = "Ocupado";
                }else{
                    estado = "None";
                }
                Bicycle bicycle = new Bicycle(acopio,color,codigo,year, estado);
                JOptionPane.showMessageDialog(null, "Entra al menos");
                vehicles.Registrar(bicycle);
                //La idea de esto es que funcione para limpiar los campos de la ventana
                //Clean();
                //} else {

                //}
            }catch (NumberFormatException x) {
            JOptionPane.showMessageDialog(null, "Don't leave empty and unselected fields and use numbers for Force, Vision and EvilScale", "Error", JOptionPane.ERROR_MESSAGE);
            }catch (HeadlessException m) {
                JOptionPane.showMessageDialog(null, "Hay errores, revise sus números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
