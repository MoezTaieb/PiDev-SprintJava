/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import user.entity.User;
import user.service.SendEmailSMTP;
import user.DAO.UserDao;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import user.service.BCrypt;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pidev.PiDev;

/**
 *
 * @author fedi
 */
public class UserController extends CommonMethod implements Initializable {

    @FXML
    Button loginButton;
    @FXML
    Button Registration;
    @FXML
    PasswordField passwordLogin;
    @FXML
    TextField username;
    @FXML
    TextField emailRegistation;
    @FXML
    TextField cinRegistation;
    @FXML
    TextField adresseRegistation;
    @FXML
    TextField telRegistation;
    @FXML
    TextField prenomRegistation;
    @FXML
    TextField nomRegistation;
    @FXML
    TextField usernameRegistation;
    @FXML
    PasswordField passwordRegister;
    @FXML
    PasswordField passwordConfRegister;

    @FXML
    PasswordField passwordReset;

    @FXML
    TextField emailReset;

    @FXML
    TextField resetCcde;

      @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
// button used to login 
        System.out.println(passwordLogin.getText());
        System.out.println(username.getText().length());

        if (username.getText().length() > 0 && passwordLogin.getText().length() > 0) {

            UserController uc = new UserController();

            try {
                PiDev.currentUser = uc.login(username.getText(), passwordLogin.getText());
                if (PiDev.currentUser != null) {
                    final Node source = (Node) event.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();
                    String url = null;
                    if (PiDev.currentUser.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                        url = "dashAdmin.fxml";
                    } else if (PiDev.currentUser.getRoles().equals("a:1:{i:0;s:9:\"ROLE_RESP\";}")) {
                        url = "dashResponsible.fxml";
                    } else if (PiDev.currentUser.getRoles().equals("a:1:{i:0;s:10:\" ROLE_USER\";}")) {
                        url = "dash.fxml";
                    }
                    try {
                        Parent myNewScene = FXMLLoader.load(getClass().getResource(url));

                        Scene scene = new Scene(myNewScene);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                    }
                }
            } catch (Exception ex) {
                alerWarning("connexion", ex.getMessage());
            }
        } else {
            alerWarning("login", "password or username invalide");
        }
    }

    @FXML
    private void resetresetPassword(ActionEvent event) {

        // button used to reset account  

        System.out.println("passwordReset" + passwordReset.getText());
        System.out.println("resetCcde" + resetCcde.getText());

        System.out.println("emailReset" + emailReset.getText());

        if (passwordReset.getText().length() > 0 && resetCcde.getText().length() == 4 && emailReset.getText().length() > 0 && isValidEmail(emailReset.getText())) {
            UserDao userDao = new UserDao();
            User u = userDao.getByEmail(emailReset.getText());
            if (u != null) {
                if(u.isEnabled())
                {
                int parseInt = Integer.parseInt(resetCcde.getText());

                resetPassword(emailReset.getText(), passwordReset.getText(), parseInt);
                }else{
                                        alerWarning("reset ", "this account is blocked");

                }}
            else{
                        alerWarning("reset ", "email  n'existe pas");
            }
        } else {
            
         
            alerWarning("login", "password or username invalide");
        }
             passwordReset.setText("");
        resetCcde.setText("");

        emailReset.setText("");
    }

    @FXML
    private void registationButtonAction(ActionEvent event) {
        
        //Action used to register
        String passwordConf = passwordRegister.getText();
        String password = passwordConfRegister.getText();
        String email = emailRegistation.getText();
        System.out.println("email"+email);
        String cinUser = cinRegistation.getText();
        String adresseUser = adresseRegistation.getText();
        String telUser = telRegistation.getText(); 
 
        String nomUser = nomRegistation.getText();
        String username = usernameRegistation.getText();
        String prenomUser = prenomRegistation.getText();
        if (username.length() > 0 && password.length() > 0 && password.equals(passwordConf)  && isValidEmail(email)) {
            User user = new User(email, username, password, nomUser, prenomUser, adresseUser, telUser, cinUser);
            System.out.println("update user" + user);
            try {
                PiDev.currentUser = registre(user);
                if (PiDev.currentUser != null) {
                    passwordRegister.setText("");
                    passwordConfRegister.setText("");
                    emailRegistation.setText("");
                    cinRegistation.setText("");
                    adresseRegistation.setText("");
                    telRegistation.setText("");
                    nomRegistation.setText("");
                    usernameRegistation.setText("");
                    prenomRegistation.setText("");

                    alerWarning("Connection", "Connect successfully!");

                } else {
                    alerWarning("warring", "username or email already yser");

                }
                System.out.println(PiDev.currentUser);
            } catch (Exception ex) {

                alerErreur("Connection", ex.getMessage());
            }
        } else {
            alerWarning("warring", "data invalid");

        }
    }

    @FXML
    private void forgotPasswordButtonAction(ActionEvent event) {
//Action used to forgot password
        String email = emailRegistation.getText();
        System.out.println(email);
        if (email.length() > 0 && isValidEmail(email)) {
            UserDao userDao = new UserDao();
            User user = userDao.getByEmail(email);
            if (user != null) {
                forgotPassword(user);
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                try {
                    Parent myNewScene = FXMLLoader.load(getClass().getResource("views/resetPassword.fxml"));
                    Scene scene = new Scene(myNewScene);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                }
            } else {
                alerWarning("Connection", "email not found");

            }

        } else {
            alerWarning("warring", "invalid email");
        }
    }

    @FXML
    private void registerButtonAction(ActionEvent event) {
       
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        try {
            Parent myNewScene = FXMLLoader.load(getClass().getResource("views/register.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void loginAction(ActionEvent event) {
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

    @FXML
    private void resetAction(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        try {
            Parent myNewScene = FXMLLoader.load(getClass().getResource("views/resetPassword.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void forgotPasswordAction(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        try {
            Parent myNewScene = FXMLLoader.load(getClass().getResource("views/forgotPassword.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {

        }
    }

  

    public User registre(User user) throws Exception {
        UserDao ud = new UserDao();

        if (ud.getByUsername(user.getUsername()) == null) {
            ud.save(user);
            User newUser = ud.getByUsername(user.getUsername());
            if (newUser != null) {
                return newUser;
            }

        } else {
            throw new Exception("Username ou email deja existe  ");
        }

        return null;

    }

    public User login(String username, String password) throws Exception {

        UserDao ud = new UserDao();
        User user = ud.getByUsername(username);

        if (user == null) {
            throw new Exception("user n'existe pas ");

        } else if (user.getPassword() == null) {

            return null;

        } else if (BCrypt.checkpw(password, user.getPassword())) {

            System.out.println("login succes");

            user.setLastLogin(date());
            ud.update(user);
            return user;

        }
        System.out.println("login echec ");

        return null;
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

    public String date() {
        Date aujourdhui = new Date();

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formater.format(aujourdhui);
    }

    public void forgotPassword(User u) {

        UserDao userDao = new UserDao();

        // User u=userDao.getByEmail(email);
        u.setPasswordRequestedAt(date());

        if (u.isEnabled() && u.getResetNumAttempts() == 0) {
            int numbre = (int) (1000 + (Math.random() * 9000));

            SendEmailSMTP.sendMail(u.getEmail(), "" + numbre, "" + numbre);

            u.setResetCode(numbre);

            u.setResetNumAttempts(u.getResetNumAttempts() + 1);

        } else if (u.isEnabled() && u.getResetNumAttempts() < 4) {
            u.setResetNumAttempts(u.getResetNumAttempts() + 1);

        } else if (u.isEnabled() && u.getResetNumAttempts() == 4) {

            u.setResetCode(0);

            u.setEnabled(false);

            u.setResetNumAttempts(0);
        }

        userDao.update(u);
    }

    public void resetPassword(String email, String newPassword, int resetCode) {
        UserDao userDao = new UserDao();
        User u = userDao.getByEmail(email);
        u.setPasswordRequestedAt(date());
        if (u.isEnabled() && u.getResetNumAttempts() < 4) {
            System.err.println("resetPassword:cas 1 ");
            u.setResetNumAttempts(u.getResetNumAttempts() + 1);
            if (u.getResetCode() == resetCode) {

                alerWarning("Reset Password", "password change");

                System.err.println("resetPassword:password change  ");
                u.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(10)));
                u.setResetCode(0);
                u.setResetNumAttempts(0);
            }else{
                         
                alerWarning("Reset Password", "reset code incorrect");

            }
        } else if (u.isEnabled() && u.getResetNumAttempts() == 4) {

            alerWarning("Reset Password", "account blocked");

            System.err.println("resetPassword:cas 2  ");
            u.setResetCode(0);
            u.setEnabled(false);
            u.setResetNumAttempts(0);
        }
        userDao.update(u);
    }

    public User changeUserData(User user) throws Exception {
        if (user == null) {
            return null;
        } else {
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

}
