/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.entity;

import Communication.DAO.AnnonceDao;
import user.entity.User;
import user.DAO.UserDao;

/**
 *
 * @author fedi
 */
public class Recommandation {

    private int id;

    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User" ,
     * inversedBy="recommandation")
     * @ORM\JoinColumn(name="recommandeur_id",referencedColumnName="id")
     */
    private int recommandeur;
    /**
     * @ORM\ManyToOne(targetEntity="Annonce" , inversedBy="recommandations")
     * @ORM\JoinColumn(name="annonce_id",referencedColumnName="id")
     */
    private int annonce;

    public int getId() {
        return id;
    }

    public Recommandation() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecommandeur() {
        return recommandeur;
    }

    public void setRecommandeur(int recommandeur) {
        this.recommandeur = recommandeur;
    }

    public int getAnnonce() {
        return annonce;
    }

    public void setAnnonce(int annonce) {
        this.annonce = annonce;
    }

    public User getRecommandeurEntity() {
        return new UserDao().get(this.id);
    }

    public void setRecommandeurEntity(User recommandeur) {
        this.recommandeur = recommandeur.getid();
    }

    public Annonce getAnnonceEntity() {
        return new AnnonceDao().get(this.annonce);
    }

    public void setAnnonceEntity(Annonce annonce) {
        this.annonce = annonce.getId();
    }
}
