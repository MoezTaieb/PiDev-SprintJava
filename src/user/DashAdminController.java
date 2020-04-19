/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.PiDev;

/**
 *
 * @author danml
 */
public class DashAdminController implements Initializable {

    @FXML
    private AnchorPane holderPane;

    //onAction="#editProfile"
    @FXML
    private void editProfileAction(ActionEvent event) {

        System.err.println("ss");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/admin/profil.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);

    }

    //Set selected node to a content holder
    private void setNode(Node node) {

        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void setAdAdmin(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("views/ad.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);
    }

    @FXML
    private void setProfilAdmin(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("../user/views/admin/profil.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);

    }

    @FXML
    private void setUserMangmentAdmin(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("../user/views/admin/userManagement.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);

    }

    @FXML
    private void setMessage(ActionEvent event) {

        AnchorPane myNewScene = null;

        try {
            myNewScene = FXMLLoader.load(getClass().getResource("../user/views/mes.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        setNode(myNewScene);
    }

    @FXML
    private void logout(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        try {
            PiDev.currentUser = null;
            Parent myNewScene = FXMLLoader.load(getClass().getResource("../user/views/login.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

}
