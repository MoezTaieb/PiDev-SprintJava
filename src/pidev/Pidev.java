/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entity.Evenement;
import Entity.Invite;
import Entity.Participation;
import Service.EvenementService;
import Service.InviteService;
import Service.ParticipationService;
import java.awt.BorderLayout;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Moez
 */
public class Pidev extends Application {
        
    public static Stage stage = null;
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane  root = FXMLLoader.load(getClass().getResource("/Views/dash.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("MOEZ");
        stage.setScene(scene);
       
        this.stage = stage;
        stage.show();
    }

    /**11
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        launch(args);
        
       /** Evenement e = new Evenement("wathek","tbolba","2019-01-04",25,"sdfhsldflsdflsdjflsdjflj");
        EvenementService es = new EvenementService();
        es.newAction(e);
        String x=e.toString();
        System.out.println(x);**/
        
       
      EvenementService es = new EvenementService();
      String x ;
      x = es.nomImage(70);
        System.out.println(x);
       
      
      
    }
    
}
