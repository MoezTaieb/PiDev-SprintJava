/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import Don.Service.EquipmentService;
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
import pidev.PiDev;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author danml
 */
public class DashController implements Initializable {

    @FXML
    private AnchorPane holderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int i=0;
         EquipmentService s=new EquipmentService();
                   s.notif();
                   String nb="";
                    String test = "notification";
                   String title = s.notif(); ;
                   if (title.equals(nb))
              // String message = "Nom : "+c.getNomProduit()+" / Prix : "+P.getPrixProduit()+" / Quantité : "+P.getQteProduit();
                  System.out.println("rien a afficher");
               else
                   {String message="vouz avez ajouter un equipment qui n est pas encore recu veuillez traiter cette notification "+test ;

                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.seconds(5));
                   }
                        
        
        
        AnchorPane myNewScene = null;
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/profil.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void setAd(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/ad.fxml"));
            
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);

    }

    @FXML
    private void setProfil(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("../user/views/profil.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);

    }

    @FXML
    private void setMessage(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("../user/views/mes.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);
    }
 @FXML
    private void AddEquipment(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("../Don/Views/AjoutEquipment.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);
    }
    
    @FXML
    private void AddService(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("../Don/Views/AjoutService.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);
    }

    //
   @FXML
 
        
         private void lstE(ActionEvent event) throws IOException {
        
          AnchorPane    myNewScene = null;
          
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/ListeEventUser.fxml"));
                
 
         } catch (IOException ex) {
           
        }

           setNode(myNewScene);

        
        
    }
    
    
       
  
    
    
    
    
    
    
    
    
    
    @FXML
    private void logout(ActionEvent event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        try {
            PiDev.currentUser = null;
            Parent myNewScene = FXMLLoader.load(getClass().getResource("../user/views/login.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

}
