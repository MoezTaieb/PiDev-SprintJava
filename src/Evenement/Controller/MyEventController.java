/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Controller;

import Don.Entity.CategorieEquipment;
import Don.Service.CategorieEquipmentService;
import Evenement.Entity.Evenement;
import Evenement.Entity.Participation;
import Evenement.Service.EvenementService;
import Evenement.Service.ParticipationService;
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
    private TableColumn<Participation, String> participer;
     @FXML
    private TableColumn<Participation, Void> annuler_participation;
     public ObservableList<Participation> data = FXCollections.observableArrayList();
    
    
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
     ParticipationService ps =new ParticipationService();
        data = ps.indexAction(1);
        
        
        table.setItems(data);
        System.out.println(data);
        
                annuler_participationButton();

      
        
      
        
       
        
        table.setEditable(true);
        
        
        
//        nomE.setCellFactory(TextFieldTableCell.forTableColumn());
//        lieuE.setCellFactory(TextFieldTableCell.forTableColumn());
//        dateE.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // TODO
    }    
    
    
    
    

        private void annuler_participationButton() {
        

        Callback<TableColumn<Participation, Void>, TableCell<Participation, Void>> cellFactory = new Callback<TableColumn<Participation, Void>, TableCell<Participation, Void>>() {
            @Override
            public TableCell<Participation, Void> call(final TableColumn<Participation, Void> param) {
                final TableCell<Participation, Void> cell = new TableCell<Participation, Void>() {

                    private final Button btn = new Button("annuler_participation"
                            + "");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Participation selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            ParticipationService suppService = new ParticipationService();
                            suppService.deleteAction(selectedCat.getId());
                          ParticipationService ser = new ParticipationService();
                            data = ser.indexAction(18);
                            table.setItems(data);
                            
                        });
                    }

        
          @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table.setItems(data);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        annuler_participation.setCellFactory(cellFactory);

        }



   
        
        
    
    
    
    
    

    
}
