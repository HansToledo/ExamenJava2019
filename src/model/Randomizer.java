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

public class Randomizer {
    private static Random random = new Random();
    private final DBqueries kustwachtQueries = new DBqueries();
    IHulpdienstStrategy geenStrategy = new GeenStrategy();

    //TODO randomizer knop voorzien in gui
    //TODO methods voor schrijven en lezen uit randomizer klasse halen

    Coördinaten coördinaten;
    //List<Actor> actoren = new ArrayList<Actor>();


    public static double getRandomDoubleBetweenRange(double min, double max){ // gebruikt worden voor ramdom met range

        double x = (Math.random()*((max-min)+1))+min;
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


    public void addEnumHulpdienstenEnSchepenToDB(){
        //Alle mogelijke enums toevoegen aan database
        int enumi=0;
        List alleBestaandeEnums = kustwachtQueries.getAllVervoermiddelTypes();
        List hulpdienstenList = java.util.Arrays.asList(Hulpdiensten.values()); //lijst met hulpdiensten enums
        List schepenList = java.util.Arrays.asList(Schepen.values());   //lijst met schepen enums

        while(enumi < hulpdienstenList.size())  //hulpdiensten enums inlezen in database
        {
            String gezocht = hulpdienstenList.get(enumi).toString();
            if ((alleBestaandeEnums.contains(gezocht))==false){
                kustwachtQueries.addTypeVervoermiddel(hulpdienstenList.get(enumi).toString(),0);
                enumi++;
            }
            else
            {
                enumi++;
            }
        }

        enumi = 0;  //enumi terug op 0 zetten voor de volgende lijst te controleren
        while(enumi < schepenList.size())   //schepen enums inlezen in database
        {
            String gezocht = schepenList.get(enumi).toString();
            if ((alleBestaandeEnums.contains(gezocht))==false){
                kustwachtQueries.addTypeVervoermiddel(schepenList.get(enumi).toString(),1);
                enumi++;
            }
            else
            {
                enumi++;
            }
        }
    }

    public void addEnumStatusVervoermiddelToDB(){
        //Alle mogelijke enums toevoegen aan database
        int enumi=0;
        List alleBestaandeStatussen = kustwachtQueries.getAllVervoermiddelStatussen();
        List statusVervoermiddelList = java.util.Arrays.asList(StatusVervoermiddel.values()); //lijst met status enums

        while(enumi < statusVervoermiddelList.size())  //hulpdiensten enums inlezen in database
        {
            String gezocht = statusVervoermiddelList.get(enumi).toString();
            if ((alleBestaandeStatussen.contains(gezocht))==false){
                kustwachtQueries.addStatusVervoermiddel(statusVervoermiddelList.get(enumi).toString());
                enumi++;
            }
            else
            {
                enumi++;
            }
        }
    }

    public void generateVerkeerstores(int aantal){
        int teller = 0;
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.HULPDIENST);

        do{
            coördinaten = new Coördinaten().getRandomCoordinaten();
            Hulpdiensten hulpdienst = Hulpdiensten.values()[(int)(Math.random()*(Hulpdiensten.values().length)-1)];

            Verkeerstoren verkeerstoren = random.setVerkeersToren(hulpdienst.VERKEERSTOREN.toString(),hulpdienst.VERKEERSTOREN.toString()+naamAddon(),Hulpdiensten.VERKEERSTOREN,coördinaten, geenStrategy);

            //actoren.add(verkeerstoren);
            //Actor toevoegen aan database
            kustwachtQueries.addVerkeerstoren(verkeerstoren.getEnumNaam(),verkeerstoren.getNaam(),coördinaten);

            ++teller;
        }while (teller<aantal);
    }

    public void generateHulpdiensten(int aantal){
        int teller = 0;
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.HULPDIENST);

        do{
            coördinaten = new Coördinaten().getRandomCoordinaten();
            Hulpdiensten hulpdienst = Hulpdiensten.values()[(int)(Math.random()*(Hulpdiensten.values().length)-1)]; //random enum hulpdienst genereren, -1 omdat verkeerstoren niet geselecteerd mag worden doordat deze de parameters zoals snelheid enzo niet heeft.

            Vervoermiddel vervoermiddel = random.setHulpDienst(hulpdienst.toString(),hulpdienst.toString()+naamAddon(),hulpdienst,coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90,
                    geenStrategy,StatusVervoermiddel.OK.toString());

            //actoren.add(vervoermiddel);

            //Actor toevoegen aan database
            kustwachtQueries.addHulpdienst(vervoermiddel.getEnumNaam(),vervoermiddel.getNaam(),vervoermiddel.getSnelheid(),
                    vervoermiddel.getReactieTijd(),vervoermiddel.getWendbaarheid(),
                    vervoermiddel.getGrootte(),vervoermiddel.getCapaciteit(),
                    vervoermiddel.getKoers(),vervoermiddel.getStatus(),coördinaten);

            ++teller;
        }while (teller<aantal);
    }


    public void generateSchepen(int aantal) {
        int teller = 0;
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.SCHIP);

        do {
            coördinaten = new Coördinaten().getRandomCoordinaten();
            Schepen schip = Schepen.values()[(int)(Math.random()*Schepen.values().length)]; //random enum schip genereren

                Vervoermiddel vervoermiddel = random.setSchip(schip.toString(), schip.toString() + naamAddon(), schip, coördinaten,
                        Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                        Math.round(100 + Math.random() * 100), (int) Math.random() * 90,
                        geenStrategy, StatusVervoermiddel.OK.toString());


            //actoren.add(vervoermiddel);

            //Actoren toevoegen aan database
            kustwachtQueries.addSchip(vervoermiddel.getEnumNaam(),vervoermiddel.getNaam(),vervoermiddel.getSnelheid(),
                    vervoermiddel.getReactieTijd(),vervoermiddel.getWendbaarheid(),
                    vervoermiddel.getGrootte(),vervoermiddel.getCapaciteit(),
                    vervoermiddel.getKoers(),vervoermiddel.getStatus(),coördinaten);

            ++teller;
        }while (teller<aantal);
    }

    public void inlezenVerkeerstorens(){
        AbstractActorFactory actor = FactoryProducer.getFactory(Actors.HULPDIENST);
        ArrayList<Verkeerstoren> verkeerstorens = kustwachtQueries.getAllVerkeerstorens();

        for(Verkeerstoren item : verkeerstorens) {
            String typeNaam = item.getEnumNaam();
            String naam = item.getNaam();
            Coördinaten coördinaten = item.getCoördinaten();
            IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere clients moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

            Verkeerstoren verkeerstoren = actor.setVerkeersToren(typeNaam,naam,Hulpdiensten.VERKEERSTOREN,coördinaten,strategy);
        }
    }

    public void inlezenSchepen(){
        AbstractActorFactory actor = FactoryProducer.getFactory(Actors.SCHIP);
        ArrayList<Vervoermiddel> schepen = kustwachtQueries.getAllSchepen();
        schepen.size();

        for(Vervoermiddel item : schepen) {
            String typeNaam = item.getEnumNaam();
            String naam = item.getNaam();
            Coördinaten coördinaten = item.getCoördinaten();
            double snelheid = item.getSnelheid();
            double reactietijd = item.getReactieTijd();
            double wendbaarheid = item.getWendbaarheid();
            double grootte = item.getGrootte();
            double capaciteit = item.getCapaciteit();
            int koers = item.getKoers();
            String status = item.getStatus();
            IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere clients moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

            Vervoermiddel vervoermiddel;
            switch (typeNaam) {
                case "CONTAINERSCHIP":
                    vervoermiddel = actor.setSchip(typeNaam,naam,Schepen.CONTAINERSCHIP,coördinaten,snelheid,grootte,capaciteit,koers,strategy,status);
                    break;
                case "MOTORBOOT":
                    vervoermiddel = actor.setSchip(typeNaam,naam,Schepen.MOTORBOOT,coördinaten,snelheid,grootte,capaciteit,koers,strategy,status);
                    break;
                case "TANKER":
                    vervoermiddel = actor.setSchip(typeNaam,naam,Schepen.TANKER,coördinaten,snelheid,grootte,capaciteit,koers,strategy,status);
                    break;
                case "ZEILBOOT":
                    vervoermiddel = actor.setSchip(typeNaam,naam,Schepen.ZEILBOOT,coördinaten,snelheid,grootte,capaciteit,koers,strategy,status);
                    break;
            }
        }
    }

    public void inlezenHulpdiensten(){
        AbstractActorFactory actor = FactoryProducer.getFactory(Actors.HULPDIENST);
        ArrayList<Vervoermiddel> hulpdiensten = kustwachtQueries.getAllHulpdiensten();
        hulpdiensten.size();

        for(Vervoermiddel item : hulpdiensten) {
            String typeNaam = item.getEnumNaam();
            String naam = item.getNaam();
            Coördinaten coördinaten = item.getCoördinaten();
            double snelheid = item.getSnelheid();
            double reactietijd = item.getReactieTijd();
            double wendbaarheid = item.getWendbaarheid();
            double grootte = item.getGrootte();
            double capaciteit = item.getCapaciteit();
            int koers = item.getKoers();
            String status = item.getStatus();
            IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

            Vervoermiddel vervoermiddel;
            switch (typeNaam) {
                case "SEAKING":
                    vervoermiddel = actor.setHulpDienst(typeNaam,naam,Hulpdiensten.SEAKING,coördinaten,snelheid,grootte,capaciteit,koers,strategy,status);
                    break;
                case "SCHEEPSVAARTPOLITIE":
                    vervoermiddel = actor.setHulpDienst(typeNaam,naam,Hulpdiensten.SCHEEPSVAARTPOLITIE,coördinaten,snelheid,grootte,capaciteit,koers,strategy,status);
                    break;
            }
        }
    }

    //Afdrukken van alle actoren.
    public void printAllActors(){
        ArrayList<String> output = new ArrayList<String>();

        output.add("\nVERKEERSTORENS");
        for (int i=0; i < Actor.verkeerstorens.size();i++) {
            output.add ("Actor: " + Actor.verkeerstorens.get(i).getEnumNaam() + "   Naam: " + Actor.verkeerstorens.get(i).getNaam() + "   Coördinaten: " + Actor.verkeerstorens.get(i).getCoördinaten());
        }
        output.add("\nHULPDIENSTEN");
        for (int i=0; i < Actor.mogelijkeHulpdiensten.size();i++) {
            output.add ("Actor: " + Actor.mogelijkeHulpdiensten.get(i).getEnumNaam() + "   Naam: " + Actor.mogelijkeHulpdiensten.get(i).getNaam() + "   Status: " + Actor.mogelijkeHulpdiensten.get(i).getStatus()
                    + "   Capaciteit: " + Actor.mogelijkeHulpdiensten.get(i).getCapaciteit() + "   Coördinaten: " + Actor.mogelijkeHulpdiensten.get(i).getCoördinaten() + "   Grootte: " + Actor.mogelijkeHulpdiensten.get(i).getGrootte()
                    + "   Koers: " + Actor.mogelijkeHulpdiensten.get(i).getKoers() + "   Reactietijd: " + Actor.mogelijkeHulpdiensten.get(i).getReactieTijd() + "   Snelheid: " + Actor.mogelijkeHulpdiensten.get(i).getSnelheid()
                    + "   Wendbaarheid: " + Actor.mogelijkeHulpdiensten.get(i).getWendbaarheid() + "   Strategy: " + Actor.mogelijkeHulpdiensten.get(i).getHulpdienstStrategy());
        }
        output.add("\nSCHEPEN");
        for (int i=0; i < Actor.schepenOpWater.size();i++) {
            output.add ("Actor: " + Actor.schepenOpWater.get(i).getEnumNaam() + "   Naam: " + Actor.schepenOpWater.get(i).getNaam() + "   Status: " + Actor.schepenOpWater.get(i).getStatus()
                    + "   Capaciteit: " + Actor.schepenOpWater.get(i).getCapaciteit() + "   Coördinaten: " + Actor.schepenOpWater.get(i).getCoördinaten() + "   Grootte: " + Actor.schepenOpWater.get(i).getGrootte()
                    + "   Koers: " + Actor.schepenOpWater.get(i).getKoers() + "   Reactietijd: " + Actor.schepenOpWater.get(i).getReactieTijd() + "   Snelheid: " + Actor.schepenOpWater.get(i).getSnelheid()
                    + "   Wendbaarheid: " + Actor.schepenOpWater.get(i).getWendbaarheid() + "   Strategy: " + Actor.schepenOpWater.get(i).getHulpdienstStrategy());
        }

        for (String object: output) {
            System.out.println(object);
        }
    }
}