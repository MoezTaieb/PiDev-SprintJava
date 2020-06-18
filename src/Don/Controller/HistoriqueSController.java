/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Controller;

import Don.Entity.CategorieService;
import Don.Entity.Equipment;
import Don.Entity.Service;
import Don.Service.CategorieServiceService;
import Don.Service.EquipmentService;
import Don.Service.ServiceService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author amin
 */
public class HistoriqueSController implements Initializable {

    /**
     * Initializes the controller class.
     */
       
       @FXML
    private TableView<Service>table;
     @FXML
    private TableColumn<Service, String>id;
    @FXML
    private TableColumn<Service, String>nom;
    @FXML
    private TableColumn<Service, String>description;
    @FXML
    private TableColumn<Service, String>date;
      @FXML
    private TableColumn<Service, String>catid;
       @FXML
    private TableColumn<Service, Void> supp;
     public ObservableList<Service> data = FXCollections.observableArrayList();
  
    @Override
  
       public void initialize(URL url, ResourceBundle rb) {
       id.setCellValueFactory(new PropertyValueFactory<Service,String>("id"));
     nom.setCellValueFactory(new PropertyValueFactory<Service,String>("nomService"));
      date.setCellValueFactory(new PropertyValueFactory<Service,String>("dateDon"));
       description.setCellValueFactory(new PropertyValueFactory<Service,String>("descriptionService"));
       catid.setCellValueFactory(new PropertyValueFactory<Service,String>("nomCategorie"));
       ServiceService ser = new ServiceService();
     data = ser.indexAction2();
        table.setItems(data);
        System.out.println(data);
        
        suppButton();
        table.setEditable(true);
       nom.setCellFactory(TextFieldTableCell.forTableColumn());
               description.setCellFactory(TextFieldTableCell.forTableColumn());

    }    
       
       private void suppButton() {
        

        Callback<TableColumn<Service, Void>, TableCell<Service, Void>> 
                cellFactory = new Callback<TableColumn<Service, Void>, TableCell<Service, Void>>() {
            @Override
            public TableCell<Service, Void> call(final TableColumn<Service, Void> param) {
                final TableCell<Service, Void> cell = new TableCell<Service, Void>() {

                    private final Button btn = new Button("supp");

                    {
                          btn.setOnAction((ActionEvent event) -> {
                            Service selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            ServiceService suppService = new ServiceService();
                            suppService.deleteAction(selectedCat.getId());
                            ServiceService ser = new ServiceService();
                            data = ser.indexAction();
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
        supp.setCellFactory(cellFactory);

        }
  @FXML
    private void editNom(TableColumn.CellEditEvent ec) {
        Service c=table.getSelectionModel().getSelectedItem();
        c.setNomService(ec.getNewValue().toString());
        ServiceService ser = new ServiceService();
        ser.updateAction(c);
    }
     @FXML
    private void editDesc(TableColumn.CellEditEvent ec) {
        Service c=table.getSelectionModel().getSelectedItem();
        c.setDescriptionService(ec.getNewValue().toString());
        ServiceService ser = new ServiceService();
        ser.updateAction(c);
    }
     
   
}
