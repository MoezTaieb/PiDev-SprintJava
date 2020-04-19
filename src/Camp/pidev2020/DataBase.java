/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.pidev2020;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Othmen
 */
public class DataBase {
    Connection cnx;
    String url="jdbc:mysql://127.0.0.1/pidev2020";
    String login="root";
    String pwd="";
    static DataBase ds;

    private DataBase() {
            try {
                cnx= DriverManager.getConnection(url,login, pwd);
                System.out.println("Connexion effectuée");
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public static DataBase getInstance(){
        if(ds==null)
        {
            ds= new DataBase();
        }
        
        return ds;
    }

    public Connection getCnx() {
        return cnx;
    }
}
