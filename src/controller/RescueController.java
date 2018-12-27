package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import model.Schepen;
import model.Verkeerstoren;
import model.Vervoermiddel;
import javafx.fxml.FXML;

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
    @FXML private Label lblSchipInNood;
    @FXML private TextField txtGrootte;
    @FXML private Label lblLatitude;
    @FXML private ListView<Vervoermiddel> lstViewHulpdiensten;

    private Schepen schip;
    private final ObservableList<String> StrategyOptions = FXCollections.observableArrayList("GeenStrategy","BrandStrategy","GekapseisdStrategy","PiratenStrategy","StormStrategy","ZiekteStrategy","ZinkendStrategy");

    public void DataTransfer(String schipInNoodNaam, KustwachtController parent, ObservableList<Vervoermiddel> hulpdienstenList){
        lblVerkeerstoren.setText("Verkeerstoren: ");
        lblSchipInNood.setText("Schip in nood: " + schipInNoodNaam);
        cbStrategy.setValue("GeenStrategy");
        cbStrategy.setItems(StrategyOptions);
        lstViewHulpdiensten.setItems(hulpdienstenList);

        // Listener gekoppeld aan de listview van de verkeerstorens zodat bij selecteren informatie wordt getoond in de tekstvelden.
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

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

