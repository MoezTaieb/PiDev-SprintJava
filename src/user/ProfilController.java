/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import user.service.BCrypt;
import user.entity.User;
import user.DAO.UserDao;
import com.jfoenix.controls.JFXPasswordField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.PiDev;
import javafx.scene.image.Image;
import user.service.UploadImage;

/**
 *
 * @author danml
 */
public class ProfilController extends CommonMethod implements Initializable {

    @FXML
    private AnchorPane holderPane;

    @FXML
    AnchorPane changePassword, profil, deleteAccount;
    @FXML
    TextField usernameInput;
    @FXML
    TextField lastnameInput;
    @FXML
    TextField firtnameInput;
    @FXML
    TextField cinInput;
    @FXML
    TextField addressInput;
    @FXML
    TextField phoneinput;
    @FXML
    TextField emailinput1;
    @FXML
    JFXPasswordField newPasswordConf;
    @FXML
    JFXPasswordField newPassword;
    @FXML
    JFXPasswordField oldPassword;

    @FXML
    JFXPasswordField PasswordDel;
    @FXML
    JFXPasswordField PasswordDelConf;
 
    @FXML
    ImageView imageProfil;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lastnameInput.setText(PiDev.currentUser.getPrenomUser());

        firtnameInput.setText(PiDev.currentUser.getNomUser());
        emailinput1.setText(PiDev.currentUser.getEmail());

        usernameInput.setText(PiDev.currentUser.getUsername());

        cinInput.setText(PiDev.currentUser.getCinUser());

        addressInput.setText(PiDev.currentUser.getAdresseUser());
        phoneinput.setText(PiDev.currentUser.getTelUser());

        FileInputStream input;
        try {
            input = new FileInputStream(".\\src\\icons\\avatar.png");
            Image image = null;
            if (PiDev.currentUser.getImageUrlUser() != null) {

                image = new Image(PiDev.brochures_host + PiDev.currentUser.getImageUrlUser());

            } else {
                image = new Image(input);

            }
            imageProfil.setImage(image);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editUser(ActionEvent event) {
        try {
            if (usernameInput.getText().length() == 0 || emailinput1.getText().length() == 0 || !isValidEmail(emailinput1.getText())) {
                alerErreur("edit user", "invalid email or username");

            } else {

                PiDev.currentUser.setUsername(usernameInput.getText());
                PiDev.currentUser.setPrenomUser(lastnameInput.getText());
                PiDev.currentUser.setNomUser(firtnameInput.getText());
                PiDev.currentUser.setEmail(emailinput1.getText());
                PiDev.currentUser.setCinUser(cinInput.getText());
                PiDev.currentUser.setAdresseUser(addressInput.getText());
                PiDev.currentUser.setTelUser(phoneinput.getText());
                changeUserData(PiDev.currentUser);
            }

        } catch (Exception ex) {

            System.out.println("message" + ex.getMessage());

        }
    }

    @FXML
    private void resetUser(ActionEvent event) {

        lastnameInput.setText(PiDev.currentUser.getPrenomUser());

        firtnameInput.setText(PiDev.currentUser.getNomUser());
        emailinput1.setText(PiDev.currentUser.getEmail());

        usernameInput.setText(PiDev.currentUser.getUsername());

        cinInput.setText(PiDev.currentUser.getCinUser());

        addressInput.setText(PiDev.currentUser.getAdresseUser());
        phoneinput.setText(PiDev.currentUser.getTelUser());
    }

    @FXML
    private void changePasswordBut(ActionEvent event) {

        String oldP = oldPassword.getText();
        String newP = newPassword.getText();
        String newPC = newPasswordConf.getText();

        String ps = PasswordDel.getText();

        if (oldP.length() == 0 || newPC.length() == 0 || newP.length() == 0 || newPC.equals(newP) == false) {
            alerErreur("password", " non valid password");
        } else {

            User user = changePassword(PiDev.currentUser, oldPassword.getText(), newPassword.getText());
            if (user != null) {
                PiDev.currentUser = user;
            } else {
                alerErreur("password", " incorrect password");
            }

        }

        oldPassword.setText("");
        newPassword.setText("");
        newPasswordConf.setText("");

    }

    @FXML
    private void changePasswordButReset(ActionEvent event) {

        oldPassword.setText("");
        newPassword.setText("");

        newPasswordConf.setText("");

    }

    @FXML
    private void imagePicture(ActionEvent event) {
// upload picture
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
                System.out.println("file" + file.getName());

                System.out.println(newfile.getName());

                new UploadImage().adImage(newfile);
                Image image = new Image(PiDev.brochures_host + newfile.getName());
                imageProfil.setImage(image);
                PiDev.currentUser.setImageUrlUser(newfile.getName());

            } catch (IOException ex) {
                Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void deleteAccoutBut(ActionEvent event) {
//http://localhost/phpmyadmin/tbl_relation.php?db=pidev2020&table=report
        // report on delete cascade
        String psC = PasswordDelConf.getText();

        String ps = PasswordDel.getText();
        System.out.println("ps" + ps + "s");

        if (psC.length() == 0 || ps.length() == 0 || psC.equals(ps) == false) {
            alerErreur("password", " non valid password");
        } else if (psC != null || ps != null || psC.equals(ps) == true) {

            new UserDao().delete(PiDev.currentUser);
            PiDev.currentUser = null;

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();

            try {

                Parent myNewScene = FXMLLoader.load(getClass().getResource("views/login.fxml"));

                Scene scene = new Scene(myNewScene);

                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
            }

        }

    }

    public User changeUserData(User user) throws Exception {

        if (user == null) {
            return null;
        } else {
            user.setAsAdmin();
            UserDao ud = new UserDao();

            User email_user = ud.getByEmail(user.getEmail());

            User username_user = ud.getByUsername(user.getUsername());

            if ((email_user != null) && (email_user.getid() != user.getid())) {
                throw new Exception("email  déjà utilisée");
            }

            if ((username_user != null) && (username_user.getid() != user.getid())) {
                throw new Exception("usename déjà utilisée");

            }

            ud.update(user);

            return user;

        }
    }

    public User changePassword(User user, String oldPassword, String newPassword) {

        if (user == null) {
            return null;
        } else {

            if (BCrypt.checkpw(oldPassword, user.getPassword())) {

                user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(10)));

                UserDao ud = new UserDao();

                ud.update(user);

                return user;

            }

        }
        return null;

    }

}
