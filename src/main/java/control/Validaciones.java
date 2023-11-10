/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author Geraldine Acevedo Restrepo
 */
public class Validaciones {
    //Nos sirve para saber cuando tenemos un campo correcto porque el color de fondo y borde es normal    
    public static void valText(JTextField txt) {
        txt.setBorder(BorderFactory.createLineBorder(Color.gray));
        txt.setBackground(Color.white);
    }
    //Sirve para verificar que no haya campos vacios
    public static boolean valRequerido(JTextField txt) {       
        boolean ok = true;
        if (txt.getText().trim().equals("")) {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok = false;
        }
        return ok;
    }
    //Verificacion de si tenemos un email y este cuenta con @  y . , entre otros
    public static boolean valEmail(JTextField txt) {
        boolean ok = true;
        Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(txt.getText().trim());
        if (!mat.find()) {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok = false;
        }
        return ok;
    }
    //Verificacion de el campo telefono, que cumpla el requisito de numeros minimos
    public static boolean valTelefono(JTextField txt) {
        boolean ok = true;
        if (!txt.getText().trim().matches("[0-9]{7,13}")) {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok = false;
        }
        return ok;
    }
    //Verificacion de la cedula, que cumpla con una cantidad minima
    public static boolean valCedula(JTextField txt) {
        boolean ok = true;
        if (!((txt.getText().trim().length() == 10) || (txt.getText().trim().length() == 13))) {
            txt.setBorder(BorderFactory.createLineBorder(Color.red));
            txt.setBackground(Color.pink);
            ok = false;
        }
        return ok;
    }
}
