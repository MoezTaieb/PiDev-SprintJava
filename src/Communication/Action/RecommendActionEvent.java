/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.Action;

import Communication.DAO.AnnonceDao;
import Communication.entity.Annonce;
import javafx.event.Event;
import javafx.event.EventHandler;
import pidev.PiDev;

/**
 *
 * @author fedi
 */
public class RecommendActionEvent implements EventHandler {

    @Override
    public void handle(Event event) {
        AnnonceDao ad = new AnnonceDao();
        Annonce annonce = ad.get(PiDev.annonceView);
        ad.recommander(annonce);
    }

}
