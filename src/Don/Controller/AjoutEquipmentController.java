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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.PiDev;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author amin
 */
public class AjoutEquipmentController implements Initializable {
   @FXML
    private Button ajouter;
   
    @FXML
    private TextField noms;
    @FXML
    private TextField nb ;
    @FXML
    private ChoiceBox<CategorieEquipment> catid;
    public ObservableList<CategorieEquipment> data2 = FXCollections.observableArrayList();
    @FXML

    private TableView<Equipment>table;
     @FXML
    private TableColumn<Equipment, Integer>id;
    @FXML
    private TableColumn<Equipment, String>nom;
    @FXML
    private TableColumn<Equipment, String>etat;
    @FXML
    private TableColumn<Equipment, Integer>nb1;
    @FXML
    private TableColumn<Equipment, String>catid1;
       @FXML
    private TableColumn<Equipment, Integer>date;
     public ObservableList<Equipment> data = FXCollections.observableArrayList();
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        
        
        
        
     id.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Equipment,String>("nomEquipment"));
            etat.setCellValueFactory(new PropertyValueFactory<Equipment,String>("etatEquipment"));
                nb1.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("nb_equipment"));
                    catid1.setCellValueFactory(new PropertyValueFactory<Equipment,String>("nomCategorie"));
                    date.setCellValueFactory(new PropertyValueFactory<Equipment,Integer>("dateDonEquipment"));
          EquipmentService ser = new EquipmentService();
       data = ser.indexAction2();
        table.setItems(data);
        System.out.println(data);
        
         
        EquipmentService e =new EquipmentService();
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        CategorieEquipmentService sere = new CategorieEquipmentService();
        data2 = sere.indexAction();
        catid.setItems(data2);
        
    }    
 
       @FXML
    private void addEquipment(ActionEvent event) throws IOException {
        CategorieEquipment p = new CategorieEquipment();
        p = catid.getValue();
         if (noms.getText() == null || noms.getText().length() == 0) {
               Alert alert = new Alert(AlertType.WARNING);
    //  alert.initOwner(AjoutEquipmentController.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No nom equipment Selected");
        alert.setContentText("Please select a Equipment .");

        alert.showAndWait();
    
        }
           else if (nb.getText() == null || nb.getText().length() == 0) {
          Alert alert = new Alert(AlertType.WARNING);
    //  alert.initOwner(AjoutEquipmentController.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("no nb equipment Selected");
        alert.setContentText("Please select a nb Equipment .");

        alert.showAndWait();
        } else  {
           
            try {
                Integer.parseInt(nb.getText());
            } catch (NumberFormatException e) {
            //    nb += "No valid postal code (must be an integer)!\n";
            }
        }
         
        Alert alert = new Alert(AlertType.CONFIRMATION);
    //  alert.initOwner(AjoutEquipmentController.getPrimaryStage());
        alert.setTitle("ajout reussi");
        alert.setHeaderText(" equipment ajouté ");
        alert.setContentText("merci pour votre don d Equipment Mr."+PiDev.currentUser.getNomUser() );
          alert.showAndWait();
        Equipment C = new Equipment(Integer.parseInt(nb.getText()),noms .getText(),p.getId());
           EquipmentService ser = new EquipmentService();
      ser.newAction(C);
      
      //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Don/Views/indexEquipment.fxml"));
        //Parent root = loader.load();
        //nom.getScene().setRoot(root);
         // Parent root = loader.load();
      //  nom.getScene().setRoot(root);
        
        EquipmentService sers = new EquipmentService();
       data = sers.indexAction2();
        table.setItems(data);
        
         String id =PiDev.currentUser.getNomUser();
         
         
     
     
        
    }
      @FXML
   
    public void historique(ActionEvent event) throws IOException {
        Parent root;
      
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Don/Views/historique.fxml"));
            Stage stage = new Stage();
            stage.setTitle("historique personnelle");
            stage.setScene(new Scene(root, 520, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
       
        
    
}
}
