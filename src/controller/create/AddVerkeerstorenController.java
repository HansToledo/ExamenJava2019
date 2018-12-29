package controller.create;

import controller.KustwachtController;
import database.DBqueries;
import enums.Actors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
        this.parent = parent;
        lblActorTitelNaam.setText(actorNaam + " toevoegen");
        this.actorNaam = actorNaam;
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
