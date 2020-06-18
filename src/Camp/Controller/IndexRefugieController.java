/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Controller;

import Camp.Entity.Refugie;
import Camp.Service.RefugieService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author ME
 */
public class IndexRefugieController implements Initializable {
    
    @FXML
    AnchorPane indexPane;

    @FXML
    private TableView<Refugie> table;
    @FXML
    private TableColumn<Refugie, String> Nom;
    @FXML
    private TableColumn<Refugie, String> Prenom;
    @FXML
    private TableColumn<Refugie, String> Adresse;
    @FXML
    private TableColumn<Refugie, String> tel;
    @FXML
    private TableColumn<Refugie, String> Numassport;
    @FXML
    private TableColumn<Refugie, String> nationalite;
    @FXML
    private TableColumn<Refugie, String> image;
    @FXML
    private TableColumn<Refugie, String> nomCamp;
   public ObservableList<Refugie> data = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Refugie, Void> Supprimer;
    @FXML
    private Button ajouter;
    @FXML
    private Button mail;
    private TextField filterField;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchTextField;
    
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
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Nom.setCellValueFactory(new PropertyValueFactory<Refugie,String>("nomRefugie"));
        Prenom.setCellValueFactory(new PropertyValueFactory<Refugie,String>("prenomRefugie"));
        Adresse.setCellValueFactory(new PropertyValueFactory<Refugie,String>("adresseRefugie"));
        tel.setCellValueFactory(new PropertyValueFactory<Refugie,String>("telRefugie"));
        Numassport.setCellValueFactory(new PropertyValueFactory<Refugie,String>("numassportRefugie"));
        nationalite.setCellValueFactory(new PropertyValueFactory<Refugie,String>("nationaliteRefugie"));
        image.setCellValueFactory(new PropertyValueFactory<Refugie,String>("image"));
        nomCamp.setCellValueFactory(new PropertyValueFactory<Refugie,String>("nomCamp"));

        RefugieService ser = new RefugieService();
        data = ser.indexAction();
        table.setItems(data);
        System.out.println(data);
        SupprimerButton();
        table.setEditable(true);
        Nom.setCellFactory(TextFieldTableCell.forTableColumn());
        Prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        Adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        tel.setCellFactory(TextFieldTableCell.forTableColumn());
        Numassport.setCellFactory(TextFieldTableCell.forTableColumn());
        nationalite.setCellFactory(TextFieldTableCell.forTableColumn());
        image.setCellFactory(TextFieldTableCell.forTableColumn());
       
//        // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<Refugie> filteredData = new FilteredList<>(data, b -> true);
//		
//		// 2. Set the filter Predicate whenever the filter changes.
//		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(Refugie -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				System.out.println(lowerCaseFilter);
//
//				if (Refugie.getNomRefugie().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//                                 
//					return true; // Filter matches first name.
//                                        
//				} else  
//				    	 return false; // Does not match.
//			});
//		});
//		
//		// 3. Wrap the FilteredList in a SortedList. 
//		SortedList<Refugie> sortedData = new SortedList<>(filteredData);
//		
//		// 4. Bind the SortedList comparator to the TableView comparator.
//		// 	  Otherwise, sorting the TableView would have no effect.
//		sortedData.comparatorProperty().bind(table.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
//		table.setItems(sortedData);
               
        
    }    
        
    
    
    private void SupprimerButton() {

        javafx.util.Callback<TableColumn<Refugie, Void>, TableCell<Refugie, Void>> cellFactory = new javafx.util.Callback<TableColumn<Refugie, Void>, TableCell<Refugie, Void>>() {
            @Override
            public TableCell<Refugie, Void> call(final TableColumn<Refugie, Void> param) {
                final TableCell<Refugie, Void> cell = new TableCell<Refugie, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Refugie selectedRefugie = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedRefugie);
                            RefugieService suppService = new RefugieService();
                            suppService.deleteAction(selectedRefugie.getId());
                            RefugieService ser = new RefugieService();
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
        Supprimer.setCellFactory(cellFactory);
   
    }     
    @FXML
    private void editNom(TableColumn.CellEditEvent ec) {
        Refugie c=table.getSelectionModel().getSelectedItem();
        c.setNomRefugie(ec.getNewValue().toString());
        RefugieService ser = new RefugieService();
        ser.updateAction(c);
    }

    @FXML
    private void editPrenom(TableColumn.CellEditEvent ec) {
        Refugie c=table.getSelectionModel().getSelectedItem();
        c.setPrenomRefugie(ec.getNewValue().toString());
        RefugieService ser = new RefugieService();
        ser.updateAction(c);
    }

    @FXML
    private void editAdresse(TableColumn.CellEditEvent ec) {
        Refugie c=table.getSelectionModel().getSelectedItem();
        c.setAdresseRefugie(ec.getNewValue().toString());
        RefugieService ser = new RefugieService();
        ser.updateAction(c);
    }
    @FXML
    private void edittel(TableColumn.CellEditEvent ec) {
        Refugie c=table.getSelectionModel().getSelectedItem();
        c.setTelRefugie(ec.getNewValue().toString());
        RefugieService ser = new RefugieService();
        ser.updateAction(c);
    }

    @FXML
    private void editNumassport(TableColumn.CellEditEvent ec) {
        Refugie c=table.getSelectionModel().getSelectedItem();
        c.setNumassportRefugie(ec.getNewValue().toString());
        RefugieService ser = new RefugieService();
        ser.updateAction(c);
    }

    @FXML
    private void editnationalite(TableColumn.CellEditEvent ec) {
        Refugie c=table.getSelectionModel().getSelectedItem();
        c.setNationaliteRefugie(ec.getNewValue().toString());
        RefugieService ser = new RefugieService();
        ser.updateAction(c);
    }

    @FXML
    private void editimage(TableColumn.CellEditEvent ec) {
        Refugie c=table.getSelectionModel().getSelectedItem();
        c.setNationaliteRefugie(ec.getNewValue().toString());
        RefugieService ser = new RefugieService();
        ser.updateAction(c);
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
AnchorPane    myNewScene = null;
                   
        try {
          myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/Views/AjouterRefugie.fxml"));
                    
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexRefugieController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
    
    }

    @FXML
    private void creeMali(ActionEvent event) throws IOException{
        
        
        AnchorPane    myNewScene = null;
                   
        try {                               
            myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/Views/Mailer.fxml"));

 
         } catch (IOException ex) {
            Logger.getLogger(IndexRefugieController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
    
      
    }

    @FXML
    private void handleSearchDemande(ActionEvent actionEvent) {
     try
        {
          //recuperer la liste des demande dans un ObservableArrayList 
        RefugieService ser = new RefugieService();
        data = FXCollections.observableArrayList(ser.retrieveByTagRefugier(searchTextField.getText()));        
        
        //afficher les resultat dans le tableaux
        table.setItems(data);
         System.out.println("recherche"+data);
        }
        catch (Exception ex) {
            
            System.out.println("Probleme de recherche"+ex);
        }
    
    }
}
