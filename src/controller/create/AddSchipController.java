package controller.create;

import controller.KustwachtController;
import enums.Actors;
import enums.Hulpdiensten;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddSchipController {
    @FXML private Label lblActorType;
    @FXML private Label lblActorCapaciteit;
    @FXML private Label lblActorLongitude;
    @FXML private Label lblActorSnelheid;
    @FXML private Label lblActorTitelNaam;
    @FXML private Label lblActorLatitude;
    @FXML private Label lblActorGrootte;
    @FXML private Label lblActorKoers;
    @FXML private Label lblActorNaam;
    @FXML private TextField txtActorGrootte;
    @FXML private TextField txtActorLatitude;
    @FXML private TextField txtActorSnelheid;
    @FXML private TextField txtActorNaam;
    @FXML private TextField txtActorLongitude;
    @FXML private TextField txtActorCapaciteit;
    @FXML private TextField txtActorKoers;
    @FXML private ChoiceBox<enums.Schepen> cbActorType;
    @FXML private Button btnToevoegen;

    KustwachtController parent;
    String actorNaam;

    public void AddActorController(KustwachtController parent, String actorNaam){
        this.parent = parent;
        lblActorTitelNaam.setText(actorNaam + " toevoegen");
        this.actorNaam = actorNaam;
        cbActorType.setItems( FXCollections.observableArrayList( enums.Schepen.values()));
        cbActorType.getSelectionModel().selectFirst();
    }

    @FXML
    void btnToevoegen_Clicked(ActionEvent event) {
        try {
            String naam = txtActorNaam.getText();
            double latitude = Double.parseDouble(txtActorLatitude.getText());
            double longitude = Double.parseDouble(txtActorLongitude.getText());
            double snelheid = Double.parseDouble(txtActorSnelheid.getText());
            double grootte = Double.parseDouble(txtActorGrootte.getText());
            double capaciteit = Double.parseDouble(txtActorCapaciteit.getText());
            int koers = Integer.parseInt(txtActorKoers.getText());

            if (grootte < capaciteit){
                displayAlert(Alert.AlertType.INFORMATION, "Controleer ingave","Grootte moet groter zijn dan de capaciteit.");
            }
            else {
                int result = database.Create.addSchip(naam, cbActorType.getValue(), latitude, longitude, snelheid, grootte, capaciteit, koers);

                if (result == 1) {
                    parent.getAllSchepenEntries();
                    parent.getAllHulpdiensten();
                    parent.txtAreaTerminal.appendText("\n" + cbActorType.getValue().toString() + " " + naam + " is aangemaakt.\n");

                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                } else {
                    displayAlert(Alert.AlertType.INFORMATION, "FOUT BIJ INGAVE", "Er bestaat al een schip met deze naam.");
                }
            }
        }
        catch(Exception ex){
            displayAlert(Alert.AlertType.ERROR, "FOUT BIJ INGAVE",ex.getMessage());
        }
    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
