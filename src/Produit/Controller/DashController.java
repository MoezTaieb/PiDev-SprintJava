/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.Controller;

 import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author danml
 */
public class DashController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        ft.setAutoReverse(true);
        ft.play();
    }

// @FXML
//    private void setProduit(ActionEvent event) {
//                            AnchorPane    myNewScene = null;
//                   
//        try {
//                myNewScene =  FXMLLoader.load(getClass().getResource("/Views/indexProduit.fxml"));
//                
// 
//         } catch (IOException ex) {
//            Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//             setNode(myNewScene);
//    }
    
    @FXML 
    private void setAd(ActionEvent event) { 
        
       
 
}
    
     @FXML
    private void setProfil(ActionEvent event) {
 
          

        
    }
    
 
    
    @FXML
    private void logout(ActionEvent event) {

      
    }

    @FXML
    private void setProduit(ActionEvent event) {
                            AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/indexProduit.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
    }

    @FXML
    private void setCat(ActionEvent event) {
        AnchorPane    myNewScene = null;     
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/indexCategorie.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(indexCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
    }
    
    

}
