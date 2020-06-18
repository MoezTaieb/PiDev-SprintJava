/* * To change this license header, choose License Headers in Project Properties.
 *0To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Entity;

import java.util.Date;


   



public class Service {
    private int id;
    private int idU;
    private String descriptionService;
    private String nomService;
private String nomCategorie;

 
    private int categorieService_id;
    private Date dateDon;

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdU() {
        return idU;
    }

    public Service(int id, String descriptionService, String nomService, int categorieService_id, Date dateDon) {
        this.id = id;
        this.descriptionService = descriptionService;
        this.nomService = nomService;
        this.categorieService_id = categorieService_id;
        this.dateDon = dateDon;
    }
      public Service( String descriptionService, String nomService) {
        
        this.descriptionService = descriptionService;
        this.nomService = nomService;
        
        
    }
         public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public void setDateDon(Date dateDon) {
        this.dateDon = dateDon;
    }   
    
     public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    public void setCategorieService_id(int categorieService_id) {
        this.categorieService_id = categorieService_id;
    }

    public int getId() {
        return id;
    }

    public String getNomService() {
        return nomService;
    }

    public int getCategorieService_id() {
        return categorieService_id;
    }

    public Service() {
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public Date getDateDon() {
        return dateDon;
    }

    @Override
    public String toString() {
         return "Equipment{" + "id=" + id + ", descriptionService=" + descriptionService + ", nomService=" + nomService + ", categorieService_id=" +
                categorieService_id + '}';
    }

    public Service(String descriptionService, String nomService, int categorieService_id) {
        this.descriptionService = descriptionService;
        this.nomService = nomService;
        this.categorieService_id = categorieService_id;
    }

   
         
   
    
    
    
}
