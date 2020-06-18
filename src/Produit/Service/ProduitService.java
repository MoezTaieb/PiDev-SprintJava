/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.Service;

import Produit.Entity.CategorieProduit;
import Produit.Entity.Produit;
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
 * @author Othmen
 */
public class ProduitService {
    Connection cx = DataBase.getInstance1().getCnx();
    
    public String nomCategorie(int id){
        String nomCategorie = "";
        String req = "select nomCategorie from categorie_produit where id ="+id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nomCategorie = resultat.getString(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nomCategorie;
    }
    
    public void newAction(Produit p)
    {   
        String nomCategorie = nomCategorie(p.getCategorie_id());
        String req = "insert into produit (nomProduit, prixProduit, qteProduit, descriptionProduit, nomCategorie, categorie_id) values ('"+p.getNomProduit()+"','"+p.getPrixProduit()+"','"+p.getQteProduit()+"','"+p.getDescriptionProduit()+"','"+nomCategorie+"','"+p.getCategorie_id()+"')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void deleteAction(int id){
        String req="delete from produit where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void updateAction(Produit p){
        String nomCategorie = nomCategorie(p.getCategorie_id());
        float prix = Float.parseFloat(p.getPrixProduit());
        int qte = Integer.parseInt(p.getQteProduit());
        
        String req= " UPDATE produit SET categorie_id='"+p.getCategorie_id()+"',nomProduit='"+p.getNomProduit()+"',prixProduit='"+prix+"',qteProduit='"+qte+"',descriptionProduit='"+p.getDescriptionProduit()+"',nomCategorie='"+nomCategorie+"' where id= '"+p.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public ObservableList<Produit> indexAction()
             
     {  
        ObservableList<Produit> mylist=FXCollections.observableArrayList();
        String req= " select * from produit order by nomProduit ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Produit p = new Produit();
                 p.setId(resultat.getInt(1));
                 p.setCategorie_id(resultat.getInt(2));
                 p.setNomProduit(resultat.getString(3));
                 Float prix = resultat.getFloat(4);
                 p.setPrixProduit(prix.toString());
                 Integer qte = resultat.getInt(5);
                 p.setQteProduit(qte.toString());
                 p.setDescriptionProduit(resultat.getString(6));
                 p.setNomCategorie(resultat.getString(7));
                 System.out.println(p);
                 mylist.add(p);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
    
    public ObservableList<Produit> filterAction(int id)
             
     {
        ObservableList<Produit> mylist=FXCollections.observableArrayList();
        String req= " select * from produit where categorie_id = "+id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Produit p = new Produit();
                 p.setId(resultat.getInt(1));
                 p.setCategorie_id(resultat.getInt(2));
                 p.setNomProduit(resultat.getString(3));
                 Float prix = resultat.getFloat(4);
                 p.setPrixProduit(prix.toString());
                 Integer qte = resultat.getInt(5);
                 p.setQteProduit(qte.toString());
                 p.setDescriptionProduit(resultat.getString(6));
                 p.setNomCategorie(resultat.getString(7));
                 System.out.println(p);
                 mylist.add(p);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
    public ObservableList<Produit> searchNameAction(String nomP)
             
     {  
        ObservableList<Produit> mylist=FXCollections.observableArrayList();
        String req= " SELECT * FROM produit WHERE '"+nomP+"' IN (SELECT nomProduit FROM produit) ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Produit p = new Produit();
                 p.setId(resultat.getInt(1));
                 p.setCategorie_id(resultat.getInt(2));
                 p.setNomProduit(resultat.getString(3));
                 Float prix = resultat.getFloat(4);
                 p.setPrixProduit(prix.toString());
                 Integer qte = resultat.getInt(5);
                 p.setQteProduit(qte.toString());
                 p.setDescriptionProduit(resultat.getString(6));
                 p.setNomCategorie(resultat.getString(7));
                 System.out.println(p);
                 mylist.add(p);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
}
