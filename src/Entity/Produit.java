/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Moez
 */
public class Produit {
    
    private int id;
    private int categorie_id;
    private String nomProduit;
    private float prixProduit;
    private int qteProduit;
    private String descriptionProduit;

    public Produit() {
    }

    public Produit(int categorie_id, String nomProduit, float prixProduit, int qteProduit, String descriptionProduit) {
        this.categorie_id = categorie_id;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.qteProduit = qteProduit;
        this.descriptionProduit = descriptionProduit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getQteProduit() {
        return qteProduit;
    }

    public void setQteProduit(int qteProduit) {
        this.qteProduit = qteProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    @Override
    public String toString() {
        return this.nomProduit;
    }
    
    

    
    
    
}
