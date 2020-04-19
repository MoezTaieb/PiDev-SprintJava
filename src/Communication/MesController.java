/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Communication.DAO.AnnonceDao;
import Communication.DAO.MessageDao;
import Communication.entity.Annonce;
import Communication.entity.Message;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import pidev.PiDev;
import user.CommonMethod;
import user.DAO.UserDao;
import user.entity.User;

/**
 *
 * @author fedi
 */
public class MesController extends CommonMethod implements Initializable {

    @FXML
    private JFXListView userList;
    @FXML
    private AnchorPane mesAnchorPane;
    @FXML
    private JFXButton sendMsg;
    @FXML
    private JFXTextField msgText;
    @FXML
    private JFXTextField subjectText;
    private User userSelected = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sendMsg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("msgText" + msgText.getText());

                System.out.println("subjectText" + subjectText.getText());
                if (msgText.getText().length() > 0 && subjectText.getText().length() > 0 && userSelected != null) {

                    Message c = new Message(0, pidev.PiDev.currentUser.getid(), userSelected.getid(), subjectText.getText(), msgText.getText(), "piece", 0, "2020-04-01 06:53:38");
                    new MessageDao().AjouterMessage(c);
                    msgText.setText("");
                    subjectText.setText("");

                    ArrayList messeages = (ArrayList) new MessageDao().GetAllMessageByEmeteur(userSelected.getid());

                    JFXListView<TextFlow> mesList = new JFXListView<>();

                    mesAnchorPane.getChildren().add(mesList);

                    mesList.setLayoutX(0);

                    mesList.setLayoutY(0);

                    mesList.setPrefHeight(635);

                    mesList.setPrefWidth(900);

                    for (int i = 0; i < messeages.size(); i++) {
                        Message a = (Message) messeages.get(i);
                        FileInputStream input = null;
                        Image image = null;
                        String ch;
                        if (a.getEmetteur_id() != pidev.PiDev.currentUser.getid()) {
                            ch = "  Sended by " + userSelected.getUsername() + " Sended at " + a.getDateEnvoi();
                            
                             if (userSelected.getImageUrlUser() != null) {
                            image = new Image(PiDev.brochures_host + userSelected.getImageUrlUser());

                        } else {
                            try {
                                input = new FileInputStream(".\\src\\icons\\avatar.png");
                            } catch (FileNotFoundException ex) {
                            }
                            image = new Image(input);
                        }
                             
                        } else {

                            ch = "  Sended by my Sended at " + a.getDateEnvoi();
                                  if ( PiDev.currentUser.getImageUrlUser() != null) {
                            image = new Image(PiDev.brochures_host +PiDev.currentUser.getImageUrlUser());

                        } else {
                            try {
                                input = new FileInputStream(".\\src\\icons\\avatar.png");
                            } catch (FileNotFoundException ex) {
                            }
                            image = new Image(input);
                        }
                        }
                      
                       
                        ImageView iv = new ImageView(image);

                        iv.setFitHeight(25);
                        iv.setFitWidth(25);

                        TextFlow tff = new TextFlow();
         
                        Text t1 = new Text(a.getBody());
                                        
                    Text t3 = new Text( "\n Subject " + a.getSubject() + "\n");
                        t1.setStyle("-fx-font-weight: bold;");
                        Text t2 = new Text(ch);
                        if (a.getEmetteur_id() != pidev.PiDev.currentUser.getid()) {
                            tff.getChildren().addAll(t1,t3, iv, t2);

                        } else {
                            JFXButton dl = new JFXButton("Delete message ");

                            dl.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    int index = mesList.getSelectionModel().getSelectedIndex();
                                    if (index > -1) {
                                        //getMessageByTitre

                                        TextFlow ss = mesList.getItems().get(index);

                                        ObservableList tflist = ss.getChildren();

                                        MessageDao md = new MessageDao();
                                        Text tt = (Text) tflist.get(0);
                                        Message messageSel = md.getMessageByTitre(tt.getText());
                                        if (messageSel != null && messageSel.getEmetteur_id() == pidev.PiDev.currentUser.getid()) {

                                            md.removeMessage(messageSel);

                                            mesList.getItems().remove(index);

                                        }
                                    }
                                }
                            });
                            tff.getChildren().addAll(t1,t3,iv,t2, dl);

                        }

                        mesList.getItems().add(tff);

                    }
                }

            }
        });

        UserDao ud = new UserDao();

        ArrayList aList = (ArrayList) ud.getAll();

        for (int i = 0; i < aList.size(); i++) {
            User a = (User) aList.get(i);
            if (a.getid() != pidev.PiDev.currentUser.getid()) {
                TextFlow tf = new TextFlow();

                FileInputStream input = null;
                Image image = null;
                if (a.getImageUrlUser() != null) {
                    image = new Image(PiDev.brochures_host + PiDev.currentUser.getImageUrlUser());

                } else {
                    try {
                        input = new FileInputStream(".\\src\\icons\\avatar.png");
                    } catch (FileNotFoundException ex) {
                    }
                    image = new Image(input);
                }
                ImageView iv = new ImageView(image);

                iv.setFitHeight(25);
                iv.setFitWidth(25);

                Text t1 = new Text(a.getUsername());
                t1.setStyle("-fx-font-weight: bold;");
                Text t2 = new Text("\n" + a.getNomUser() + " " + a.getPrenomUser());
                tf.getChildren().addAll(iv, t1);
                userList.getItems().add(tf);
                /*
          
                tf.getChildren().add(iv);
                
                 */
            }
        }

        userList.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {

                TextFlow tf = (TextFlow) userList.getItems().get(userList.getSelectionModel().getSelectedIndex());

                ObservableList tflist = tf.getChildren();

                Text tt = (Text) tflist.get(1);

                User user = new UserDao().getByUsername(tt.getText());
                userSelected = user;
                ArrayList messeages = (ArrayList) new MessageDao().GetAllMessageByEmeteur(user.getid());

                JFXListView<TextFlow> mesList = new JFXListView<>();

                mesAnchorPane.getChildren().add(mesList);

                mesList.setLayoutX(0);

                mesList.setLayoutY(0);

                mesList.setPrefHeight(635);

                mesList.setPrefWidth(900);

                for (int i = 0; i < messeages.size(); i++) {
                    Message a = (Message) messeages.get(i);

                    FileInputStream input = null;
                    Image image = null;

                    String ch;
                    if (a.getEmetteur_id() != pidev.PiDev.currentUser.getid()) {
                        ch = " Sended by " + userSelected.getUsername() + " Sended at " + a.getDateEnvoi();

                        if (userSelected.getImageUrlUser() != null) {
                            image = new Image(PiDev.brochures_host + userSelected.getImageUrlUser());

                        } else {

                            try {
                                input = new FileInputStream(".\\src\\icons\\avatar.png");

                            } catch (FileNotFoundException ex) {

                            }

                            image = new Image(input);

                        }

                        ImageView iv = new ImageView(image);

                        iv.setFitHeight(25);

                        iv.setFitWidth(25);

                        //
                    } else {
                        ch = " Sended by my Sended at " + a.getDateEnvoi();
                        if (PiDev.currentUser.getImageUrlUser() != null) {
                            image = new Image(PiDev.brochures_host + PiDev.currentUser.getImageUrlUser());

                        } else {

                            try {
                                input = new FileInputStream(".\\src\\icons\\avatar.png");

                            } catch (FileNotFoundException ex) {

                            }

                            image = new Image(input);

                        }

                    }

                    ImageView iv = new ImageView(image);

                    iv.setFitHeight(25);

                    iv.setFitWidth(25);

                    TextFlow tff = new TextFlow();
                    Text t1 = new Text(a.getBody());
                                        
                    Text t3 = new Text( "\n Subject " + a.getSubject() + "\n");

                    t1.setStyle("-fx-font-weight: bold;");
                    Text t2 = new Text(ch);
                    if (a.getEmetteur_id() != pidev.PiDev.currentUser.getid()) {
                        tff.getChildren().addAll(t1,t3, iv, t2);

                    } else {
                        JFXButton dl = new JFXButton("Delete message ");

                        dl.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                int index = mesList.getSelectionModel().getSelectedIndex();
                                if (index > -1) {
                                    TextFlow ss = mesList.getItems().get(index);
                                    ObservableList tflist = ss.getChildren();
                                    MessageDao md = new MessageDao();
                                    Text tt = (Text) tflist.get(0);
                                    Message messageSel = md.getMessageByTitre(tt.getText());
                                    if (messageSel != null && messageSel.getEmetteur_id() == pidev.PiDev.currentUser.getid()) {
                                        md.removeMessage(messageSel);
                                        mesList.getItems().remove(index);
                                    }
                                }
                            }
                        });
                        tff.getChildren().addAll(t1,t3, iv,t2, dl);
                        
                    }
                    mesList.getItems().add(tff);
                }

            }
        });

    }

}
