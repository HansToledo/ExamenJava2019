package controller;

import controller.create.AddHulpdienstController;
import controller.create.AddSchipController;
import controller.create.AddVerkeerstorenController;
import database.DBqueries;
import enums.StatusVervoermiddel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import model.*;
import model.Schepen;
import java.util.ArrayList;
import java.util.Random;

public class KustwachtController {
    @FXML private Button randomNoodButton;
    @FXML private TextField txtNoodNaam;
    @FXML private TextField txtNoodStatus;
    @FXML private TextField txtNoodLongitude;
    @FXML private TextField txtNoodCapaciteit;
    @FXML private TextField txtNoodGrootte;
    @FXML private TextField txtNoodLatitude;
    @FXML private TextField txtNoodSnelheid;
    @FXML private TextField txtNoodKoers;
    @FXML private TextField txtSchipNaam;
    @FXML private TextField txtSchipStatus;
    @FXML private TextField txtSchipLongitude;
    @FXML private TextField txtSchipCapaciteit;
    @FXML private TextField txtSchipGrootte;
    @FXML private TextField txtSchipLatitude;
    @FXML private TextField txtSchipSnelheid;
    @FXML private TextField txtSchipKoers;
    @FXML private TextField txtHulpdienstNaam;
    @FXML private TextField txtHulpdienstStatus;
    @FXML private TextField txtHulpdienstLongitude;
    @FXML private TextField txtHulpdienstCapaciteit;
    @FXML private TextField txtHulpdienstGrootte;
    @FXML private TextField txtHulpdienstLatitude;
    @FXML private TextField txtHulpdienstSnelheid;
    @FXML private TextField txtHulpdienstKoers;
    @FXML private TextField txtVerkeerstorenLongitude;
    @FXML private TextField txtVerkeerstorenLatitude;
    @FXML private TextField txtVerkeerstorenNaam;
    @FXML public TextArea txtAreaTerminal;
    @FXML private Label lblNoodNaam;
    @FXML private Label lblNoodLongitude;
    @FXML private Label lblNoodSnelheid;
    @FXML private Label lblNoodCapaciteit;
    @FXML private Label lblNoodLatitude;
    @FXML private Label lblNoodStatus;
    @FXML private Label lblNoodGrootte;
    @FXML private Label lblNoodKoers;
    @FXML private Label lblSchipNaam;
    @FXML private Label lblSchipLongitude;
    @FXML private Label lblSchipSnelheid;
    @FXML private Label lblSchipCapaciteit;
    @FXML private Label lblSchipLatitude;
    @FXML private Label lblSchipStatus;
    @FXML private Label lblSchipGrootte;
    @FXML private Label lblSchipKoers;
    @FXML private Label lblHulpdienstNaam;
    @FXML private Label lblHulpdienstLongitude;
    @FXML private Label lblHulpdienstSnelheid;
    @FXML private Label lblHulpdienstCapaciteit;
    @FXML private Label lblHulpdienstLatitude;
    @FXML private Label lblHulpdienstStatus;
    @FXML private Label lblHulpdienstGrootte;
    @FXML private Label lblHulpdienstKoers;
    @FXML private Label lblVerkeerstorenNaam;
    @FXML private Label lblVerkeerstorenLongitude;
    @FXML private Label lblVerkeerstorens;
    @FXML private Label lblSchepen;
    @FXML private Label lblHulpdiensten;
    @FXML private Label lblLatitude;
    @FXML private ListView<Verkeerstoren> lstViewVerkeerstorens;
    @FXML private ListView<Vervoermiddel> lstViewSchepen;
    @FXML private ListView<Vervoermiddel> lstViewHulpdiensten;
    @FXML private ListView<Schepen> lstViewSchepenInNood;

    private Random randomGenerator = new Random();
    private final DBqueries kustwachtQueries = new DBqueries();
    private ArrayList<Schepen> schepenNietInNood = new ArrayList<Schepen>();
    private ArrayList<Schepen> schepenAlGekozen = new ArrayList<Schepen>();
    private final ObservableList<Verkeerstoren> verkeerstorenList = FXCollections.observableArrayList();
    private final ObservableList<Vervoermiddel> schepenList = FXCollections.observableArrayList();
    private final ObservableList<Vervoermiddel> hulpdienstenList = FXCollections.observableArrayList();
    private final ObservableList<Schepen> schepenInNoodList = FXCollections.observableArrayList();
    private ArrayList<Stage> openWindows = new ArrayList<>();   //Houd de geopende vensters van de schepen in nood bij.

    public void initialize() {
        lstViewVerkeerstorens.setItems(verkeerstorenList); // Lijst van verkeertorens koppelen aan de listview
        lstViewSchepen.setItems(schepenList); // Lijst van schepen koppelen aan de listview
        lstViewHulpdiensten.setItems(hulpdienstenList); // Lijst van hulpdiensten koppelen aan de listview
        lstViewSchepenInNood.setItems(schepenInNoodList); // Lijst van hulpdiensten koppelen aan de listview
        getAllVerkeerstorenEntries();
        getAllSchepenEntries();
        getAllHulpdiensten();

        for (String item : Randomizer.output) {
            txtAreaTerminal.appendText(item + "\n");
        }

        //region Listener gekoppeld aan de listview van de verkeerstorens zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewVerkeerstorens.getSelectionModel().selectedItemProperty().addListener(
                (observableVerkeerstorenValue, oldVerkeerstorenValue, newVerkeerstorenValue) -> {
                    displayVerkeerstoren(newVerkeerstorenValue);
                }
        );
        //endregion

        //region Listener gekoppeld aan de listview van de schepen zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewSchepen.getSelectionModel().selectedItemProperty().addListener(
                (observableSchipValue, oldSchipValue, newSchipValue) -> {
                    displaySchip(newSchipValue);
                }
        );
        //endregion

        //region Listener gekoppeld aan de listview van de hulpdiensten zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().addListener(
                (observableHulpdienstValue, oldHulpdienstValue, newHulpdienstValue) -> {
                    displayHulpdienst(newHulpdienstValue);
                }
        );
        //endregion
    }

    @FXML
    void lstViewSchepenInNood_Clicked(MouseEvent getOnMouseClicked){
        try {
            if (!schepenInNoodList.isEmpty()) {
                displaySchepenInNood(lstViewSchepenInNood.getSelectionModel().selectedItemProperty().get());

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller/Rescue.fxml"));
                Parent parent = fxmlLoader.load();
                RescueController dialogFXController = fxmlLoader.getController();

                Schepen schipInNood = lstViewSchepenInNood.getSelectionModel().selectedItemProperty().get();
                String schipInNoodNaam = lstViewSchepenInNood.getSelectionModel().selectedItemProperty().getValue().getNaam();

                Verkeerstoren geregistreerdeVerkeerstoren = null;
                for (Verkeerstoren item : verkeerstorenList) {
                    String vk = lstViewSchepenInNood.getSelectionModel().selectedItemProperty().getValue().getVerkeerstorenIngeschreven().toString();
                    if (item.getNaam().equals(vk)) {
                        geregistreerdeVerkeerstoren = item;
                    }
                }
                ArrayList<Vervoermiddel> redders = null;

                //Doorgeven beschikbare hulpdiensten voor schip in nood, eerst kijken welke verkeerstoren en dan de redders van deze verkeerstoren opvragen.
                for (Verkeerstoren item : verkeerstorenList) {
                    String vk = lstViewSchepenInNood.getSelectionModel().selectedItemProperty().getValue().getVerkeerstorenIngeschreven().toString();
                    if (item.getNaam().equals(vk)) {
                        redders = item.Redders;
                        break;
                    }
                }

                dialogFXController.RescueController(KustwachtController.this, redders, geregistreerdeVerkeerstoren, schipInNood);

                Stage inNoodStage = new Stage();
                inNoodStage.setTitle(schipInNoodNaam + "      NOODSITUATIE: " + schipInNood.getStatus());

                boolean windowExists = false;
                for (Stage item: openWindows){
                    if((item.getTitle().equals(inNoodStage.getTitle()))){
                        windowExists = true;
                        break;
                    }
                }
                if (!windowExists){
                    openWindows.add(inNoodStage);
                    inNoodStage.setResizable(false);
                    inNoodStage.setScene(new Scene(parent));
                    inNoodStage.setOnCloseRequest(
                            event -> openWindows.remove(inNoodStage));
                    inNoodStage.show();
                }
            }
        }
        catch (NullPointerException E) {
            //Normaal wordt nullpointerexception gesmeten bij selecteren van een leeg record. Hiermee wordt de error opgevangen zonder dat de gebruiker dit merkt.
        }
        catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden.\n" + E);
            System.out.println(E);
        }
    }

    @FXML
    void btnVerkeerstorenToevoegen_Clicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller/create/AddVerkeerstoren.fxml"));
            Parent parent = fxmlLoader.load();
            AddVerkeerstorenController addVerkeerstorenController = fxmlLoader.getController();
            addVerkeerstorenController.AddActorController(KustwachtController.this,"Verkeerstoren");
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Verkeerstoren aanmaken");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden.\n" + E);
            System.out.println(E);
        }
    }

    @FXML
    void btnVerkeerstorenWissen_Clicked(ActionEvent event) {
        try{
            String naam = lstViewVerkeerstorens.getSelectionModel().selectedItemProperty().getValue().getNaam();
            int markedForDeletion = kustwachtQueries.deleteVerkeerstoren(naam); //Verkeerstoren wordt gewist uit de database.
            Verkeerstoren teDeletenVerkeerstoren = null;
            for (Verkeerstoren item: verkeerstorenList){
                if(item.getNaam().equals(naam)){
                    teDeletenVerkeerstoren = item;
                    Actor.verkeerstorens.remove(item);
                }
            }

            if (markedForDeletion == 1) {
                txtAreaTerminal.appendText("\nVerkeerstoren " + teDeletenVerkeerstoren.getNaam() + " succesvol verwijderd.\n");
                displayAlert(Alert.AlertType.INFORMATION, "Verkeerstoren verwijderd.", teDeletenVerkeerstoren.getNaam() + " succesvol verwijderd.");
                getAllVerkeerstorenEntries();
            }
            else {
                displayAlert(Alert.AlertType.ERROR, "Error bij verwijderen.", "De te verwijderen verkeerstoren werd niet gevonden.");
            }
        }
        catch (NullPointerException E) {
            displayAlert(Alert.AlertType.INFORMATION, "GEEN SELECTIE", "Gelieve een verkeerstoren te selecteren.");
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    @FXML
    void btnSchipToevoegen_Clicked(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller/create/AddSchip.fxml"));
            Parent parent = fxmlLoader.load();
            AddSchipController addSchipController = fxmlLoader.getController();
            addSchipController.AddActorController(KustwachtController.this,"Schip");
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Schip aanmaken");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden.\n" + E);
            System.out.println(E);
        }
    }

    @FXML
    void btnSchipWissen_Clicked(ActionEvent event) {
        try{
            String naam = lstViewSchepen.getSelectionModel().selectedItemProperty().getValue().getNaam();
            int markedForDeletion = kustwachtQueries.deleteSchip(naam); //Schip wordt gewist uit de database.
            Vervoermiddel teDeletenSchip = null;
            for (Vervoermiddel item: schepenList){
                if(item.getNaam().equals(naam)){
                    teDeletenSchip = item;
                    Actor.schepenOpWater.remove(item);
                    Actor.mogelijkeHulpdiensten.remove(item);
                }
            }

            if (markedForDeletion == 1) {
                txtAreaTerminal.appendText("\nSchip " + teDeletenSchip.getNaam() + " succesvol verwijderd.\n");
                displayAlert(Alert.AlertType.INFORMATION, "Schip verwijderd.", teDeletenSchip.getNaam() + " succesvol verwijderd.");
                getAllSchepenEntries();
                getAllHulpdiensten();
                getAllSchepenInNood();
            }
            else {
                displayAlert(Alert.AlertType.ERROR, "Error bij verwijderen.", "Het te verwijderen schip werd niet gevonden.");
            }
        }
        catch (NullPointerException E) {
            displayAlert(Alert.AlertType.INFORMATION, "GEEN SELECTIE", "Gelieve een schip te selecteren.");
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    @FXML
    void btnHulpdienstToevoegen_Clicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controller/create/AddHulpdienst.fxml"));
            Parent parent = fxmlLoader.load();
            AddHulpdienstController addHulpdienstController = fxmlLoader.getController();
            addHulpdienstController.AddActorController(KustwachtController.this, "Hulpdienst");
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Hulpdienst aanmaken");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden.\n" + E);
            System.out.println(E);
        }
    }

    @FXML
    void btnHulpdienstWissen_Clicked(ActionEvent event) {
        try{
            String wat="Hulpdienst verwijderd.";
            String naam = lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().getNaam();
            int markedForDeletion = kustwachtQueries.deleteSchip(naam); //Hulpdienst wordt gewist uit de database.
            Vervoermiddel teDeletenHulpdienst = null;
            for (Vervoermiddel item: hulpdienstenList){
                if(item.getNaam().equals(naam)){
                    teDeletenHulpdienst = item;
                    Actor.mogelijkeHulpdiensten.remove(item);

                    for (enums.Schepen itemEnum: enums.Schepen.values()){
                        if(itemEnum.toString().equals(item.getEnumNaam())){
                            Actor.schepenOpWater.remove(item);
                            wat = "Hulpdienst en schip verwijderd.";
                            break;
                        }
                    }
                }
            }

            if (markedForDeletion == 1) {
                txtAreaTerminal.appendText("\n" + wat + " " + teDeletenHulpdienst.getNaam() + " succesvol verwijderd.\n");
                displayAlert(Alert.AlertType.INFORMATION, wat, teDeletenHulpdienst.getNaam() + " succesvol verwijderd.");
                getAllSchepenEntries();
                getAllHulpdiensten();
                getAllSchepenInNood();
            }
            else {
                displayAlert(Alert.AlertType.ERROR, "Error bij verwijderen.", "De te verwijderen hulpdienst werd niet gevonden.");
            }
        }
        catch (NullPointerException E) {
            displayAlert(Alert.AlertType.INFORMATION, "GEEN SELECTIE", "Gelieve een hulpdienst te selecteren.");
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    @FXML
    void randomNoodButton_Clicked(ActionEvent event) {
        int teller = 0;
        int aantalRandomInNood=0;
        StatusVervoermiddel nieuwNoodSignaal;
        Schepen schip;
        schepenNietInNood.clear();
        schepenAlGekozen.clear();

        for (Schepen item : Actor.schepenOpWater) {
            if (item.getStatus().equals(StatusVervoermiddel.OK.toString())) {
                schepenNietInNood.add(item);
            }
        }

        switch (schepenNietInNood.size()) {
            case 0:
                displayAlert(Alert.AlertType.INFORMATION, "Iedereen is al aan het verdrinken!", "Alle schepen hebben al een noodsituatie!");
                break;
            case 1:
                aantalRandomInNood = 1;
                nieuwNoodSignaal = StatusVervoermiddel.values()[(int) (Math.random() * StatusVervoermiddel.values().length)];
                schip = schepenNietInNood.get(0);
                schip.setNoodSignaal(nieuwNoodSignaal);

                txtAreaTerminal.appendText("\nSchip in nood : " + schip.getNaam() + " signaal ontvangen door verkeerstoren : " + schip.getVerkeerstorenIngeschreven() + " Noodsignaal is : " + nieuwNoodSignaal+"\n");
                break;
            default:
                aantalRandomInNood = randomGenerator.nextInt((schepenNietInNood.size() / 2) + 1);
                do {
                    nieuwNoodSignaal = StatusVervoermiddel.values()[(int) (Math.random() * StatusVervoermiddel.values().length)];
                    schip = kiesRandomSchip();
                    schip.setNoodSignaal(nieuwNoodSignaal);

                    txtAreaTerminal.appendText("\nSchip in nood : " + schip.getNaam() + " signaal ontvangen door verkeerstoren : " + schip.getVerkeerstorenIngeschreven() + " Noodsignaal is : " + nieuwNoodSignaal+"\n");
                    ++teller;
                } while (aantalRandomInNood > teller);
                break;
        }
        getAllHulpdiensten();
        getAllSchepenInNood();
    }


    public Schepen kiesRandomSchip() {
        int index = randomGenerator.nextInt(schepenNietInNood.size());
        Schepen schip = schepenNietInNood.get(index);
        boolean schipAlGekozen = schipAlGekozen(schip);

        if (schipAlGekozen == false) {

            EventLogger.logger.info(String.format("Random schip in nood gekozen " + schip.getNaam()));
            txtAreaTerminal.appendText("\nRandom schip in nood gekozen " + schip.getNaam());
            //System.out.println("\nRandom schip in nood gekozen " + schip.getNaam());
            schepenAlGekozen.add(schip);
            return schip;
        } else if (schipAlGekozen == true) {

            int index2;
            Schepen schip2;
            boolean schipAlGekozen2 = false;

            do {
                index2 = randomGenerator.nextInt(schepenNietInNood.size());
                schip2 = schepenNietInNood.get(index);
                schipAlGekozen2 = schipAlGekozen(schip2);

            } while (schipAlGekozen2 == true);

            EventLogger.logger.info(String.format("Random schip in nood gekozen " + schip.getNaam()));
            txtAreaTerminal.appendText("\nRandom schip in nood gekozen " + schip2.getNaam());
            //System.out.println("\nRandom schip in nood gekozen " + schip2.getNaam());
            schepenAlGekozen.add(schip2);
            return schip2;
        }

        return null;
        //txtAreaTerminal.clear();
    }

    public boolean schipAlGekozen(Schepen schip) {
        for (Schepen item : schepenAlGekozen) {
            if (item.getNaam().equals(schip.getNaam())) {
                return true;
            }
        }
        return false;
    }

    //alle entries van de tabel met de leden van de database opvragen en invullen in de ledenlijst
    public void getAllVerkeerstorenEntries() {
        try {
            if (!Actor.verkeerstorens.isEmpty())
            {
                verkeerstorenList.setAll(Actor.verkeerstorens);
            }
            else
            {
                verkeerstorenList.clear();
            }
            //verkeerstorenList.setAll(kustwachtQueries.getAllVerkeerstorens()); //deze uncommenten om rechtstreeks data uit database in te laden ipv de ingeladen lijst.
        } catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een fout opgetreden bij het inladen van de verkeerstorens.");
        }
    }

    //alle entries van de tabel met de schepen van de database opvragen en invullen in de schepenlijst
    public void getAllSchepenEntries() {
        try {
            if (!Actor.schepenOpWater.isEmpty())
            {
                schepenList.setAll(Actor.schepenOpWater);
                schepenList.get(0).getStatus();
            }
            else
            {
                schepenList.clear();
            }
            //schepenList.setAll(kustwachtQueries.getAllSchepen()); //deze uncommenten om rechtstreeks data uit database in te laden ipv de ingeladen lijst.
        } catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een fout opgetreden bij het inladen van de schepen.");
        }
    }

    //alle entries van de tabel met de hulpdiensten van de database opvragen en invullen in de hulpdienstenlijst
    public void getAllHulpdiensten() {
        ArrayList<Vervoermiddel> hulpdiensten = Actor.mogelijkeHulpdiensten;
        ArrayList<Vervoermiddel> hulpdienstenOK = new ArrayList<>();
        try {
            for (Vervoermiddel item : hulpdiensten) {
                if ((item.getStatus().equals(StatusVervoermiddel.OK.toString()))) {
                    hulpdienstenOK.add(item);
                }
            }
            hulpdienstenList.setAll(hulpdienstenOK);
        } catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een fout opgetreden bij het inladen van de hulpdiensten.");
        }
    }

    //alle entries van de tabel met de schepen in nood van de database opvragen en invullen in de schepeninnoodlijst
    public void getAllSchepenInNood() {
        ArrayList<Schepen> schepen = Actor.schepenOpWater;
        ArrayList<Schepen> schepenInNood = new ArrayList<>();
        try {
            for (Schepen item : schepen) {
                if (!(item.getStatus().equals(StatusVervoermiddel.OK.toString()))) {
                    schepenInNood.add(item);
                }
            }
            schepenInNoodList.clear();
            schepenInNoodList.setAll(schepenInNood);
        } catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een fout opgetreden bij het inladen van de schepen in nood.");
        }
    }

    //informatie verkeerstoren tonen in de voorziene vakken
    private void displayVerkeerstoren(Verkeerstoren verkeerstoren) {
        try {
            if (verkeerstoren != null) {
                txtVerkeerstorenNaam.setText(verkeerstoren.getNaam());
                txtVerkeerstorenLatitude.setText(String.valueOf(verkeerstoren.getCoördinaten().getBreedte()));
                txtVerkeerstorenLongitude.setText(String.valueOf(verkeerstoren.getCoördinaten().getLengte()));
            } else {
                txtVerkeerstorenNaam.clear();
                txtVerkeerstorenLatitude.clear();
                txtVerkeerstorenLongitude.clear();
            }
        } catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden." + "\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    //informatie schepen tonen in de voorziene vakken
    private void displaySchip(Vervoermiddel vervoermiddel) {
        try {
            if (vervoermiddel != null) {
                txtSchipNaam.setText(String.valueOf(vervoermiddel.getNaam()));
                txtSchipSnelheid.setText(String.valueOf(vervoermiddel.getSnelheid()));
                txtSchipGrootte.setText(String.valueOf(vervoermiddel.getGrootte()));
                txtSchipCapaciteit.setText(String.valueOf(vervoermiddel.getCapaciteit()));
                txtSchipKoers.setText(String.valueOf(vervoermiddel.getKoers()));
                txtSchipLatitude.setText(String.valueOf(vervoermiddel.getCoördinaten().getBreedte()));
                txtSchipLongitude.setText(String.valueOf(vervoermiddel.getCoördinaten().getLengte()));
                txtSchipStatus.setText(String.valueOf(vervoermiddel.getStatus()));
            } else {
                txtSchipNaam.clear();
                txtSchipSnelheid.clear();
                txtSchipGrootte.clear();
                txtSchipCapaciteit.clear();
                txtSchipKoers.clear();
                txtSchipLatitude.clear();
                txtSchipLongitude.clear();
                txtSchipStatus.clear();
            }
        } catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden." + "\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    //informatie hulpdiensten tonen in de voorziene vakken
    private void displayHulpdienst(Vervoermiddel vervoermiddel) {
        try {
            if (vervoermiddel != null) {
                txtHulpdienstNaam.setText(String.valueOf(vervoermiddel.getNaam()));
                txtHulpdienstSnelheid.setText(String.valueOf(vervoermiddel.getSnelheid()));
                txtHulpdienstGrootte.setText(String.valueOf(vervoermiddel.getGrootte()));
                txtHulpdienstCapaciteit.setText(String.valueOf(vervoermiddel.getCapaciteit()));
                txtHulpdienstKoers.setText(String.valueOf(vervoermiddel.getKoers()));
                txtHulpdienstLatitude.setText(String.valueOf(vervoermiddel.getCoördinaten().getBreedte()));
                txtHulpdienstLongitude.setText(String.valueOf(vervoermiddel.getCoördinaten().getLengte()));
                txtHulpdienstStatus.setText(String.valueOf(vervoermiddel.getStatus()));
            } else {
                txtHulpdienstNaam.clear();
                txtHulpdienstSnelheid.clear();
                txtHulpdienstGrootte.clear();
                txtHulpdienstCapaciteit.clear();
                txtHulpdienstKoers.clear();
                txtHulpdienstLatitude.clear();
                txtHulpdienstLongitude.clear();
                txtHulpdienstStatus.clear();
            }
        } catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden." + "\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    //informatie schepen in nood tonen in de voorziene vakken
    private void displaySchepenInNood(Vervoermiddel vervoermiddel) {
        try {
            if (vervoermiddel != null) {
                txtNoodNaam.setText(String.valueOf(vervoermiddel.getNaam()));
                txtNoodSnelheid.setText(String.valueOf(vervoermiddel.getSnelheid()));
                txtNoodGrootte.setText(String.valueOf(vervoermiddel.getGrootte()));
                txtNoodCapaciteit.setText(String.valueOf(vervoermiddel.getCapaciteit()));
                txtNoodKoers.setText(String.valueOf(vervoermiddel.getKoers()));
                txtNoodLatitude.setText(String.valueOf(vervoermiddel.getCoördinaten().getBreedte()));
                txtNoodLongitude.setText(String.valueOf(vervoermiddel.getCoördinaten().getLengte()));
                txtNoodStatus.setText(String.valueOf(vervoermiddel.getStatus()));
            } else {
                txtNoodNaam.clear();
                txtNoodSnelheid.clear();
                txtNoodGrootte.clear();
                txtNoodCapaciteit.clear();
                txtNoodKoers.clear();
                txtNoodLatitude.clear();
                txtNoodLongitude.clear();
                txtNoodStatus.clear();
            }
        } catch (Exception E) {
            displayAlert(Alert.AlertType.ERROR, "ERROR", "Er is een onverwachte fout opgetreden." + "\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
