package controller.create;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Tonen van scherm waarmee een verkeerstoren wordt toegevoegd.
 */

import controller.KustwachtController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.EventLogger;

public class AddVerkeerstorenController {
    @FXML private Label lblActorType;
    @FXML private Label lblActorLongitude;
    @FXML private Label lblActorTitelNaam;
    @FXML private Label lblActorLatitude;
    @FXML private Label lblActorNaam;
    @FXML private TextField txtActorLatitude;
    @FXML private TextField txtActorNaam;
    @FXML private TextField txtActorLongitude;
    @FXML private Button btnToevoegen;

    KustwachtController parent;
    String actorNaam;

    public void AddActorController(KustwachtController parent, String actorNaam){
        try {
            this.parent = parent;
            lblActorTitelNaam.setText(actorNaam + " toevoegen");
            this.actorNaam = actorNaam;
        }
        catch(Exception ex){
            EventLogger.logger.error(String.format(ex.getMessage()));
        }
    }

    @FXML
    void btnToevoegen_Clicked(ActionEvent event) {
        try{
            String naam = txtActorNaam.getText();
            double latitude = Double.parseDouble(txtActorLatitude.getText());
            double longitude = Double.parseDouble(txtActorLongitude.getText());

            int result = database.Create.addVerkeerstoren(naam,latitude,longitude);

            if (result == 1) {
                parent.getAllVerkeerstorenEntries();
                parent.txtAreaTerminal.appendText("\nVerkeerstoren " + naam + " is aangemaakt.\n");

                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            }
            else{
                displayAlert(Alert.AlertType.INFORMATION, "FOUT BIJ INGAVE","Er bestaat al een vuurtoren met deze naam.");
            }
        }
        catch(Exception ex){
            EventLogger.logger.error(String.format(ex.getMessage()));
            displayAlert(Alert.AlertType.ERROR, "FOUT BIJ INGAVE",ex.getMessage());
        }
    }

    private void displayAlert(Alert.AlertType type, String title, String message) {
        try {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
        catch(Exception ex){
            EventLogger.logger.error(String.format(ex.getMessage()));
        }
    }
}
