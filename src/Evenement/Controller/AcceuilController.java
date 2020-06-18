/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button lE;
    @FXML
    private Button aE;
    @FXML
    private Button aI;
    @FXML
    private Button listIn;
    @FXML
    private Button us;

   @FXML
   private AnchorPane acc;

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


//
   @FXML
//    private void eventButton(ActionEvent event) {
//        
        
        
         private void lstE(ActionEvent event) throws IOException {
        
          AnchorPane    myNewScene = null;
          
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/ListEvenement.fxml"));
                
 
         } catch (IOException ex) {
           
        }

           setNode(myNewScene);

        
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

//    @FXML
//    private void lstE(ActionEvent event) throws IOException {
//        
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ListEvenement.fxml"));
//        Parent root = loader.load();
//        lE.getScene().setRoot(root);
//    }

    @FXML
    private void ajE(ActionEvent event) throws IOException {
        
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ajoutEvenement.fxml"));
//        Parent root = loader.load();
//        aE.getScene().setRoot(root);
          AnchorPane    myNewScene = null;
          
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/ajoutEvenement.fxml"));
                
 
         } catch (IOException ex) {
           
        }

           setNode(myNewScene);

    }

    @FXML
    private void addInvite(ActionEvent event) throws IOException {
    
       //  FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ajoutInvite.fxml"));
        // Parent root = loader.load();
         //aI.getScene().setRoot(root);
         AnchorPane    myNewScene = null;
          
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/ajoutInvite.fxml"));
                
 
         } catch (IOException ex) {
           
        }

             setNode(myNewScene);
         //
    }     

    @FXML
    private void listI(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/ListInvite.fxml"));
         Parent root = loader.load();
         listIn.getScene().setRoot(root);
    }

    @FXML
    private void user(ActionEvent event)  throws IOException  {
    
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/listeEventUser.fxml"));
//         Parent root = loader.load();
//         us.getScene().setRoot(root);
         
         AnchorPane    myNewScene = null;
          
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/listeEventUser.fxml"));
                
 
         } catch (IOException ex) {
           
        }

             setNode(myNewScene);
         }
}
    
