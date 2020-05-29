/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produit.Controller;

import static Produit.Controller.AjouterProduitController.searchName;
import Produit.Entity.CategorieProduit;
import Produit.Entity.Produit;
import Produit.Service.CategorieProduitService;
import Produit.Service.ProduitService;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Othmen
 */
public class MajProduitController implements Initializable {

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
    
    private int produit_id;
    @FXML
    private Button upload;
    @FXML
    private ImageView img;
    String path = "";
    @FXML
    private Label nomImage;
    @FXML
    private JFXButton retour;
    @FXML
    private Label title;
    Image defaultImg = new Image("file:D:\\3A20\\Semestre2\\PI\\Java\\pidev2020\\src\\Images\\Produit.png");
    
    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Produit P = new Produit();
        Gson gson = new Gson();
        
        try {
            P = gson.fromJson(new FileReader("config.txt"), Produit.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MajProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
            produit_id = Integer.valueOf(P.getId());
            title.setText("Modification : "+ P.getNomProduit());
            String selectedImgPath = String.valueOf(P.getDescriptionProduit());
            Image selecImage = new Image("http://localhost/Images/"+selectedImgPath);
            img.setImage(selecImage);
        
        
        CategorieProduitService ser = new CategorieProduitService();
        data = ser.indexAction();
        categorie.setItems(data);
        initDefaultValues();
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
            Produit prod = getProduit();
            Produit P = new Produit(produit_id, nom.getText(),prix.getText(), qte.getText(), path, C.getId());
            if(P.getDescriptionProduit().isEmpty()){
                P.setDescriptionProduit("Produit.png");
            }
            if(!P.getNomProduit().equals(prod.getNomProduit()) && (searchName(nom.getText()) == true)){
                verifPrix.setText("");
                verifQte.setText("");
                verifCat.setText("");
                Alert A = new Alert(Alert.AlertType.ERROR);
                A.setTitle("Modification non effectuée");
                A.setHeaderText("");
                A.setContentText("Nom de Produit existant !");
                A.show();
            }
            else{
                ProduitService ser = new ProduitService();
                ser.updateAction(P);
                verifNom.setText("");
                verifPrix.setText("");
                verifQte.setText("");
                verifCat.setText("");
                notifMaj(prod, P);
                AnchorPane    myNewScene = null;
                try {
                    myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/indexProduit.fxml"));
 
                } catch (IOException ex) {
                Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
                setNode(myNewScene);
                }   
            
        }
    }
    
    public static void notifMaj(Produit PJson, Produit P){
            TrayNotification tray = new TrayNotification();
            ProduitService ps = new ProduitService();
            String nomCat = ps.nomCategorie(P.getCategorie_id());
            String title;
            String message = "Anciennes valeurs{Prix: "+PJson.getPrixProduit()+", Quantité: "+PJson.getQteProduit()+", Catégorie: "+PJson.getNomCategorie()+"}\n"
                        + "Nouvelles valeurs{Prix: "+P.getPrixProduit()+", Quantité: "+P.getQteProduit()+", Catégorie: "+nomCat+"}";
            String vide = "";
                if(!PJson.getNomProduit().equals(P.getNomProduit())){
                    title = "Nom de Produit ("+PJson.getNomProduit()+") est changé à ("+P.getNomProduit()+")";
                    tray.setMessage(message);
                }
                if(PJson.getNomProduit().equals(P.getNomProduit()) && (PJson.getPrixProduit().equals(P.getPrixProduit())) 
                        && (PJson.getQteProduit().equals(P.getQteProduit())) && (PJson.getNomCategorie().equals(nomCat))){
                    title = "Produit: "+PJson.getNomProduit()+" n'est pas modifié" ;
                    tray.setMessage(vide);
                }
                else{
                    title = "Produit: "+PJson.getNomProduit()+" modifié" ;
                    tray.setMessage(message);
                }
                tray.setTitle(title);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(5));
        }

    @FXML
    private void annuler(ActionEvent event) {
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
    
    public static Produit getProduit(){
        Gson gson = new Gson();
        Produit prod = new Produit();
        try {
             prod = gson.fromJson(new FileReader("config.txt"), Produit.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MajProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }
    
    public void initDefaultValues(){
        Produit prod = getProduit();
        nom.setText(String.valueOf(prod.getNomProduit()));
        prix.setText(String.valueOf(prod.getPrixProduit()));
        qte.setText(String.valueOf(prod.getQteProduit()));
        Image selectedImage = new Image( "http://localhost/Images/"+prod.getDescriptionProduit() );
        img.setImage(selectedImage);
        
        
    }

    @FXML
    private void upload(ActionEvent event) {
        BufferedOutputStream stream = null;
	String globalPath="D:\\\\wamp64\\\\www\\\\Images";
        
        
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
            img.setImage(image);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }
        
                } catch (IOException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

    @FXML
    private void retour(MouseEvent event) {
        AnchorPane    myNewScene = null;
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Produit/Views/indexProduit.fxml"));
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }

             setNode(myNewScene);
    }

 
    
}
