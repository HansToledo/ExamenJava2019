package controller;

import enums.Actors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddVerkeerstorenController {
    @FXML private Label lblActorType;
    @FXML private Label lblActorCapaciteit;
    @FXML private Label lblActorLongitude;
    @FXML private Label lblActorSnelheid;
    @FXML private Label lblActorTitelNaam;
    @FXML private Label lblActorLatitude;
    @FXML private Label lblActorStatus;
    @FXML private Label lblActorGrootte;
    @FXML private Label lblActorKoers;
    @FXML private Label lblActorNaam;
    @FXML private TextField txtActorGrootte;
    @FXML private TextField txtActorLatitude;
    @FXML private TextField txtActorSnelheid;
    @FXML private TextField txtActorStatus;
    @FXML private TextField txtActorNaam;
    @FXML private TextField txtActorLongitude;
    @FXML private TextField txtActorCapaciteit;
    @FXML private TextField txtActorKoers;
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
}
