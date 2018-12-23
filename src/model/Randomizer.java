package model;

import calculations.Coördinaten;
import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import enums.StatusVervoermiddel;
import strategy.GeenStrategy;
import strategy.MeldingStrategy;
import strategy.PickupStrategy;
import database.DBqueries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {
    private static Random random = new Random();
    private final DBqueries kustwachtQueries = new DBqueries();
    IHulpdienstStrategy pickupStrategy = new PickupStrategy();
    IHulpdienstStrategy meldingStrategy = new MeldingStrategy();
    IHulpdienstStrategy geenStrategy = new GeenStrategy();

    Coördinaten coördinaten = new Coördinaten();
    List<Actor> actoren = new ArrayList<Actor>();


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
            Hulpdiensten hulpdienst = Hulpdiensten.values()[(int)(Math.random()*(Hulpdiensten.values().length)-1)];

            Verkeerstoren verkeerstoren = random.setVerkeersToren(hulpdienst.VERKEERSTOREN.toString(),hulpdienst.VERKEERSTOREN.toString()+naamAddon(),Hulpdiensten.VERKEERSTOREN,coördinaten, geenStrategy);

            //actoren.add(verkeerstoren);
            //Actor toevoegen aan database
            kustwachtQueries.addVerkeerstoren(verkeerstoren.getEnumNaam(),verkeerstoren.getNaam());

            ++teller;
        }while (teller<aantal);
    }

    public void generateHulpdiensten(int aantal){
        int teller = 0;
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.HULPDIENST);

        do{
            Hulpdiensten hulpdienst = Hulpdiensten.values()[(int)(Math.random()*(Hulpdiensten.values().length)-1)]; //random enum hulpdienst genereren, -1 omdat verkeerstoren niet geselecteerd mag worden doordat deze de parameters zoals snelheid enzo niet heeft.

            Vervoermiddel vervoermiddel = random.setHulpDienst(hulpdienst.toString(),hulpdienst.toString()+naamAddon(),hulpdienst,coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90,
                    geenStrategy);

            //actoren.add(vervoermiddel);

            //Actor toevoegen aan database
            kustwachtQueries.addHulpdienst(vervoermiddel.getEnumNaam(),vervoermiddel.getNaam(),vervoermiddel.getSnelheid(),
                    vervoermiddel.getReactieTijd(),vervoermiddel.getWendbaarheid(),
                    vervoermiddel.getGrootte(),vervoermiddel.getCapaciteit(),
                    vervoermiddel.getKoers(),vervoermiddel.getStatus());

            ++teller;
        }while (teller<aantal);
    }

    public void generateSchepen(int aantal) {
        int teller = 0;
        AbstractActorFactory random = FactoryProducer.getFactory(Actors.SCHIP);

        do {
            Schepen schip = Schepen.values()[(int)(Math.random()*Schepen.values().length)]; //random enum schip genereren

            Vervoermiddel vervoermiddel = random.setSchip(schip.toString(),schip.toString()+naamAddon(),schip, coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90,
                    geenStrategy);

            //actoren.add(vervoermiddel);

            //Actoren toevoegen aan database
            kustwachtQueries.addSchip(vervoermiddel.getEnumNaam(),vervoermiddel.getNaam(),vervoermiddel.getSnelheid(),
                    vervoermiddel.getReactieTijd(),vervoermiddel.getWendbaarheid(),
                    vervoermiddel.getGrootte(),vervoermiddel.getCapaciteit(),
                    vervoermiddel.getKoers(),vervoermiddel.getStatus());

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
            IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

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
            IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

            Vervoermiddel vervoermiddel;
            switch (typeNaam) {
                case "CONTAINERSCHIP":
                    vervoermiddel = actor.setSchip(typeNaam,naam,Schepen.CONTAINERSCHIP,coördinaten,snelheid,grootte,capaciteit,koers,strategy);
                    break;
                case "MOTORBOOT":
                    vervoermiddel = actor.setSchip(typeNaam,naam,Schepen.MOTORBOOT,coördinaten,snelheid,grootte,capaciteit,koers,strategy);
                    break;
                case "TANKER":
                    vervoermiddel = actor.setSchip(typeNaam,naam,Schepen.TANKER,coördinaten,snelheid,grootte,capaciteit,koers,strategy);
                    break;
                case "ZEILBOOT":
                    vervoermiddel = actor.setSchip(typeNaam,naam,Schepen.ZEILBOOT,coördinaten,snelheid,grootte,capaciteit,koers,strategy);
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
            IHulpdienstStrategy strategy = geenStrategy;    //Default waarde. Indien we met meerdere moeten werken zal dit moeten worden weggeschreven in DB zodat iedereen de strategy kan zien.

            Vervoermiddel vervoermiddel;
            switch (typeNaam) {
                case "SEAKING":
                    vervoermiddel = actor.setHulpDienst(typeNaam,naam,Hulpdiensten.SEAKING,coördinaten,snelheid,grootte,capaciteit,koers,strategy);
                    break;
                case "SCHEEPSVAARTPOLITIE":
                    vervoermiddel = actor.setHulpDienst(typeNaam,naam,Hulpdiensten.SCHEEPSVAARTPOLITIE,coördinaten,snelheid,grootte,capaciteit,koers,strategy);
                    break;
            }
        }
    }

    //Afdrukken van alle actoren.
    public void printAllActors(){
        System.out.println("VERKEERSTORENS");
        for (int i=0; i < Actor.verkeerstorens.size();i++) {
            System.out.println(Actor.verkeerstorens.get(i));
        }
        System.out.println("HULPDIENSTEN");
        for (int i=0; i < Actor.mogelijkeHulpdiensten.size();i++) {
            System.out.println(Actor.mogelijkeHulpdiensten.get(i));
        }
        System.out.println("SCHEPEN");
        for (int i=0; i < Actor.schepenOpWater.size();i++) {
            System.out.println(Actor.schepenOpWater.get(i));
        }
    }
}