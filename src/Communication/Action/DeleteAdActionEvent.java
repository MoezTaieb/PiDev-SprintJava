/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.Action;

import Communication.entity.Annonce;
import Communication.DAO.AnnonceDao;
import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pidev.PiDev;

/**
 *
 * @author fedi
 */
public class DeleteAdActionEvent implements EventHandler {

    @Override
    public void handle(Event e) {
        System.out.println("PiDev.currentUser=" + PiDev.annonceView);
        Annonce a = new Annonce();
        a.setId(PiDev.annonceView);
        new AnnonceDao().delete(a);
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        try {
            Parent myNewScene = FXMLLoader.load(getClass().getResource("../user/views/ad.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }

    }

}
