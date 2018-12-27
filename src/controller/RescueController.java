package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import model.*;
import javafx.fxml.FXML;
import strategy.*;

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
    private Schepen schip;
    IStatusObserver vkObserver;
    private final ObservableList<String> StrategyOptions = FXCollections.observableArrayList("geenStrategy","brandStrategy","gekapseisdStrategy","piratenStrategy","stormStrategy","ziekteStrategy","zinkendStrategy");

    public void DataTransfer(String schipInNoodNaam, KustwachtController parent, ObservableList<Vervoermiddel> hulpdienstenList, IStatusObserver vkObserver){
        cbStrategy.setItems(StrategyOptions);
        lstViewHulpdiensten.setItems(hulpdienstenList);
        this.vkObserver = vkObserver;
        lblVerkeerstoren.setText(vkObserver.toString());

        // Listener gekoppeld aan de listview van de verkeerstorens zodat bij selecteren informatie wordt getoond in de tekstvelden.
        lstViewHulpdiensten.getSelectionModel().selectedItemProperty().addListener(
                (observableHulpdienstenValue, oldHulpdienstenValue, newHulpdienstenValue) -> { displayHulpdiensten(newHulpdienstenValue); }
        );

        //Selected Item Changed.
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

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

