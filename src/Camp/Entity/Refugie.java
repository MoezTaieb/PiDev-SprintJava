/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Entity;

/**
 *
 * @author ME
 */
public class Refugie {
    private int id;
    private int camp_id ;
    private String nomRefugie ;
    private String prenomRefugie;
    private String adresseRefugie;
    private String telRefugie;
    private String numassportRefugie;
    private String nationaliteRefugie;
    private String image;
    private String nomCamp;

    public Refugie(int camp_id, String nomRefugie, String prenomRefugie, String adresseRefugie, String telRefugie, String numassportRefugie, String nationaliteRefugie, String image) {
        this.camp_id = camp_id;
        this.nomRefugie = nomRefugie;
        this.prenomRefugie = prenomRefugie;
        this.adresseRefugie = adresseRefugie;
        this.telRefugie = telRefugie;
        this.numassportRefugie = numassportRefugie;
        this.nationaliteRefugie = nationaliteRefugie;
        this.image = image;
    }

    public Refugie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCamp_id() {
        return camp_id;
    }

    public void setCamp_id(int camp_id) {
        this.camp_id = camp_id;
    }

    public String getNomRefugie() {
        return nomRefugie;
    }

    public void setNomRefugie(String nomRefugie) {
        this.nomRefugie = nomRefugie;
    }

    public String getPrenomRefugie() {
        return prenomRefugie;
    }

    public void setPrenomRefugie(String prenomRefugie) {
        this.prenomRefugie = prenomRefugie;
    }

    public String getAdresseRefugie() {
        return adresseRefugie;
    }

    public void setAdresseRefugie(String adresseRefugie) {
        this.adresseRefugie = adresseRefugie;
    }

    public String getTelRefugie() {
        return telRefugie;
    }

    public void setTelRefugie(String telRefugie) {
        this.telRefugie = telRefugie;
    }

    public String getNumassportRefugie() {
        return numassportRefugie;
    }

    public void setNumassportRefugie(String numassportRefugie) {
        this.numassportRefugie = numassportRefugie;
    }

    public String getNationaliteRefugie() {
        return nationaliteRefugie;
    }

    public void setNationaliteRefugie(String nationaliteRefugie) {
        this.nationaliteRefugie = nationaliteRefugie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNomCamp() {
        return nomCamp;
    }

    public void setNomCamp(String nomCamp) {
        this.nomCamp = nomCamp;
    }
    
    

    @Override
    public String toString() {
        return nomRefugie ;
    }
    
    
    
}
