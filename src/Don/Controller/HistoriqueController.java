/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Controller;

import Don.Entity.Equipment;
import Don.Service.EquipmentService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author amin
 */
public class HistoriqueController implements Initializable {

    
      @FXML
    private TableView<Equipment>table;
     @FXML
    private TableColumn<Equipment, Integer>id;
    @FXML
    private TableColumn<Equipment, String>nom;
    @FXML
    private TableColumn<Equipment, String>etat;
    @FXML
    private TableColumn<Equipment, Integer>nb;
    @FXML
    private TableColumn<Equipment, String>catid;
       @FXML
    private TableColumn<Equipment, Integer>date;
     public ObservableList<Equipment> data = FXCollections.observableArrayList();
      @FXML
    private TableColumn<Equipment, Void> supp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
     id.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Equipment,String>("nomEquipment"));
            etat.setCellValueFactory(new PropertyValueFactory<Equipment,String>("etatEquipment"));
                nb.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("nb_equipment"));
                    catid.setCellValueFactory(new PropertyValueFactory<Equipment,String>("nomCategorie"));
                    date.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("dateDonEquipment"));
          EquipmentService ser = new EquipmentService();
       data = ser.indexAction2();
        table.setItems(data);
        System.out.println(data);
          suppButton();
            table.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        
        etat.setCellFactory(TextFieldTableCell.forTableColumn());
        EquipmentService e =new EquipmentService();
   //  for(int i=0;i<table.getItems().size()
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }    
    private void suppButton() {
        

        Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>> cellFactory = new Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>>() {
            @Override
            public TableCell<Equipment, Void> call(final TableColumn<Equipment, Void> param) {
                final TableCell<Equipment, Void> cell = new TableCell<Equipment, Void>() {

                    private final Button btn = new Button("Supp");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Equipment selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            EquipmentService suppService = new EquipmentService();
                            suppService.deleteAction(selectedCat.getId());
                            EquipmentService ser = new EquipmentService();
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
        Equipment c=table.getSelectionModel().getSelectedItem();
        c.setNomEquipment(ec.getNewValue().toString());
        EquipmentService ser = new EquipmentService();
        ser.updateAction(c);
    }
   @FXML
    private void editnb(TableColumn.CellEditEvent ec) {
        Equipment p=table.getSelectionModel().getSelectedItem();
        p.setNb_equipment(Integer.parseInt(ec.getNewValue().toString()));
        EquipmentService ser = new EquipmentService();
        ser.updateAction(p);
    }
     @FXML
    private void editetat(TableColumn.CellEditEvent ec) {
        Equipment p=table.getSelectionModel().getSelectedItem();
       p.setEtatEquipment(ec.getNewValue().toString());
        EquipmentService ser = new EquipmentService();
        ser.updateAction(p);
    }
}
