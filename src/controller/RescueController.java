package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Schepen;
import model.Vervoermiddel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    private Schepen schip;
    private final ObservableList<String> StrategyOptions = FXCollections.observableArrayList("GeenStrategy","BrandStrategy","GekapseisdStrategy","PiratenStrategy","StormStrategy","ZiekteStrategy","ZinkendStrategy");

    public void DataTransfer(String schipInNoodNaam, KustwachtController parent, ObservableList<Vervoermiddel> schepenInNood){
        lblVerkeerstoren.setText("Verleerstoren: ");
        lblSchipInNood.setText("Schip in nood: " + schipInNoodNaam);
        cbStrategy.setValue("GeenStrategy");
        cbStrategy.setItems(StrategyOptions);
    }

    public void start() {

         //this.schip = schipInNood;

    }
}

