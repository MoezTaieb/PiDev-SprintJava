/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entity.Invite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.DataBase;

/**
 *
 * @author Moez
 */
public class InviteService {
     Connection cx = DataBase.getInstance().getCnx();
      public void newAction(Invite i)
    {   String req = "INSERT INTO invite (evenement_id, nomInvite, prenomInvite) VALUES ('"+i.getIdEvenement()+"','"+i.getNomInvite()+"','"+i.getPrenomInvite()+"')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      
       public void deleteAction(int id){
        String req="delete from invite where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
       public void updateAction(Invite i){
        String req= " UPDATE invite SET nomInvite='"+i.getNomInvite()+"',prenomInvite='"+i.getPrenomInvite()+"',evenement_id='"+i.getIdEvenement()+"' where id= '"+i.getId()+"' " ;

        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
        public ObservableList<Invite> indexAction(int idE)
             
     {  
       
        ObservableList<Invite> mylist=FXCollections.observableArrayList();
        String req= " select * from invite where evenement_id = '"+idE+"'" ;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Invite i = new Invite();
                 i.setId(resultat.getInt(1));
                 i.setIdEvenement(resultat.getInt(2));
                 i.setNomInvite(resultat.getString(3));
                 i.setPrenomInvite(resultat.getString(4));
                

                 mylist.add(i);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(Invite.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
}
