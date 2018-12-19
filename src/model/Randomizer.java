package model;

import enums.Actors;
import enums.Schepen;

import java.util.ArrayList;
import java.util.List;

public class Randomizer {

    public void generetaData() {

        int teller = 0;
        List<Actor> actoren = new ArrayList<Actor>();
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.SCHIP);
        Coördinaten coördinaten = new Coördinaten();



        do {

            Schepen schip = Schepen.values()[(int)(Math.random()*Schepen.values().length)]; //random enum schip genereren

            Vervoermiddel actor = random.setSchip(schip, coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90);

            actoren.add(actor);
            ++teller;

            System.out.println(actor.toString());

        }while (teller<10);


    }



}
