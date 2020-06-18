/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Service;
import Don.Entity.CategorieEquipment;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import bd.DataBase;
import user.entity.User;

/**
 *
 * @author amin
 */
public class CategorieEquipmentService {

     Connection cx = bd.DataBase.getInstance1().getCnx();
    
    public void newAction(CategorieEquipment c)
    {   String req="insert into categorie_equipment (nomCategorie) values ('"+c.getNom()+"')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAction(int id){
        String req="delete from categorie_equipment where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateAction(CategorieEquipment c){
        
        String req= " UPDATE categorie_equipment SET nomCategorie='"+c.getNom()+"' where id= '"+c.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(CategorieEquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public List<String> indexAction()
    {
        List<String> ListeCategories = new ArrayList<>();
        String req= " select nomCategorie from categorie_produit ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.first())
                ListeCategories.add("nomCategorie");          
         }catch (SQLException ex) {
            Logger.getLogger(CategorieProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListeCategories;
    }
    */
    
     public ObservableList<CategorieEquipment> indexAction()
             
     {  
        ObservableList<CategorieEquipment> mylist=FXCollections.observableArrayList();
        String req= " select * from categorie_equipment ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    CategorieEquipment c = new CategorieEquipment();
                 c.setIdentifiant(resultat.getInt(1));
                c.setNom(resultat.getString(2));
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEquipmentService.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("rrrr");
        }
          return mylist;
     }
}


