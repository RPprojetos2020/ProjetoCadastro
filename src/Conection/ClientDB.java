package Conection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Client;

public class ClientDB {
    
    public void salvar(String id, String name, String phone, String date){
        
        String sql = "INSERT INTO CLIENTE(ID_CLIENTE, NOME, TELEFONE, DATA_NASC) VALUES ('"+ id +"', '" + name +"', '" + phone + "', '" + date + "')";
        Connection conn = Conexao.getConexao();
        
        try {
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            Conexao.close(conn, stm);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    public void delet(String id){
        
        String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = '" + id + "'";
        Connection conn = Conexao.getConexao();
        
        try {
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            Conexao.close(conn, stm);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    public void atualization(String id, String id_novo, String name, String phone, String data){
        
        String sql = "UPDATE CLIENTE SET ID_CLIENTE = '" + id_novo + "', NOME = '" + name + "', TELEFONE = '" + phone + "', DATA_NASC = '" + data + "' WHERE ID_CLIENTE = " + id + "";
        Connection conn = Conexao.getConexao();
        
        try {
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            Conexao.close(conn, stm);
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Client consult(String id){
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        String sql = "SELECT ID_CLIENTE, NOME, TELEFONE, DATA_NASC FROM CLIENTE WHERE ID_CLIENTE = " + id;
        Connection conn = Conexao.getConexao();
        
        try {
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            if(rs.equals(null)){
                
                JOptionPane.showMessageDialog(null, "Este ID não existe em registro");
                
            }else{
            
                while(rs.next()){

                    String id_client = rs.getString(1);
                    String name = rs.getString(2);
                    String phone = rs.getString(3);
                    String born = sdf.format(rs.getDate(4));

                    try {

                        Client c = new Client(id_client, name, phone, born);
                        Conexao.close(conn, stm, rs);
                        return c;

                    } catch (ParseException ex) {

                        Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);

                    } catch(NullPointerException ex){
                        JOptionPane.showMessageDialog(null, "Este ID não existe em registro!!!");
                    }

                }    
            }
            Conexao.close(conn, stm);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Este ID não existe em registro!!!");
            Logger.getLogger(ClientDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Este ID não existe em registro!!!");
        }
        
        return null;
        
    }
}
