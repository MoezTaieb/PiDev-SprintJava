/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.Service;

import Produit.Entity.CategorieProduit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import bd.DataBase;
import user.entity.User;
/**
 *
 * @author Othmen
 */
public class CategorieProduitService {
    
    Connection cx = DataBase.getInstance1().getCnx();
    
    public void newAction(CategorieProduit c)
    {   String req="insert into categorie_produit (nomCategorie) values ('"+c.getNomCategorie()+"')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAction(int id){
        String req="delete from categorie_produit where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAction(CategorieProduit c){
        String req= " UPDATE categorie_produit SET nomCategorie='"+c.getNomCategorie()+"' where id= '"+c.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(CategorieProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<CategorieProduit> indexAction()
             
     {  
        ObservableList<CategorieProduit> mylist=FXCollections.observableArrayList();
        String req= " select * from categorie_produit order by nomCategorie ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    CategorieProduit c = new CategorieProduit();
                 c.setId(resultat.getInt(1));
                 c.setNomCategorie(resultat.getString(2));
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
}
