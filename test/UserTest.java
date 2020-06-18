
import Communication.entity.Annonce;
import Communication.AnnonceController;
import Communication.DAO.AnnonceDao;
import java.util.logging.Level;
import java.util.logging.Logger;
 import user.entity.User;
import user.UserController;
import user.DAO.UserDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fedi
 */
public class UserTest {
      public static User currentUser;

    
    public static void main(String[] args) {
    
        
     //   new AnnonceDao().getAll().forEach(n -> System.out.println(n)); 
        //System.err.println("dss"+ new AnnonceDao().get(30)); 
       
//new AnnonceDao().save(new Annonce("titreAnnonce", "descriptionAnnonce", "imageUrlAnnonce", 1));

Annonce a=new Annonce("ddd", "ddd", "dd", 1);


System.out.println(new AnnonceController().publishAd(a));
  //       UserTest.currentUser=new User("userTest@userTest.com","Testuser","Testuser","Testuser","Testuser","Testuser","Testuser","Testuser");

//            UserController  uc= new UserController();
  //              User u =uc.login("Testuser", "fedi");

            
  /*     User ua=new User("userTest@userTest.com","Testuser","Testuser","Testuser","Testuser","Testuser","Testuser","Testuser");

                       
 try {
                System.out.println("user registre  "+uc.registre(ua));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

*/

//            try {
  //              User u =uc.login("Testuser", "fedi");
    //            System.out.println("user login  "+u);
                
            //    u.setPrenomUser("fedi la");
                                
              //  u.setNomUser("fedi la");
                //u.setAdresseUser("fedi");
               // u.setTypeUser("dededed");
      //        System.err.println("changer password;"+uc.changePassword(u,"fedi","Testuser"));

               // u.setEmail("ppp@ppp.com");
          
               // u.setUsername("ppp");
             //   uc.changeUserData(u);
                
              // uc.forgotPassword("lahbib.fedi@gmail.com");
            //    uc.resetPassword("lahbib.fedi@gmail.com","userTest",2881);
      
        //              } catch (Exception ex) {
          //      Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
            //}
            
        
        
        
    }
}
