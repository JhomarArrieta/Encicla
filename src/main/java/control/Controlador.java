/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import database.Conexion;
import database.OperacionesPrestamo;
import database.OperacionesUsuarios;
import database.OperacionesVehiculos;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Bicycle;
import model.Persona;
import view.BicycleRegister;
import view.Menu;
import view.Prestamo;
import view.UserRegister;
import view.searchBicycle;
import view.searchUser;

/**
 *
 * @author Geraldine Acevedo Restrepo
 */
public class Controlador implements ActionListener {
    Menu formMenuRegister;
    UserRegister formUserRegister;
    BicycleRegister formBicycleRegister;
    Prestamo formPrestamoRegister;
    searchUser formUserView;
    searchBicycle formBicycleView;
    Persona persona = new Persona();
    Bicycle bicicleta = new Bicycle();
    OperacionesUsuarios users = new OperacionesUsuarios();
    OperacionesVehiculos vehicles = new OperacionesVehiculos();
    OperacionesPrestamo prestamo = new OperacionesPrestamo();
    DefaultTableModel model =  new DefaultTableModel();
    
    public Controlador() {
        this.users = users;
        this.vehicles = vehicles;
        this.persona = persona;
        this.bicicleta = bicicleta;
        this.formMenuRegister = new Menu();
        this.formUserRegister = new UserRegister();
        this.formBicycleRegister = new BicycleRegister();
        this.formPrestamoRegister =  new Prestamo();
        this.formUserView = new searchUser();
        this.formBicycleView = new searchBicycle();
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
        //Es el escuchador para abrir la ventana de prestamo de bicicletas
        formMenuRegister.getOp_Prestamo_Btn().addActionListener(this);
        //Es el escuchador para el boton de guardar usuarios
        formBicycleRegister.getSave_bicycle_btn().addActionListener(this);
        //Es el escuchador para el boton de guardar bicicletas
        formUserRegister.getUser_save_btn().addActionListener(this);
        //Es el escuchador para el boton buscar usuario en el prestamo
        formPrestamoRegister.getBuscar_txt_prestamo().addActionListener(this);
        //Es el escuchador para el boton buscar bicicleta en el prestamo
        formPrestamoRegister.getElegir_txt().addActionListener(this);
        //Es el esuchador para la ventana mostrar usuarios en la base de datos
        formUserRegister.getUser_mostrar_btn().addActionListener(this);
        //Es el escuchador para el boton de mostrar usuarios
        formUserView.getBuscar_txt().addActionListener(this);
        //Es el escuchador para el boton mostrar bicicletas
        formBicycleView.getMostrar_txt().addActionListener(this);
        //Es el escuchador para la ventana mostrar bicicletas
        formBicycleRegister.getMostrar_bicycle_btn().addActionListener(this);
        //Es el escuchador para guardar el prestamo de la biciccleta en mariadb
        formPrestamoRegister.getGenerar_prestamo_txt().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Permite visualizar la ventana del menu de forma que quede centrado
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
        //Permite visualizar la ventana de el registro de usuarios de forma que quede centrado
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
        //Permite visualizar la ventana de mostrar usuarios de forma que quede centrado
        if(e.getSource().equals(formUserRegister.getUser_mostrar_btn())){
            formUserView.setLocationRelativeTo(null);
            formUserView.setVisible(true);
            int x = (formMenuRegister.getEscritorio().getWidth() / 2) - (formUserView.getWidth() / 2);
            int y = (formMenuRegister.getEscritorio().getHeight() / 2) - (formUserView.getHeight() / 2);
            if (formUserView.isShowing()) {
                formUserView.setLocation(x, y);
            } else {
                formMenuRegister.getEscritorio().add(formUserView );
                formUserView.setLocation(x, y);
                formUserView.show();
            }
        }
        //Permite guardar los usuarios en la base de datos
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
            }catch (NumberFormatException x) {
            JOptionPane.showMessageDialog(null, "Don't leave empty and unselected fields and use numbers for Force, Vision and EvilScale", "Error", JOptionPane.ERROR_MESSAGE);
            }catch (HeadlessException m) {
                JOptionPane.showMessageDialog(null, "Hay errores, revise sus números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Permite guardar las bicicletas en las base de datos
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
                Bicycle bicycle = new Bicycle(codigo,acopio,year,color, estado);
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
        //Permite visualizar la ventana de prestamo de bicicletas
        if(e.getSource().equals(formMenuRegister.getOp_Prestamo_Btn())){
            formPrestamoRegister.setLocationRelativeTo(null);
            formPrestamoRegister.setVisible(true);
            int x = (formMenuRegister.getEscritorio().getWidth() / 2) - (formPrestamoRegister.getWidth() / 2);
            int y = (formMenuRegister.getEscritorio().getHeight() / 2) - (formPrestamoRegister.getHeight() / 2);
            if (formPrestamoRegister.isShowing()) {
                formPrestamoRegister.setLocation(x, y);
            } else {
                formMenuRegister.getEscritorio().add(formPrestamoRegister);
                formPrestamoRegister.setLocation(x, y);
                formPrestamoRegister.show();
            }   
        }
        //Permite visualizar la ventana de mostrar bicicletas de la base de datos
        if(e.getSource().equals(formBicycleRegister.getMostrar_bicycle_btn())){
            formBicycleView.setLocationRelativeTo(null);
            formBicycleView.setVisible(true);
            int x = (formMenuRegister.getEscritorio().getWidth() / 2) - (formBicycleView.getWidth() / 2);
            int y = (formMenuRegister.getEscritorio().getHeight() / 2) - (formBicycleView.getHeight() / 2);
            if (formBicycleView.isShowing()) {
                formBicycleView.setLocation(x, y);
            } else {
                formMenuRegister.getEscritorio().add(formBicycleView );
                formBicycleView.setLocation(x, y);
                formBicycleView.show();
            }
        }
        //Permite buscar un usuario en la base de datos y luego colocar los valores que tenga en esta de forma automatica
        //para el prestamo
        if(e.getSource().equals(formPrestamoRegister.getBuscar_txt_prestamo())){
           persona.setCedula(Integer.parseInt(formPrestamoRegister.getCedula_txt().getText()));
           if(users.Buscar(persona)){
                formPrestamoRegister.getName_txt().setText(persona.getName());
                formPrestamoRegister.getCiudad_txt().setText(persona.getCiudad());
                formPrestamoRegister.getDireccion_txt().setText(persona.getDireccion());
                formPrestamoRegister.getEmail_txt().setText(persona.getEmail());
                formPrestamoRegister.getTelefono_txt().setText(String.valueOf(persona.getTelefono()));   
           }else{
               JOptionPane.showMessageDialog(null, "No se encontro registro");
           }
        }
        //Permite escoger la bicicleta para que el usuario haga uso de ella
        if(e.getSource().equals(formPrestamoRegister.getElegir_txt())){
           bicicleta.setCode(Integer.parseInt(formPrestamoRegister.getCod_txt_pre().getText()));
           if(vehicles.Buscar(bicicleta)){
                formPrestamoRegister.getAcopio_txt_pre().setText(bicicleta.getAcopio());
                formPrestamoRegister.getYear_txt_pre().setText(String.valueOf(bicicleta.getYear()));
                formPrestamoRegister.getColor_txt_pre().setText(bicicleta.getColor());
                formPrestamoRegister.getEstado_txt_pre().setText(bicicleta.getEstado());  
           }else{
               JOptionPane.showMessageDialog(null, "No se encontro registro");
           }
        }
        //Escuchador que permite registrar el prestamo de una bicicleta
        if(e.getSource().equals(formPrestamoRegister.getGenerar_prestamo_txt())){
            try{
                //Registro del usuario que presta la bicicleta
                String name, direccion, email, ciudad;
                int cedula, telefono;
                name = formPrestamoRegister.getName_txt().getText();
                cedula = Integer.parseInt(formPrestamoRegister.getCedula_txt().getText());
                direccion = formPrestamoRegister.getDireccion_txt().getText();
                telefono = Integer.parseInt(formPrestamoRegister.getTelefono_txt().getText());
                email = formPrestamoRegister.getEmail_txt().getText();
                ciudad = formPrestamoRegister.getCiudad_txt().getText();
                Persona usuario = new Persona(name,cedula,telefono,direccion,email,ciudad);

                //Registro de la bicicleta
                String acopio, color, estado;
                int codigo, year;
                
                acopio = formPrestamoRegister.getAcopio_txt_pre().getText();
                year = Integer.parseInt(formPrestamoRegister.getYear_txt_pre().getText());
                color = formPrestamoRegister.getColor_txt_pre().getText();
                codigo = Integer.parseInt(formPrestamoRegister.getCod_txt_pre().getText());
                estado = formPrestamoRegister.getEstado_txt_pre().getText();
                Bicycle bicycle = new Bicycle(codigo,acopio,year,color,estado);
                //Modificar el estado de la bicicleta de Disponible a ocupada
                vehicles.modificar(bicycle);
                //Aqui se llama a la operacion que reaiza el debido registro en la base de datos
                prestamo.Registrar(usuario, bicycle);
                
            }catch (NumberFormatException x) {
            JOptionPane.showMessageDialog(null, "Don't leave empty and unselected fields and use numbers for Force, Vision and EvilScale", "Error", JOptionPane.ERROR_MESSAGE);
            }catch (HeadlessException m) {
                JOptionPane.showMessageDialog(null, "Hay errores, revise sus números", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        //Permite mostrar usuarios de la base de datos
        if(e.getSource().equals(formUserView.getBuscar_txt())){
            startTable(formUserView.getTableConsult());           
        }
        //Permite mostrar las bicicletas de la base de datos
        if(e.getSource().equals(formBicycleView.getMostrar_txt())){
            //starTable2(formBicycleView.getjTable1());
        }
    }
    public void startTable2(JTable tableM){
        model = (DefaultTableModel)tableM.getModel();       
        ArrayList<Bicycle> bicicletas = vehicles.getBicicletas();
        for (Bicycle bicy : bicicletas) {
            model.addRow(new Object[] {bicy.getCode(),bicy.getAcopio(),bicy.getYear(),bicy.getColor(),bicy.getEstado()} );
        } 
    }    
    public void startTable(JTable tableH){
        model = (DefaultTableModel)tableH.getModel();       
        ArrayList<Persona> personas = users.getPersonas();
        for (Persona person : personas) {
            model.addRow(new Object[] {person.getName(),person.getCedula(),person.getTelefono(),person.getDireccion(),person.getEmail(),person.getCiudad()} );
        }   
    }

}
