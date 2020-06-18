/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Controller;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author ME
 */
public class dashController implements Initializable{

    @FXML
    private Button affichageC;
    @FXML
    private Button affichageR;
    @FXML
    private AnchorPane holderPane;

    

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        AnchorPane    myNewScene = null;           
        try {
                myNewScene = FXMLLoader.load(getClass().getResource("/Camp/views/AjouterCamp.fxml"));
                
         } catch (IOException ex) {
        }

             setNode(myNewScene);

        
    }

    //Set selected node to a content holder
    private void setNode(Node node) {
        
        
        
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }


    
    @FXML 
    private void indexCamp(ActionEvent event) { 
        
       
        AnchorPane    myNewScene = null;
                   
        try {
                myNewScene = FXMLLoader.load(getClass().getResource("/Camp/views/indexCamp.fxml"));
                
 
         } catch (IOException ex) {
        }

     setNode(myNewScene);
 
}
    
    private void AjouterCamp(ActionEvent event) {
 
                              AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/user/views/profil.fxml"));
                
 
         } catch (IOException ex) {
        }

             setNode(myNewScene);

        
    }
   
    @FXML 
    private void indexRefugie(ActionEvent event) { 
        
       
        AnchorPane    myNewScene = null;
                   
        try {
                myNewScene = FXMLLoader.load(getClass().getResource("/Camp/views/indexRefugie.fxml"));
                
 
         } catch (IOException ex) {
        }

     setNode(myNewScene);
 
}
    
    private void AjouterRefugie(ActionEvent event) {
 
                              AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/user/views/AjouterRefugie.fxml"));
                
 
         } catch (IOException ex) {
        }

             setNode(myNewScene);

        
    }
    
    }
    
    

