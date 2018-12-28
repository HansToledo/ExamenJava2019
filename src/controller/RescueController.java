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
    KustwachtController parent;

    public void RescueController(KustwachtController parent, ArrayList<Vervoermiddel> redders, String verkeerstorenNaam, Schepen schipInNood){
        cbStrategy.setItems(StrategyOptions);
        cbStrategy.setValue("geenStrategy");
        this.redders = redders;
        this.verkeerstorenNaam = verkeerstorenNaam;
        this.parent = parent;

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

        // Listener gekoppeld aan de listview van de redders zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().addListener(
                (observableHulpdienstenValue, oldHulpdienstenValue, newHulpdienstenValue) -> { displayHulpdiensten(newHulpdienstenValue); }
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
        if (cbStrategy.toString()==("geenStrategy")){
            displayAlert(Alert.AlertType.WARNING, "OPGEPAST", "Gelieve een redding strategie te bepalen.");
        }
        else {
            for (Vervoermiddel item : redders) {
                switch (cbStrategy.getValue()) {
                    case ("geenStrategy"):
                        item.setHulpdienstStrategy(geenStrategy);
                        break;
                    case ("piratenStrategy"):
                        item.setHulpdienstStrategy(piratenStrategy);
                        break;
                    case ("brandStrategy"):
                        item.setHulpdienstStrategy(brandStrategy);
                        break;
                    case ("gekapseisdStrategy"):
                        item.setHulpdienstStrategy(gekapseisdStrategy);
                        break;
                    case ("stormStrategy"):
                        item.setHulpdienstStrategy(stormStrategy);
                        break;
                    case ("ziekteStrategy"):
                        item.setHulpdienstStrategy(ziekteStrategy);
                        break;
                    case ("zinkendStrategy"):
                        item.setHulpdienstStrategy(zinkendStrategy);
                        break;
                }
            }

            schipInNood.setNoodSignaal(StatusVervoermiddel.OK);
            ArrayList<String> output = new ArrayList<String>();
            output.add("Reddingsactie wordt gestart!]");
            output.add("\n\nNoodsignaal ontvangen door " + verkeerstorenNaam + ".");
            output.add("\nHulpdiensten onderweg: ");
            for (Vervoermiddel item : redders) {
                output.add("\nNaam: " + item.getNaam() + "Strategy: " + item.getHulpdienstStrategy().Reddingstype());
                item.setHulpdienstStrategy(geenStrategy);
            }
            output.add("\n\n[Noodsituatie opgelost!");

            displayAlert(Alert.AlertType.INFORMATION, "SUCCESS", output.toString());
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            parent.getAllHulpdiensten();
            parent.getAllSchepenInNood();
        }
    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

