/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.entity;

import Communication.DAO.AnnonceDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import user.entity.User;
import user.DAO.UserDao;

/**
 *
 * @author fedi
 */
public class Commentaire {

    private int id;

    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User" ,
     * inversedBy="commentaires")
     * @ORM\JoinColumn(name="commentateur_id",referencedColumnName="id")
     */
    private int commentateur;

    /**
     * @ORM\ManyToOne(targetEntity="Annonce" , inversedBy="comments")
     * @ORM\JoinColumn(name="annonce_id",referencedColumnName="id")
     */
    private int annonce;

    /**
     * @var string
     *
     * @ORM\Column(name="contenuCommentaire", type="text")
     * @Assert\NotBlank
     * @Assert\Length( min = 2, minMessage = "Your comment must be at least {{
     * limit }} characters long", )
     */
    private String contenuCommentaire;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateCommentaire", type="datetime")
     */
    private String dateCommentaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentateur() {
        return commentateur;
    }

    public void setCommentateur(int commentateur) {
        this.commentateur = commentateur;
    }

    public User getCommentateurEntity() {

        return new UserDao().get(commentateur); //;

    }

    public void setCommentateurEntity(User commentateur) {

        this.commentateur = commentateur.getid();

    }

    public int getAnnonce() {
        return annonce;
    }

    public void setAnnonce(int annonce) {
        this.annonce = annonce;
    }

    public Annonce getAnnonceEntity() {
        return new AnnonceDao().get(annonce); //;
    }

    public void setAnnonceEntity(Annonce annonce) {
        this.annonce = annonce.getId();
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    public String getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(String dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public Commentaire() {
    }

    public Commentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;

        Date aujourdhui = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.dateCommentaire = formater.format(aujourdhui);

    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", commentateur=" + commentateur + ", annonce=" + annonce + ", contenuCommentaire=" + contenuCommentaire + ", dateCommentaire=" + dateCommentaire + '}';
    }

}
