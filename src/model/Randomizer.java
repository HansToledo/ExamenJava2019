package model;

import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import enums.StatusVoertuig;
import strategy.GeenStrategy;
import strategy.MeldingStrategy;
import strategy.PickupStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {
    private static Random random = new Random();

    public String naamAddon() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer randomNaam = new StringBuffer();
        randomNaam.append("-");
        for (int i = 0; i < 2; i++) {
            randomNaam.append(letters.charAt(random.nextInt(26)));
        }
        randomNaam.append("-");
        for (int i = 0; i < 2; i++) {
            randomNaam.append(random.nextInt(10));
        }
        return randomNaam.toString();
    }

    public void generetaData() {

        int teller = 0;
        List<Actor> actoren = new ArrayList<Actor>();
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.SCHIP);
        AbstractActorFactory random2 = FactoryProducer.getFactory(Actors.HULPDIENST);
        AbstractActorFactory random3 = FactoryProducer.getFactory(Actors.HULPDIENST);
        Coördinaten coördinaten = new Coördinaten();
        IHulpdienstStrategy pickupStrategy = new PickupStrategy();
        IHulpdienstStrategy meldingStrategy = new MeldingStrategy();
        IHulpdienstStrategy geenStrategy = new GeenStrategy();


        do {
            Schepen schip = Schepen.values()[(int)(Math.random()*Schepen.values().length)]; //random enum schip genereren
            Hulpdiensten hulpdienst = Hulpdiensten.values()[(int)(Math.random()*(Hulpdiensten.values().length)-1)]; //random enum hulpdienst genereren, -1 omdat verkeerstoren niet geselecteerd mag worden doordat deze de parameters zoals snelheid enzo niet heeft.

            Verkeerstoren actor3 = random3.setVerkeersToren("vt"+naamAddon(),Hulpdiensten.VERKEERSTOREN,coördinaten, geenStrategy);

            Vervoermiddel actor2 = random2.setHulpDienst(hulpdienst.toString()+naamAddon(),hulpdienst,coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90,
                    geenStrategy);

            Vervoermiddel actor = random.setSchip(schip.toString()+naamAddon(),schip, coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90,
                    geenStrategy);

            actoren.add(actor);
            actoren.add(actor2);
            actoren.add(actor3);

            actor.addVerkeerstorenObserver(actor3);                             //TODO Observer pattern
            actor.notifyVerkeerstorenObservers(StatusVoertuig.OK.toString());   //Alles mogelijke statussen bevinden zich in de enum StatusVoertuig.

            ++teller;

        }while (teller<10);


        //Afdrukken van alle actoren.
        for (int i=0; i < actoren.size();i++){
            System.out.println(actoren.get(i));
        }
    }



}
