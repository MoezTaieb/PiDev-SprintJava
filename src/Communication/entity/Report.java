/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication.entity;

/**
 *
 * @author fedi
 */
public class Report {

    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private int id;

    /**
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User")
     * @ORM\JoinColumn(name="reporteur_id",referencedColumnName="id")
     */
    private int reporteur;
    /**
     * @ORM\ManyToOne(targetEntity="Annonce" )
     * @ORM\JoinColumn(name="annonce_id",referencedColumnName="id")
     */
    private int annonce;

    public Report() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReporteur() {
        return reporteur;
    }

    public void setReporteur(int reporteur) {
        this.reporteur = reporteur;
    }

    public int getAnnonce() {
        return annonce;
    }

    public void setAnnonce(int annonce) {
        this.annonce = annonce;
    }

}
