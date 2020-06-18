/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Controller;

import Don.Service.EquipmentService;
 import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 *
 * @author danml
 */
public class DashController implements Initializable {

    @FXML
    private AnchorPane holderPane;
               @FXML
    private PieChart PieChart;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        
  EquipmentService e =new EquipmentService();
   //  for(int i=0;i<table.getItems().size()
     //  ;i++)
     //{
//     }}
        ObservableList<PieChart.Data> piechartdata = e.pie();
                 
                   PieChart.setData(piechartdata);
                 EquipmentService s=new EquipmentService();
                  
                   String nb="";
                    int test =  s.notifa() ;
                   String title = "notification" ;
                   if (test==0)
              // String message = "Nom : "+c.getNomProduit()+" / Prix : "+P.getPrixProduit()+" / Quantité : "+P.getQteProduit();
                  System.out.println("rien a afficher");
               else
                   {String message="vouz avez "+test+" nouveau equipment a gerer leur etat" ;
  
                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndDismiss(Duration.seconds(5));
                   }
                        
  
        
    }

    //Set selected node to a content holder
    public void setNode(Node node) {
        
        
        
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
    private void catServie(ActionEvent event) { 
        
         
                              AnchorPane    myNewScene = null;
                   
        try {
                myNewScene = FXMLLoader.load(getClass().getResource("/Don/Views/indexCatService.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexCatServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(myNewScene);
 
}
    
     @FXML
    private void catEquipment(ActionEvent event) {
 
                              AnchorPane    myNewScene = null;
                   
        try {
            myNewScene = FXMLLoader.load(getClass().getResource("/Don/Views/indexcatEquipment.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexCatEquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(myNewScene);
          

        
    }
    @FXML
    private void Service(ActionEvent event) {
 
                               AnchorPane    myNewScene = null;
                   
        try {
                myNewScene = FXMLLoader.load(getClass().getResource("/Don/Views/indexService.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(myNewScene);
          

           

        
    }
    @FXML
    private void Equipment(ActionEvent event) {
 
          
            AnchorPane    myNewScene = null;
                   
        try {
                myNewScene = FXMLLoader.load(getClass().getResource("/Don/Views/indexEquipment.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexEquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(myNewScene);
          
        
    }
    
    
      @FXML
    private void traiter(ActionEvent event) {
 
          
            AnchorPane    myNewScene = null;
                   
        try {
                myNewScene = FXMLLoader.load(getClass().getResource("/Don/Views/traiter.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexEquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(myNewScene);
          
        
    }
 
    
    @FXML
    private void logout(ActionEvent event) {

       
    }

}
