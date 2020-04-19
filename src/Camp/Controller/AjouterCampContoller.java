/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Controller;

import Camp.Entity.Camp;
import Camp.Entity.User;
import Camp.Service.CampService;
import Camp.Service.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author ME
 */
public class AjouterCampContoller implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button ajouter;
    @FXML
    private TextField adresse;
    @FXML
    private TextField nbrefugier;
    @FXML
    private ChoiceBox<User> responsable;
    public ObservableList<User> data = FXCollections.observableArrayList();
    @FXML
    private Pane cancel;
    @FXML
    private AnchorPane indexPane;

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

   @Override
    public void initialize(URL url, ResourceBundle rb) {
       //To change body of generated methods, choose Tools | Templates.
        UserService ser = new UserService();
        data = ser.indexAction();
        responsable.setItems(data);
    }
    @FXML
     private void addCamp(ActionEvent event) throws IOException {
         
         User U = new User();
           U = responsable.getValue();
        Camp C = new Camp(nom.getText(), adresse.getText(), nbrefugier.getText(), U.getId());
        CampService ser = new CampService();
        ser.newAction(C);
          
        AnchorPane    myNewScene = null;
                   
        try {
          myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/Views/indexCamp.fxml"));
                    
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexRefugieController.class.getName()).log(Level.SEVERE, null, ex);
        }
             setNode(myNewScene);

        }

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        
         AnchorPane    myNewScene = null;
                   
        try {
          myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/Views/indexCamp.fxml"));
                    
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexRefugieController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
       
        
        
      
    }         


}
