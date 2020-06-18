/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.Controller;

import Produit.Entity.CategorieProduit;
import Produit.Service.CategorieProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Othmen
 */
public class indexCategorieController implements Initializable {

    @FXML
    private AnchorPane indexPane;
    @FXML
    private TableView<CategorieProduit> table;
    @FXML
    private TableColumn<CategorieProduit, String> nom;
    public ObservableList<CategorieProduit> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<CategorieProduit, Void> supp;
    @FXML
    private JFXButton retour;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom.setCellValueFactory(new PropertyValueFactory<CategorieProduit,String>("nomCategorie"));
        CategorieProduitService ser = new CategorieProduitService();
        data = ser.indexAction();
        table.setItems(data);
        suppButton();
        table.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
    }  
    
    private void setNode(Node node) {
        indexPane.getChildren().clear();
        indexPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }
    
    private void suppButton() {
        

        Callback<TableColumn<CategorieProduit, Void>, TableCell<CategorieProduit, Void>> cellFactory = new Callback<TableColumn<CategorieProduit, Void>, TableCell<CategorieProduit, Void>>() {
            @Override
            public TableCell<CategorieProduit, Void> call(final TableColumn<CategorieProduit, Void> param) {
                final TableCell<CategorieProduit, Void> cell = new TableCell<CategorieProduit, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setId("red");
                        btn.setOnAction((ActionEvent event) -> {
                            CategorieProduit selectedCat = getTableView().getItems().get(getIndex());
                            CategorieProduitService suppService = new CategorieProduitService();
                            suppService.deleteAction(selectedCat.getId());
                            CategorieProduitService ser = new CategorieProduitService();
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
        CategorieProduit c=table.getSelectionModel().getSelectedItem();
        c.setNomCategorie(ec.getNewValue().toString());
        CategorieProduitService ser = new CategorieProduitService();
        ser.updateAction(c);
    }

    
/*
    @FXML
    private void search(KeyEvent event) {
        FilteredList<CategorieProduit> filterdata = new FilteredList<>(data, e->true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterdata.setPredicate((cat) -> {
                if (newValue.isEmpty()|| newValue == null){
                    return true;
                }
                String typedText = newValue.toLowerCase();
                if (cat.getNomCategorie().toLowerCase().indexOf(typedText) != -1){
                    return true;
                }
            
                return false;
            });
            SortedList<CategorieProduit> sort = new SortedList<>(filterdata);
            sort.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sort);
        });
        
    }
*/

    @FXML
    private void ajouter(MouseEvent event) throws IOException {
        AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/AjouterCategorieProduit.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }

             setNode(myNewScene);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Produit/Views/dash.fxml"));
        Parent root = loader.load();
        table.getScene().setRoot(root);
    }

}
