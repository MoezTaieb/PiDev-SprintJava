/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Communication.DAO.AnnonceDao;
import Communication.entity.Annonce;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.PiDev;
import user.CommonMethod;
import user.ProfilController;
import user.entity.User;
import user.DAO.UserDao;
import user.service.UploadImage;

/**
 *
 * @author fedi
 */
public class EditAdController extends CommonMethod implements Initializable {

    @FXML
    JFXTextArea decr;
    @FXML
    JFXTextField title;
    @FXML
    ImageView imageProfil;
    String imageProfilUrl = null;
    Annonce annonce;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        annonce = new AnnonceDao().get(PiDev.annonceView);
        decr.setText(annonce.getDescriptionAnnonce());
        title.setText(annonce.getTitreAnnonce());
        String imageUrl;
        FileInputStream input = null;
        Image image = null;

            
                    try {

//                        input = new FileInputStream(PiDev.brochures_directory + annonce.getImageUrlAnnonce());
                        if (annonce.getImageUrlAnnonce() != null) {
                            image = new Image(PiDev.brochures_host + annonce.getImageUrlAnnonce());
                        } else {
                           input = new FileInputStream(".\\src\\icons\\picture.png");
                            image = new Image(input);

                        }
        
  
            imageProfil.setImage(image);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
 private void imagePicture(ActionEvent event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file);
        if (file != null) {

            Path temp = null;
            try {
                int random_int = (int) (Math.random() * (1000 - 1000000 + 1) + 1000);
                File newfile = new File(random_int + "-" + file.getName());
                file.renameTo(newfile);
                newfile.createNewFile();
//                temp = Files.copy(Paths.get(newfile.getAbsolutePath()),
//                        Paths.get(PiDev.brochures_directory + newfile.getName()));
//
//                if (temp != null) {
//                    
                    
                    new UploadImage().adImage(newfile);

 
                     
                    Image image = new Image(PiDev.brochures_host + newfile.getName());
                    imageProfil.setImage(image);
                    imageProfilUrl = newfile.getName();

//                } else {
//                    System.out.println("Failed to move the file");
//                }
            } catch (IOException ex) {
                Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void editAdAction(ActionEvent event) {
        String titleEdit = title.getText();
        String descrEdit = decr.getText();
        if (imageProfilUrl != null) {
            annonce.setImageUrlAnnonce(imageProfilUrl);
        }
        annonce.setTitreAnnonce(titleEdit);
        annonce.setDescriptionAnnonce(descrEdit);
        new AnnonceDao().update(annonce);
    }

    @FXML
    private void resetAdPublishAction(ActionEvent event) {
        title.setText(annonce.getTitreAnnonce());
        decr.setText(annonce.getDescriptionAnnonce());
        imageProfilUrl = null;
        if (annonce.getImageUrlAnnonce() == null) {
            try {
                FileInputStream input;

                input = new FileInputStream(".\\src\\icons\\picture.png");

                Image image = new Image(input);
                imageProfil.setImage(image);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
