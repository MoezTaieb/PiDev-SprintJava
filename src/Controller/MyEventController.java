/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Evenement;
import Entity.Participation;
import Service.EvenementService;
import Service.ParticipationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class MyEventController implements Initializable {

    @FXML
    private TableView<Participation> table;
    @FXML
    private TableColumn<Participation, String> nomE;
    @FXML
    private TableColumn<Participation, String> lieuE;
    @FXML
    private TableColumn<Participation, String> dateE;
    @FXML
    private TableColumn<Participation, Void> participer;
     public ObservableList<Participation> ob = FXCollections.observableArrayList();
    
    
    private int idU=1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
                nomE.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        lieuE.setCellValueFactory(new PropertyValueFactory<>("lieuEvent"));
        dateE.setCellValueFactory(new PropertyValueFactory<>("dateParticipation"));
        participer.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
     
        
        ParticipationService ps = new ParticipationService();
        ObservableList<Participation> data = ps.indexAction(idU);
        
        
        table.setItems(data);
        System.out.println(data);
        
                //suppButton();

      
        
      
        
       
        
        table.setEditable(true);
        
        
        
//        nomE.setCellFactory(TextFieldTableCell.forTableColumn());
//        lieuE.setCellFactory(TextFieldTableCell.forTableColumn());
//        dateE.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // TODO
    }    
    
    
    
    
     private void suppButton() {
         System.out.println("aa");

        Callback<TableColumn<Participation, Void>, TableCell<Participation, Void>> cellFactory = new Callback<TableColumn<Participation, Void>, TableCell<Participation, Void>>() {
           
            @Override
            public TableCell<Participation, Void> call(final TableColumn<Participation, Void> param) {
                
                final TableCell<Participation, Void> cell = new TableCell<Participation, Void>() {
                    
                          
                    private final Button btnn = new Button("Supprimer");

//                    {
//                        System.out.println("bb");
//
//                      btnn.setOnAction((ActionEvent event) -> {
//                            System.out.println("cc");
////                            Participation selectedCat = getTableView().getItems().get(getIndex());
////                            System.out.println("selectedDat\n" +
////"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
////                            ParticipationService suppService = new ParticipationService();
////                            suppService.deleteAction(selectedCat.getId());
////                            ParticipationService es = new ParticipationService();
////                            ob = es.indexAction(selectedCat.getIdU());
////                            table.setItems(ob);
////                            
//                       });
//                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table.setItems(ob);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnn);
                        }
                    }
                };
                return cell;
            }
        };
        participer.setCellFactory(cellFactory);

        }



   
        
        
    
    
    
    
    

    
}
