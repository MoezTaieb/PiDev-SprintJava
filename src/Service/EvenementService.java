    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Evenement;
import Entity.Produit;
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
import pidev.DataBase;


/**
 *
 * @author Moez
 */
public class EvenementService {
    Connection cx = DataBase.getInstance().getCnx();
    
    public String nomProduit(int id){
        String nomCategorie = "";
        String req = "select nomProduit from produit where id ="+id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nomCategorie = resultat.getString(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nomCategorie;
    }
    
    public void newAction(Evenement e)
    {   String nomProduit = nomProduit(e.getProduit_id());
        String req = "insert into evenement (nomEvenement, lieuEvenement, dateEvenement, nombreMaxParticipant, image, id_produit, nomProduit) values ('"+e.getNomEvenement()+"','"+e.getLieuEvenement()+"','"+e.getDateEvenement()+"','"+e.getNbrMaxParticipants()+"','"+e.getImageEvenement()+"','"+e.getProduit_id()+"','"+nomProduit+"')"; 
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
           
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAction(int id){
        String req="delete from evenement where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAction(Evenement e){
            String req= " UPDATE evenement SET nomEvenement='"+e.getNomEvenement()+"',lieuEvenement='"+e.getLieuEvenement()+"',dateEvenement='"+e.getDateEvenement()+"',nombreMaxParticipant='"+e.getNbrMaxParticipants()+"',image='"+e.getImageEvenement()+"',id_produit='"+e.getProduit_id()+"',nomProduit='"+e.getNomProduit()+"' where id= '"+e.getId()+"' " ;

            Statement st;
            try {
                 st = cx.createStatement();
                 st.executeUpdate(req);
                        }   
            catch (SQLException ex) {
                Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
   
    public ObservableList<Evenement> indexAction()
             
     {  
        ObservableList<Evenement> mylist=FXCollections.observableArrayList();
        String req= " select * from evenement ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Evenement e = new Evenement();
                 e.setId(resultat.getInt(1));
                 e.setNomEvenement(resultat.getString(2));
                 e.setLieuEvenement(resultat.getString(3));
                 e.setDateEvenement(resultat.getString(4));
                 e.setNbrMaxParticipants(resultat.getInt(5));
                 e.setImageEvenement(resultat.getString(6));

                 mylist.add(e);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
    public ObservableList<Produit> indexActionProduit()
             
     {  
        ObservableList<Produit> mylist=FXCollections.observableArrayList();
        String req= " select id, nomProduit from produit order by nomProduit ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Produit p = new Produit();
                p.setId(resultat.getInt(1));
                
                
                 p.setNomProduit(resultat.getString(2));
                 
                 System.out.println(p);
                 mylist.add(p);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }

    public String nomImage(int id) throws SQLException{
          String req = " select image from evenement where id = '"+id+"' ";
     Statement st ;
     String x = "" ;
     st=cx.createStatement();
     ResultSet resultat;
      resultat = st.executeQuery(req);
     while (resultat.next())
     {
         x = resultat.getString(1);
         
     }
     return x;
    }


    

}