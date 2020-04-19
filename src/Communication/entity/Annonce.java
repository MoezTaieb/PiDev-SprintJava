package Communication.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import user.entity.User;
import user.DAO.UserDao;

/**
 *
 * @author fedi
 */
public class Annonce {

    private int id;

    /**
     * @var string
     *
     * @ORM\Column(name="titreAnnonce", type="string", length=255)
     * @Assert\NotBlank
     * @Assert\Length( min = 2, minMessage = "Your title must be at least {{
     * limit }} characters long", )
     */
    private String titreAnnonce;

    /**
     * @var string
     *
     * @ORM\Column(name="descriptionAnnonce", type="text")
     * @Assert\NotBlank
     * @Assert\Length( min = 2, minMessage = "Your description must be at least
     * {{ limit }} characters long", )
     */
    private String descriptionAnnonce;

    /**
     * @var string
     *
     * @ORM\Column(name="imageUrlAnnonce", type="string", length=255
     * ,nullable=true)
     */
    private String imageUrlAnnonce;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateAnnonce", type="datetime" ,nullable=true)
     */
    private String dateAnnonce;

    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User" ,
     * inversedBy="annonces" )
     * @ORM\JoinColumn(name="posteur_id",referencedColumnName="id")
     */
    private int posteur;

    /**
     * @ORM\OneToMany(targetEntity="Commentaire" , mappedBy="annonce"
     * ,cascade={"remove"})
     */
    private int comments;

    /**
     * @ORM\OneToMany(targetEntity="Recommandation" , mappedBy="annonce"
     * ,cascade={"remove"})
     */
    private int recommandations;

    public Annonce(String titreAnnonce, String descriptionAnnonce, String imageUrlAnnonce, int posteur) {
        this.titreAnnonce = titreAnnonce;
        this.descriptionAnnonce = descriptionAnnonce;

        this.imageUrlAnnonce = imageUrlAnnonce;

        Date aujourdhui = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.dateAnnonce = formater.format(aujourdhui);

        this.posteur = posteur;

    }

    public Annonce() {
    }

    public User getPosteurUser() {
        return new UserDao().get(this.posteur);
    }

    public void setPosteurUser(User posteur) {
        this.posteur = posteur.getid();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreAnnonce() {
        return titreAnnonce;
    }

    public void setTitreAnnonce(String titreAnnonce) {
        this.titreAnnonce = titreAnnonce;
    }

    public String getDescriptionAnnonce() {
        return descriptionAnnonce;
    }

    public void setDescriptionAnnonce(String descriptionAnnonce) {
        this.descriptionAnnonce = descriptionAnnonce;
    }

    public String getImageUrlAnnonce() {
        return imageUrlAnnonce;
    }

    public void setImageUrlAnnonce(String imageUrlAnnonce) {
        this.imageUrlAnnonce = imageUrlAnnonce;
    }

    public String getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(String dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public int getPosteur() {
        return posteur;
    }

    public void setPosteur(int posteur) {
        this.posteur = posteur;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getRecommandations() {
        return recommandations;
    }

    public void setRecommandations(int recommandations) {
        this.recommandations = recommandations;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", titreAnnonce=" + titreAnnonce + ", descriptionAnnonce=" + descriptionAnnonce + ", imageUrlAnnonce=" + imageUrlAnnonce + ", dateAnnonce=" + dateAnnonce + ", posteur=" + posteur + ", comments=" + comments + ", recommandations=" + recommandations + '}';
    }
}
