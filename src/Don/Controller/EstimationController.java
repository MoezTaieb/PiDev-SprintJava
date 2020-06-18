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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;

/**
 * FXML Controller class
 *
 * @author amin
 */
public class EstimationController implements Initializable {
  
    @FXML
    
    private ChoiceBox<CategorieEquipment> cat;
    public ObservableList<CategorieEquipment> data = FXCollections.observableArrayList();
   
    @FXML
    private TextField e1;
    @FXML
    private TextField e2 ;
      @FXML
    private TextField e3;
    @FXML
    private TextField e4 ;
     

    @FXML
    private Button esti;
   

    @FXML
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         CategorieEquipmentService ser = new CategorieEquipmentService();
        data = ser.indexAction();
        cat.setItems(data);
          CategorieEquipment p = new CategorieEquipment();
           EquipmentService e =new EquipmentService();
       
         float  t=e.nb();
         float  k=e.nbmonth();
         float  c=e.nbweek();
          String s=String.valueOf(t);  
        e2.setText(s);
         String s1=String.valueOf(k); 
         e3.setText(s1);
          String s2=String.valueOf(c); 
        e4.setText(s2);
    }    
         @FXML
    private void esti(ActionEvent event) throws IOException {
         CategorieEquipment p = new CategorieEquipment();
           EquipmentService e =new EquipmentService();
        p = cat.getValue();
        
    
   String sa=String.valueOf(p);
        
            
      float  v=e.nbcat(sa);
    String a=String.valueOf(v);  
        
        e1.setText(a);
     

}
}
   