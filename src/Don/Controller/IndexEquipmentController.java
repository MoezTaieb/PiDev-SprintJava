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
public class IndexEquipmentController implements Initializable {
    @FXML
    private JFXButton retour ;
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
        @FXML
    private Button estimation ;
       @FXML
    private Button  handle;
        @FXML
    private Button add;
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
       data = ser.indexAction();
        table.setItems(data);
        System.out.println(data);
          suppButton();
            table.setEditable(true);
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
     @FXML
   
    public void handle(ActionEvent event) throws IOException {
        Parent root;
      
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Don/Views/estimation.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Estimation");
            stage.setScene(new Scene(root, 800, 550));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
       
        
    
}
   @FXML
    
        private void add(ActionEvent event) throws IOException {
    
           
              Parent root;
      
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Don/Views/ajoutEquipment.fxml"));
            Stage stage = new Stage();
            stage.setTitle("ajout Equipment");
            stage.setScene(new Scene(root, 730,330));
            stage.show();
            
            
    }
        
  @FXML
    private void est(ActionEvent event) throws IOException, DocumentException {
       /*XMLLoader loader = new FXMLLoader(getClass().getResource("/Views/traiterEquipment.fxml"));
        Parent root = loader.load();
        table.getScene().setRoot(root);*/
       String path="C:\\Users\\amin\\Documents\\NetBeansProjects\\PiDev\\equipment.pdf";
      // JFileChooser j=new JFileChooser();
       //j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     //int x=  j.showSaveDialog(j);
     //if(x==JFileChooser.APPROVE_OPTION)
     //{
     //path=j.getSelectedFile().getPath();
     //}
         Document doc  =new Document(); 
     
       
            PdfWriter.getInstance(doc, new FileOutputStream(path));
         EquipmentService e =new EquipmentService();
        doc.open();
         PdfPTable table1=new PdfPTable(4);
       table1.setWidthPercentage(100);
       table1.getDefaultCell().setBorder(2);
       table1.addCell("nom");
       table1.addCell("nb equipment");
       table1.addCell("etat");
       table1.addCell("nomcategorie");
       
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Equipment List ");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
         
      // Paragraph para2 =new Paragraph("le nom de l equipment est ");

          for (int i = 0; i < table.getItems().size(); i++)
              
              
              
              
     { 
     String r=table.getItems().get(i).getNomEquipment().toString();
         int a =e.indexAction().get(i).getNb_equipment();
         String s=String.valueOf(a);
         table1.addCell(e.indexAction().get(i).getNomEquipment());
     table1.addCell(s);
      table1.addCell(e.indexAction().get(i).getEtatEquipment());
      table1.addCell(e.indexAction().get(i).getNomCategorie()); 

           /* Paragraph para2 =new Paragraph("le nom de l equipment est "
                    + ""+e.indexAction().get(i).getNomEquipment()+" la quantite "+
                    e.indexAction().get(i).getNb_equipment()+" son etat"+
                    e.indexAction().get(i).getEtatEquipment()+" il apartient a la categorie"
            
            +e.indexAction().get(i).getNomCategorie()
          
            );  */
         
    //     doc.add(para2);
              doc.add(table1);
          

          
       
        
            System.out.println("finish");
       
        
        
        
        
        
        
        
        
       
        
         
    
       
        
        
  }
          
          doc.close();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Génération du PDF");
        a.setHeaderText("Liste des produits exportée");
        a.setContentText("Chemin du fichier : "+path);
        a.showAndWait();
                   TextArea textArea = new TextArea();
        textArea.setMinHeight(70);
        final FileChooser fileChooser = new FileChooser();
           textArea.clear();
        Window stage = null;
           
                File file = fileChooser.showOpenDialog(stage);
                 System.out.println(file);
//                 path=file.getAbsolutePath();
                if (file != null) {
                    
                    openFile(file);
                    List<File> files = Arrays.asList(file);
                    printLog(textArea, files);
               
                
                 fileChooser.setTitle("Select Some Files");
 
        // Set Initial Directory
           fileChooser.setInitialDirectory(new File(System.getProperty("user")));
            
        
       
     

       
        
       
     

       
            }



 
    
}

    


// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    private void openFile(File file) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     try {
            this.desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
    
    }
    }
    private void printLog(TextArea textArea, List<File> files) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    
      
    if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }
    }







@FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/user/DashAdmin.fxml"));
        Parent root = loader.load();
        table.getScene().setRoot(root);
    }
}

    
  
   
     

  