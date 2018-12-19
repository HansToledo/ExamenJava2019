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

            Vervoermiddel actor = random.setSchip(Schepen.CONTAINERSCHIP, coördinaten,
                    1 + Math.random() * 40, 100 + Math.random() * 100,
                    100 + Math.random() * 100, (int) Math.random() * 90);

            actoren.add(actor);
            ++teller;
            
            System.out.println(actor.toString());

        }while (teller<10);


    }



}
