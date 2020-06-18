/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.Controller;

import Produit.Entity.CategorieProduit;
import Produit.Entity.Produit;
import Produit.Service.CategorieProduitService;
import Produit.Service.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Othmen
 */
public class AjouterProduitController implements Initializable {

    @FXML
    AnchorPane indexPane;
    @FXML
    private TextField nom;
    @FXML
    private Button ajouter;
    @FXML
    private TextField prix;
    @FXML
    private TextField qte;
    @FXML
    private JFXComboBox<CategorieProduit> categorie;
    public ObservableList<CategorieProduit> data = FXCollections.observableArrayList();
    @FXML
    private Button annuler;
    @FXML
    private Label verifNom;
    @FXML
    private Label verifPrix;
    @FXML
    private Label verifQte;
    @FXML
    private Label verifCat;
    @FXML
    private ImageView img;
    @FXML
    private Button upload;
    
    String path = "";
    @FXML
    private Label nomImage;
    Image defaultImg = new Image("file:C:\\Users\\amin\\Documents\\NetBeansProjects\\s\\pidev2020\\src\\Produit\\Images\\Produit.png");
    
    @FXML
    private JFXButton retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategorieProduitService ser = new CategorieProduitService();
        data = ser.indexAction();
        categorie.setItems(data);
        img.setImage(defaultImg);
        
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
    
    public static boolean isNumeric(String strNum) {
    if (strNum == null) 
    {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
    }
    
    public static boolean searchName(String Nom){
        ObservableList<Produit> data = FXCollections.observableArrayList();
        ProduitService ser = new ProduitService();
        data = ser.searchNameAction(Nom);
        if (!data.isEmpty()){
            return true;
        }
        else return false;
    }
    
    @FXML
    private void addProduit(MouseEvent event) throws IOException {
       if (nom.getText().isEmpty() || prix.getText().isEmpty() || qte.getText().isEmpty() || categorie.getValue() == null){
           if((nom.getText().isEmpty() || nom.getText().length() > 20)){
            verifNom.setText("Nom invalide.");
            }
            if(!(nom.getText().isEmpty() || nom.getText().length() > 20)){
            verifNom.setText("");
            }
            
            if(!(prix.getText().matches("([0-9]+(\\.[0-9]+)?)+")) || Float.parseFloat(prix.getText()) <= 0){
            verifPrix.setText("Prix invalide.");
            }
            if(!(!(prix.getText().matches("([0-9]+(\\.[0-9]+)?)+")) || Float.parseFloat(prix.getText()) <= 0)){
            verifPrix.setText("");
            }
            
            if(!(qte.getText().matches("([0-9]+(\\.[0-9]+)?)+"))){
            verifQte.setText("Quantité invalide.");
            }
            if(!(!(qte.getText().matches("([0-9]+(\\.[0-9]+)?)+")))){
            verifQte.setText("");
            }
            if(categorie.getValue() == null){
                verifCat.setText("Catégorie requise.");
            }
            if(categorie.getValue() != null){
                verifCat.setText("");
            }
       }
       else{
            CategorieProduit C = new CategorieProduit();
            C = categorie.getValue();
            Produit P = new Produit(nom.getText(),prix.getText(), qte.getText(), path, C.getId());
            if(P.getDescriptionProduit().isEmpty()){
                P.setDescriptionProduit("Produit.png");
            }
            if(searchName(nom.getText()) == true){
                verifPrix.setText("");
                verifQte.setText("");
                verifCat.setText("");
                Alert A = new Alert(Alert.AlertType.ERROR);
                A.setTitle("Ajout non effectué");
                A.setHeaderText("");
                A.setContentText("Nom de Produit existant !");
                A.show();
            }
            else{
                ProduitService ser = new ProduitService();
                ser.newAction(P);
                img.setImage(defaultImg);
                nom.clear();
                verifNom.setText("");
                prix.clear();
                verifPrix.setText("");
                qte.clear();
                verifQte.setText("");
                categorie.setValue(null);
                verifCat.setText("");
        
                String title = "Nouveau Produit ajouté";
                String message = "Nom : "+P.getNomProduit()+" / Prix : "+P.getPrixProduit()+" / Quantité : "+P.getQteProduit();
        

                TrayNotification tray = new TrayNotification();
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(5));
            }
            
        }
        

        
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        nom.clear();
        prix.clear();
        qte.clear();
        categorie.setValue(null);
        verifNom.setText("");
        verifPrix.setText("");
        verifQte.setText("");
        verifCat.setText("");
        img.setImage(defaultImg);
        
    }

    @FXML
    private void upload(ActionEvent event) {
        BufferedOutputStream stream = null;
	String globalPath="c:\\\\wamp64\\\\www\\\\Images";
        
        
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
            System.out.println(path2);
            Image image = new Image(path2);
            img.setImage(image);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }
        
                } catch (IOException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
                             AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/indexProduit.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
    }

}
