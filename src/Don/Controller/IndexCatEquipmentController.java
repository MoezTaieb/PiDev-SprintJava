/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Controller;

import Don.Entity.CategorieEquipment;

import Don.Entity.Equipment;

import Don.Service.CategorieEquipmentService;

import Don.Service.EquipmentService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author amin
 */
public class IndexCatEquipmentController implements Initializable {

    
    @FXML
    private TextField noms;
    @FXML
    private Button ajouter;
      @FXML
    private JFXButton retour ;
    @FXML
    private TableView<CategorieEquipment> table;
     @FXML
    private TableColumn<CategorieEquipment, Integer> id;
    @FXML
    private TableColumn<CategorieEquipment, String> nom;
     public ObservableList<CategorieEquipment> data = FXCollections.observableArrayList();
           @FXML
    private TableColumn<CategorieEquipment, Void> supp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       id.setCellValueFactory(new PropertyValueFactory<CategorieEquipment,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<CategorieEquipment,String>("nom"));
          CategorieEquipmentService ser = new CategorieEquipmentService();
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
        alert.setHeaderText("No nom Categorie Equipment Selected");
        alert.setContentText("Please select a Categorie Equipment .");

        alert.showAndWait();
    
        }
        else
        {
        
        CategorieEquipment C = new CategorieEquipment(noms.getText());
        CategorieEquipmentService ser = new CategorieEquipmentService();
        ser.newAction(C);
     
       
       
          
        CategorieEquipmentService sera = new CategorieEquipmentService();
       data = sera.indexAction();
       
        CategorieEquipmentService seraa = new CategorieEquipmentService();
         
       data = seraa.indexAction();
        table.setItems(data);
         
         
    }}
     private void suppButton() {
        

        Callback<TableColumn<CategorieEquipment, Void>, TableCell<CategorieEquipment, Void>> cellFactory = new Callback<TableColumn<CategorieEquipment, Void>, TableCell<CategorieEquipment, Void>>() {
            @Override
            public TableCell<CategorieEquipment, Void> call(final TableColumn<CategorieEquipment, Void> param) {
                final TableCell<CategorieEquipment, Void> cell = new TableCell<CategorieEquipment, Void>() {

                    private final Button btn = new Button("Supp");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            CategorieEquipment selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            CategorieEquipmentService suppService = new CategorieEquipmentService();
                            suppService.deleteAction(selectedCat.getId());
                          CategorieEquipmentService ser = new CategorieEquipmentService();
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
        CategorieEquipment c=table.getSelectionModel().getSelectedItem();
        c.setNom(ec.getNewValue().toString());
        CategorieEquipmentService ser = new CategorieEquipmentService();
        ser.updateAction(c);
    }
  
    @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/DashAdmin.fxml"));
        Parent root = loader.load();
        table.getScene().setRoot(root);
    }
}
