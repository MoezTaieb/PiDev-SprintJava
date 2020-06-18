/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.Controller;

import Produit.Entity.CategorieProduit;
import Produit.Service.CategorieProduitService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Othmen
 */
public class AjouterCategorieProduitController implements Initializable {

    @FXML
    private AnchorPane indexPane;
    @FXML
    private TextField nom;
    @FXML
    private Label verifNom;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void setNode(Node node) {
        indexPane.getChildren().clear();
        indexPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void addCategorie(ActionEvent event) throws IOException {
        if(nom.getText().isEmpty() || nom.getText().length() > 20){
            verifNom.setText("Nom invalide.");
        }
        else{
            CategorieProduit C = new CategorieProduit(nom.getText());
            CategorieProduitService ser = new CategorieProduitService();
            ser.newAction(C);
            nom.clear();
            verifNom.setText("");
            
            String title = "Nouvelle Catégorie vide ajoutée";
            String message = "Nom : "+C.getNomCategorie();
        

            TrayNotification tray = new TrayNotification();
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(5));
        }
    }

    @FXML
    private void retour(ActionEvent event) {
                             AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/indexCategorie.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
    }



    

    
    
}
