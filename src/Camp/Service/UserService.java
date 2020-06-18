/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Service;

import Camp.Entity.User;
import bd.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author ME
 */
public class UserService {
        Connection cx = DataBase.getInstance1().getCnx();
        
         public ObservableList<User> indexAction()
             
     {  
        ObservableList<User> mylist=FXCollections.observableArrayList();
        String req= " select id,username from fos_user ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    User u = new User();
                 u.setId(resultat.getInt(1));
                 u.setUsername(resultat.getString(2));
                
                 mylist.add(u);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(CampService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }

    
}
