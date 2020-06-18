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
public class CategorieService {
private int id ;
private String nomCategorie ;
   
   public CategorieService(String nomCategorie) {
        this.nomCategorie = nomCategorie;
       
    }

    public CategorieService(int id, String nomCategorie) {
        this.id = id;
        this.nomCategorie = nomCategorie;
       
    
    }

    public CategorieService() {

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
        return nomCategorie ;
    }
    
    
}

