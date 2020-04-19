/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Controller;

import Camp.Entity.Camp;
import Camp.Service.CampService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.security.auth.callback.Callback;
import javax.swing.JOptionPane;
import Camp.pidev2020.DataBase;

/**
 *
 * @author ME
 */
public class IndexCampContoller implements Initializable{
    Connection cx = DataBase.getInstance().getCnx();

    @FXML
    private TableView<Camp> table;
    @FXML
    private TableColumn<Camp, String> nom;
    @FXML
    private TableColumn<Camp, String> adresse;
    @FXML
    private TableColumn<Camp, String> nbrefugier;
     public ObservableList<Camp> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Camp, Void> supp;
    @FXML
    private Button ajouter;
    @FXML
    private TableColumn<Camp, String> NomResp;
    @FXML
    private Button CreatePdf;
    @FXML
    private AnchorPane indexPane;
    @FXML
    private JFXTextField searchTextField;
    @FXML
    private JFXButton searchButton;

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
        nom.setCellValueFactory(new PropertyValueFactory<Camp,String>("nomCamp"));
        adresse.setCellValueFactory(new PropertyValueFactory<Camp,String>("adresseCamp"));
        nbrefugier.setCellValueFactory(new PropertyValueFactory<Camp,String>("nbrefugier"));
        NomResp.setCellValueFactory(new PropertyValueFactory<Camp,String>("nomuser"));

        CampService ser = new CampService();
        data = ser.indexAction();
        table.setItems(data);
        System.out.println(data);
        suppButton();
        table.setEditable(true);
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        nbrefugier.setCellFactory(TextFieldTableCell.forTableColumn());
        NomResp.setCellFactory(TextFieldTableCell.forTableColumn());

    } 
    
    private void suppButton() {
        
        javafx.util.Callback<TableColumn<Camp, Void>, TableCell<Camp, Void>> cellFactory = new javafx.util.Callback<TableColumn<Camp, Void>, TableCell<Camp, Void>>() {
            @Override
            public TableCell<Camp, Void> call(final TableColumn<Camp, Void> param) {
                final TableCell<Camp, Void> cell = new TableCell<Camp, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Camp selectedCamp = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDat\n" +
"                            ds.SupprimerDocument(data.getId());a: " + selectedCamp);
                            CampService suppService = new CampService();
                            suppService.deleteAction(selectedCamp.getId());
                            CampService ser = new CampService();
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
        Camp c=table.getSelectionModel().getSelectedItem();
        c.setNomCamp(ec.getNewValue().toString());
        CampService ser = new CampService();
        ser.updateAction(c);
    }

    @FXML
    private void editAdresse(TableColumn.CellEditEvent ec) {
         Camp c=table.getSelectionModel().getSelectedItem();
        c.setAdresseCamp(ec.getNewValue().toString());
        CampService ser = new CampService();
        ser.updateAction(c);
    }
    @FXML
    private void editNbr(TableColumn.CellEditEvent ec) {
         Camp c=table.getSelectionModel().getSelectedItem();
        c.setNbrefugier(ec.getNewValue().toString());
        CampService ser = new CampService();
        ser.updateAction(c);
    }
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        
        AnchorPane    myNewScene = null;
                   
        try {
          myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/Views/AjouterCamp.fxml"));
                    
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexRefugieController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
       
    }

    @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
        try {
        Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\ME\\Documents\\NetBeansProjects\\pidev2020-v2\\pidev2020\\src\\Camp\\pdf\\RefugiePDF.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(4);
       table.setWidthPercentage(100);
       table.getDefaultCell().setBorder(2);
       table.addCell("nomCamp");
       table.addCell("adresseCamp");
       table.addCell("nbrefugier");
       table.addCell("nomuser");
       
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Refugie List ");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                  // document.add(com.itextpdf.text.Image.getInstance("C:/Users/pablo/Documents/NetBeansProjects/Pi_dev/src/Images/bank.png"));

       //Class.forName("com.mysql.jdbc.Driver");
       //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/velo", "root", "");
       Statement st=cx.createStatement();
       ResultSet rs=st.executeQuery("SELECT nomCamp, adresseCamp, nbrefugier,nomuser, responsable_id FROM `camp`");
       while(rs.next()){
       table.addCell(rs.getString("nomCamp"));
       table.addCell(rs.getString("adresseCamp")); //name in DB

       table.addCell(rs.getString("nbrefugier"));
       table.addCell(rs.getString("nomuser")); //name in DB
       }
       document.add(table);
        JOptionPane.showMessageDialog(
                null, " données exportées en pdf avec succés ");
               document.close();
           
            

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
   
    }

    @FXML
    private void handleSearchDemande(ActionEvent event) {
    try
        {
          //recuperer la liste des demande dans un ObservableArrayList 
        CampService ser = new CampService();
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
