package controller;

import enums.StatusVervoermiddel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Actor;
import model.IHulpdienstStrategy;
import model.Schepen;
import strategy.*;

import java.util.Random;

public class Controller {

    @FXML
    private Button randomNoodButton;
    private Random randomGenerator = new Random();


    @FXML
    void randomNoodButton_Clicked(ActionEvent event) {
        StatusVervoermiddel nieuwNoodSignaal = StatusVervoermiddel.values()[(int)(Math.random()*StatusVervoermiddel.values().length)];
        kiesRandomSchip().setNoodSignaal(nieuwNoodSignaal);
    }


    public Schepen kiesRandomSchip()
    {
        int index = randomGenerator.nextInt(Actor.schepenOpWater.size());
        Schepen schip = Actor.schepenOpWater.get(index);

        System.out.println("Random schip gekozen " + schip.getNaam());
        return schip;
    }

    public void kiesStrategy(){
        // Prepare strategies
        IHulpdienstStrategy brandStrategy = new BrandStrategy();
        IHulpdienstStrategy geenStrategy = new GeenStrategy();
        IHulpdienstStrategy gekapseisdStrategy = new GekapseisdStrategy();
        IHulpdienstStrategy piratenStrategy = new PiratenStrategy();
        IHulpdienstStrategy stormStrategy = new StormStrategy();
        IHulpdienstStrategy ziekteStrategy = new ZiekteStrategy();
        IHulpdienstStrategy zinkendStrategy = new ZinkendStrategy();
    }
}
