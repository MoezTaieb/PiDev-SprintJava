/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Invite;
import Service.InviteService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public TextField getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(TextField evenementId) {
        this.evenementId = evenementId;
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
        
        System.out.println("x ="+eventId);
        Invite I = new Invite(eventId, nomI.getText(), prenomI.getText());
        InviteService is = new InviteService();
        is.newAction(I);
        String y = I.toString();
        System.out.println(y);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/acceuil.fxml"));
        Parent root = loader.load();
        nomI.getScene().setRoot(root);
        
        
    }
}
