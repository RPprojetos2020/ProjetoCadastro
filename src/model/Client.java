package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Client {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private String id_Cliente;
    private String name;
    private String phone;
    private Date born;

    public Client(String id_Cliente, String name, String phone, String born) throws ParseException {
        
        this.id_Cliente = id_Cliente;
        this.name = name;
        this.phone = phone;
        this.born = sdf.parse(born);
            
    }

    public String getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(String id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }
    
}
