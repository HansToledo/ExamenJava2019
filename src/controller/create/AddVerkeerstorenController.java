package controller.create;

import controller.KustwachtController;
import database.DBqueries;
import enums.Actors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML private ChoiceBox<enums.Actors> cbActorType;
    @FXML private Button btnToevoegen;

    KustwachtController parent;
    String actorNaam;



    public void AddActorController(KustwachtController parent, String actorNaam){
        this.parent = parent;
        lblActorTitelNaam.setText(actorNaam + " toevoegen");
        this.actorNaam = actorNaam;
        cbActorType.setItems( FXCollections.observableArrayList( Actors.VERKEERSTOREN));
        cbActorType.getSelectionModel().selectFirst();
    }

    @FXML
    void btnToevoegen_Clicked(ActionEvent event) {
        String naam = txtActorNaam.getText();
        double latitude = Double.parseDouble(txtActorLatitude.getText());
        double longitude = Double.parseDouble(txtActorLongitude.getText());
        database.Create.addVerkeerstoren(naam,latitude,longitude);

        parent.getAllVerkeerstorenEntries();
        parent.txtAreaTerminal.appendText("\n" + cbActorType.getValue().toString() + " " + naam + "is aangemaakt.\n");

        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
