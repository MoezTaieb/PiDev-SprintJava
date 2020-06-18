/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Controller;


import static Evenement.Controller.ListeEventUserController.idEvent1;
import Evenement.Entity.Invite;
import Evenement.Service.InviteService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


/**
 * FXML Controller class
 *
 * @author Moez
 */
public class ListeInviteController implements Initializable {

    private TableView<Invite> tablee;
    TextField label ,label2;
    @FXML
    private TableColumn<Invite, String> nomI;
    @FXML
    private TableColumn<Invite, String> prenomI;
    public ObservableList<Invite> data = FXCollections.observableArrayList();
    
    private int eventId= 12;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


  

 

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nomI.setCellValueFactory(new PropertyValueFactory<>("nomInvite"));
        prenomI.setCellValueFactory(new PropertyValueFactory<>("prenomInvite"));
      
        System.out.println("aprés : "+idEvent1);
//      
//              
//        
        
     
        InviteService is = new InviteService();
        System.out.println("aaaa");
        data = is.indexAction(idEvent1);
        System.out.println("bbbb"+data);
   tablee.setItems(data);
//        System.out.println("resultat : "+data);
//        System.out.println(data);
       tablee.setEditable(true);
        
        
        
        nomI.setCellFactory(TextFieldTableCell.forTableColumn());
       prenomI.setCellFactory(TextFieldTableCell.forTableColumn());
    }  
    public void fct(String text)
    {
        label.setText(text);
        
    }
 
 
}
