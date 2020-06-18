/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Don.Service;



import Don.Entity.CategorieService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import bd.DataBase;

/**
 *
 * @author amin
 */
public class CategorieServiceService {

   Connection cx = bd.DataBase.getInstance1().getCnx();
    
    public void newAction(CategorieService c)
    {   String req="insert into categorie_Service (nomCategorie) values ('"+c.getNom()+"')";
        Statement st;
        try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAction(int id){
        String req="delete from categorie_Service where Id= '"+id+"'";
        Statement st;
         try {
            st = cx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAction(CategorieService c){
        String req= " UPDATE categorie_Service SET nomCategorie='"+c.getNom()+"' where id= '"+c.getId()+"' " ;
        Statement st;
        try {
             st = cx.createStatement();
             st.executeUpdate(req);
                    }   
        catch (SQLException ex) {
            Logger.getLogger(CategorieServiceService.class.getName()).log(Level.SEVERE, null, ex);
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
     public ObservableList<CategorieService> indexAction()
             
     {  
        ObservableList<CategorieService> mylist=FXCollections.observableArrayList();
        String req= " select * from categorie_service ";
        Statement st;
        try {
            st=cx.createStatement();
            ResultSet resultat=st.executeQuery(req);
            while(resultat.next())
            {    CategorieService c = new CategorieService();
                 c.setIdentifiant(resultat.getInt(1));
                 c.setNom(resultat.getString(2));
                 mylist.add(c);
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServiceService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return mylist;
     }
    
}
