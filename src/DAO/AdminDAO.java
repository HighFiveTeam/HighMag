/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import Connection.MyConnection;
import entites.Admin;
import gui.Rubriques;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author manel101
 */
public class AdminDAO {
        private Connection con = null;
    private Statement st;
    private ResultSet rs = null;
    PreparedStatement pst = null;
    
    Admin a=new Admin();
    
    
    public boolean Authentifier(String login, String password){
        
        boolean exist = false;
        try{
        String req="select Login,password from Admin where login=?and password=?";
        PreparedStatement pst = MyConnection.getInstance().prepareStatement(req);
        pst.setString(1, login);
        pst.setString(2, password);

            rs = pst.executeQuery();
            
            while (rs.next()) {
                a.setLogin(login);
                a.setPassword(password);
                exist = true;
                System.out.println("exist");
            }
        

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }     
       return exist;// TODO add your handling code here:
    }                                         

        
    
    public void Ajouter_Admin(){
 String req="insert into Admin values(0,?,?)";
    try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(req);
            ps.setString(1, a.getLogin());
            ps.setString(1, a.getPassword());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion "+ex.getMessage());
        }
    }
}
    
    
    


//            PreparedStatement pst = MyConnection.getInstance().prepareStatement("select Login,password from Admin where login='" +TLogin.getText() + "'and password='" + jPassword.getText() + "'");

