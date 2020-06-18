/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Camp.Controller;


import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;

/**
 *
 * @author ME
 */
public class MailerController implements Initializable {

    private TextField mailaddress;
    private TextField mailpassword;
    @FXML
    private TextField mail_subject;
    @FXML
    private TextArea mail_description;
    @FXML
    private Pane cancel;
    @FXML
    private AnchorPane indexPane;

     @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
       private void setNode(Node node) {
        indexPane.getChildren().clear();
        indexPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }
    
    @FXML
    private void SendMail(MouseEvent event) {
        
        if(!mail_subject.getText().toString().equals("") & !mail_description.getText().toString().equals("")){
        try {
        String host="wassimsefi28@gmail.com";  //← my email address
        final String user="wassimsefi28@gmail.com";//← my email address
        final String password="123456789wassim";//change accordingly   //← my email password
 
        String to="amineuteri@gmail.com";//→ the EMAIL i want to send TO →
 
        // session object
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
 
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });
 
        //My message :
        
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Subject : " + mail_subject.getText());
            message.setText(" Contacting Administration : ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "<h1 style =\"color:red\" > " + mail_description.getText() + "  </h1>","text/html");
 
            //send the message
            Transport.send(message);
 
            
            System.out.println("message sent successfully via mail ... !!! ");
             Image image = new Image("/Camp/image/sent.png");
            Notifications notification = Notifications.create()
                    .title("Sent Successfully")
                    .text("Email Sent Successfully to Administration !")
                    .hideAfter(Duration.seconds(999))
                    .position(Pos.CENTER)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
 
        }else{  Image image = new Image("/Camp/image/error.gif");
            Notifications notification = Notifications.create()
                    .title("Error")
                    .text("Please Check Your TextFields ")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.CENTER)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();}
    }
  
    @FXML
    private void cancel(MouseEvent event) throws IOException {
         AnchorPane    myNewScene = null;
                   
        try {
          myNewScene =  FXMLLoader.load(getClass().getResource("/Camp/Views/indexRefugie.fxml"));
                    
                
 
         } catch (IOException ex) {
            Logger.getLogger(IndexRefugieController.class.getName()).log(Level.SEVERE, null, ex);
        }

             setNode(myNewScene);
       
        
      
    }
}
   
    

