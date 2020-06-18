/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Controller;

import Evenement.Entity.Evenement;

import Evenement.Service.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class ListEvenementController implements Initializable {

    
    @FXML
    private TableView<Evenement> table;
    @FXML
    private Button ajouter;
    @FXML
    private TableColumn<Evenement, String> nomE;
    @FXML
    private TableColumn<Evenement, String> lieuE;
    @FXML
    private TableColumn<Evenement, String> dateE;
    private TableColumn<Evenement, String> nbrmaxE;
    @FXML
    private TableColumn<Evenement, Void>  imageE;
    public ObservableList<Evenement> ob = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Evenement, Void> supp;
     @FXML
    private TableColumn<Evenement, Void> ajoutI;
    private TableColumn<Evenement, Void> modifE;
    @FXML
    private TableColumn<Evenement, Void> ModifierEvent;
   private TableColumn<Evenement,Void> listInvité;
    @FXML
    private TextField recherche;

    @FXML    
   private AnchorPane acc;
   
   
   public static int idEvent1;
   public static String image1;
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
     
//    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        // TODO
         System.out.println("1");
        nomE.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        lieuE.setCellValueFactory(new PropertyValueFactory<>("lieuEvenement"));
        dateE.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
      Image e =  new Image("http://localhost/pidev20/web/uploads/images/a/");
      imageE.setPrefWidth(60);
   // imageE.setCellValueFactory(new PropertyValueFactory<>("imageEvenement"));
    //String x = imageE.setCellValueFactory(new PropertyValueFactory<>("imageEvenement"));
        ImageView imageView; 
        EvenementService es = new EvenementService();
//        ob= es.indexAction();
//        ArrayList<Evenement> obsC = (ArrayList<Evenement>) es.indexAction();
//        recherche.setOnKeyReleased(e -> {
//                recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
//                    
//                    if (!newValue.isEmpty()) {
//                        List<Evenement> obsRr = obsC.stream().filter(o -> (o.getNomEvenement().toLowerCase().contains(newValue.toLowerCase()))).collect((Collectors.toList()));
//                        
//                        ObservableList<Evenement> sortedList = FXCollections.observableArrayList(obsRr);
//                        table.getColumns().clear();
//        table.getColumns().addAll(nomE, lieuE, dateE);
//        table.setItems(sortedList);
//                    }
//                    else {
//                        table.setItems((ObservableList<Evenement>) obsC);
//                    }
//
////                 
//                });
//
//            });
       ob = es.indexAction();
             System.out.println("2");
        // ArrayList<Evenement> data = (ArrayList<Evenement>) es.indexAction();
         System.out.println("222");
      ObservableList<Evenement> ev = FXCollections.observableArrayList(es.indexAction());

          FilteredList<Evenement> filterdData = new FilteredList<>(ev, p -> true);
         System.out.println("3");
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
          //  ArrayList<Evenement> per = (ArrayList<Evenement>) es.indexAction();
       // ObservableList<Evenement> datalist = FXCollections.observableArrayList(es.indexAction());
         System.out.println("4");
     filterdData.setPredicate((Liv) -> {
                if (newValue == null || newValue.isEmpty()) {
                    System.out.println("5");
                    return true;
                }
                String LowerCaseFilter = newValue.toLowerCase();
System.out.println("6 "+LowerCaseFilter);
                if (Liv.getNomEvenement().toLowerCase().contains(LowerCaseFilter)) {
                    return true;

                }  
                    return false;
                

            });

            SortedList<Evenement> sortedData = new SortedList<>(filterdData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
           
        });
        table.setItems(ev); 
        
        ob = es.indexAction();
        table.setItems(ob);
        System.out.println(ob);
        afficheImage();
        suppButton();
        ajoutInButton();
        updateButton();
        //listIButton();
        
       
        
        table.setEditable(true);
        
        
        
        
        
        
//        
//       nomE.setCellFactory(TextFieldTableCell.forTableColumn());
//        lieuE.setCellFactory(TextFieldTableCell.forTableColumn());
//        dateE.setCellFactory(TextFieldTableCell.forTableColumn());
        
      //  EvenementService es = new EvenementService();
//         System.out.println("2");
//         ArrayList<Evenement> dataa = (ArrayList<Evenement>) es.indexAction();
//         System.out.println("222");
//        ObservableList<Evenement> ev = FXCollections.observableArrayList(dataa);
//         
//         System.out.println("3");
//        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
//            ArrayList<Evenement> per = (ArrayList<Evenement>) es.indexAction();
//        ObservableList<Evenement> datalist = FXCollections.observableArrayList(per);
//        es.indexAction();
//         System.out.println("4");
//        FilteredList<Evenement> filterdData = new FilteredList<>(datalist, p -> true);
//     filterdData.setPredicate((Liv) -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    System.out.println("5");
//                    return true;
//                     
//                }
//                String LowerCaseFilter = newValue.toLowerCase();
//System.out.println("6 ");
//                if (Liv.getNomEvenement().toLowerCase().contains(LowerCaseFilter)) {
//                    return true;
//
//                }  
//                    return false;
//                
//
//            });
//
//            SortedList<Evenement> sortedData = new SortedList<>(filterdData);
//            sortedData.comparatorProperty().bind(table.comparatorProperty());
//            table.setItems(sortedData);
//           
//        });
//        table.setItems(ev);        
		
		
               
        
     
    

        
    }    
    
    
    
    
    private void suppButton() {
        

        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            EvenementService suppService = new EvenementService();
                            suppService.deleteAction(selectedCat.getId());
                            EvenementService es = new EvenementService();
                            ob = es.indexAction();
                            table.setItems(ob);
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table.setItems(ob);
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
        e.setDateEvenement( ec.getNewValue().toString());
        EvenementService es = new EvenementService();
        es.updateAction(e);
    }

    private void editNbrMax(TableColumn.CellEditEvent ec) {
        Evenement e=table.getSelectionModel().getSelectedItem();
        e.setNbrMaxParticipants(Integer.parseInt(ec.getNewValue().toString()));
        EvenementService es = new EvenementService();
        es.updateAction(e);
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/AjoutEvenement.fxml"));
//        Parent root = loader.load();
//        table.getScene().setRoot(root);
AnchorPane    myNewScene = null;
  
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/AjoutEvenement.fxml"));
                    
                   


 
         } catch (IOException ex) {
           
        }
                     setNode(myNewScene);

    
    }
    private void invitButton(int evenementId) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/ajoutInvite.fxml"));
//        Parent root = loader.load();
//        AjoutInviteController controller = loader.<AjoutInviteController>getController();
//       // TextField t = new TextField();
//        //t.setText(String.valueOf(evenementId));
//        controller.setEventId(evenementId);
//        table.getScene().setRoot(root); 

 AnchorPane    myNewScene = null;
  
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/ajoutInvite.fxml"));
                    
                   


 
         } catch (IOException ex) {
           
        }
                     setNode(myNewScene);

    }
    
        
     private void modifButton(int evenementId) throws IOException {
         
         AnchorPane    myNewScene = null;

                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/updateE.fxml"));
                    
                   
                System.out.println("bonjour fedi");

 
         } catch (IOException ex) {
           
        }

             setNode(myNewScene);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/updateE.fxml"));
//        Parent root = loader.load();
//        UpdateEController controller = loader.<UpdateEController>getController();
//       // TextField t = new TextField();
//        //t.setText(String.valueOf(evenementId));
//        controller.setEventID(evenementId);
//         System.out.println(evenementId);
//        table.getScene().setRoot(root); 
        
        
    }

    private void ajoutInButton() {
       
        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final Button btn = new Button("Ajouter Inv");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            System.out.println("fedifedi");
                            idEvent1 = selectedCat.getId();
                            System.out.println("avant id = "+idEvent1);
                            ///EvenementService suppService = new EvenementService();
                            //suppService.deleteAction(selectedCat.getId());
                            //EvenementService es = new EvenementService();
                            //data = es.indexAction();
                            //table.setItems(data);
                            try {
                                //idEvent1 = selectedCat.getId();
                                //System.out.println(" id evenement = "+idEvent1);
                                //invitButton(idEvent1);
                                AnchorPane    myNewScene = null;
  
                   
        
               
                                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/ajoutInvite.fxml"));
                    
                   

                      setNode(myNewScene);
                            } catch (Exception e) {
                                         System.out.println("e : "+e.getMessage());
                                                                                 
                                         System.out.println("e"+e.getStackTrace());


                            }
                            
                           
                                
                            
                           
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table.setItems(ob);
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
        ajoutI.setCellFactory(cellFactory);

        }

    private void updateButton() {
        
         Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            idEvent1= selectedCat.getId();
                            ///EvenementService suppService = new EvenementService();
                            //suppService.deleteAction(selectedCat.getId());
                            //EvenementService es = new EvenementService();
                            //data = es.indexAction();
                            //table.setItems(data);
                            try {

                                modifButton(selectedCat.getId());
                            } catch (Exception e) {}
                            
                           
                                
                            
                           
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table.setItems(ob);
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
        ModifierEvent.setCellFactory(cellFactory);
    }

    private void listeIButton(int evenementId) throws IOException {
//       FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/ListInvite.fxml"));
//        Parent root = loader.load();
//        ListInviteController controller = loader.<ListInviteController>getController();
//        controller.setEventId(evenementId);
//         System.out.println("Bonjour liste des ivités");
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

                    private final Button btn = new Button("liste invités");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement selectedCat = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            idEvent1 = selectedCat.getId();
                            System.out.println("avant in :"+idEvent1);
                            ///EvenementService suppService = new EvenementService();
                            //suppService.deleteAction(selectedCat.getId());
                            //EvenementService es = new EvenementService();
                            //data = es.indexAction();
                            //table.setItems(data);
                            try {

                                listeIButton(idEvent1);
                            } catch (Exception e) {System.out.println("erreur yezzebi ma dkhalech"+e);}
                            
                           
                                
                            
                           
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table.setItems(ob);
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

//    private void engage_search(MouseEvent event) {
//        FilteredList<Evenement> filteredData;
//        filteredData = new FilteredList<>(data, b -> true);
//        // 2. Set the filter Predicate whenever the filter changes.
//		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(e -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (e.getNomEvenement().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//                                    System.out.println("nom");
//					return true; // Filter matches first name.
//				} else if (e.getLieuEvenement().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                                    System.out.println("nlieu");
//					return true; // Filter matches last name.
//				}
//				/*else if (String.valueOf(e.getDateEvenement()).indexOf(lowerCaseFilter)!=-1)
//				     return true;*/
//				     else  
//				    	 return false; // Does not match.
//			});
//		});
//		
//		// 3. Wrap the FilteredList in a SortedList. 
//		SortedList<Evenement> sortedData = new SortedList<>(filteredData);
//		
//		// 4. Bind the SortedList comparator to the TableView comparator.
//		// 	  Otherwise, sorting the TableView would have no effect.
//		sortedData.comparatorProperty().bind(table.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
//		table.setItems(sortedData);
//    }

//    private void afficheImage() {
//           Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
//            @Override
//            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
//                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
//
//                    private final ImageView btn = new ImageView();
//
//                    {
//                        System.out.println("aaaaaaa");
//                        // Evenement selectedCat = getTableView().getItems().get(getIndex());
//                        EvenementService es = new EvenementService();
//                           ImageView e =  new ImageView();
//                       
//                         System.out.println("selectedDat\n" +
//"                            ds.SupprimerDocument(data.getId());a: " );
//                            EvenementService suppService = new EvenementService();
//                            String img = " ";
//                        try {
//                            
//                             img =  es.nomImage(70);
//                             System.out.println("aqw"+img);
//                             
//                        } catch (SQLException ex) {
//                            Logger.getLogger(ListEvenementController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        System.out.println("image = "+img);
//                        btn.setImage(new Image("http://localhost/pidev20/web/uploads/images/a/"+img));
//                        
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        table.setItems(ob);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(btn);
//                        }
//                    }
//                };
//                return cell;
//            }
//        };
//        imageE.setCellFactory(cellFactory);
//       
//    }

    
     private void display(String img) throws IOException {

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/imageEvent.fxml"));
//        Parent root = loader.load();
//       Display controller = loader.<Display>getController();
//                   ImageView imageEV = null ;
//            controller.setImg(img);
//       // TextField t = new TextField();
//        //t.setText(String.valueOf(evenementId));
//        controller.setEventID(evenementId);
//         System.out.println(evenementId);
//        table.getScene().setRoot(root); 
        
        
        AnchorPane    myNewScene = null;
  
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/imageEvent.fxml"));
                    
                   


 
         } catch (IOException ex) {
           
        }
                     setNode(myNewScene);

//    
    }
     private void afficheImage() {
        

        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {

                    private final Button btn = new Button("Afficher Image ");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement selectedCat = getTableView().getItems().get(getIndex());
//                            System.out.println("selectedDat\n" +
//"                            ds.SupprimerDocument(data.getId());a: " + selectedCat);
                            EvenementService suppService = new EvenementService();
                            try {
                               String img = suppService.nomImage(selectedCat.getId());
                               idEvent1 = selectedCat.getId();
                                System.out.println(img);
                                image1 = "http://localhost/pidev20mod/web/uploads/images/a/"+img;
                                System.out.println("avant : "+image1);
                                display(image1);

          
            
           
          
                            } catch (SQLException ex) {
                                Logger.getLogger(ListEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) { 
                                Logger.getLogger(ListEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           EvenementService es = new EvenementService();
                            ob = es.indexAction();
                            table.setItems(ob);
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        table.setItems(ob);
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
        imageE.setCellFactory(cellFactory);

        }
    
}

    
   
    

//@FXML
//   
//    public void handle(ActionEvent event) throws IOException {
//        Parent root;
//      
//            root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/estimation.fxml"));
//            Stage stage = new Stage();
//            stage.setTitle("Estimation");
//            stage.setScene(new Scene(root, 520, 450));
//            stage.show();
//            // Hide this current window (if this is what you want)
//            //((Node)(event.getSource())).getScene().getWindow().hide();
//       
//        
//    
//}