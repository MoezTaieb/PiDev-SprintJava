/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Argents.Entitys;

import java.sql.Date;

/**
 *
 * @author SIRINE
 */
public class Argent {
    
    private int id;
<<<<<<< HEAD
    private float montant;
=======
    private int montant;
>>>>>>> master
    private Date date;

    public Argent() {
    }

<<<<<<< HEAD
    public Argent(int id, float montant, Date date) {
=======
    public Argent(int id, int montant, Date date) {
>>>>>>> master
        this.id = id;
        this.montant = montant;
        this.date = date;
    }
    
<<<<<<< HEAD
    public Argent(float montant, Date date) {
=======
    public Argent(int montant, Date date) {
>>>>>>> master
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public float getMontant() {
        return montant;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public void setMontant(float montant) {
=======
    public void setMontant(int montant) {
>>>>>>> master
        this.montant = montant;
    }

    public void setDate(Date date) {
        this.date = date;
    }
       
}
