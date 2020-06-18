/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Controller;

import static Evenement.Controller.ListEvenementController.idEvent1;
import Evenement.Entity.Invite;
import Evenement.Service.InviteService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Moez
 */




public class AjoutInviteController implements Initializable {

    @FXML
    private TextField nomI;
    @FXML
    private TextField prenomI;
    @FXML
    private Button ajoutI;
    private TextField evenementId;
    
    private int  eventId ;

  
    @FXML    
   private AnchorPane acc;
    
    
    
    
 @FXML  
   private void setNode(Node node) {
        
        
        
        acc.getChildren().clear();
        acc.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addInvite(ActionEvent event) throws IOException {
        
        System.out.println("aprés id ="+idEvent1);
        Invite I = new Invite(idEvent1, nomI.getText(), prenomI.getText());
        InviteService is = new InviteService();
        is.newAction(I);
        String y = I.toString();
        System.out.println(y);
       // FXMLLoader loader = new FXMLLoader(getClass().getResource("Evenement/Views/acceuil.fxml"));
       // Parent root = loader.load();
       // nomI.getScene().setRoot(root);
         AnchorPane    myNewScene = null;

                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/acceuil.fxml"));
                    
                   
                System.out.println("bonjour fedi");

 
         } catch (IOException ex) {
           
        }

             setNode(myNewScene);
        
        
    }
}
