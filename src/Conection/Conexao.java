package Conection;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;


public class Conexao {

    
    public static Connection getConexao(){
        
        String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=CADASTRO_CLIENTE";
        String user = "sa";
        String password = "menemosine27";
        
        try {
            
             //Class.forName(drive);
             return DriverManager.getConnection(url, user, password);
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao banco de dados");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
    public static void close(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "A conexão com o banco de dados não pode ser fechada");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void close(Connection connection, Statement stm){
        close(connection);
        try {
            if (stm != null){
                stm.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void close(Connection connection, Statement stm, ResultSet rs){
        close(connection, stm);
        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
