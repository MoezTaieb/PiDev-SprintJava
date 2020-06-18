/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Service;

import Don.Entity.CategorieEquipment;
import Don.Entity.CategorieService;
import Don.Entity.Equipment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import bd.DataBase;
import pidev.PiDev;

/**
 *
 * @author amin
 */
public class EquipmentService {

  
  Connection cx = bd.DataBase.getInstance1().getCnx();
    
 
  public String nomCategorie(int id){
        String nomCategorie = "";
        String req = "select nomCategorie from categorie_equipment where id ="+id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nomCategorie = resultat.getString(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nomCategorie;
    }
    
    public void newAction(Equipment c)
    {  String nomCategorie = nomCategorie(c.getCategorieEquipment_id());
    int a =PiDev.currentUser.getid();
        String req="insert into equipment (nomEquipment,etatEquipment,nbEquipment,CategorieEquipment_id,nomCategorie,idU) values ('"+c.getNomEquipment()+
            "','"+"En Cours"+
       
          "','"+c.getNb_equipment()+
            
              "','"+c.getCategorieEquipment_id()+
               "','"+nomCategorie+ 
                 "','"+a+ 
            "')";
      
    
    
    
    
    
    
    
    Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAction(int id){
        String req="delete from equipment where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void updateAction(Equipment p){
        String req= " UPDATE equipment SET nomEquipment='"+p.getNomEquipment()+"',nbEquipment='"+p.getNb_equipment()+"',etatEquipment='"
               +p.getEtatEquipment()+"' where id= '"+p.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public List<String> indexAction()
    {
        List<String> ListeCategories = new ArrayList<>();
        String req= " select nomCategorie from categorie_produit ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.first())
                ListeCategories.add("nomCategorie");          
         }catch (SQLException ex) {
            Logger.getLogger(CategorieProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListeCategories;
    }
    */
 public ObservableList<Equipment> indexAction()
             
     {  
        ObservableList<Equipment> mylist=FXCollections.observableArrayList();
        String req= " select * from equipment ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Equipment p = new Equipment();
                  p.setId(resultat.getInt(1));
                  
                 p.setNomEquipment(resultat.getString(4));
                 p.setEtatEquipment(resultat.getString(5));
              p.setCategorieEquipment_id(resultat.getInt(7));    
                     p.setNb_equipment(resultat.getInt(8));
                   p.setDateDonEquipment(resultat.getDate(6));
              p.setNomCategorie(resultat.getString(11));
                
                 mylist.add(p);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
 
 
 public float nb(){
     //ObservableList<int> mylist=FXCollections.observableArrayList();
        float nb = 0;
        String req = "SELECT sum(nbEquipment)/CONVERT(TIMESTAMPDIFF(Day,min(dateDonEquipment),Now()), SIGNED INTEGER)"
                + " from equipment ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nb = resultat.getFloat(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb;
    }
 
 
 
 
 
 
 
  public ObservableList<PieChart.Data> pie()
             
     {  
        ObservableList<PieChart.Data> data=FXCollections.observableArrayList();
        String req= " select count(*) from equipment ";
        String reqa= " select count(*) from service ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
          
            if(resultat.next())
            {    
            int count=resultat.getInt(1);
            
                
                 data.add(new PieChart.Data("total equipment "+count, count));
                 System.out.println("Service.EquipmentService.pie()  aa");
                    }
             resultat=st.executeQuery(reqa);
              if(resultat.next())
            {    
            int count=resultat.getInt(1);
            
                
                 data.add(new PieChart.Data("total service "+count, count));
                 System.out.println("Service.EquipmentService.pie()  bb");
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Service.EquipmentService.pie()  bb");
        }
          return data;
     }
    




   public float nbweek(){
     //ObservableList<int> mylist=FXCollections.observableArrayList();
        float nb = 0;
        String req ;
     req = "SELECT sum(nbEquipment)/CONVERT(TIMESTAMPDIFF(week,min(dateDonEquipment),Now()), SIGNED INTEGER)from equipment";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nb = resultat.getFloat(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb;
    }
  public float nbmonth(){
     //ObservableList<int> mylist=FXCollections.observableArrayList();
        float nb = 0;
        String req = "SELECT sum(nbEquipment)/CONVERT(TIMESTAMPDIFF(month,min(dateDonEquipment),Now()), SIGNED INTEGER)"
                + " from equipment";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nb = resultat.getFloat(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb;
    }
  
    public float nbcat(String nom){
     //ObservableList<int> mylist=FXCollections.observableArrayList();
        float nb = 0;
        String req = "SELECT sum(nbEquipment)/CONVERT(TIMESTAMPDIFF(Day,min(dateDonEquipment),Now()), SIGNED INTEGER)\n" +
"                 from equipment WHERE nomCategorie like '"+nom+"'";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nb = resultat.getFloat(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb;
    }
  public ObservableList<Equipment> indexAction2()
             
     {   int a =PiDev.currentUser.getid();
        ObservableList<Equipment> mylist=FXCollections.observableArrayList();
        String req= " select * from equipment where idU= '"+a+"'";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Equipment p = new Equipment();
                  p.setId(resultat.getInt(1));
                  
                 p.setNomEquipment(resultat.getString(4));
                 p.setEtatEquipment(resultat.getString(5));
              p.setCategorieEquipment_id(resultat.getInt(7));    
                     p.setNb_equipment(resultat.getInt(8));
                   p.setDateDonEquipment(resultat.getDate(6));
              p.setNomCategorie(resultat.getString(11));
               
                 mylist.add(p);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
  
  
  public ObservableList<Equipment> index1Action()
             
     {  
        ObservableList<Equipment> mylist=FXCollections.observableArrayList();
        String req= "select * from equipment where `etatEquipment`like 'En cours' ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Equipment p = new Equipment();
                  p.setId(resultat.getInt(1));
                  
                 p.setNomEquipment(resultat.getString(4));
                 p.setEtatEquipment(resultat.getString(5));
              p.setCategorieEquipment_id(resultat.getInt(7));    
                     p.setNb_equipment(resultat.getInt(8));
                   p.setDateDonEquipment(resultat.getDate(6));
              p.setNomCategorie(resultat.getString(11));
                
                 mylist.add(p);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    public void traiterAction(int p){
        String s="Recu";
        String req= " UPDATE equipment SET etatEquipment='"
               +s+"' where id= '"+p+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void traiter1Action(int p){
        String s="Annuler";
        String req= " UPDATE equipment SET etatEquipment='"
               +s+"' where id= '"+p+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public String notif(){
     //ObservableList<int> mylist=FXCollections.observableArrayList();
         int id =PiDev.currentUser.getid(); 
     String nb = "";
        String req = "SELECT nomEquipment, dateDonEquipment date FROM equipment WHERE now() >=  DATE_ADD(dateDonEquipment, INTERVAL 2 DAY) and idu="+id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nb = resultat.getString(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb;
    }
        public int notifa(){
     //ObservableList<int> mylist=FXCollections.observableArrayList();
    int nb = 0;
      String req = "select count(nomEquipment) from equipment where `etatEquipment`like 'En cours' ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nb = resultat.getInt(1);
              //  System.out.println("Don.Service.EquipmentService.notifa()"+nb);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nb;
    }
  
}

