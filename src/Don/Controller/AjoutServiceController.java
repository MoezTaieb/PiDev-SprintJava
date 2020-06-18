
/**
 * FXML Controller class
 *
 * @author amin
 */



package Don.Controller;


import Don.Entity.CategorieService;
import Don.Entity.Equipment;
import Don.Entity.Service;
import Don.Service.CategorieEquipmentService;
import Don.Service.CategorieServiceService;

import Don.Service.EquipmentService;
import Don.Service.ServiceService;
//import com.teknikindustries.bulksms.SMS;
import static de.jensd.fx.glyphs.materialicons.MaterialIcon.SMS;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pidev.PiDev;

/**
 * FXML Controller class
 *
 * @author amin
 */
public class AjoutServiceController implements Initializable {
   @FXML
    private Button ajouter;
   
    @FXML
    private TextField nom;
   
           ;@FXML
    private TextField description ;
           ;
            @FXML
    private ChoiceBox<CategorieService> catid;
    public ObservableList<CategorieService> data1 = FXCollections.observableArrayList();
    @FXML
   
    private TableView<Service>table;
     @FXML
    private TableColumn<Service, String>id;
    @FXML
    private TableColumn<Service, String>nom1;
    @FXML
    private TableColumn<Service, String>description1;
    @FXML
    private TableColumn<Service, String>date;
      @FXML
    private TableColumn<Service, String>catid1;
      
     public ObservableList<Service> data = FXCollections.observableArrayList();
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          CategorieServiceService ser = new CategorieServiceService();
        data1 = ser.indexAction();
        catid.setItems(data1);
         id.setCellValueFactory(new PropertyValueFactory<Service,String>("id"));
     nom1.setCellValueFactory(new PropertyValueFactory<Service,String>("nomService"));
      date.setCellValueFactory(new PropertyValueFactory<Service,String>("dateDon"));
       description1.setCellValueFactory(new PropertyValueFactory<Service,String>("descriptionService"));
       catid1.setCellValueFactory(new PropertyValueFactory<Service,String>("nomCategorie"));
       ServiceService sere = new ServiceService();
     data = sere.indexAction2();
        table.setItems(data);
        System.out.println(data);
    }    
 
       @FXML
    private void addService(ActionEvent event) throws IOException {
       
        if (nom.getText() == null || nom.getText().length() == 0) {
               Alert alert = new Alert(Alert.AlertType.WARNING);
    //  alert.initOwner(AjoutEquipmentController.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No nom Service Selected");
        alert.setContentText("Please select a Service .");

        alert.showAndWait();
    
        }
          if (description.getText() == null || description.getText().length() == 0) {
               Alert alert = new Alert(Alert.AlertType.WARNING);
    //  alert.initOwner(AjoutEquipmentController.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No description Service Selected");
        alert.setContentText("Please select a Service .");

        alert.showAndWait();
    
        }
          else{
        CategorieService p = new CategorieService();
        p = catid.getValue();
        Service C = new Service(description.getText(),nom.getText(),p.getId());
           ServiceService ser = new ServiceService();
      //   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //  alert.initOwner(AjoutEquipmentController.getPrimaryStage());
   //     alert.setTitle("ajout reussi");
     //   alert.setHeaderText(" service ajouté ");
       // alert.setContentText("merci pour votre don d Equipment Mr."+PiDev.currentUser.getNomUser()+"en attend confirmation du code SMS" );
         // alert.showAndWait();
     
        
     ser.newAction(C);
        ServiceService sere = new ServiceService();
     data = sere.indexAction2();
        table.setItems(data);
        
          Random rand = new Random(); 
      //Generate random int value from 50 to 100 
     int rand_int1 = rand.nextInt(10000); 
      String str3 = String.valueOf(rand_int1); 
 
  
        // Print random integers 
        System.out.println("Random Integers: "+rand_int1);
        
        
       // SMS sms=new SMS();
         String id =PiDev.currentUser.getNomUser();
         
                String nt= "+216"+53418774;
         //   sms.SendSMS("teted","25718774aA", "pour valider votre demande saisir ce code"+rand_int1, nt ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
   
       TextInputDialog dialog = new TextInputDialog("****");
dialog.setTitle("ajout reussi");
dialog.setHeaderText("service ajouté,attante de confirmation");
dialog.setContentText("merci pour votre don d Equipment Mr."+PiDev.currentUser.getNomUser()+"en attend confirmation du code SMS\"");

// Traditional way to get the response value.
Optional<String> result = dialog.showAndWait();
if (result.get().equals(str3)){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
 
       alert.setTitle("ajout reussi");
       alert.setHeaderText(" code correct ");
       alert.setContentText("merci pour votre don   Mr."+PiDev.currentUser.getNomUser() );
         alert.showAndWait();
}

// The Java 8 way to get the response value (with lambda expression).
else
{
 Alert alert = new Alert(Alert.AlertType.ERROR);
 
       alert.setTitle("ajout no reussi");
       alert.setHeaderText(" code incorrect ");
       alert.setContentText("merci de retapper le code  Mr."+PiDev.currentUser.getNomUser()+" confirmation du code SMS" );
         alert.showAndWait();

}
        
    

    }
}}
