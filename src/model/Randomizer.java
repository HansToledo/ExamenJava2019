package model;

import calculations.Coördinaten;
import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import enums.StatusVervoermiddel;
import strategy.*;
import database.DBqueries;
import java.util.Random;

public class Randomizer {
    private static Random random = new Random();
    private final DBqueries kustwachtQueries = new DBqueries();
    IHulpdienstStrategy geenStrategy = new GeenStrategy();
    Coördinaten coördinaten;


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
}