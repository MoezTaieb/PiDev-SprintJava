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
public class Camp {

 
    
    public int id ;
    private int responsable_id;
    private String nomCamp ;
    private String adresseCamp ;
    private String nbrefugier ;
    private String nomuser ;

    public Camp(String nomCamp, String adresseCamp, String nbrrefugier,int responsable_id) {
        this.nomCamp = nomCamp;
        this.adresseCamp = adresseCamp;
        this.nbrefugier = nbrrefugier;
        this.responsable_id=responsable_id;
    }
    
    
       public Camp() {
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResponsable_id() {
        return responsable_id;
    }

    public void setResponsable_id(int responsable_id) {
        this.responsable_id = responsable_id;
    }

    public String getNomCamp() {
        return nomCamp;
    }

    public void setNomCamp(String nomCamp) {
        this.nomCamp = nomCamp;
    }

    public String getAdresseCamp() {
        return adresseCamp;
    }

    public void setAdresseCamp(String adresseCamp) {
        this.adresseCamp = adresseCamp;
    }

    public String getNbrefugier() {
        return nbrefugier;
    }

    public void setNbrefugier(String nbrrefugier) {
        this.nbrefugier = nbrrefugier;
    }

    @Override
    public String toString() {
        return nomCamp ;
    }
       
       
       
    
}
