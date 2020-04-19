/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Communication.DAO.CommentaireDao;
import Communication.DAO.AnnonceDao;
import Communication.entity.Commentaire;
import Communication.entity.Annonce;
import com.jfoenix.controls.JFXButton;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
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

/**
 *
 * @author fedi
 */
public class CommentController extends CommonMethod implements Initializable {

    @FXML
    JFXTextArea decr;
    @FXML
    JFXTextField title;
    @FXML
    ImageView imageProfil;
    @FXML
    JFXListView listView;
    String imageProfilUrl = null;
    @FXML
    JFXTextArea commentText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Annonce annonce = new AnnonceDao().get(PiDev.annonceView);
        decr.setText(annonce.getDescriptionAnnonce());
        decr.setEditable(false);
        title.setText(annonce.getTitreAnnonce());
        title.setEditable(false);
        String imageUrl;
    
        try {
             Image image=null;
            FileInputStream input;
            
            
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
        ArrayList aList = (ArrayList) new CommentaireDao().getCommentsByAnnonce(annonce.getId());
        for (int i = 0; i < aList.size(); i++) {
            Commentaire a = (Commentaire) aList.get(i);
            TextFlow tf = new TextFlow();
            Text t1 = new Text(a.getContenuCommentaire());
            User user = new UserDao().get(a.getCommentateur());
            FileInputStream input;
             Image image = null;
            try {
                
              
             input = new FileInputStream(".\\src\\icons\\avatar.png");
           
            if (user.getImageUrlUser() != null) {
                
                            image = new Image(PiDev.brochures_host + user.getImageUrlUser());
                
            }else{
                            image = new Image(input);

            }
                 ImageView iv = new ImageView(image);
                iv.setFitHeight(50);
                iv.setFitWidth(50);
                tf.getChildren().add(iv);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Text t2 = new Text(user.getUsername() + "\n");
            t2.setStyle("-fx-font-weight: bold; -fx-font:30 px; ");
            tf.getChildren().addAll(t2, t1);
            listView.getItems().add(tf);
        }
    }
 
    @FXML
    private void commentButton(ActionEvent event) {

        System.out.println(commentText.getText());
        if (commentText.getText().length() > 0) {
            Commentaire c = new Commentaire(commentText.getText());
            c.setAnnonce(PiDev.annonceView);
            c.setCommentateur(PiDev.currentUser.getid());
            new CommentaireDao().save(c);

            TextFlow tf = new TextFlow();
            Text t1 = new Text(c.getContenuCommentaire());
            User user = new UserDao().get(c.getCommentateur());

            FileInputStream input;
                        Image image = null;

             try {
              input = new FileInputStream(".\\src\\icons\\avatar.png");
          /*   if (PiDev.currentUser.getImageUrlUser() != null) {
            
                image = new Image(PiDev.brochures_host + PiDev.currentUser.getImageUrlUser());
                 
            }else{
                            image = new Image(input);

            }
                */
                if (user.getImageUrlUser() != null) {
               //     input = new FileInputStream(PiDev.brochures_directory + PiDev.currentUser.getImageUrlUser());
                               image = new Image(PiDev.brochures_host + PiDev.currentUser.getImageUrlUser());

                } else {
                    input = new FileInputStream(".\\src\\icons\\avatar.png");
                   image = new Image(input);
                }

             

                ImageView iv = new ImageView(image);

                iv.setFitHeight(50);
                iv.setFitWidth(50);
                tf.getChildren().add(iv);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Text t2 = new Text(user.getUsername() + "\n");
            t2.setStyle("-fx-font-weight: bold; -fx-font:30 px; ");
            tf.getChildren().addAll(t2, t1);
            listView.getItems().add(tf);
            commentText.setText("");
        }
    }

    @FXML
    private void adPublishBut(ActionEvent event) {

        Annonce an = new Annonce(title.getText(), decr.getText(), imageProfilUrl, PiDev.currentUser.getid());
        new AnnonceDao().save(an);
        title.setText("");
        decr.setText("");
        imageProfilUrl = null;
        if (imageProfilUrl == null) {

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

 
    
    @FXML
    private void resetAdPublishBut(ActionEvent event) {
        title.setText("sssssss");
        title.setEditable(false);
        decr.setText("");
        imageProfilUrl = null;
        if (imageProfilUrl == null) {
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
