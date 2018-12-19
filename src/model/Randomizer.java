package model;

import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;

import java.util.ArrayList;
import java.util.List;

public class Randomizer {

    public void generetaData() {

        int teller = 0;
        List<Actor> actoren = new ArrayList<Actor>();
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.SCHIP);
        AbstractActorFactory random2 = FactoryProducer.getFactory(Actors.HULPDIENST);
        AbstractActorFactory random3 = FactoryProducer.getFactory(Actors.HULPDIENST);
        Coördinaten coördinaten = new Coördinaten();


        do {

            Schepen schip = Schepen.values()[(int)(Math.random()*Schepen.values().length)]; //random enum schip genereren
            Hulpdiensten hulpdienst = Hulpdiensten.values()[(int)(Math.random()*Hulpdiensten.values().length)]; //random enum hulpdienst genereren

            Verkeerstoren actor3 = random3.setVerkeersToren(Hulpdiensten.VERKEERSTOREN);

            Vervoermiddel actor2 = random2.setHulpDienst(hulpdienst,coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90);

            Vervoermiddel actor = random.setSchip(schip, coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90);

            actoren.add(actor);
            actoren.add(actor2);
            actoren.add(actor3);

            ++teller;

            System.out.println(actor.toString() );

        }while (teller<10);


    }



}
