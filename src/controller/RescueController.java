package controller;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Scherm dat wordt getoond na selectie van schip in nood.
 */

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
    @FXML private Label lblGrootte;
    @FXML private Label lblLongitude;
    @FXML private Label lblNaam;
    @FXML private Label lblStrategy;
    @FXML private Label lblKoers;
    @FXML private Label lblNoodNaam;
    @FXML private Label lblNoodKoers;
    @FXML private Label lblNoodLatitude;
    @FXML private Label lblNoodLongitude;
    @FXML private Label lblNoodCapaciteit;
    @FXML private Label lblNoodStatus;
    @FXML private Label lblBeschikbareHulpdiensten;
    @FXML private Label lblVerkeerstoren;
    @FXML private Label lblCapaciteit;
    @FXML private Label lblOnvoldoendeCapaciteit;
    @FXML private TextField txtNaam;
    @FXML private TextField txtKoers;
    @FXML private TextField txtLatitude;
    @FXML private TextField txtCapaciteit;
    @FXML private TextField txtGrootte;
    @FXML private TextField txtLongitude;
    @FXML private TextField txtNoodNaam;
    @FXML private TextField txtNoodKoers;
    @FXML private TextField txtNoodLatitude;
    @FXML private TextField txtNoodLongitude;
    @FXML private TextField txtNoodCapaciteit;
    @FXML private TextField txtNoodStatus;
    @FXML private Label lblLatitude;
    @FXML private ChoiceBox<String> cbStrategy;
    @FXML private ListView<Vervoermiddel> lstViewHulpdiensten;

    IHulpdienstStrategy geenStrategy = new GeenStrategy();
    IHulpdienstStrategy piratenStrategy = new PiratenStrategy();
    IHulpdienstStrategy brandStrategy = new BrandStrategy();
    IHulpdienstStrategy gekapseisdStrategy = new GekapseisdStrategy();
    IHulpdienstStrategy stormStrategy = new StormStrategy();
    IHulpdienstStrategy ziekteStrategy = new ZiekteStrategy();
    IHulpdienstStrategy zinkendStrategy = new ZinkendStrategy();

    private IStatusObserver vkObserver;
    private Schepen schipInNood;
    private ArrayList<Vervoermiddel> redders;
    private final ObservableList<String> StrategyOptions = FXCollections.observableArrayList("geenStrategy","brandStrategy","gekapseisdStrategy","piratenStrategy","stormStrategy","ziekteStrategy","zinkendStrategy");
    private String verkeerstorenNaam;
    private KustwachtController parent;
    private Verkeerstoren geregistreerdeVerkeerstoren;
    private String windowTitle;

    public void RescueController(KustwachtController parent, ArrayList<Vervoermiddel> redders, Verkeerstoren geregistreerdeVerkeerstoren, Schepen schipInNood, String windowTitle){
        try {
            this.redders = redders;
            this.parent = parent;
            this.geregistreerdeVerkeerstoren = geregistreerdeVerkeerstoren;
            this.verkeerstorenNaam = geregistreerdeVerkeerstoren.getNaam();
            this.windowTitle = windowTitle;
            this.schipInNood = schipInNood;
            lblVerkeerstoren.setText(verkeerstorenNaam);
            cbStrategy.setItems(StrategyOptions);   // Choicebox opvullen met waarden.
            cbStrategy.setValue("geenStrategy");

            getSchipInNood();
            getRedders();
            capaciteitReddingsdiensten();

            //region Listener gekoppeld aan de listview van de redders zodat bij selecteren informatie wordt getoond in de tekstvelden.
            //Dit had ook via event handler op listview kunnen gebeuren. Eventlistener en eventhandler werken hetzelfde maar dan op andere positie gedefinieerd.
            lstViewHulpdiensten.getSelectionModel().selectedItemProperty().addListener(
                    (observableHulpdienstenValue, oldHulpdienstenValue, newHulpdienstenValue) -> { displayHulpdiensten(newHulpdienstenValue); }
            );
            //endregion
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden bij het."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
            //System.out.println(E);
        }
    }

    @FXML
    void btnStartReddingsoperatie_Clicked(ActionEvent event) {
        try {
            IHulpdienstStrategy gekozenStrategy = geenStrategy;
            if (cbStrategy.getValue()==("geenStrategy")){
                displayAlert(Alert.AlertType.WARNING, "OPGEPAST", "Gelieve een redding strategie te bepalen.");
            }
            else {
                for (Vervoermiddel item : redders) {
                    switch (cbStrategy.getValue()) {
                        case ("geenStrategy"):
                            item.setHulpdienstStrategy(geenStrategy);
                            gekozenStrategy = geenStrategy;
                            break;
                        case ("piratenStrategy"):
                            item.setHulpdienstStrategy(piratenStrategy);
                            gekozenStrategy = piratenStrategy;
                            break;
                        case ("brandStrategy"):
                            item.setHulpdienstStrategy(brandStrategy);
                            gekozenStrategy = brandStrategy;
                            break;
                        case ("gekapseisdStrategy"):
                            item.setHulpdienstStrategy(gekapseisdStrategy);
                            gekozenStrategy = gekapseisdStrategy;
                            break;
                        case ("stormStrategy"):
                            item.setHulpdienstStrategy(stormStrategy);
                            gekozenStrategy = stormStrategy;
                            break;
                        case ("ziekteStrategy"):
                            item.setHulpdienstStrategy(ziekteStrategy);
                            gekozenStrategy = ziekteStrategy;
                            break;
                        case ("zinkendStrategy"):
                            item.setHulpdienstStrategy(zinkendStrategy);
                            gekozenStrategy = zinkendStrategy;
                            break;
                    }
                }

                geregistreerdeVerkeerstoren.doNotifyNoodObserver(gekozenStrategy,schipInNood.getCoördinaten(),schipInNood.getNaam());
                schipInNood.setNoodSignaal(StatusVervoermiddel.OK);

                ArrayList<String> output = new ArrayList<String>();
                output.add("Reddingsactie gestart voor " + schipInNood.getNaam() + "]");
                output.add("\n\nNoodsignaal ontvangen door " + verkeerstorenNaam + ".");
                output.add("\nHulpdiensten onderweg naar " + schipInNood.getNaam() + ":");
                for (Vervoermiddel item : redders) {
                    output.add("\nHulpdienst: " + item.getNaam() + "\nStrategy: " + item.getHulpdienstStrategy().Reddingstype());
                    item.setHulpdienstStrategy(geenStrategy);
                }
                output.add("\n\n[Noodsituatie opgelost!");

                parent.txtAreaTerminal.appendText("\n"+output.toString()+"\n");

                displayAlert(Alert.AlertType.INFORMATION, "SUCCESS", output.toString());

                //Window uit lijst met open schermen wissen.
                for (Stage item: parent.openWindows){
                    if(item.getTitle().equals(windowTitle)){
                        parent.openWindows.remove(item);
                        break;
                    }
                }

                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                parent.getAllHulpdiensten();
                parent.getAllSchepenInNood();
            }
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden bij het."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
            //System.out.println(E);
        }
    }

    //capaciteit reddingsdiensten optellen
    public void capaciteitReddingsdiensten(){
        try {
            int totaleCapaciteit = 0;
            for(Vervoermiddel item: redders){
                totaleCapaciteit += item.getCapaciteit();
            }

            if (totaleCapaciteit < schipInNood.getCapaciteit()){
                lblOnvoldoendeCapaciteit.setText("MERK OP: de capaciteit van de reddingsdiensten is onvoldoende!\nSchip in nood heeft "+(int)(schipInNood.getCapaciteit())
                        + " opvarenden." + " De hulpdiensten hebben slechts " + totaleCapaciteit + " plaatsen beschikbaar. Best contact opnemen met een 2de verkeerstoren.");
            }
            else
            {
                lblOnvoldoendeCapaciteit.setText("");
            }
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden bij het."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
            //System.out.println(E);
        }
    }

    //Redders in lijst inladen.
    public void getRedders(){
        try {
            ObservableList<Vervoermiddel> vkHulpdienstenList = FXCollections.observableArrayList();
            vkHulpdienstenList.setAll(redders);
            lstViewHulpdiensten.setItems(vkHulpdienstenList);
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            displayAlert(Alert.AlertType.ERROR, "ERROR.", E.toString());
            //System.out.println(E);
        }
    }

    //Informatie hulpdiensten tonen in de voorziene vakken.
    private void displayHulpdiensten(Vervoermiddel vervoermiddel) {
        try {
            if (vervoermiddel != null) {
                txtNaam.setText(String.valueOf(vervoermiddel.getNaam()));
                txtGrootte.setText(String.valueOf(vervoermiddel.getGrootte()));
                txtCapaciteit.setText(String.valueOf((int)vervoermiddel.getCapaciteit()));
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
            EventLogger.logger.error(String.format(E.getMessage()));
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
            //System.out.println(E);
        }
    }

    //Informatie schip in nood tonen in de voorziene vakken.
    private void getSchipInNood() {
        try {
            txtNoodNaam.setText(String.valueOf(schipInNood.getNaam()));
            txtNoodCapaciteit.setText(String.valueOf((int)schipInNood.getCapaciteit()));
            txtNoodKoers.setText(String.valueOf(schipInNood.getKoers()));
            txtNoodLatitude.setText(String.valueOf(schipInNood.getCoördinaten().getBreedte()));
            txtNoodLongitude.setText(String.valueOf(schipInNood.getCoördinaten().getLengte()));
            txtNoodStatus.setText(String.valueOf(schipInNood.getStatus()));
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            displayAlert(Alert.AlertType.ERROR, "ERROR.", "Er is een onverwachte fout opgetreden bij het."+"\n\nERROR INFO:\n" + E.fillInStackTrace());
            //System.out.println(E);
        }
    }

    //Voor het tonen van een messagebox met de nodige uitleg.
    private void displayAlert(Alert.AlertType type, String title, String message) {
        try {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        }
        catch (Exception E){
            EventLogger.logger.error(String.format(E.getMessage()));
            //System.out.println(E);
        }
    }
}

