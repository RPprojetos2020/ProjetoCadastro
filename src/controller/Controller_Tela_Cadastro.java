package controller;

import Conection.ClientDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Client;
import model.List_Client;
import util.ValidadorCampos;
import views.Tela_Cadastro;

public class Controller_Tela_Cadastro{
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private Tela_Cadastro tc;
    public ArrayList <Client> list = new ArrayList();
    private ClientDB cdb = new ClientDB();
    
    public Controller_Tela_Cadastro(Tela_Cadastro tc) {
        
        this.tc = tc;
        
    }
    
    private boolean validator(String id_client, String name, String phone, String born){
       //metodo 
       ValidadorCampos vc = new ValidadorCampos(id_client, name, phone, born);
       
       if(vc.validaCampo()){
           
            return true;
            
        }else{
            
            return false;
            
        }
       
    }
    
    public void addClient(String id_client, String name, String phone, String born) {
        
        //metodo responsavel por validar os campos, adicionar o cliente ao banco e atualizar a lista na JTabel tambem por controlar outros parametros
        
        if(validator(id_client, name, phone, born)){
    
            try {
                
                cdb.salvar(id_client, name, phone, born);
                Client client = new Client(id_client, name, phone, born);
                list.add(client);
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
                
                tc.cleanCamps();
                
            } catch (ParseException ex) {
                
                JOptionPane.showMessageDialog(null, "Campo data invalido. Digite Novamente. ex: dd/MM/yyyy");
                tc.cleanData();
            }
            
        }else{
            
            JOptionPane.showMessageDialog(null, "Cadastro não relizado. Verifique se os campos foram preenchidos corretamente!!!");
            
        }
        
    }
    
    public void deletClient(JTable tabela){
        //metodo responsavel por questionar o usuario se confirma a exclusão, realizar a exclusão no banco e também por limpar os campos da tela
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        
        if(model.getRowCount() == 0){
            
            JOptionPane.showMessageDialog(null, "Faça uma consulta de cliente para realizar uma exclusão");
            
        }else{
        
            int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar este cliente?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if(i == JOptionPane.YES_OPTION){
            
                String idSelecionado = list.get(0).getId_Cliente();

                cdb.delet(idSelecionado);
                
                tc.cleanCamps();

                model.setNumRows(0);
                list.clear();
                
                JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!!!");
                
            }
        }
        
    }
    
    public void atualizar(JTable tabela){
        //Metodo responsavel por atualizar os dados na tabela
        
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        
        model.setNumRows(0);
        
        for(int i = 0; i<list.size(); i++){
            model.addRow(new Object[]{list.get(i).getId_Cliente(),
                                      list.get(i).getName(),
                                      list.get(i).getPhone(),
                                      sdf.format(list.get(i).getBorn())});
         }
    }
    
    public void consulta(JTextField jTId, JTextField jTName, JTextField jTPhone, JTextField jTBorn, JTable table){
        
        //Metodo responsavel por realizar as consultas no banco de dados e atualizar a tabela
        
        String id = JOptionPane.showInputDialog("Entre com o ID que deseja consultar: ");
        
        if(id.equals("") || id == null){
            
             JOptionPane.showMessageDialog(null, "Para consultar Digite um ID");
             id = JOptionPane.showInputDialog("Entre com o ID que deseja consultar: ");
             
        }
        
        try{
            
            Client c = cdb.consult(id);
            list.clear();
            list.add(c);
            
            atualizar(table);

            jTId.setText(c.getId_Cliente());
            jTName.setText(c.getName());
            jTPhone.setText(c.getPhone());
            jTBorn.setText(sdf.format(c.getBorn()));
            
        }catch(NullPointerException ex){
            
            JOptionPane.showMessageDialog(null, "Este ID não esta registrado no sistema!!!");
            
        }

        
        
    }
    
    public void atualizarDadosBanco(JTextField jTId, JTextField jTName, JTextField jTPhone, JTextField jTBorn, JTable tabela){
        //metodo responsavel por fazer as validações e tambem as modificações no banco de dados;
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        
        String id_atual = list.get(0).getId_Cliente();
        
        if(model.getRowCount() == 0){
            
            JOptionPane.showMessageDialog(null, "Realize a pesquisa do cliente que deseja atualizar");
            
        }else{
            
            String id_novo= jTId.getText();
            String name = jTName.getText();
            String phone = jTPhone.getText();
            String data = jTBorn.getText();
            
            cdb.atualization( id_atual, id_novo, name, phone, data);
            
            jTId.setText("");
            jTName.setText("");
            jTPhone.setText("");
            jTBorn.setText("");
            
            list.clear();
            model.setNumRows(0);
            
            JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!!!");
            
        }
        
    }
}
