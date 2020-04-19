/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Service;

import Camp.Entity.Camp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Camp.pidev2020.DataBase;

/**
 *
 * @author ME
 */
public class CampService {
    
    Connection cx = DataBase.getInstance().getCnx();
     public String nomuser(int id){
        String nomuser = "";
        String req = "select username from fos_user where id ="+id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nomuser = resultat.getString(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(CampService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nomuser;
    }
    public void newAction(Camp c)
    {   String nomuser = nomuser(c.getResponsable_id());
        String req = "insert into camp (nomCamp, adresseCamp, nbrefugier,nomuser, responsable_id) values ('"+c.getNomCamp()+"','"+c.getAdresseCamp()+"','"+c.getNbrefugier()+"','"+nomuser+"','"+c.getResponsable_id()+"')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CampService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void deleteAction(int id){
        String req="delete from camp where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CampService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void updateAction(Camp c){
         int nbrrefugie = Integer.parseInt(c.getNbrefugier());
        String req= " UPDATE camp SET nomCamp='"+c.getNomCamp()+"',adresseCamp='"+c.getAdresseCamp()+"',nbrefugier='"+nbrrefugie+"' where id= '"+c.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(CampService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public ObservableList<Camp> indexAction()
             
     {  
        ObservableList<Camp> mylist=FXCollections.observableArrayList();
        String req= " select * from camp ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Camp c = new Camp();
                 c.setId(resultat.getInt(1));
                 c.setResponsable_id(resultat.getInt(2));
                 c.setNomCamp(resultat.getString(3));
                 c.setAdresseCamp(resultat.getString(4));
                 c.setNbrefugier(resultat.getString(5));
                 c.setNomuser(resultat.getString(6));
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(CampService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
      public ObservableList<Camp> retrieveByTagRefugier(String tag)
    { 
        ObservableList<Camp> mylist=FXCollections.observableArrayList();
        String query="select * from camp where(nomCamp like '%"+tag+"%'or adresseCamp like '%"+tag+"%'or nbrefugier like '%"+tag+"%'or nomuser like '%"+tag+"%')";
        Statement st;

        try{                          
            st=cx.createStatement();
            ResultSet resultat =  st.executeQuery(query);
        
         while(resultat.next())
            {    Camp c = new Camp();
                c.setId(resultat.getInt(1));
                 c.setResponsable_id(resultat.getInt(2));
                 c.setNomCamp(resultat.getString(3));
                 c.setAdresseCamp(resultat.getString(4));
                 c.setNbrefugier(resultat.getString(5));
                 c.setNomuser(resultat.getString(6));
                 mylist.add(c);
                    }
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de selection by tag"+ex);
        }
        
        return mylist;
    }
    
}
