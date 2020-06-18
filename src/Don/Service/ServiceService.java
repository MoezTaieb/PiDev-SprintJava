/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Service;


import Don.Entity.Equipment;
import Don.Entity.Service;
//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import pidev.PiDev;

/**
 *
 * @author amin
 */
public class ServiceService {
  Connection cx = bd.DataBase.getInstance1().getCnx();
  
    public String nomCategorie(int id){
        String nomCategorie = "";
        String req = "select nomCategorie from categorie_service where id ="+id;
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    nomCategorie = resultat.getString(1);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return nomCategorie;
    }  
    public void newAction(Service c)
            
    {  String nomCategorie = nomCategorie(c.getCategorieService_id());
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //  alert.initOwner(AjoutEquipmentController.getPrimaryStage());
        
int a  =PiDev.currentUser.getid();
    
        String req="insert into service (nomService,descriptionService,categorieService_id,nomCategorie,idU) values ('"+c.getNomService()+
            "','"+c.getDescriptionService()+
                
       "','"+c.getCategorieService_id()+
         "','"+nomCategorie+
            "','"+a+
            
            "')";
      
    
    
    
    
    
    
    
    Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAction(int c){
        String req="delete from service where Id= '"+c+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAction(Service c){
       
         String req= " UPDATE service SET nomService='"+c.getNomService()+"',descriptionService='"+c.getDescriptionService()+"',dateDon='"
               +c.getDateDon()+"' where id= '"+c.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
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
     public ObservableList<Service> indexAction()
             
     {  
        ObservableList<Service> mylist=FXCollections.observableArrayList();
     
        
        String req= " select * from service";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Service c = new Service();
                 c.setId(resultat.getInt(1));
                 c.setNomService(resultat.getString(2));
                 c.setDateDon(resultat.getDate(4));
                 c.setDescriptionService(resultat.getString(3));
                  c.setCategorieService_id(resultat.getInt(5));
                  c.setNomCategorie(resultat.getString(7));
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
    public ObservableList<Service> indexAction2()
             
     {  
        ObservableList<Service> mylist=FXCollections.observableArrayList();
      
          
        int a =PiDev.currentUser.getid();
        
            String req= " select * from service where idU= '"+a+"'";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    Service c = new Service();
                 c.setId(resultat.getInt(1));
                 c.setNomService(resultat.getString(2));
                 c.setDateDon(resultat.getDate(4));
                 c.setDescriptionService(resultat.getString(3));
                  c.setCategorieService_id(resultat.getInt(5));
                  c.setNomCategorie(resultat.getString(7)); 
                
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
}
