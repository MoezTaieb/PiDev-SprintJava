/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Entity;

import java.util.Date;



/**
 *
 * @author Othmen
 */
public class Equipment {
    private int id;
    private String etatEquipment;
    private String nomEquipment;
    private String nomCategorie ;
    private int nb_equipment;
  private int idU;

   

    private int categorieEquipment_id;
    private Date dateDonEquipment;
    
  public Equipment(int id, String etatEquipment, String nomEquipment, int categorieEquipment_id ) {
        this.id = id;
        this.etatEquipment = etatEquipment;
        this.nomEquipment = nomEquipment;
        this.categorieEquipment_id = categorieEquipment_id;
       
    }
 public int getIdU() {
        return idU;
    }
    public void setIdU(int idU) {
        this.idU = idU;
    }
 
    
    public Equipment(int nb_equipment, String nomEquipment, int categorieEquipment_id) {
        this.nb_equipment = nb_equipment;
        this.nomEquipment = nomEquipment;
        this.categorieEquipment_id = categorieEquipment_id;
    }

   

    public Equipment() {
      
    }

    public Equipment(String text, int parseInt) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     this.nomEquipment = nomEquipment;
        this.nb_equipment=nb_equipment;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }
    

    public int getId() {
        return id;
    }

    public String getEtatEquipment() {
        return etatEquipment;
    }

    public String getNomEquipment() {
        return nomEquipment;
    }

    public int getCategorieEquipment_id() {
        return categorieEquipment_id;
    }

    public Date getDateDonEquipment() {
        return dateDonEquipment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtatEquipment(String etatEquipment) {
        this.etatEquipment = etatEquipment;
    }

    public void setNomEquipment(String nomEquipment) {
        this.nomEquipment = nomEquipment;
    }

    public void setCategorieEquipment_id(int categorieEquipment_id) {
        this.categorieEquipment_id = categorieEquipment_id;
    }

    public void setDateDonEquipment(Date dateDonEquipment) {
        this.dateDonEquipment = dateDonEquipment;
    }
   public int getNb_equipment() {
        return nb_equipment;
    }

    public void setNb_equipment(int nb_equipment) {
        this.nb_equipment = nb_equipment;
    }
   
    @Override
    public String toString() {
        return "Equipment{" + "id=" + id + ", etatEquipment=" 
                + etatEquipment + ", nomEquipment=" + nomEquipment +
                ", categorieEquipment_id=" + categorieEquipment_id + 
                ", dateDonEquipment=" + dateDonEquipment + 
                
                 ", user ID=" + idU + 
                '}';
    }

    
       
    
}
