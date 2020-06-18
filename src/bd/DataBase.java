/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author fedi
 */
public class DataBase {
    //URL de connexion

    
    //private String url = "jdbc:mysql://localhost/pidev2020";
    private String url = "jdbc:mysql://localhost/pidevint";

    //Nom du user
    private String user = "root";
    //Mot de passe de l'utilisateur
    private String passwd = "";
    //Objet Connection
    private static Connection connect;
     static DataBase ds;

    //Constructeur privé
    private DataBase() {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
            System.err.println("succes");
        } catch (SQLException e) {
            System.err.println("echec");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("data base");
            alert.setContentText("Error in database");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
    public static Connection getInstance() {
        if (connect == null) {
            new DataBase();
        }
        return connect;
    }
    
      public static DataBase getInstance1(){
        if(ds==null)
        {
            ds= new DataBase();
        }
        
        return ds;
    }
     public Connection getCnx() {
        return connect;
    }
}
