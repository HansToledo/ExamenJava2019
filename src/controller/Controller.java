package controller;

import database.DBqueries;
import enums.Hulpdiensten;
import enums.StatusVervoermiddel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import model.*;
import model.IHulpdienstStrategy;
import model.Schepen;
import strategy.*;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.util.Random;

public class Controller {
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
    @FXML private ListView<Vervoermiddel> lstViewSchepenInNood;
    private Random randomGenerator = new Random();
    private final DBqueries kustwachtQueries = new DBqueries();
    private ArrayList<Schepen> schepenNietInNood = new ArrayList<Schepen>();

    @FXML
    void randomNoodButton_Clicked(ActionEvent event) {

        int teller = 0;
       //ArrayList<Schepen> schepenNietInNood = new ArrayList<Schepen>();
        //TODO soms 2 x hetzelfde schip in nood nog voorkomen Peter => mee bezig
        //TODO indien schip gered status veranderen?
        schepenNietInNood.clear();

        for(Schepen item : Actor.schepenOpWater){

            if(item.getStatus().equals(StatusVervoermiddel.OK.toString())){

                schepenNietInNood.add(item);

            }
        }

        int aantalRandomInNood = randomGenerator.nextInt(schepenNietInNood.size()/2)+1;
        //int aantalRandomInNood = 1;//test

        do {

            StatusVervoermiddel nieuwNoodSignaal = StatusVervoermiddel.values()[(int) (Math.random() * StatusVervoermiddel.values().length)];
            kiesRandomSchip().setNoodSignaal(nieuwNoodSignaal);
            ++teller;

        }while(aantalRandomInNood > teller);

        getAllVerkeerstorenEntries();
        getAllSchepenEntries();
        getAllHulpdiensten();
        getAllSchepenInNood();
    }


    public Schepen kiesRandomSchip()
    {
        int index = randomGenerator.nextInt(schepenNietInNood.size());
        Schepen schip = schepenNietInNood.get(index);

        System.out.println("\nRandom schip gekozen " + schip.getNaam());
        return schip;
    }

    
    
    private final ObservableList<Verkeerstoren> verkeerstorenList = FXCollections.observableArrayList();
    private final ObservableList<Vervoermiddel> schepenList = FXCollections.observableArrayList();
    private final ObservableList<Vervoermiddel> hulpdienstenList = FXCollections.observableArrayList();
    private final ObservableList<Vervoermiddel> schepenInNoodList = FXCollections.observableArrayList();

    public void initialize() {
        lstViewVerkeerstorens.setItems(verkeerstorenList); // Lijst van verkeertorens koppelen aan de listview
        lstViewSchepen.setItems(schepenList); // Lijst van schepen koppelen aan de listview
        lstViewHulpdiensten.setItems(hulpdienstenList); // Lijst van hulpdiensten koppelen aan de listview
        lstViewSchepenInNood.setItems(schepenInNoodList); // Lijst van hulpdiensten koppelen aan de listview
        getAllVerkeerstorenEntries();
        getAllSchepenEntries();
        getAllHulpdiensten();

        // Listener gekoppeld aan de listview van de verkeerstorens zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewVerkeerstorens.getSelectionModel().selectedItemProperty().addListener(
                (observableVerkeerstorenValue, oldVerkeerstorenValue, newVerkeerstorenValue) -> { displayVerkeertoren(newVerkeerstorenValue); }
        );

        // Listener gekoppeld aan de listview van de schepen zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewSchepen.getSelectionModel().selectedItemProperty().addListener(
                (observableSchipValue, oldSchipValue, newSchipValue) -> { displaySchip(newSchipValue); }
        );

        // Listener gekoppeld aan de listview van de hulpdiensten zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().addListener(
                (observableHulpdienstValue, oldHulpdienstValue, newHulpdienstValue) -> { displayHulpdienst(newHulpdienstValue); }
        );

        // Listener gekoppeld aan de listview van de schepen in nood zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewSchepenInNood.getSelectionModel().selectedItemProperty().addListener(
                (observableSchepenInNoodValue, oldSchepenInNoodValue, newSchepenInNoodValue) -> { displaySchepenInNood(newSchepenInNoodValue); }
        );
    }

    // alle entries van de tabel met de leden van de database opvragen en invullen in de ledenlijst
    private void getAllVerkeerstorenEntries() {
        ArrayList<Verkeerstoren> verkeerstorens = Actor.verkeerstorens;
        try {
            verkeerstorenList.setAll(verkeerstorens);
            //verkeerstorenList.setAll(kustwachtQueries.getAllVerkeerstorens()); //deze uncommenten om rechtstreeks data uit database in te laden ipv de ingeladen lijst.
            }
            catch (Exception E){
                displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een fout opgetreden bij het inladen van de verkeerstorens.");
            }
    }
    
    // alle entries van de tabel met de schepen van de database opvragen en invullen in de schepenlijst
    private void getAllSchepenEntries() {
        ArrayList<Schepen> schepen = Actor.schepenOpWater;
        try {
            schepenList.setAll(schepen);
            schepenList.get(0).getStatus();
            //schepenList.setAll(kustwachtQueries.getAllSchepen()); //deze uncommenten om rechtstreeks data uit database in te laden ipv de ingeladen lijst.
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een fout opgetreden bij het inladen van de schepen.");
        }
    }

    // alle entries van de tabel met de hulpdiensten van de database opvragen en invullen in de hulpdienstenlijst
    private void getAllHulpdiensten() {
        ArrayList<Vervoermiddel> hulpdiensten = Actor.mogelijkeHulpdiensten;
        ArrayList<Vervoermiddel> hulpdienstenOK = new ArrayList<>();
        try {
            for(Vervoermiddel item: hulpdiensten){
                if ((item.getStatus().equals(StatusVervoermiddel.OK.toString()))){
                        hulpdienstenOK.add(item);
                }
            }
            hulpdienstenList.setAll(hulpdienstenOK);
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een fout opgetreden bij het inladen van de hulpdiensten.");
        }
    }

    // alle entries van de tabel met de schepen in nood van de database opvragen en invullen in de schepeninnoodlijst
    private void getAllSchepenInNood() {
        ArrayList<Schepen> schepen = Actor.schepenOpWater;
        ArrayList<Schepen> schepenInNood = new ArrayList<>();
        try {
            for(Schepen item: schepen){
                if (!(item.getStatus().equals(StatusVervoermiddel.OK.toString()))){
                    schepenInNood.add(item);
                }
            }
            schepenInNoodList.setAll(schepenInNood);
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een fout opgetreden bij het inladen van de schepen in nood.");
        }
    }



    // informatie verkeerstoren tonen in de voorziene vakken
    private void displayVerkeertoren(Verkeerstoren verkeerstoren) {
        try {
            if (verkeerstoren != null) {
                txtVerkeerstorenNaam.setText(verkeerstoren.getNaam());
                txtVerkeerstorenLatitude.setText(String.valueOf(verkeerstoren.getCoördinaten().getBreedte()));
                txtVerkeerstorenLongitude.setText(String.valueOf(verkeerstoren.getCoördinaten().getLengte()));
            }
            else {
                txtVerkeerstorenNaam.clear();
                txtVerkeerstorenLatitude.clear();
                txtVerkeerstorenLongitude.clear();
            }
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    // informatie schepen tonen in de voorziene vakken
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
            }
            else {
                txtSchipNaam.clear();
                txtSchipSnelheid.clear();
                txtSchipGrootte.clear();
                txtSchipCapaciteit.clear();
                txtSchipKoers.clear();
                txtSchipLatitude.clear();
                txtSchipLongitude.clear();
                txtSchipStatus.clear();
            }
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    // informatie hulpdiensten tonen in de voorziene vakken
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
            }
            else {
                txtHulpdienstNaam.clear();
                txtHulpdienstSnelheid.clear();
                txtHulpdienstGrootte.clear();
                txtHulpdienstCapaciteit.clear();
                txtHulpdienstKoers.clear();
                txtHulpdienstLatitude.clear();
                txtHulpdienstLongitude.clear();
                txtHulpdienstStatus.clear();
            }
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    // informatie schepen in nood tonen in de voorziene vakken
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
            }
            else {
                txtNoodNaam.clear();
                txtNoodSnelheid.clear();
                txtNoodGrootte.clear();
                txtNoodCapaciteit.clear();
                txtNoodKoers.clear();
                txtNoodLatitude.clear();
                txtNoodLongitude.clear();
                txtNoodStatus.clear();
            }
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    public void kiesStrategy(Schepen schip){
        // Prepare strategies
        IHulpdienstStrategy brandStrategy = new BrandStrategy();
        IHulpdienstStrategy geenStrategy = new GeenStrategy();
        IHulpdienstStrategy gekapseisdStrategy = new GekapseisdStrategy();
        IHulpdienstStrategy piratenStrategy = new PiratenStrategy();
        IHulpdienstStrategy stormStrategy = new StormStrategy();
        IHulpdienstStrategy ziekteStrategy = new ZiekteStrategy();
        IHulpdienstStrategy zinkendStrategy = new ZinkendStrategy();

    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
