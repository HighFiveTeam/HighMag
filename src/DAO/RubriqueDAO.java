/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;
import entites.Rubrique;
import Connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.plaf.OptionPaneUI;
/**
 *
 * @author manel101
 */
public class RubriqueDAO {
   // private Connection con;
    //private Statement st;
    
    
    
    //ajeut d'1 rubrique 
       public void Ajouter_Rubrique(Rubrique r){
        String req1, req2;
        req1 = "update rubrics set rubric_have_sub = 1 where rubric_id = ?";
        req2 = "insert into rubrics values (0,?,?,?,?,?)";
        try {
            
            if(r.getRubric_main_id() != -1){
            PreparedStatement pst1 = MyConnection.getInstance().prepareStatement(req1);
            pst1.setInt(1, r.getRubric_main_id());
            pst1.executeUpdate();
            }
            
            PreparedStatement pst2 = MyConnection.getInstance().prepareStatement(req2);
            pst2.setString(1, r.getRubric_title());
            if(r.getRubric_main_id() == -1){
                pst2.setInt(2, 1);
                pst2.setInt(3, 0);
                pst2.setInt(4, 0);
                pst2.setInt(5, 0);
            }
            else{
                pst2.setInt(2, 0);
                pst2.setInt(3, 0);
                pst2.setInt(4, 1);
                pst2.setInt(5, r.getRubric_main_id());
            }
            pst2.executeUpdate();
            System.out.println("ajout avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur ajout "+ex.getMessage());
        }
    }
       
       
       
       //rmplir liste 
        public DefaultListModel getTitles(){
        DefaultListModel titleslistModel = new DefaultListModel();
        titleslistModel.addElement("menu");
        String mainrubrique = "select * from rubrics where rubric_is_main = 1";
        String subrubrique = "select * from rubrics where rubric_is_sub = 1 and rubric_main_id = ?";
        
        try {
            PreparedStatement pst1 = MyConnection.getInstance().prepareStatement(mainrubrique);
            PreparedStatement pst2 = MyConnection.getInstance().prepareStatement(subrubrique);
            ResultSet r1 = pst1.executeQuery();
            while(r1.next()){
                titleslistModel.addElement("+"+r1.getString(2));
                if(r1.getInt(4) == 1){
                    pst2.setInt(1, r1.getInt(1));
                    ResultSet r2 = pst2.executeQuery();
                    while(r2.next()){
                        titleslistModel.addElement("   -"+r2.getString(2));
                    }
                }
            }
            return titleslistModel;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'extraction des rubriques "+ex.getMessage());
            return null;
        }   
    }
       
       
        
        // selection de l'id d'1 rubrique (caché)
        public DefaultListModel getIds(){
        DefaultListModel idslistModel = new DefaultListModel();
        idslistModel.addElement("-1");
        String mainrubrics = "select * from rubrics where rubric_is_main = 1";
        String subrubrics = "select * from rubrics where rubric_is_sub = 1 and rubric_main_id = ?";
        
        try {
            PreparedStatement pst1 = MyConnection.getInstance().prepareStatement(mainrubrics);
            PreparedStatement pst2 = MyConnection.getInstance().prepareStatement(subrubrics);
            ResultSet r1 = pst1.executeQuery();
            while(r1.next()){
                idslistModel.addElement(r1.getInt(1));
                if(r1.getInt(4) == 1){
                    pst2.setInt(1, r1.getInt(1));
                    ResultSet r2 = pst2.executeQuery();
                    while(r2.next()){
                        idslistModel.addElement(r2.getInt(1));
                    }
                }
            }
            return idslistModel;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'extraction des id des rubriques "+ex.getMessage());
            return null;
        }   
    }
        
        
        
        
       //suppression d'1 rubrigue
       public void Supprimer_Rubrique(Rubrique rubric){
        String req1 = "select count(*) from rubrics where rubric_main_id = ?";
        String req2 = "update rubrics set rubric_have_sub = 0 where rubric_id = ?";
        String req3 = "delete from rubrics where rubric_id = ? or rubric_main_id = ?";
        int count = 0;
        try {
            PreparedStatement pst1 = MyConnection.getInstance().prepareStatement(req1);
            pst1.setInt(1, rubric.getRubric_id());
            ResultSet resultat = pst1.executeQuery();
            while (resultat.next())
            {count = resultat.getInt(1);}
            if(count == 0){
            PreparedStatement pst2 = MyConnection.getInstance().prepareStatement(req2);
            pst2.setInt(1, rubric.getRubric_id());
            pst2.executeUpdate();
            }
            PreparedStatement pst3 = MyConnection.getInstance().prepareStatement(req3);
            pst3.setInt(1, rubric.getRubric_id());
            pst3.setInt(2, rubric.getRubric_id());
            pst3.executeUpdate();
            System.out.println("Rubrique supprimée avec succés");
        } catch (SQLException ex) {
            System.out.println("erreur supp "+ex.getMessage());
        }
    
       }
       
       
       //remplir le champ 
       public Rubrique List_rubrique_by_id(int id){
        Rubrique rubric = new Rubrique();
     String requete = "select * from rubrics where rubric_id = ?";
        try {
            PreparedStatement ps = MyConnection.getInstance().prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next())
            {
                rubric.setRubric_id(id);
                rubric.setRubric_title(resultat.getString(2));
                rubric.setRubric_is_main(resultat.getInt(3));
                rubric.setRubric_have_sub(resultat.getInt(4));
                rubric.setRubric_is_sub(resultat.getInt(5));
                rubric.setRubric_main_id(resultat.getInt(6));
            }
            return rubric;

        } catch (SQLException ex) {
            System.out.println("erreur lors l'extraction de la rubrique"+ex.getMessage());
            return null;
        }
    }
       
       /*
       public DefaultListModel Selectin_id(){
        DefaultListModel idslistModel = new DefaultListModel();
        idslistModel.addElement("-1");
        String mainrubrics = "select * from rubrics where rubric_is_main = 1";
        String subrubrics = "select * from rubrics where rubric_is_sub = 1 and rubric_main_id = ?";
        
        try {
            PreparedStatement ps1 = MyConnection.getInstance().prepareStatement(mainrubrics);
            PreparedStatement ps2 = MyConnection.getInstance().prepareStatement(subrubrics);
            ResultSet r1 = ps1.executeQuery();
            while(r1.next()){
                idslistModel.addElement(r1.getInt(1));
                if(r1.getInt(4) == 1){
                    ps2.setInt(1, r1.getInt(1));
                    ResultSet r2 = ps2.executeQuery();
                    while(r2.next()){
                        idslistModel.addElement(r2.getInt(1));
                    }
                }
            }
            return idslistModel;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'extraction des id des rubriques "+ex.getMessage());
            return null;
        }   
    }*/
       
       
       
       // modification rubrique
       
         public void update_rubric(Rubrique rubric){
        String requete1 = "update rubrics set rubric_title = ? where rubric_id = ?";
        try {
            PreparedStatement ps1 = MyConnection.getInstance().prepareStatement(requete1);
            ps1.setString(1, rubric.getRubric_title());
            ps1.setInt(2, rubric.getRubric_id());
            ps1.executeUpdate();
            System.out.println("Rubrique modifiée avec succé");
        } catch (SQLException ex) {
            System.out.println("erreur modif "+ex.getMessage());
        }
    }

   
    
}
