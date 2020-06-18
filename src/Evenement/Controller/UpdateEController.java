/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evenement.Controller;

import static Evenement.Controller.ListEvenementController.idEvent1;
import Evenement.Entity.Evenement;
import Evenement.Entity.Produit;
import Evenement.Service.EvenementService;
import Evenement.Service.UploadImage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class UpdateEController implements Initializable {

    @FXML
    private TextField nomE;
    @FXML
    private TextField lieuE;
    @FXML
    private DatePicker dateE;
    @FXML
    private TextField nbrmaxE;
    @FXML
    private Button imageE;
    
    
    private int eventID;
    @FXML
    private ComboBox<Produit> produit;
    public ObservableList<Produit> data = FXCollections.observableArrayList();
    @FXML
    private AnchorPane acc;
     @FXML  
   private void setNode(Node node) {
        
        
        
        acc.getChildren().clear();
        acc.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EvenementService es = new EvenementService();
      data = es.indexActionProduit();
              String[]    villepossible = {"rades","megrine","Tunis",	
"Sfax","Sousse",	"Kairouan","Bizerte",	"Gabès"	,"La Soukra","Ariana"	,"Sidi Hassine",	"El Mourouj",	"Gafsa"	,"Raoued	","Monastir",	
"La Marsa",	"Mnihla","Ettadhamen"	,"Kasserine"	,"Douar Hicher"	,"Djerba - Houmt Souk",	
"Le Kram",
"Hammamet",	"Zarzis",	"Le Bardo",	"Médenine",	"Nabeul",	"Tataouine",	"Ben Gardane",	" Mohamedia",	
"Djerba - Midoun",	"Béja",	"M'saken	",	"Oued Ellil",	"Moknine	","Le Kef",	
"Menzel Bourguiba",	"Kalâa Kebira	","Sakiet Ezzit",	"Mahdia",	"Jemmal",	"Ksar Hellal",	
"Sidi Bouzid",

"Kélibia",	"Fouchana",	"Sakiet Eddaïer",	"La Goulette",	"Jendouba",	"El Aïn",	"Hammam Sousse",	"Hammam Lif",	"Dar Chaâbane	","El Hamma	","Gremda	","Ennour	",
"Bou Mhel el-Bassatine",	 "Menzel Temime	","Korba	","Métlaoui",	"Soliman	",
"Téboulba",	"Tozeur	","Ezzahra	","Ouabed Khazanet",	"Kalâa Seghira	","Mateur	","El Ksar","Thyna	","La Manouba	",
"Hammam Chott	","Sisseb-Driaat	","Siliana	","El Amra	","Balta-Bou Aouane",	"Douz	","Zarzis Nord",	"Mornag	","Fériana","Joumine","Ksour Essef"	,"Djedeida"	,"Ras Jebel"	,
"Naassen	","Ghannouch",	"Tebourba",	"Akouda	","Ghezala",	"Den Den",	"Chihia","Redeyef	","Sbeïtla	","Grombalia	","Djerba - Ajim	","Raqqada	","Chrayaa-Machrek Chams",	"El Fahs","Fondouk Jedid-Seltene	","Essaïda	","Ouled Chamekh	",
"Menzel Jemil	","Chebba	","Faiedh Bennour	",
"Takelsa	","Ouchtata-Jmila	","Ezzouhour	","Ouerdanine	","Teboulbou	","Souk Jedid",	"Nefta	","Medjez el-Bab	","Bou Salem	","Béni Khiar",	"Moularès",	
"El Jem","Tinja	","Sidi Zid-Awled Moulahem	","Nadhour-Sidi Ali Ben Abed	","Zaghouan	","Zaouiet Sousse",	"Kébili	","Utique","Mornaguia	","Tabarka	","Ghardimaou",	
"Hadjeb",
" Lessouda	", "Hassi El Ferid	",
"Habib Thameur Bouatouch", "El Ayoun	","Menzel Abderrahmane",	"Sahline Moôtmar",	"Grimet-Hicher	", "Hazeg Ellouza	","Souk Lahad	","Menzel Bouzelfa",	
"El Hachachna",	
"El Alia",	"Thala	","Kalâat el-Andalous",	"Bekalta	", "Aachech-Aouadna-Boujarbou-Majel Draj	","Tajerouine",	"Ezzouhour",	"Carthage",	 "Ennasr","Zéramdine",	
"Jouaouda",	 "Tlelsa","Bembla-Mnara",	"Mahrès	","Baten Ghzal	","Kerkennah	","Béni Khalled	", "Faouar	","Tazougrane-Boukrim-Zaouiet El Mgaies	","Zannouch",	"Abida	",
"Aïn Sobh-Nadhour",	"Rakhmat	","Chenini Nahal",	 "Smâr	", "Belkhir	","Meknassy	",  "Bouzguem	","Sidi Morched	","Sidi Jedidi	","Bennane-Bodheur",	"El Guettar	",
 "Hkaima	","El Bassatine	","Makthar	","Kalaa-Maaden-Farksan	","Aïn Khmaissia	","Testour	","Kettana	","Bou Arada	","Ksibet el-Médiouni	","Souk Sebt",	"Dahmani",	"Sayada	",
"Aïn El Beïdha	","Menzel Hayet	","Messaadine	","Khmouda	","Zaafrana-Dir Kef",	"Mdhilla	","Saouaf	","Chrifet-Boucharray	", "Bouchemma",	"Khmairia",	"Le Sers",	 "Zelba	","El Amaiem	",
"Bou Argoub",	"Skhira	","Téboursouk	",
"Zriba	","Bechli-Blidet-Jerssin	","Menzel Ennour",	"Mareth	","Ksibet Thrayet	","Agareb	","Chaouachi","Regueb",	
"Sidi Thabet",	
"Khniss	","Thibar	","Enfida	","Lela	","Rejiche",	"Métouia","Hajeb El Ayoun	",
"Chraitia-Ksour	","Gaâfour	","Sidi Bou Ali	","Dkhilet Toujane",	"Menzel El Habib",	"Sidi Aïch	","Sidi Ismaïl	","Oudhref",	"Bouficha",	
"Metline",
"Raf Raf	","Jérissa	","Aïn Draham	","Mansoura	","Ghomrassen",	"Sened	","El Haouaria",	"Tazarka","Sidi Ali Ben Aoun	","Jhina	","Hammam Ghezèze	",
"Oueslatia	",
"Bechri-Fatnassa",	"Beni Hassen	","Khalidia	","Menzel Kamel	","Haffouz	","Sidi Ameur-Mesjed-Aïssa	","Rahal	","Bir Mcherga",	"Bohra", 
     "Regueb",	
"Sidi Thabet",	
"Khniss	","Thibar	","Enfida	","Lela	","Rejiche",	"Métouia","Hajeb El Ayoun	",
"Chraitia-Ksour	","Gaâfour	","Sidi Bou Ali	","Dkhilet Toujane",	"Menzel El Habib",	"Sidi Aïch	","Sidi Ismaïl	","Oudhref",	"Bouficha",	
"Metline",
"Raf Raf	","Jérissa	","Aïn Draham	","Mansoura	","Ghomrassen",	"Sened	","El Haouaria",	"Tazarka","Sidi Ali Ben Aoun	","Jhina	","Hammam Ghezèze	",
"Oueslatia	",
"Bechri-Fatnassa",	"Beni Hassen	","Khalidia	","Menzel Kamel	","Haffouz	","Sidi Ameur-Mesjed-Aïssa	","Rahal	","Bir Mcherga",	"Bohra",		
"Jilma	","Lamta	","Chorbane",	"Sejnane",	"Zarat	",
"El Marja	","Essouassi	","Ghar El Melh	","Djebel Oust	","Amiret El Fhoul	","Menzel Horr",	
"Amdoun	","Aousja	","El Ghnada",	"Azmour	","Nasrallah",	"Bargou	","Bir Ali Ben Khalifa	","El Masdour-Menzel Harb	","Hazoua	",
"Rouhia	",
"Dar Allouch	","Sidi Bennour	","Cherahil	","Jedelienne",	"Bou Merdes",	"Dehiba	","Rjim Maatoug	","El Mida	",
"Goubellat	","Sidi Boubaker	","Menzel Mehiri	","Fernana",	"Kondar	","Menzel Fersi",	"Korbous",	"Haïdra	","Cebbala Ouled Asker	","Nebeur	",
"El Alâa	",
"Sidi Bou Rouis	","Graïba	","Hebira	","Sidi Makhlouf	","Beni Khedache	","Chebika	","El Aroussa	","Sidi El Hani",	"Kesra	","Kalâat Khasba	","Ouled Haffouz	",
"Oued Meliz	",
"Tamerza	",
"Menzel Chaker	",
"Touiref	",
"Matmata	",
"Menzel Salem",	
"Aïn Djeloula",
"Echrarda"	,"Beni M'Tir"   };
        TextFields.bindAutoCompletion(lieuE, villepossible);
        FileChooser fileChooser = new FileChooser();
        imageE.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    Window stage = null;
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        UploadImage ui = new UploadImage();
                        
                        
                        try {
                            System.out.print("file"+file.getName());
                           
                            ui.adImage(file);
                        } catch (IOException ex) {
                            Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        imageE.setText(file.getName());
                        
                    }
                }
            });  
      
      produit.setItems(data);
    }    

    @FXML
    private void updateE(ActionEvent event) throws IOException {
        Produit P = new Produit();
        P = produit.getValue();
         Evenement E = new Evenement(idEvent1,nomE.getText(), lieuE.getText(), dateE.getValue().toString(), Integer.parseInt(nbrmaxE.getText()), imageE.getText(), P.getId());        
         System.out.println(idEvent1);
         EvenementService ser = new EvenementService();
        ser.updateAction(E);
        String x = E.toString();
        System.out.println("update after: "+x);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Evenement/Views/ListEvenement.fxml"));
//        Parent root = loader.load();
//        nomE.getScene().setRoot(root);
 AnchorPane    myNewScene = null;
  
                   
        try {
                myNewScene =  FXMLLoader.load(getClass().getResource("/Evenement/Views/ListEvenement.fxml"));
                    
                   


 
         } catch (IOException ex) {
           
        }
                     setNode(myNewScene);

    }

    
}
