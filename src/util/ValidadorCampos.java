package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ValidadorCampos {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private String id_Cliente;
    private String name;
    private String phone;
    private String date_born;

    public ValidadorCampos(String id_Cliente, String name, String phone, String date_born) {
        this.id_Cliente = id_Cliente;
        this.name = name;
        this.phone = phone;
        this.date_born = date_born;
    }

    public String getId_Cliente() {
        return id_Cliente;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate_born() {
        return date_born;
    }
    
    public boolean validaCampo(){
        
        if(this.id_Cliente.equals("") || this.name.equals("") || this.phone.equals("") || this.date_born.equals("") || !phone.matches("[0-9]*") || name.matches("[0-9]*")){
            return false;
        }else{
            return true;
        }
        
    }
    
}
