/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Communication.DAO.CommentaireDao;
import Communication.DAO.ReportDao;
import Communication.DAO.AnnonceDao;
import Communication.entity.Commentaire;
import Communication.entity.Annonce;
import Communication.entity.Report;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import pidev.PiDev;
import user.entity.User;
import java.util.StringTokenizer;

/**
 *
 * @author fedi
 */
public class AnnonceController {

    public void signaler(Annonce annonce) {

        Report r = new Report();
        r.setAnnonce(annonce.getId());

        r.setReporteur(PiDev.currentUser.getid());

        ReportDao rd = new ReportDao();

        rd.save(r);

        if (rd.nbReport(r) > 1) {    
            new AnnonceDao().delete(annonce);

            rd.delete(r);
            System.out.println("annonce"+annonce);
        }

        ArrayList mots = (ArrayList) rd.mots();

        System.out.println("mots" + mots);

        StringTokenizer st = new StringTokenizer(annonce.getDescriptionAnnonce(), " :/.-");
        while (st.hasMoreTokens()) {
            String ch = st.nextToken();
            System.out.println(ch);

            System.out.println(mots.contains(ch));

            if (mots.contains(ch.toLowerCase())) {
                //tu doit change constaint de table commentraire en delette cascade           
                //mauvais mots

                rd.delete(r);
                new AnnonceDao().delete(annonce);

            }
        }

    }

    public Annonce publishAd(Annonce annonce) {

        if (annonce == null) {
            return null;
        } else {

            annonce.setPosteur(PiDev.currentUser.getid());

            AnnonceDao ad = new AnnonceDao();

            ad.save(annonce);

            return ad.get(annonce);
        }

    }

    public void removeAd(Annonce annonce) throws Exception {

        // tu dois changer constraint dans la table commentaire  on delete casscade 
        //  http://localhost/phpmyadmin/tbl_relation.php?db=pidev2020&table=commentaire
        AnnonceDao ad = new AnnonceDao();
        if (annonce != null) {

            if (annonce.getPosteur() == PiDev.currentUser.getid()) {
                ad.delete(annonce);
            } else {
                throw new Exception("l'utilisateur n'est pas autorisé à supprimer cet annonce");
            }
        }

    }

    public void editAd(Annonce annonce) throws Exception {

        AnnonceDao ad = new AnnonceDao();

        if (annonce != null) {

            if (annonce.getPosteur() == PiDev.currentUser.getid()) {
                ad.update(annonce);
            } else {
                throw new Exception("l'utilisateur n'est pas autorisé à modifier cet annonce");
            }
        }

    }

    public List<Annonce> getAllAds() {

        AnnonceDao ad = new AnnonceDao();
        return ad.getAll();
    }

    public Annonce getAdById(int id) {

        AnnonceDao ad = new AnnonceDao();
        return ad.get(id);
    }

    public Commentaire publishComment(Annonce annonce, Commentaire commentaire) {

        if (annonce == null || commentaire == null) {

            System.err.println("commentaire");

            return null;
        } else {

             commentaire.setAnnonceEntity(annonce);

            commentaire.setCommentateurEntity(PiDev.currentUser);

            System.err.println(commentaire);

            CommentaireDao cd = new CommentaireDao();
            cd.save(commentaire);

            return cd.get(commentaire); 
        }

    }
}
