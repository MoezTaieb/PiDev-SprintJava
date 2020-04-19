/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Service;

import Camp.Entity.Camp;
import Camp.Entity.Refugie;
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
import Camp.pidev2020.DataBase;

/**
 *
 * @author ME
 */
public class RefugieService {
    
     Connection cx = DataBase.getInstance().getCnx();
     
    public String nomCamp(int id){
        String nomCamp = "";
        String req = "select nomCamp from camp where id ="+id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nomCamp = resultat.getString(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(CampService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nomCamp;
    }
    
    public void newAction(Refugie r)
    {   String nomCamp = nomCamp(r.getCamp_id());
        String req = "insert into refugie (camp_id, nomRefugie, prenomRefugie, adresseRefugie,telRefugie,numassportRefugie,nationaliteRefugie,image,nomCamp) values ('"+r.getCamp_id()+"','"+r.getNomRefugie()+"','"+r.getPrenomRefugie()+"','"+r.getAdresseRefugie()+"','"+r.getTelRefugie()+"','"+r.getNumassportRefugie()+"','"+r.getNationaliteRefugie()+"','"+r.getImage()+"','"+nomCamp+"')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void deleteAction(int id){
        String req="delete from refugie where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void updateAction(Refugie r){
        String req= " UPDATE refugie SET camp_id='"+r.getCamp_id()+"',nomRefugie='"+r.getNomRefugie()+"',prenomRefugie='"+r.getPrenomRefugie()+"',adresseRefugie='"+r.getAdresseRefugie()+"',telRefugie='"+r.getTelRefugie()+"',numassportRefugie='"+r.getNumassportRefugie()+"',nationaliteRefugie='"+r.getNationaliteRefugie()+"',image='"+r.getImage()+"' where id= '"+r.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public ObservableList<Refugie> indexAction()
             
     {  
        ObservableList<Refugie> mylist=FXCollections.observableArrayList();
        String req= " select * from refugie ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Refugie r = new Refugie();
                 r.setId(resultat.getInt(1));
                 r.setCamp_id(resultat.getInt(2));
                 r.setNomRefugie(resultat.getString(3));
                 r.setPrenomRefugie(resultat.getString(4));
                 r.setAdresseRefugie(resultat.getString(5));
                 r.setTelRefugie(resultat.getString(6));
                 r.setNumassportRefugie(resultat.getString(7));
                 r.setNationaliteRefugie(resultat.getString(8));
                 r.setImage(resultat.getString(9));
                 r.setNomCamp(resultat.getString(10));
                 mylist.add(r);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
    public ObservableList<Refugie> retrieveByTagRefugier(String tag)
    { 
        ObservableList<Refugie> mylist=FXCollections.observableArrayList();
        String query="select * from refugie where(nomRefugie like '%"+tag+"%'or prenomRefugie like '%"+tag+"%'or adresseRefugie like '%"+tag+"%'or telRefugie like '%"+tag+"%'or numassportRefugie like '%"+tag+"%'or nationaliteRefugie like '%"+tag+"%'or image like '%"+tag+"%'or nomCamp like '%"+tag+"%')";
        Statement st;

        try{                          
            st=cx.createStatement();
            ResultSet resultat =  st.executeQuery(query);
        
         while(resultat.next())
            {    Refugie r = new Refugie();
                 r.setId(resultat.getInt(1));
                 r.setCamp_id(resultat.getInt(2));
                 r.setNomRefugie(resultat.getString(3));
                 r.setPrenomRefugie(resultat.getString(4));
                 r.setAdresseRefugie(resultat.getString(5));
                 r.setTelRefugie(resultat.getString(6));
                 r.setNumassportRefugie(resultat.getString(7));
                 r.setNationaliteRefugie(resultat.getString(8));
                 r.setImage(resultat.getString(9));
                 r.setNomCamp(resultat.getString(10));
                 mylist.add(r);
                    }
        
        } catch (SQLException ex) {
            
            System.out.println("Probleme de selection by tag"+ex);
        }
        
        return mylist;
    }
    
}
