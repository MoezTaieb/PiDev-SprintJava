/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Controller;

import Don.Entity.CategorieEquipment;

import Don.Entity.CategorieService;
import Don.Entity.Equipment;

import Don.Service.CategorieEquipmentService;

import Don.Service.CategorieServiceService;
import Don.Service.EquipmentService;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author amin
 */
public class IndexCatServiceController implements Initializable {


    
        @FXML
    private JFXButton retour ;
     
    @FXML
    private TextField noms;
    @FXML
    private Button ajouter;
  
    @FXML
    private TableView<CategorieService> table;
     @FXML
    private TableColumn<CategorieService, String> id;
    @FXML
    private TableColumn<CategorieService, String> nom;
     public ObservableList<CategorieService> data = FXCollections.observableArrayList();
           @FXML
    private TableColumn<CategorieService, Void> supp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       id.setCellValueFactory(new PropertyValueFactory<CategorieService,String>("id"));
      nom.setCellValueFactory(new PropertyValueFactory<CategorieService,String>("nom"));
       CategorieServiceService ser = new CategorieServiceService();
     data = ser.indexAction();
        table.setItems(data);
        System.out.println(data);
        
        suppButton();
        table.setEditable(true);
       nom.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        
        
    }   
          @FXML
    private void addCategorie(ActionEvent event) throws IOException {
        
        
        if (noms.getText() == null || noms.getText().length() == 0) {
               Alert alert = new Alert(Alert.AlertType.WARNING);
    //  alert.initOwner(AjoutEquipmentController.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No nom Categorie Equipment Service");
        alert.setContentText("Please select a Categorie Service .");

        alert.showAndWait();
    
        }
        else
        {
        CategorieService C = new CategorieService(noms.getText());
        CategorieServiceService ser = new CategorieServiceService();
        ser.newAction(C);
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/Don/Views/indexCatService.fxml"));
        Parent root = loader.load();
       noms.getScene().setRoot(root);
    }
}
     private void suppButton() {
        

        Callback<TableColumn<CategorieService, Void>, TableCell<CategorieService, Void>> cellFactory = new Callback<TableColumn<CategorieService, Void>, TableCell<CategorieService, Void>>() {
            @Override
            public TableCell<CategorieService, Void> call(final TableColumn<CategorieService, Void> param) {
                final TableCell<CategorieService, Void> cell = new TableCell<CategorieService, Void>() {

                    private final Button btn = new Button("Supp");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            CategorieService selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            CategorieServiceService suppService = new CategorieServiceService();
                            suppService.deleteAction(selectedCat.getId());
                          CategorieServiceService ser = new CategorieServiceService();
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
        CategorieService c=table.getSelectionModel().getSelectedItem();
        c.setNom(ec.getNewValue().toString());
        CategorieServiceService ser = new CategorieServiceService();
        ser.updateAction(c);
    }
    
    
     @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/DashAdmin.fxml"));
        Parent root = loader.load();
        table.getScene().setRoot(root);
    }
    
    }    
    

