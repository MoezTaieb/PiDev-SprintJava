/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import user.entity.User;

/**
 *
 * @author fedi
 */
public class PiDev extends Application {

    /**
     * @param args the command line arguments
     */
    /**
     * current user connected
     */
    public static User currentUser;

    /**
     * ad selected
     */
    public static int annonceView = 40;

    /**
     * Picture storage
     */
    public static String brochures_host = "http://localhost/pidev20mod/web/uploads/brochures/";

    @Override
    public void start(Stage primaryStage) {
        BorderPane root;

        Scene scene;
        try {

            root = FXMLLoader.load(getClass().getResource("../user/views/login.fxml"));
            ;
            scene = new Scene(root);

            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PiDev.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PiDev.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        try {
            launch(args);
        } catch (Exception ex) {

        }
    }

}
