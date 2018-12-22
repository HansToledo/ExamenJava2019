package model;

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
                kustwachtQueries.addTypeVervoermiddel(hulpdienstenList.get(enumi).toString());
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
                kustwachtQueries.addTypeVervoermiddel(schepenList.get(enumi).toString());
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

            Verkeerstoren actor3 = random3.setVerkeersToren(hulpdienst.VERKEERSTOREN.toString(),hulpdienst.VERKEERSTOREN.toString()+naamAddon(),Hulpdiensten.VERKEERSTOREN,coördinaten, geenStrategy);

            Vervoermiddel actor2 = random2.setHulpDienst(hulpdienst.toString(),hulpdienst.toString()+naamAddon(),hulpdienst,coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90,
                    geenStrategy);

            Vervoermiddel actor = random.setSchip(schip.toString(),schip.toString()+naamAddon(),schip, coördinaten,
                    Math.round(1 + Math.random() * 40), Math.round(100 + Math.random() * 100),
                    Math.round(100 + Math.random() * 100), (int) Math.random() * 90,
                    geenStrategy);

            actoren.add(actor);
            actoren.add(actor2);
            actoren.add(actor3);


            //Actoren toevoegen aan database
            kustwachtQueries.addSchip(actor.getEnumNaam(),actor.getNaam(),actor.getSnelheid(),
                    actor.getReactieTijd(),actor.getWendbaarheid(),
                    actor.getGrootte(),actor.getCapaciteit(),
                    actor.getKoers(),actor.getStatus());

            kustwachtQueries.addHulpdienst(actor2.getEnumNaam(),actor2.getNaam(),actor2.getSnelheid(),
                    actor2.getReactieTijd(),actor2.getWendbaarheid(),
                    actor2.getGrootte(),actor2.getCapaciteit(),
                    actor2.getKoers(),actor2.getStatus());

            kustwachtQueries.addVerkeerstoren(actor3.getEnumNaam(),actor3.getNaam());


           // actor.addVerkeerstorenObserver(actor3);                             //TODO Observer pattern
           // actor.notifyVerkeerstorenObservers(StatusVervoermiddel.OK.toString());   //Alles mogelijke statussen bevinden zich in de enum StatusVervoermiddel.

            ++teller;

        }while (teller<10);


        //Afdrukken van alle actoren.
        for (int i=0; i < actoren.size();i++){
            System.out.println(actoren.get(i));
        }
    }



}
