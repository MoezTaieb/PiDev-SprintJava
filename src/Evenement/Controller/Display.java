/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Controller;

import static Evenement.Controller.ListEvenementController.idEvent1;
import static Evenement.Controller.ListEvenementController.image1;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author Moez
 */
public class Display implements Initializable {
    @FXML
    ImageView imageEV ;
    
    private  String img;

    public ImageView getImageEV() {
        return imageEV;
    }

//    public void setImageEV(ImageView imageEV) {
//        imageEV.setImage(new Image(image1));
//    }

    public String getImg() {
        return img;
    }


    public void setImg(String image1) {
       // imageEV = new Image(image1);
    }
    
 
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
         imageEV.setImage(new Image(image1));
    
        System.out.println("aprés : "+image1);
      // imageEV.setImage(new Image(img));
        
    }
    
}
