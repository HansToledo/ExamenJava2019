package model;

import calculations.Coördinaten;
import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import enums.StatusVervoermiddel;
import strategy.*;
import database.DBqueries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static enums.Hulpdiensten.SCHEEPSVAARTPOLITIE;
import static enums.Hulpdiensten.SEAKING;

public class Randomizer {
    private static Random random = new Random();
    private final DBqueries kustwachtQueries = new DBqueries();
    public static ArrayList<String> output = new ArrayList<String>();
    IHulpdienstStrategy geenStrategy = new GeenStrategy();
    Coördinaten coördinaten;

    //TODO randomizer knop voorzien in gui
    //TODO methods voor schrijven en lezen uit randomizer klasse halen

    public static double getRandomDoubleBetweenRange(double min, double max) { // gebruikt worden voor ramdom met range

        double x = (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

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


    public void generateVerkeerstores(int aantal) {
        int teller = 0;
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.HULPDIENST);

        do {
            coördinaten = new Coördinaten().getRandomCoordinaten();
            Hulpdiensten hulpdienst = Hulpdiensten.values()[(int) (Math.random() * (Hulpdiensten.values().length) - 1)];
            String randomNaam = hulpdienst.VERKEERSTOREN.toString() + naamAddon();
            boolean alreadyExists = false;

            Verkeerstoren verkeerstoren = random.setVerkeersToren(hulpdienst.VERKEERSTOREN.toString(), randomNaam, Hulpdiensten.VERKEERSTOREN, coördinaten, geenStrategy);

            //Actor toevoegen aan database
            for (Verkeerstoren item: kustwachtQueries.getAllVerkeerstorens() ) {
                if (!(item.getNaam().equals(randomNaam)) ){
                } else {
                    alreadyExists = true;
                }
            }

            if (!alreadyExists){
                kustwachtQueries.addVerkeerstoren(verkeerstoren.getEnumNaam(), verkeerstoren.getNaam(), coördinaten);
                ++teller;
            }

        } while (teller < aantal);
    }

    public void generateHulpdiensten(int aantal) {
        int teller = 0;
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.HULPDIENST);

        do {
            coördinaten = new Coördinaten().getRandomCoordinaten();
            Hulpdiensten hulpdienst = Hulpdiensten.values()[(int) (Math.random() * (Hulpdiensten.values().length) - 1)]; //random enum hulpdienst genereren, -1 omdat verkeerstoren niet geselecteerd mag worden doordat deze de parameters zoals snelheid enzo niet heeft.
            String randomNaam = hulpdienst.toString() + naamAddon();
            boolean alreadyExists = false;

            Vervoermiddel vervoermiddel = random.setHulpDienst(hulpdienst.toString(), randomNaam, hulpdienst, coördinaten,
                    getSnelheid(hulpdienst.toString()), Math.round(100 + Math.random() * 100),
                    getCapaciteit(hulpdienst.toString()), getKoers(),
                    geenStrategy, StatusVervoermiddel.OK.toString());

            //Actor toevoegen aan database
            for (Vervoermiddel item: kustwachtQueries.getAllHulpdiensten() ) {
                if (!(item.getNaam().equals(randomNaam)) ){
                } else {
                    alreadyExists = true;
                }
            }

            if (!alreadyExists) {
                kustwachtQueries.addHulpdienst(vervoermiddel.getEnumNaam(), vervoermiddel.getNaam(), vervoermiddel.getSnelheid(),
                        vervoermiddel.getReactieTijd(), vervoermiddel.getWendbaarheid(),
                        vervoermiddel.getGrootte(), vervoermiddel.getCapaciteit(),
                        vervoermiddel.getKoers(), vervoermiddel.getStatus(), coördinaten);
                ++teller;
            }
        } while (teller < aantal);
    }


    public void generateSchepen(int aantal) {
        int teller = 0;
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.SCHIP);

        do {
            coördinaten = new Coördinaten().getRandomCoordinaten();
            Schepen schip = Schepen.values()[(int) (Math.random() * Schepen.values().length)]; //random enum schip genereren
            String randomNaam = schip.toString() + naamAddon();
            boolean alreadyExists = false;

            Vervoermiddel vervoermiddel = random.setSchip(schip.toString(), randomNaam, schip, coördinaten,
                    getSnelheid(schip.toString()), Math.round(100 + Math.random() * 100),
                    getCapaciteit(schip.toString()), getKoers(),
                    geenStrategy, StatusVervoermiddel.OK.toString());

            //Actoren toevoegen aan database
            for (Vervoermiddel item: kustwachtQueries.getAllSchepen() ) {
                if (!(item.getNaam().equals(randomNaam)) ){
                } else {
                    alreadyExists = true;
                }
            }

            if (!alreadyExists) {
                kustwachtQueries.addSchip(vervoermiddel.getEnumNaam(), vervoermiddel.getNaam(), vervoermiddel.getSnelheid(),
                        vervoermiddel.getReactieTijd(), vervoermiddel.getWendbaarheid(),
                        vervoermiddel.getGrootte(), vervoermiddel.getCapaciteit(),
                        vervoermiddel.getKoers(), vervoermiddel.getStatus(), coördinaten);
                ++teller;
            }

        } while (teller < aantal);
    }

    private double getSnelheid(String type){

        switch (type) {

            case "SEAKING":
                return Math.floor(Math.random()*((180-120)+1))+120;
            case "SCHEEPSVAARTPOLITIE":
                return Math.floor(Math.random()*((60-30)+1))+60;
            case "CONTAINERSCHIP":
                return Math.floor(Math.random()*((20-10)+1))+10;
            case "MOTORBOOT":
                return Math.floor(Math.random()*((50-20)+1))+20;
            case "TANKER":
                return Math.floor(Math.random()*((25-10)+1))+10;
            case "ZEILBOOT":
                return Math.floor(Math.random()*((50-20)+1))+20;

        }

        return 0.0;
    }

    private double getCapaciteit(String type){

        switch (type) {

            case "SEAKING":
                return Math.floor(Math.random()*((20-10)+1))+10;
            case "SCHEEPSVAARTPOLITIE":
                return Math.floor(Math.random()*((40-20)+1))+20;
            case "CONTAINERSCHIP":
                return Math.floor(Math.random()*((60-30)+1))+30;
            case "MOTORBOOT":
                return Math.floor(Math.random()*((15-5)+1))+5;
            case "TANKER":
                return Math.floor(Math.random()*((50-30)+1))+30;
            case "ZEILBOOT":
                return Math.floor(Math.random()*((10-5)+1))+5;
        }

        return 0.0;
    }

    private int getKoers(){
        Random r = new Random();
        return r.nextInt(((359 - 1) + 1) + 1);
    }

    public void inlezenVerkeerstorens() {
        AbstractActorFactory actor = FactoryProducer.getFactory(Actors.HULPDIENST);
        ArrayList<Verkeerstoren> verkeerstorens = kustwachtQueries.getAllVerkeerstorens();

        for (Verkeerstoren item : verkeerstorens) {
            String typeNaam = item.getEnumNaam();
            String naam = item.getNaam();
            Coördinaten coördinaten = item.getCoördinaten();
            IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere clients moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

            Verkeerstoren verkeerstoren = actor.setVerkeersToren(typeNaam, naam, Hulpdiensten.VERKEERSTOREN, coördinaten, strategy);
        }
    }

    public void inlezenSchepen() {

        try {
            AbstractActorFactory actor = FactoryProducer.getFactory(Actors.SCHIP);
            ArrayList<Vervoermiddel> schepen = kustwachtQueries.getAllSchepen();
            schepen.size();

            for (Vervoermiddel item : schepen) {
                String typeNaam = item.getEnumNaam();
                String naam = item.getNaam();
                Coördinaten coördinaten = item.getCoördinaten();
                double snelheid = item.getSnelheid();
                double grootte = item.getGrootte();
                double capaciteit = item.getCapaciteit();
                int koers = item.getKoers();
                String status = item.getStatus();
                IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere clients moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

                Vervoermiddel vervoermiddel;
                switch (typeNaam) {
                    case "CONTAINERSCHIP":
                        vervoermiddel = actor.setSchip(typeNaam, naam, Schepen.CONTAINERSCHIP, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                    case "MOTORBOOT":
                        vervoermiddel = actor.setSchip(typeNaam, naam, Schepen.MOTORBOOT, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                    case "TANKER":
                        vervoermiddel = actor.setSchip(typeNaam, naam, Schepen.TANKER, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                    case "ZEILBOOT":
                        vervoermiddel = actor.setSchip(typeNaam, naam, Schepen.ZEILBOOT, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                        break;
                }
            }
        }
        catch (Exception ex)
        {

        }
    }

    public void inlezenHulpdiensten() {
        AbstractActorFactory actor = FactoryProducer.getFactory(Actors.HULPDIENST);
        ArrayList<Vervoermiddel> hulpdiensten = kustwachtQueries.getAllHulpdiensten();
        hulpdiensten.size();

        for (Vervoermiddel item : hulpdiensten) {
            String typeNaam = item.getEnumNaam();
            String naam = item.getNaam();
            Coördinaten coördinaten = item.getCoördinaten();
            double snelheid = item.getSnelheid();
            double grootte = item.getGrootte();
            double capaciteit = item.getCapaciteit();
            int koers = item.getKoers();
            String status = item.getStatus();
            IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

            Vervoermiddel vervoermiddel;
            switch (typeNaam) {
                case "SEAKING":
                    vervoermiddel = actor.setHulpDienst(typeNaam, naam, SEAKING, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                    break;
                case "SCHEEPSVAARTPOLITIE":
                    vervoermiddel = actor.setHulpDienst(typeNaam, naam, SCHEEPSVAARTPOLITIE, coördinaten, snelheid, grootte, capaciteit, koers, strategy, status);
                    break;
            }
        }
    }

    //Afdrukken van alle actoren.
    public void printAllActors() {
        //ArrayList<String> output = new ArrayList<String>();

        output.add("\nVERKEERSTORENS");
        for (int i = 0; i < Actor.verkeerstorens.size(); i++) {
            output.add("Actor: " + Actor.verkeerstorens.get(i).getEnumNaam() + "   Naam: " + Actor.verkeerstorens.get(i).getNaam() + "   Coördinaten: " + Actor.verkeerstorens.get(i).getCoördinaten());
        }
        output.add("\nHULPDIENSTEN");
        for (int i = 0; i < Actor.mogelijkeHulpdiensten.size(); i++) {
            output.add("Actor: " + Actor.mogelijkeHulpdiensten.get(i).getEnumNaam() + "   Naam: " + Actor.mogelijkeHulpdiensten.get(i).getNaam() + "   Status: " + Actor.mogelijkeHulpdiensten.get(i).getStatus()
                    + "   Capaciteit: " + Actor.mogelijkeHulpdiensten.get(i).getCapaciteit() + "   Coördinaten: " + Actor.mogelijkeHulpdiensten.get(i).getCoördinaten() + "   Grootte: " + Actor.mogelijkeHulpdiensten.get(i).getGrootte()
                    + "   Koers: " + Actor.mogelijkeHulpdiensten.get(i).getKoers() + "   Reactietijd: " + Actor.mogelijkeHulpdiensten.get(i).getReactieTijd() + "   Snelheid: " + Actor.mogelijkeHulpdiensten.get(i).getSnelheid()
                    + "   Wendbaarheid: " + Actor.mogelijkeHulpdiensten.get(i).getWendbaarheid() + "   Strategy: " + Actor.mogelijkeHulpdiensten.get(i).getHulpdienstStrategy());
        }
        output.add("\nSCHEPEN");
        for (int i = 0; i < Actor.schepenOpWater.size(); i++) {
            output.add("Actor: " + Actor.schepenOpWater.get(i).getEnumNaam() + "   Naam: " + Actor.schepenOpWater.get(i).getNaam() + "   Status: " + Actor.schepenOpWater.get(i).getStatus()
                    + "   Capaciteit: " + Actor.schepenOpWater.get(i).getCapaciteit() + "   Coördinaten: " + Actor.schepenOpWater.get(i).getCoördinaten() + "   Grootte: " + Actor.schepenOpWater.get(i).getGrootte()
                    + "   Koers: " + Actor.schepenOpWater.get(i).getKoers() + "   Reactietijd: " + Actor.schepenOpWater.get(i).getReactieTijd() + "   Snelheid: " + Actor.schepenOpWater.get(i).getSnelheid()
                    + "   Wendbaarheid: " + Actor.schepenOpWater.get(i).getWendbaarheid() + "   Strategy: " + Actor.schepenOpWater.get(i).getHulpdienstStrategy());
        }

        for (String object : output) {
            EventLogger.logger.info(String.format(object));
            //System.out.println(object);
        }
    }
}