/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Entity;

/**
 *
 * @author Othmen
 */
public class CategorieEquipment{
private int id ;
private String nomCategorie ;
   
   public CategorieEquipment(String nomCategorie) {
        this.nomCategorie = nomCategorie;
       
    }

    public CategorieEquipment(int id, String nomCategorie) {
        this.id = id;
        this.nomCategorie = nomCategorie;
       
    
    }

    public CategorieEquipment() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    

   public int getId()
   {
       return id ;
   }

    public void setIdentifiant(int id) {
        this.id = id;
    }

    public String getNom() {
        return nomCategorie;
    }

    public void setNom(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

   

    @Override
    public String toString() {
        return nomCategorie  ;
    }
    
    
}
