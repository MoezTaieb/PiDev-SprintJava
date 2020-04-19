/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Controller;

import Camp.Entity.Camp;
import Camp.Entity.Refugie;
import Camp.Service.CampService;
import Camp.Service.RefugieService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ME
 */
public class AjouterRefugieController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button ajouter;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField nationalite;
    @FXML
    private ImageView image;
    @FXML
    private TextField numpassport;
    @FXML
    private TextField tel;
    @FXML
    private ChoiceBox<Camp> camp;
    public ObservableList<Camp> data = FXCollections.observableArrayList();
    @FXML
    private Pane cancel;
    @FXML
    private Button upload;
    
    String path = "";
    @FXML
    private AnchorPane indexPane;
    
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
        CampService ser = new CampService();
        data = ser.indexAction();
        camp.setItems(data);
    }

    @FXML
    private void addRefugier(ActionEvent event) throws IOException {
        Camp C = new Camp();
        C = camp.getValue();
        Refugie R = new Refugie(C.getId() ,nom.getText(),prenom.getText(),adresse.getText(),tel.getText(),numpassport.getText(),nationalite.getText(),path);        
        RefugieService ser = new RefugieService();
        ser.newAction(R);
 
        AnchorPane    myNewScene = null;
                   
        try {
          myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/Views/indexRefugie.fxml"));
                    
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexRefugieController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
                    setNode(myNewScene);

    }

      @FXML
    private void cancel(MouseEvent event) throws IOException {
        
       AnchorPane    myNewScene = null;
                   
        try {
          myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/Views/indexRefugie.fxml"));
                    
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexRefugieController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
                    setNode(myNewScene);
    }

    @FXML
    private void upload(ActionEvent event) {
        BufferedOutputStream stream = null;
	String globalPath="C:\\xampp\\htdocs\\Images";
        
        
        try {
        
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "jpeg", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {         
            
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getName();
            
            Path p = selectedFile.toPath();      
            byte[] bytes = Files.readAllBytes(p); 
            File dir = new File(globalPath);
            
            File serverFile = new File(dir.getAbsolutePath()+File.separator + path);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            
            
            String path2 = selectedFile.toURI().toURL().toString();
            System.out.println(path);
            Image image = new Image(path2);
            this.image.setImage(image);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }
        
                } catch (IOException ex) {
            Logger.getLogger(RefugieService.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
   }
    

