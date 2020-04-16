/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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

    public void setImageEV(ImageView imageEV) {
        imageEV.setImage(new Image(img));
    }

    public String getImg() {
        return img;
    }


    public void setImg(String img) {
        imageEV.setImage(new Image(img));
    }
    
 
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
    
        System.out.println("aaaaa");
      // imageEV.setImage(new Image(img));
        
    }
    
}
