package controller;

import enums.StatusVervoermiddel;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import model.*;
import strategy.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RescueController {
    @FXML private Button btnStartReddingsoperatie;
    @FXML private TextField txtLongitude;
    @FXML private Label lblGrootte;
    @FXML private Label lblLongitude;
    @FXML private Label lblNaam;
    @FXML private Label lblStrategy;
    @FXML private TextField txtKoers;
    @FXML private TextField txtLatitude;
    @FXML private Label lblKoers;
    @FXML private Label lblBeschikbareHulpdiensten;
    @FXML private Label lblVerkeerstoren;
    @FXML private Label lblCapaciteit;
    @FXML private TextField txtNaam;
    @FXML private ChoiceBox<String> cbStrategy;
    @FXML private TextField txtCapaciteit;
    @FXML private TextField txtGrootte;
    @FXML private Label lblLatitude;
    @FXML private ListView<Vervoermiddel> lstViewHulpdiensten;

    IHulpdienstStrategy geenStrategy = new GeenStrategy();
    IHulpdienstStrategy piratenStrategy = new PiratenStrategy();
    IHulpdienstStrategy brandStrategy = new BrandStrategy();
    IHulpdienstStrategy gekapseisdStrategy = new GekapseisdStrategy();
    IHulpdienstStrategy stormStrategy = new StormStrategy();
    IHulpdienstStrategy ziekteStrategy = new ZiekteStrategy();
    IHulpdienstStrategy zinkendStrategy = new ZinkendStrategy();
    private Schepen schipInNood;
    IStatusObserver vkObserver;
    ArrayList<Vervoermiddel> redders;
    private final ObservableList<String> StrategyOptions = FXCollections.observableArrayList("geenStrategy","brandStrategy","gekapseisdStrategy","piratenStrategy","stormStrategy","ziekteStrategy","zinkendStrategy");
    String verkeerstorenNaam;

    public void RescueController(KustwachtController parent, ArrayList<Vervoermiddel> redders, String verkeerstorenNaam, Schepen schipInNood){
        cbStrategy.setItems(StrategyOptions);
        this.redders = redders;
        this.verkeerstorenNaam = verkeerstorenNaam;

        ObservableList<Vervoermiddel> vkHulpdienstenList = FXCollections.observableArrayList();
        try {
            vkHulpdienstenList.setAll(redders);
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", E.toString());
        }

        lstViewHulpdiensten.setItems(vkHulpdienstenList);
        lblVerkeerstoren.setText(verkeerstorenNaam);
        this.schipInNood = schipInNood;

        // Listener gekoppeld aan de listview van de verkeerstorens zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().addListener(
                (observableHulpdienstenValue, oldHulpdienstenValue, newHulpdienstenValue) -> { displayHulpdiensten(newHulpdienstenValue); }
        );

        // Listened gekoppeld aan ChoiseBox zodat bij wijziging de strategie wordt weggeschreven.
        cbStrategy.getSelectionModel().selectedItemProperty().addListener(
            (observableStrategyValue, oldStrategyValue, newStrategyValue) -> {
                switch (newStrategyValue){
                    case ("geenStrategy"):
                        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().setHulpdienstStrategy(geenStrategy);
                        break;
                    case ("piratenStrategy"):
                        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().setHulpdienstStrategy(piratenStrategy);
                        break;
                    case ("brandStrategy"):
                        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().setHulpdienstStrategy(brandStrategy);
                        break;
                    case ("gekapseisdStrategy"):
                        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().setHulpdienstStrategy(gekapseisdStrategy);
                        break;
                    case ("stormStrategy"):
                        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().setHulpdienstStrategy(stormStrategy);
                        break;
                    case ("ziekteStrategy"):
                        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().setHulpdienstStrategy(ziekteStrategy);
                        break;
                    case ("zinkendStrategy"):
                        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().setHulpdienstStrategy(zinkendStrategy);
                        break;
                }
            }
        );
    }

    // informatie schepen tonen in de voorziene vakken
    private void displayHulpdiensten(Vervoermiddel vervoermiddel) {
        try {
            if (vervoermiddel != null) {
                txtNaam.setText(String.valueOf(vervoermiddel.getNaam()));
                txtGrootte.setText(String.valueOf(vervoermiddel.getGrootte()));
                txtCapaciteit.setText(String.valueOf(vervoermiddel.getCapaciteit()));
                txtKoers.setText(String.valueOf(vervoermiddel.getKoers()));
                txtLatitude.setText(String.valueOf(vervoermiddel.getCoördinaten().getBreedte()));
                txtLongitude.setText(String.valueOf(vervoermiddel.getCoördinaten().getLengte()));
                cbStrategy.setValue(lstViewHulpdiensten.getSelectionModel().selectedItemProperty().getValue().getHulpdienstStrategy().toString());
            }
            else {
                txtNaam.clear();
                txtGrootte.clear();
                txtCapaciteit.clear();
                txtKoers.clear();
                txtLatitude.clear();
                txtLongitude.clear();
            }
        }
        catch (Exception E){
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
        }
    }

    @FXML
    void btnStartReddingsoperatie_Clicked(ActionEvent event) {
        boolean allHaveStrategy = true;
        for (Vervoermiddel item: redders){
            if (item.getHulpdienstStrategy().toString().equals("geenStrategy")){
                allHaveStrategy = false;
                displayAlert(Alert.AlertType.WARNING, "OPGEPAST", "Gelieve voor ieder reddingsvoertuig een strategy te bepalen.");
                break;
            }
        }
        if (allHaveStrategy){
            schipInNood.setNoodSignaal(StatusVervoermiddel.OK);

            ArrayList<String> output = new ArrayList<String>();
            output.add ("Reddingsactie wordt gestart!]");
            output.add ("\n\nNoodsignaal ontvangen door " + verkeerstorenNaam +".");
            output.add ("\nHulpdiensten onderweg: ");
            for (Vervoermiddel item: redders){
                output.add("\nNaam: " + item.getNaam() + "Strategy: " + item.getHulpdienstStrategy().Reddingstype());
            }
            output.add ("\n\n[Noodsituatie opgelost!");

            displayAlert(Alert.AlertType.INFORMATION, "SUCCESS", output.toString());
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

