/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Service;


import Evenement.Entity.Evenement;
import Evenement.Entity.Participation;
import bd.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.PiDev;

/**
 *
 * @author Moez
 */
public class ParticipationService {
     Connection cx = DataBase.getInstance1().getCnx();
    
    public void newAction(Participation p)
    {    int a =PiDev.currentUser.getid();
        String req = "insert into participation (participant_id, dateParticipation, evenement_id, nomE, lieuE, dateE) "
            + "values ('"
                                    +a+"','"+p.getDateParticipation()+"','"
                             +p.getIdE()+"','"+p.getNomEvent()+"','"+p.getLieuEvent()+"','"+p.getDateEvent()+"')"; 
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAction(int id){
        String req="delete from participation where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public ObservableList<Participation> indexAction(int idU)
             
     {   int a =PiDev.currentUser.getid();
        ObservableList<Participation> mylist=FXCollections.observableArrayList();
        String req= " select * from participation where participant_id = '"+a+"'";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Participation e = new Participation();
                 e.setId(resultat.getInt(1));
                 e.setIdU(resultat.getInt(2));
           
                 e.setDateParticipation(resultat.getString(3));
               e.setIdE(resultat.getInt(4));
               e.setNomEvent(resultat.getString(5));
                 e.setLieuEvent(resultat.getString(6));
                   e.setDateEvent(resultat.getString(7));

                 mylist.add(e);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
     
     
 public int nbrPartAction(int id ) throws SQLException
             
     {  
      
        String req= " select count(*) As count from participation where evenement_id = '"+id+"' ";
        Statement st;
        int c= 0 ;
            st=cx.createStatement();
            ResultSet resultat;
             resultat = st.executeQuery(req);
             while (resultat.next())
             {
                  c = resultat.getInt("count");
             }
           
            
       
          return c;
                  }

 
 public int nbrMaxParticipant(int id) throws SQLException {
     
     
     String req = " select nombreMaxParticipant from evenement where id = '"+id+"' ";
     Statement st ;
     int x = 0 ;
     st=cx.createStatement();
     ResultSet resultat;
      resultat = st.executeQuery(req);
     while (resultat.next())
     {
         x = resultat.getInt(1);
         
     }
     return x;
     
 }
 
 public int Participant(int id) throws SQLException {
      int a =PiDev.currentUser.getid();
     
     String req = " select count(*) As count from participation where evenement_id = '"+id+"' and participant_id ='"+a+"' ";
     Statement st ;
     int x = 0 ;
     st=cx.createStatement();
     ResultSet resultat;
      resultat = st.executeQuery(req);
     while (resultat.next())
     {
         x = resultat.getInt("count");
         
     }
     return x;
     
 }
    
    
    
}
