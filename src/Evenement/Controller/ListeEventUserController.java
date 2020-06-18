/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Controller;

import Evenement.Entity.Evenement;
import Evenement.Entity.Invite;
import Evenement.Entity.Participation;
import Evenement.Service.EvenementService;
import Evenement.Service.ParticipationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class ListeEventUserController implements Initializable {

    
    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> nomE;
    @FXML
    private TableColumn<Evenement, String> lieuE;
    @FXML
    private TableColumn<Evenement, String> dateE;
    private TableColumn<Evenement, String> nbrmaxE;
    private TableColumn<Evenement, String> imageE;
    public ObservableList<Evenement> data = FXCollections.observableArrayList();
    
 
    @FXML
    private TableColumn<Evenement,Void> listInvité;
    @FXML
    private TableColumn<Evenement,Void> participer;
    @FXML
    private Button myevent;
    
    public static int idEvent1;
    
     @FXML
   private AnchorPane acc;
     @FXML
   private void setNode(Node node) {
        
        
        
        acc.getChildren().clear();
        acc.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
     
    
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        nomE.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        lieuE.setCellValueFactory(new PropertyValueFactory<>("lieuEvenement"));
        dateE.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
     
        EvenementService es = new EvenementService();
        data = es.indexAction();
        table.setItems(data);
        System.out.println(data);
      
        listIButton();
        participerButton();
        
       
        
        table.setEditable(true);
        
        
        
        nomE.setCellFactory(TextFieldTableCell.forTableColumn());
        lieuE.setCellFactory(TextFieldTableCell.forTableColumn());
        dateE.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }    
    
    
    
    

    @FXML
    private void editNom(TableColumn.CellEditEvent ec) {
        Evenement e=table.getSelectionModel().getSelectedItem();
        e.setNomEvenement(ec.getNewValue().toString());
        EvenementService es = new EvenementService();
        es.updateAction(e);
    }

    private void editLieu(TableColumn.CellEditEvent ec) {
        Evenement e=table.getSelectionModel().getSelectedItem();
        e.setLieuEvenement(ec.getNewValue().toString());
        EvenementService es = new EvenementService();
        es.updateAction(e);
    }

    private void editDate(TableColumn.CellEditEvent ec) {
        Evenement e=table.getSelectionModel().getSelectedItem();
        e.setDateEvenement(ec.getNewValue().toString());
        EvenementService es = new EvenementService();
        es.updateAction(e);
    }

    private void editNbrMax(TableColumn.CellEditEvent ec) {
        Evenement e=table.getSelectionModel().getSelectedItem();
        e.setNbrMaxParticipants(Integer.parseInt(ec.getNewValue().toString()));
        EvenementService es = new EvenementService();
        es.updateAction(e);
    }

    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/AjoutEvenement.fxml"));
        Parent root = loader.load();
        table.getScene().setRoot(root);
    }

    private void invitButton(int evenementId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/ajoutInvite.fxml"));
        Parent root = loader.load();
        ListeInviteController controller = loader.<ListeInviteController>getController();
       // TextField t = new TextField();
        //t.setText(String.valueOf(evenementId));
      String  text = "ab";
        
        controller.fct(text);
        System.out.println("aabb"+text);
        table.getScene().setRoot(root); 
    }
   


 
    private void listeIButton(int evenementId) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/ListInvite.fxml"));
//        Parent root = loader.load();
//    ListInviteController controller = loader.<ListInviteController>getController();
////       // TextField t = new TextField();
////        //t.setText(String.valueOf(evenementId));
//
//        System.out.println("evenement id = "+evenementId);
//        table.getScene().setRoot(root); 
 AnchorPane    myNewScene = null;
  
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/ListInvite.fxml"));
                    
                   


 
         } catch (IOException ex) {
           
        }
                     setNode(myNewScene);

    }
    private void listIButton() {
        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final Button btn = new Button("Liste invité");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            idEvent1 = selectedCat.getId();
                            System.out.println("avant : "+idEvent1);
                            ///EvenementService suppService = new EvenementService();
                            //suppService.deleteAction(selectedCat.getId());
                            //EvenementService es = new EvenementService();
                            //data = es.indexAction();
                            //table.setItems(data);
                            try {

                                listeIButton(selectedCat.getId());
                                int x = selectedCat.getId();
                                System.out.println("x = "+x);
                            } catch (Exception e) {}
                            
                           
                                
                            
                           
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
        listInvité.setCellFactory(cellFactory);
    
       
    }

    private void participationButton(Evenement e) throws IOException, SQLException {
       
       // TextField t = new TextField();
        //t.setText(String.valueOf(evenementId));
        
        System.out.println("evenement id = "+e.getId());
        Participation p = new Participation();
        p.setIdE(e.getId());
        p.setIdU(1);
        p.setNomEvent(e.getNomEvenement());
        p.setDateEvent(e.getDateEvenement());
        p.setLieuEvent(e.getLieuEvenement());
     DateFormat format = new SimpleDateFormat("yyyy/MM/dd ");
            Date date = new Date();
        p.setDateParticipation("2020-04-16");
        ParticipationService ps = new ParticipationService();
       
      int nbr= ps.nbrPartAction(e.getId());
      int part = ps.nbrMaxParticipant(e.getId());
        System.out.println("aaaaaa"+part);
        System.out.println("nbrrrrrrrrrrrrr"+nbr);
        int pp;
        pp = ps.Participant(e.getId());
        System.out.println("pp"+pp);
        
        if(part>nbr+1)
        {
            if(pp>0)
            {
  Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("Evenment  : "+e.getNomEvenement());
         alert.setHeaderText("vous etes deja participant !");
          alert.showAndWait();
            }else
            {ps.newAction(p);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Evenment  : "+e.getNomEvenement());
         alert.setHeaderText("participation validée !");
          alert.showAndWait();
            
            }
             
        }
        else
        {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Evenment  : "+e.getNomEvenement());
         alert.setHeaderText("complet !");
        
         alert.showAndWait();
        }
        
        
    }

    
     private void participerButton() {
        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final Button btn = new Button("Participer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement selectedCat = getTableView().getItems().get(getIndex());
                          
        
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            ///EvenementService suppService = new EvenementService();
                            //suppService.deleteAction(selectedCat.getId());
                            //EvenementService es = new EvenementService();
                            //data = es.indexAction();
                            //table.setItems(data);
                            System.err.println("amininini");
                            try {
                       

                                participationButton(selectedCat);
                                int x = selectedCat.getId();
                                
                            } catch (Exception e) {}
                            
                           
                                
                            
                           
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
        participer.setCellFactory(cellFactory);
    
       
    }

    @FXML
    private void AffichelisteE(ActionEvent event) throws IOException {
   // FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/MyEvent.fxml"));
     //    Parent root = loader.load();
       //  myevent.getScene().setRoot(root);

   AnchorPane    myNewScene = null;
          
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/MyEvent.fxml"));
                
 
         } catch (IOException ex) {
           
        }

             setNode(myNewScene);
         
    
    }
}
    

    
   
    


    

