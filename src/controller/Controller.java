package controller;

import enums.StatusVervoermiddel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Actor;
import model.Schepen;

import java.util.Random;

public class Controller {

    @FXML
    private Button randomNoodButton;
    private Random randomGenerator = new Random();


    @FXML
    void randomNoodButton_Clicked(ActionEvent event) {

        kiesRandomSchip().setNoodSignaal(StatusVervoermiddel.ZINKEND);

    }


    public Schepen kiesRandomSchip()
    {
        int index = randomGenerator.nextInt(Actor.schepenOpWater.size());
        Schepen schip = Actor.schepenOpWater.get(index);
        System.out.println("Random schip gekozen " + schip.getNaam());
        return schip;
    }

}
