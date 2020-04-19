/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.admin;

import Communication.entity.Annonce;
import bd.DataBase;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import user.CommonMethod;
import user.entity.User;
import user.DAO.UserDao;

/**
 *
 * @author fedi
 */
public class UserManagementController extends CommonMethod implements Initializable {

    private final ObservableList<user.entity.User> data
            = FXCollections.observableArrayList();
    @FXML
    private TableView urserList;
    @FXML
    private TableColumn usernameC;
    @FXML
    private TableColumn EmailC;
    @FXML
    private TableColumn LastnameC;
    @FXML
    private TableColumn FirstnameC;
    @FXML
    private TableColumn RolesC;
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableColumn StatC;
    private FilteredList filteredList = new FilteredList<>(data);

    @FXML
    private AnchorPane changePassword, profil, deleteAccount;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField lastnameInput;
    @FXML
    private TextField firtnameInput;
    @FXML
    private TextField cinInput;
    @FXML
    private TextField addressInput;
    @FXML
    private TextField phoneinput;
    @FXML
    private TextField emailinput1;

    @FXML
    private JFXPasswordField newPasswordConf;

    @FXML
    private JFXPasswordField newPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usernameC.setCellValueFactory(new PropertyValueFactory<user.entity.User, String>("username"));

        EmailC.setCellValueFactory(new PropertyValueFactory<user.entity.User, String>("email"));

        LastnameC.setCellValueFactory(new PropertyValueFactory<user.entity.User, String>("nomUser"));

        FirstnameC.setCellValueFactory(new PropertyValueFactory<user.entity.User, String>("prenomUser"));

        RolesC.setCellValueFactory(new PropertyValueFactory<user.entity.User, String>("rolesUser"));

        StatC.setCellValueFactory(new PropertyValueFactory<user.entity.User, String>("enabledString"));

        urserList.setItems(data);

        UserDao ud = new UserDao();
        data.addAll((ArrayList) ud.getAll());

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("text" + newValue);
            updatePredicate();

            SortedList<user.entity.User> sortedData = new SortedList<>(filteredList);

            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(urserList.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            urserList.setItems(sortedData);

        });

    }

    private void updatePredicate() {
        filteredList.setPredicate((data) -> {
            boolean showItem = true;
            if (!searchField.getText().isEmpty()) {
                if (((user.entity.User) data).getUsername().contains(searchField.getText())) {
                    return true;
                }
            } else if (searchField.getText().isEmpty()) {

                return true;

            }
            return false;
        });
    }

    @FXML
    private void blockAction(ActionEvent event) {
        user.entity.User user = (user.entity.User) urserList.getSelectionModel().getSelectedItem();
        UserDao ud = new UserDao();
        data.remove(user);
        user.setEnabled(false);
        new UserDao().update(user);
        data.add(user);

    }
    
     
 
    @FXML
    private void resetAction(ActionEvent event) {
    usernameInput.setText("");
     lastnameInput.setText("");
   firtnameInput.setText("");
     cinInput.setText("");
     addressInput.setText("");
    phoneinput.setText("");
     emailinput1.setText("");
     newPasswordConf.setText("");
     newPassword.setText("");

    }
    
    @FXML
    private void addManagerAction(ActionEvent event) {

        System.out.println("addManagerAction");
        //PiDev.currentUser.setAdresseUser(adresseUser);
        if (usernameInput.getText().length() == 0 || emailinput1.getText().length() == 0 || !isValidEmail(emailinput1.getText()) || (newPassword.getText().length() == 0 && !newPassword.getText().equals(newPasswordConf.getText()))) {
            System.out.println("addManagerAction:cas 1");

            alerErreur("add user", "invalid data ");

        } else {
            System.out.println("addManagerAction:cas 2");

            User user = new User(emailinput1.getText(), usernameInput.getText(), newPassword.getText(), firtnameInput.getText(), lastnameInput.getText(), addressInput.getText(), phoneinput.getText(), cinInput.getText());

            user.setAsRESP();
            new UserDao().save(user);

        }

    }

  
    @FXML
    private void unblockAction(ActionEvent event) {
        user.entity.User user = (user.entity.User) urserList.getSelectionModel().getSelectedItem();
        UserDao ud = new UserDao();
        data.remove(user);
        user.setEnabled(true);
        new UserDao().update(user);
        data.add(user);

    }

    @FXML
    private void deleteAction(ActionEvent event) {
        user.entity.User user = (user.entity.User) urserList.getSelectionModel().getSelectedItem();
        UserDao ud = new UserDao();
        data.remove(user);
        new UserDao().delete(user);
    }

    @FXML
    private void setAdAdmin(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();

        try {

            Parent myNewScene = FXMLLoader.load(getClass().getResource("../../user/views/admin/ad.fxml"));

            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
        }
    }

    @FXML
    private void setProfilAdmin(ActionEvent event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();

        try {

            Parent myNewScene = FXMLLoader.load(getClass().getResource("../../user/views/admin/profil.fxml"));

            Scene scene = new Scene(myNewScene);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

    }

    @FXML
    private void setUserMangmentAdmin(ActionEvent event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();

        try {

            Parent myNewScene = FXMLLoader.load(getClass().getResource("../user/views/admin/userManagement.fxml"));

            Scene scene = new Scene(myNewScene);

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

    }

}
