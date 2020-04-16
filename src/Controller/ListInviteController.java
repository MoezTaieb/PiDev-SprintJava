/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Invite;
import Service.InviteService;
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
public class ListInviteController implements Initializable {

    private TableView<Invite> table;
    TextField label ,label2;
    @FXML
    private TableColumn<Invite, Void> nomI;
    @FXML
    private TableColumn<Invite, Void> prenomI;
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
        
//        nomI.setCellValueFactory(new PropertyValueFactory<>("nomInvite"));
//        prenomI.setCellValueFactory(new PropertyValueFactory<>("prenomInvite"));
      
        System.out.println("aaaa"+eventId);
      
              
//        
//        
//     
//        InviteService is = new InviteService();
//        data = is.indexAction(eventId);
//        table.setItems(data);
//        System.out.println(data);
//        table.setEditable(true);
        
        
        
       // nomI.setCellFactory(TextFieldTableCell.forTableColumn());
        //prenomI.setCellFactory(TextFieldTableCell.forTableColumn());
    }  
    public void fct(String text)
    {
        label.setText(text);
        
    }
 
 
}
