/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.Controller;

import Produit.Entity.CategorieProduit;
import Produit.Entity.Produit;
import static Produit.Entity.Produit.CONFIG_FILE;
import Produit.Service.CategorieProduitService;
import Produit.Service.ProduitService;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
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
public class IndexProduitController implements Initializable {

    @FXML
    AnchorPane indexPane;
    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<Produit, String> nom;
    @FXML
    private TableColumn<Produit, String> categorie;
    @FXML
    private TableColumn<Produit, String> prix;
    @FXML
    private TableColumn<Produit, String> qte;
    private TableColumn<Produit, String> desc;
    public ObservableList<Produit> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Produit, Void> supp;
    @FXML
    private ComboBox<CategorieProduit> filter;
    public ObservableList<CategorieProduit> datacat = FXCollections.observableArrayList();
    public FilteredList filterdata = new FilteredList(data, e->true);
    @FXML
    private TableColumn<Produit, Void> modif;
    @FXML
    private ImageView image;
    
    private Produit selectedProd;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton pdf;
    @FXML
    private JFXButton retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
        categorie.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomCategorie"));
        prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prixProduit"));
        qte.setCellValueFactory(new PropertyValueFactory<Produit,String>("qteProduit"));
        
        
        ProduitService ser = new ProduitService();
        data = ser.indexAction();
        table.setItems(data);
        updateButton();
        suppButton();
        table.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        prix.setCellFactory(TextFieldTableCell.forTableColumn());
        qte.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        CategorieProduitService serCat = new CategorieProduitService();
        datacat = serCat.indexAction();
        filter.setItems(datacat);
        
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

 
    
    private void modifButton(int produit_id) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/MajProduit.fxml"));
//        Parent root = loader.load();
//        MajProduitController controller = loader.<MajProduitController>getController();
//        controller.setProduit_id(produit_id);
//         System.out.println(produit_id);
//        table.getScene().setRoot(root); 

                       
        AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/MajProduit.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }

             setNode(myNewScene);

    }
    
    private void updateButton() {
        
         Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setId("blue");
                        btn.setOnAction((ActionEvent event) -> {
                            Produit selectedProd = getTableView().getItems().get(getIndex());
                            try {
                                initConfig(selectedProd);
                                modifButton(selectedProd.getId());
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
        modif.setCellFactory(cellFactory);
    }
    
    private void suppButton() {
        

        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setId("red");
                        btn.setOnAction((ActionEvent event) -> {
                            Produit selectedProd = getTableView().getItems().get(getIndex());
                            ProduitService suppService = new ProduitService();
                            suppService.deleteAction(selectedProd.getId());
                            ProduitService ser = new ProduitService();
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
        Produit p=table.getSelectionModel().getSelectedItem();
        p.setNomProduit(ec.getNewValue().toString());
        ProduitService ser = new ProduitService();
        ser.updateAction(p);
    }

    @FXML
    private void editPrix(TableColumn.CellEditEvent ec) {
        Produit p=table.getSelectionModel().getSelectedItem();
        String note=ec.getNewValue().toString();
        p.setPrixProduit(note);
        ProduitService ser = new ProduitService();
        ser.updateAction(p);
    }

    @FXML
    private void editQte(TableColumn.CellEditEvent ec) {
        Produit p=table.getSelectionModel().getSelectedItem();
        p.setQteProduit(ec.getNewValue().toString());
        ProduitService ser = new ProduitService();
        ser.updateAction(p);
    }

    @FXML
    private void ajouter(MouseEvent event) throws IOException {
        AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/AjouterProduit.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }

             setNode(myNewScene);
        
//        Parent root;
//        root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/AjouterProduit.fxml"));
//        Stage stage = new Stage();
//        stage.setTitle("Ajouter un nouveau produit");
//        stage.setScene(new Scene(root, 1000, 500));
//        stage.show();
            
    }
    
    @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Produit/Views/Dash.fxml"));
        Parent root = loader.load();
        table.getScene().setRoot(root);
    }

    @FXML
    private void filterCat(ActionEvent event) {
        CategorieProduit C = new CategorieProduit();
        C = (CategorieProduit) filter.getValue();
        image.setImage(null);
        
        nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
        categorie.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomCategorie"));
        prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prixProduit"));
        qte.setCellValueFactory(new PropertyValueFactory<Produit,String>("qteProduit"));
        
        ProduitService ser = new ProduitService();
        data = ser.filterAction(C.getId());
        table.setItems(data);
    }
/*
    @FXML
    private void search(KeyEvent event) {
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filterdata.setPredicate((Predicate<? super Produit>)(Produit produit)->{
                
                if (newValue.isEmpty()|| newValue == null){
                    return true;
                }
                else if (produit.getNomProduit().contains(newValue)){
                    return true;
                }
            
            return false;
            });
        });
        SortedList sort = new SortedList(filterdata);
        sort.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sort);
    }
*/    

    private void initialize(ActionEvent event) {
        nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
        categorie.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomCategorie"));
        prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prixProduit"));
        qte.setCellValueFactory(new PropertyValueFactory<Produit,String>("qteProduit"));
        //qte.setStyle("-fx-background-color: red;");
        desc.setCellValueFactory(new PropertyValueFactory<Produit,String>("descriptionProduit"));
        
        ProduitService ser = new ProduitService();
        data = ser.indexAction();
        table.setItems(data);
        suppButton();
        table.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        prix.setCellFactory(TextFieldTableCell.forTableColumn());
        qte.setCellFactory(TextFieldTableCell.forTableColumn());
        desc.setCellFactory(TextFieldTableCell.forTableColumn());
        
        CategorieProduitService serCat = new CategorieProduitService();
        datacat = serCat.indexAction();
        filter.setItems(datacat);
    }

    @FXML
    private void pdf(MouseEvent event) throws IOException, DocumentException {
       String path = "D:\\3A20\\Liste_Produits.pdf";
       Document doc  = new Document();
       PdfWriter.getInstance(doc, new FileOutputStream(path));
       ProduitService ser = new ProduitService();
       doc.open();
       PdfPTable tablepdf = new PdfPTable(8);
       
       for (int i = 0; i < table.getItems().size(); i++)
       {
           Paragraph listp =new Paragraph("- "
                    + ser.indexAction().get(i).getNomProduit()
                    + " (Prix :  " + ser.indexAction().get(i).getPrixProduit() + " DT"
                    + ", Quantité : " + ser.indexAction().get(i).getQteProduit()+ " pièce(s)"
                    + ", Catégorie : " + ser.indexAction().get(i).getNomCategorie()
                    + " )");
           doc.add(listp);
        }
       
        doc.close();
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Génération du PDF");
        a.setHeaderText("Liste des produits exportée");
        a.setContentText("Chemin du fichier : "+path);
        a.showAndWait();
    }
    
    public static void initConfig(Produit P){
        Writer writer = null;
        try {
            Gson gson = new Gson();
            writer = new FileWriter(CONFIG_FILE);
            gson.toJson(P, writer);
                    } catch (IOException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void afficherImage(MouseEvent event) {
        selectedProd = (Produit) table.getSelectionModel().getSelectedItem();
        Image selectedImage = new Image( "http://localhost/Images/"+selectedProd.getDescriptionProduit() );
        image.setImage(selectedImage);
    }

    @FXML
    private void reset(MouseEvent event) {
        nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomProduit"));
        categorie.setCellValueFactory(new PropertyValueFactory<Produit,String>("nomCategorie"));
        prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prixProduit"));
        qte.setCellValueFactory(new PropertyValueFactory<Produit,String>("qteProduit"));
        
        ProduitService ser = new ProduitService();
        data = ser.indexAction();
        table.setItems(data);
        image.setImage(null);
        updateButton();
        suppButton();
        table.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        prix.setCellFactory(TextFieldTableCell.forTableColumn());
        qte.setCellFactory(TextFieldTableCell.forTableColumn());
       
        
        CategorieProduitService serCat = new CategorieProduitService();
        datacat = serCat.indexAction();
        filter.setItems(datacat);
    }

}
