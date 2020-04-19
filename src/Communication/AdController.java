package Communication;

import Communication.Action.DeleteAdActionEvent;
import Communication.Action.RecommendActionEvent;
import Communication.DAO.AnnonceDao;
import Communication.entity.Annonce;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.PiDev;
import user.CommonMethod;
import user.ProfilController;
import user.service.UploadImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fedi
 */
public class AdController extends CommonMethod implements Initializable {

    @FXML
    private AnchorPane editPanee;
    @FXML
    AnchorPane ListAnchorPane;

    @FXML
    TextField usernameInput;
    @FXML
    TextField lastnameInput;
    @FXML
    TextField firtnameInput;
    @FXML
    TextField cinInput;
    @FXML
    TextField addressInput;
    @FXML
    TextField phoneinput;
    @FXML
    TextField emailinput1;

    @FXML
    JFXTextArea decr;
    @FXML
    JFXTextField title;

    @FXML
    ImageView imageProfil;
    @FXML
    JFXTextField searchFiled;

    ImageView lookCommentsimage = new ImageView(new Image(getClass().getResourceAsStream("../icons/listComments.png")));
    ImageView recommendedimage = new ImageView(new Image(getClass().getResourceAsStream("../icons/recomend.png")));
    ImageView editIconimage = new ImageView(new Image(getClass().getResourceAsStream("../icons/editIcon.png")));
    ImageView deleteimage = new ImageView(new Image(getClass().getResourceAsStream("../icons/Delete.png")));

    ImageView reportimage = new ImageView(new Image(getClass().getResourceAsStream("../icons/alarme.png")));

    JFXButton edit = new JFXButton("Edit Ad");

    JFXButton delete = new JFXButton("Delete Ad");

    JFXButton report = new JFXButton("Report Ad");

    JFXButton lookComments = new JFXButton("Look comments");

    JFXButton recommended = new JFXButton("Recommend");

    String imageProfilUrl = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lookComments.setOnAction(((event) -> {

            System.out.println("PiDev.currentUser=" + PiDev.annonceView);

            AnchorPane myNewScene = null;

            try {
                myNewScene = null;

                myNewScene = FXMLLoader.load(getClass().getResource("../user/views/comments.fxml"));

            } catch (IOException ex) {
            }

            this.editPanee.getChildren().clear();
            this.editPanee.getChildren().add((Node) myNewScene);
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            ft.setNode(myNewScene);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();

        }));
        lookCommentsimage.setFitHeight(25);

        lookCommentsimage.setFitWidth(25);

        lookComments.setGraphic(lookCommentsimage);

        recommendedimage.setFitHeight(25);

        recommendedimage.setFitWidth(25);

        recommended.setGraphic(recommendedimage);
        edit.setGraphic(editIconimage);
        delete.setGraphic(deleteimage);
        report.setGraphic(reportimage);
        delete.setOnAction(new DeleteAdActionEvent());
        report.setOnAction((event) -> {

            System.out.println("reportr=" + PiDev.annonceView);
            Annonce a = new AnnonceDao().get(PiDev.annonceView);
            System.out.println("report" + a);
            new AnnonceController().signaler(a);
        });
        edit.setOnAction((event) -> {

            System.out.println("PiDev.currentUser=" + PiDev.annonceView);

            AnchorPane myNewScene = null;

            try {
                myNewScene = null;

                myNewScene = FXMLLoader.load(getClass().getResource("../user/views/editAd.fxml"));
            } catch (IOException ex) {
            }

            this.editPanee.getChildren().clear();
            this.editPanee.getChildren().add((Node) myNewScene);
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            ft.setNode(myNewScene);
            ft.setFromValue(0.1);
            ft.setToValue(1);
            ft.setCycleCount(1);
            ft.setAutoReverse(false);
            ft.play();
        }
        );
        deleteimage.setFitHeight(25);

        deleteimage.setFitWidth(25);

        reportimage.setFitHeight(25);

        reportimage.setFitWidth(25);

        editIconimage.setFitHeight(25);

        editIconimage.setFitWidth(25);

        recommended.setOnAction(new RecommendActionEvent());
        JFXListView<TextFlow> list = new JFXListView<>();
        list.setLayoutX(6);

        list.setLayoutY(70);

        list.setPrefHeight(600);

        list.setPrefWidth(1170);

        searchFiled.setOnKeyReleased((event) -> {
            search(searchFiled.getText());

        });

        ArrayList aList = (ArrayList) new AnnonceDao().getAll();

        for (int i = 0; i < aList.size(); i++) {
            Annonce a = (Annonce) aList.get(i);

            TextFlow tf = new TextFlow();
            Text t1 = new Text(a.getTitreAnnonce());
            t1.setStyle("-fx-font-weight: bold;");
            Text t2 = new Text("\n" + a.getDateAnnonce());
            tf.getChildren().addAll(t1, t2);
            list.getItems().add(tf);
        }

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());

                    PiDev.annonceView = annonce.getId();
                    tf = new TextFlow();
                    Text t1 = new Text(annonce.getTitreAnnonce());
                    t1.setStyle("-fx-font-weight: bold;");
                    Text t2 = new Text("\n" + annonce.getDateAnnonce());
                    tf.getChildren().addAll(t1, t2);

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                } else if (event.getClickCount() == 1) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());
                    PiDev.annonceView = annonce.getId();

                    tf = new TextFlow();

                    Text t1 = new Text(annonce.getTitreAnnonce());

                    t1.setStyle("-fx-font-weight: bold;");

                    Text t2 = new Text("\n" + annonce.getDescriptionAnnonce() + "\n");

                    t2.setStyle("-fx-font-family: LATO");

                    tf.getChildren().add(t1);

                    tf.getChildren().add(t2);

                    tf.getChildren().add(lookComments);
                    tf.getChildren().add(recommended);
                    if (annonce.getPosteur() == PiDev.currentUser.getid()) {
                        tf.getChildren().add(edit);
                        tf.getChildren().add(delete);

                        tf.getChildren().add(report);

                    }

                    FileInputStream input = null;

                    Image image = null;

                    try {

//                        input = new FileInputStream(PiDev.brochures_directory + annonce.getImageUrlAnnonce());
                        if (annonce.getImageUrlAnnonce() != null) {
                            image = new Image(PiDev.brochures_host + annonce.getImageUrlAnnonce());
                        } else {
                            input = new FileInputStream(".\\src\\icons\\picture.png");
                            image = new Image(input);
                        }

                    } catch (Exception ex) {

                    } finally {

                    }

                    ImageView iv = new ImageView(image);
                    iv.setFitHeight(300);
                    iv.setFitWidth(300);

                    iv.setLayoutX(100);

                    tf.getChildren().add(iv);

                    int x = 0;

                    int height = x * 20;

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                    list.setStyle(".jfx-list-cell:selected{-fx-cell-size: " + height + "px}");
                }

            }
        });

        list.setMinWidth(800);
        ListAnchorPane.getChildren().add(list);
        AnchorPane.setLeftAnchor(list, 20.0);

    }

    @FXML
    private void MoreRecent(ActionEvent event) {

        JFXListView<TextFlow> list = new JFXListView<>();
        list.setLayoutX(6);

        list.setLayoutY(70);

        list.setPrefHeight(600);

        list.setPrefWidth(1170);

        ArrayList aList = (ArrayList) new AnnonceDao().orderByDESC();

        for (int i = 0; i < aList.size(); i++) {
            Annonce a = (Annonce) aList.get(i);
            TextFlow tf = new TextFlow();
            Text t1 = new Text(a.getTitreAnnonce());
            t1.setStyle("-fx-font-weight: bold;");
            Text t2 = new Text("\n" + a.getDateAnnonce());
            tf.getChildren().addAll(t1, t2);
            list.getItems().add(tf);
        }

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());

                    PiDev.annonceView = annonce.getId();
                    tf = new TextFlow();
                    Text t1 = new Text(annonce.getTitreAnnonce());
                    t1.setStyle("-fx-font-weight: bold;");
                    Text t2 = new Text("\n" + annonce.getDateAnnonce());
                    tf.getChildren().addAll(t1, t2);

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                } else if (event.getClickCount() == 1) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());
                    PiDev.annonceView = annonce.getId();

                    tf = new TextFlow();

                    Text t1 = new Text(annonce.getTitreAnnonce());

                    t1.setStyle("-fx-font-weight: bold;");

                    Text t2 = new Text("\n" + annonce.getDescriptionAnnonce() + "\n");

                    t2.setStyle("-fx-font-family: LATO");

                    tf.getChildren().add(t1);

                    tf.getChildren().add(t2);

                    tf.getChildren().add(lookComments);
                    tf.getChildren().add(recommended);
                    tf.getChildren().add(report);

                    FileInputStream input = null;

                    Image image = null;

                    try {

//                        input = new FileInputStream(PiDev.brochures_directory + annonce.getImageUrlAnnonce());
                        if (annonce.getImageUrlAnnonce() != null) {
                            image = new Image(PiDev.brochures_host + annonce.getImageUrlAnnonce());
                        } else {
                            input = new FileInputStream(".\\src\\icons\\picture.png");
                            image = new Image(input);
                        }

                    } catch (FileNotFoundException ex) {
                        try {
                            input = new FileInputStream(".\\src\\icons\\picture.png");
                        } catch (FileNotFoundException ex1) {
                            Logger.getLogger(AdController.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    } finally {

                    }

                    ImageView iv = new ImageView(image);
                    iv.setFitHeight(300);
                    iv.setFitWidth(300);

                    iv.setLayoutX(100);

                    tf.getChildren().add(iv);

                    int x = 0;

                    int height = x * 20;

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                    list.setStyle(".jfx-list-cell:selected{-fx-cell-size: " + height + "px}");
                }

            }
        });

        list.setMinWidth(800);
        ListAnchorPane.getChildren().add(list);
        AnchorPane.setLeftAnchor(list, 20.0);
    }

    private void search(String ch) {

        JFXListView<TextFlow> list = new JFXListView<>();

        list.setLayoutX(6);

        list.setLayoutY(70);

        list.setPrefHeight(600);

        list.setPrefWidth(1170);
        ArrayList aList;

        if (ch.length() > 0) {
            aList = (ArrayList) new AnnonceDao().search(ch);
        } else {
            aList = (ArrayList) new AnnonceDao().getAll();
        }

        System.out.println("alist" + aList);

        for (int i = 0; i < aList.size(); i++) {
            Annonce a = (Annonce) aList.get(i);
            TextFlow tf = new TextFlow();
            Text t1 = new Text(a.getTitreAnnonce());
            t1.setStyle("-fx-font-weight: bold;");
            Text t2 = new Text("\n" + a.getDateAnnonce());
            tf.getChildren().addAll(t1, t2);
            list.getItems().add(tf);
        }

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());

                    PiDev.annonceView = annonce.getId();
                    tf = new TextFlow();
                    Text t1 = new Text(annonce.getTitreAnnonce());
                    t1.setStyle("-fx-font-weight: bold;");
                    Text t2 = new Text("\n" + annonce.getDateAnnonce());
                    tf.getChildren().addAll(t1, t2);

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                } else if (event.getClickCount() == 1) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());
                    PiDev.annonceView = annonce.getId();

                    tf = new TextFlow();

                    Text t1 = new Text(annonce.getTitreAnnonce());

                    t1.setStyle("-fx-font-weight: bold;");

                    Text t2 = new Text("\n" + annonce.getDescriptionAnnonce() + "\n");

                    t2.setStyle("-fx-font-family: LATO");

                    tf.getChildren().add(t1);

                    tf.getChildren().add(t2);

                    tf.getChildren().add(lookComments);
                    tf.getChildren().add(recommended);

                    FileInputStream input = null;

                    Image image;

                    try {

                        if (annonce.getImageUrlAnnonce() != null) {
                            image = new Image(PiDev.brochures_host + annonce.getImageUrlAnnonce());
                        } else {
                            input = new FileInputStream(".\\src\\icons\\picture.png");

                            image = new Image(input);
                        }

                    } catch (FileNotFoundException ex) {
                        try {
                            input = new FileInputStream(".\\src\\icons\\picture.png");
                        } catch (FileNotFoundException ex1) {
                            Logger.getLogger(AdController.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    } finally {

                    }

                    image = new Image(input);
                    ImageView iv = new ImageView(image);
                    iv.setFitHeight(300);
                    iv.setFitWidth(300);

                    iv.setLayoutX(100);

                    tf.getChildren().add(iv);

                    int x = 0;

                    int height = x * 20;

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                    list.setStyle(".jfx-list-cell:selected{-fx-cell-size: " + height + "px}");
                }

            }
        });

        list.setMinWidth(800);
        ListAnchorPane.getChildren().add(list);
        AnchorPane.setLeftAnchor(list, 20.0);
    }

    @FXML
    private void LessRecent(ActionEvent event) {

        JFXListView<TextFlow> list = new JFXListView<>();
        list.setLayoutX(6);

        list.setLayoutY(70);

        list.setPrefHeight(600);

        list.setPrefWidth(1170);

        ArrayList aList = (ArrayList) new AnnonceDao().orderByASC();

        for (int i = 0; i < aList.size(); i++) {
            Annonce a = (Annonce) aList.get(i);
            TextFlow tf = new TextFlow();
            Text t1 = new Text(a.getTitreAnnonce());
            t1.setStyle("-fx-font-weight: bold;");
            Text t2 = new Text("\n" + a.getDateAnnonce());
            tf.getChildren().addAll(t1, t2);
            list.getItems().add(tf);
        }

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());
                    PiDev.annonceView = annonce.getId();
                    tf = new TextFlow();
                    Text t1 = new Text(annonce.getTitreAnnonce());
                    t1.setStyle("-fx-font-weight: bold;");
                    Text t2 = new Text("\n" + annonce.getDateAnnonce());
                    tf.getChildren().addAll(t1, t2);

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                } else if (event.getClickCount() == 1) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());
                    PiDev.annonceView = annonce.getId();

                    tf = new TextFlow();

                    Text t1 = new Text(annonce.getTitreAnnonce());

                    t1.setStyle("-fx-font-weight: bold;");

                    Text t2 = new Text("\n" + annonce.getDescriptionAnnonce() + "\n");

                    t2.setStyle("-fx-font-family: LATO");

                    tf.getChildren().add(t1);

                    tf.getChildren().add(t2);

                    tf.getChildren().add(lookComments);
                    tf.getChildren().add(recommended);

                    FileInputStream input = null;

                    Image image = null;

                    try {

                        if (annonce.getImageUrlAnnonce() != null) {
                            image = new Image(PiDev.brochures_host + annonce.getImageUrlAnnonce());
                        } else {
                            input = new FileInputStream(".\\src\\icons\\picture.png");

                            image = new Image(input);
                        }

                    } catch (FileNotFoundException ex) {
                        try {
                            input = new FileInputStream(".\\src\\icons\\picture.png");

                            image = new Image(input);

                        } catch (FileNotFoundException ex1) {
                            Logger.getLogger(AdController.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    } finally {

                    }

                    ImageView iv = new ImageView(image);
                    iv.setFitHeight(300);
                    iv.setFitWidth(300);

                    iv.setLayoutX(100);

                    tf.getChildren().add(iv);

                    int x = 0;

                    int height = x * 20;

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                    list.setStyle(".jfx-list-cell:selected{-fx-cell-size: " + height + "px}");
                }

            }
        });

        list.setMinWidth(800);
        ListAnchorPane.getChildren().add(list);
        AnchorPane.setLeftAnchor(list, 20.0);

    }

    @FXML
    private void mostrecommended(ActionEvent event) {

        JFXListView<TextFlow> list = new JFXListView<>();
        list.setLayoutX(6);
        list.setLayoutY(70);

        list.setPrefHeight(600);

        list.setPrefWidth(1170);

        ArrayList aList = (ArrayList) new AnnonceDao().orderByRecommondation();

        for (int i = 0; i < aList.size(); i++) {
            Annonce a = (Annonce) aList.get(i);
            TextFlow tf = new TextFlow();
            Text t1 = new Text(a.getTitreAnnonce());
            t1.setStyle("-fx-font-weight: bold;");
            Text t2 = new Text("\n" + a.getDateAnnonce());
            tf.getChildren().addAll(t1, t2);
            list.getItems().add(tf);
        }

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());
                    PiDev.annonceView = annonce.getId();
                    tf = new TextFlow();
                    Text t1 = new Text(annonce.getTitreAnnonce());
                    t1.setStyle("-fx-font-weight: bold;");
                    Text t2 = new Text("\n" + annonce.getDateAnnonce());
                    tf.getChildren().addAll(t1, t2);

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                } else if (event.getClickCount() == 1) {

                    TextFlow tf = list.getItems().get(list.getSelectionModel().getSelectedIndex());

                    ObservableList tflist = tf.getChildren();

                    Text tt = (Text) tflist.get(0);

                    Annonce annonce = new AnnonceDao().getByTitle(tt.getText());
                    PiDev.annonceView = annonce.getId();

                    tf = new TextFlow();

                    Text t1 = new Text(annonce.getTitreAnnonce());

                    t1.setStyle("-fx-font-weight: bold;");

                    Text t2 = new Text("\n" + annonce.getDescriptionAnnonce() + "\n");

                    t2.setStyle("-fx-font-family: LATO");

                    tf.getChildren().add(t1);

                    tf.getChildren().add(t2);

                    tf.getChildren().add(lookComments);
                    tf.getChildren().add(recommended);

                    FileInputStream input = null;

                    Image image = null;

                    try {

                        if (annonce.getImageUrlAnnonce() != null) {
                            image = new Image(PiDev.brochures_host + annonce.getImageUrlAnnonce());
                        } else {
                            input = new FileInputStream(".\\src\\icons\\picture.png");

                            image = new Image(input);
                        }

                    } catch (FileNotFoundException ex) {
                        try {
                            input = new FileInputStream(".\\src\\icons\\picture.png");
                        } catch (FileNotFoundException ex1) {
                            Logger.getLogger(AdController.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    } finally {

                    }

                    ImageView iv = new ImageView(image);
                    iv.setFitHeight(300);
                    iv.setFitWidth(300);

                    iv.setLayoutX(100);

                    tf.getChildren().add(iv);

                    int x = 0;

                    int height = x * 20;

                    list.getItems().set(list.getSelectionModel().getSelectedIndex(), tf);

                    list.setStyle(".jfx-list-cell:selected{-fx-cell-size: " + height + "px}");
                }

            }
        });

        list.setMinWidth(800);
        ListAnchorPane.getChildren().add(list);
        AnchorPane.setLeftAnchor(list, 20.0);

    }

    @FXML
    private void adPublishBut(ActionEvent event) {

        Annonce an = new Annonce(title.getText(), decr.getText(), imageProfilUrl, PiDev.currentUser.getid());
        new AnnonceDao().save(an);
        title.setText("");
        decr.setText("");
        imageProfilUrl = null;
        if (imageProfilUrl == null) {

            try {
                FileInputStream input;

                input = new FileInputStream(".\\src\\icons\\picture.png");

                Image image = new Image(input);
                imageProfil.setImage(image);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void imagePicture(ActionEvent event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file);
        if (file != null) {

//            Path temp = null;
            try {
                int random_int = (int) (Math.random() * (1000 - 1000000 + 1) + 1000);
                File newfile = new File(random_int + "-" + file.getName());
                file.renameTo(newfile);
                newfile.createNewFile();
//                temp = Files.copy(Paths.get(newfile.getAbsolutePath()),
//                        Paths.get(PiDev.brochures_directory + newfile.getName()));

//                if (temp != null) {
                new UploadImage().adImage(newfile);

//                    System.out.println("File renamed and moved successfully" + temp.getFileName().toAbsolutePath().toString());
                FileInputStream input;
//                    input = new FileInputStream(PiDev.brochures_directory + newfile.getName());

                Image image = new Image(PiDev.brochures_host + newfile.getName());

                imageProfil.setImage(image);
                imageProfilUrl = newfile.getName();

//                } else {
//                    System.out.println("Failed to move the file");
//                }
            } catch (IOException ex) {
                Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void resetAdPublishBut(ActionEvent event) {

        title.setText("");
        title.setEditable(false);
        decr.setText("");
        imageProfilUrl = null;
        if (imageProfilUrl == null) {

            try {
                FileInputStream input;

                input = new FileInputStream(".\\src\\icons\\picture.png");

                Image image = new Image(input);
                imageProfil.setImage(image);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
