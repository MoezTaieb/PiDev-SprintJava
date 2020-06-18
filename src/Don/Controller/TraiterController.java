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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
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

import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;

import javax.swing.JFileChooser;




/**
 * FXML Controller class
 *
 * @author amin
 */
public class TraiterController implements Initializable {
    @FXML
    private JFXButton retour ;
    @FXML
    private TableView<Equipment>table1;
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
            @FXML
    private TableColumn<Equipment, Void> supp1;
       
    /**
     * Initializes the controller class.
     */
 
        private Desktop desktop = Desktop.getDesktop();
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
       data = ser.index1Action();
        table1.setItems(data);
        System.out.println(data);
          suppButton();
             supp1Button();
            table1.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        
        etat.setCellFactory(TextFieldTableCell.forTableColumn());
        EquipmentService e =new EquipmentService();
   //  for(int i=0;i<table.getItems().size()
     //  ;i++)
     //{
//     }}
      
                        
  
       
        
        
    }    
    
      private void suppButton() {
        

        Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>> cellFactory = new Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>>() {
            @Override
            public TableCell<Equipment, Void> call(final TableColumn<Equipment, Void> param) {
                final TableCell<Equipment, Void> cell = new TableCell<Equipment, Void>() {

                    private final Button btn = new Button("recu");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Equipment selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            EquipmentService suppService = new EquipmentService();
                            suppService.traiterAction(selectedCat.getId());
                            EquipmentService ser = new EquipmentService();
                            data = ser.index1Action();
                            table1.setItems(data);
                            
                        });
                    }

        
          @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table1.setItems(data);
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
      private void supp1Button() {
        

        Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>> cellFactory = new Callback<TableColumn<Equipment, Void>, TableCell<Equipment, Void>>() {
            @Override
            public TableCell<Equipment, Void> call(final TableColumn<Equipment, Void> param) {
                final TableCell<Equipment, Void> cell = new TableCell<Equipment, Void>() {

                    private final Button btn1 = new Button("annuller");

                    {
                        btn1.setOnAction((ActionEvent event) -> {
                            Equipment selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            EquipmentService suppService = new EquipmentService();
                            suppService.traiterAction(selectedCat.getId());
                            EquipmentService ser = new EquipmentService();
                            data = ser.index1Action();
                            table1.setItems(data);
                            
                        });
                    }

        
          @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table1.setItems(data);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn1);
                        }
                    }
                };
                return cell;
            }
        };
        supp1.setCellFactory(cellFactory);

        }
      
   @FXML
    private void editNom(TableColumn.CellEditEvent ec) {
        Equipment c=table1.getSelectionModel().getSelectedItem();
        c.setNomEquipment(ec.getNewValue().toString());
        EquipmentService ser = new EquipmentService();
        ser.updateAction(c);
    }
   @FXML
    private void editnb(TableColumn.CellEditEvent ec) {
        Equipment p=table1.getSelectionModel().getSelectedItem();
        p.setNb_equipment(Integer.parseInt(ec.getNewValue().toString()));
        EquipmentService ser = new EquipmentService();
        ser.updateAction(p);
    }
     @FXML
    private void editetat(TableColumn.CellEditEvent ec) {
        Equipment p=table1.getSelectionModel().getSelectedItem();
       p.setEtatEquipment(ec.getNewValue().toString());
        EquipmentService ser = new EquipmentService();
        ser.updateAction(p);
    }
@FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/DashAdmin.fxml"));
        Parent root = loader.load();
        table1.getScene().setRoot(root);
    }
}


 
    

    











    
  
   
     

  