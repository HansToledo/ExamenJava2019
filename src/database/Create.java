package database;

import calculations.Coördinaten;
import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import enums.StatusVervoermiddel;
import model.*;
import strategy.GeenStrategy;

public class Create {
    private static final IHulpdienstStrategy geenStrategy = new GeenStrategy();
    private static final DBqueries kustwachtQueries = new DBqueries();

    public static void addVerkeerstoren(String naam, double latitude, double longitude) {
        AbstractActorFactory abstractVerkeerstoren = FactoryProducer.getFactory(Actors.HULPDIENST);
        Coördinaten coördinaten = new Coördinaten(latitude, longitude);
        Hulpdiensten hulpdienst = Hulpdiensten.VERKEERSTOREN;

        boolean alreadyExists = false;

        Verkeerstoren verkeerstoren = abstractVerkeerstoren.setVerkeersToren(hulpdienst.VERKEERSTOREN.toString(), "VERKEERSTOREN-" + naam, Hulpdiensten.VERKEERSTOREN, coördinaten, geenStrategy);

        //Actor toevoegen aan database
        for (Verkeerstoren item: kustwachtQueries.getAllVerkeerstorens() ) {
            if (!(item.getNaam().equals(naam)) ){
            } else {
                alreadyExists = true;
            }
        }

        if (!alreadyExists){
            kustwachtQueries.addVerkeerstoren(verkeerstoren.getEnumNaam(), verkeerstoren.getNaam(), coördinaten);
        }
    }

    public static void addHulpdienst(String naam, Hulpdiensten hulpdienst, double latitude, double longitude, double snelheid, double grootte, double capaciteit, int koers) {
        AbstractActorFactory abstractHulpdienst = FactoryProducer.getFactory(Actors.HULPDIENST);
        Coördinaten coördinaten = new Coördinaten(latitude, longitude);

        boolean alreadyExists = false;

        Vervoermiddel vervoermiddel = abstractHulpdienst.setHulpDienst(hulpdienst.toString(), hulpdienst.toString() + "-" + naam, hulpdienst, coördinaten, snelheid, grootte, capaciteit, koers,
                geenStrategy, StatusVervoermiddel.OK.toString());

        //Actor toevoegen aan database
        for (Vervoermiddel item: kustwachtQueries.getAllHulpdiensten() ) {
            if (!(item.getNaam().equals(naam))) {
            } else {
                alreadyExists = true;
            }
        }

        if (!alreadyExists) {
            kustwachtQueries.addHulpdienst(vervoermiddel.getEnumNaam(), vervoermiddel.getNaam(), vervoermiddel.getSnelheid(),
                    vervoermiddel.getReactieTijd(), vervoermiddel.getWendbaarheid(),
                    vervoermiddel.getGrootte(), vervoermiddel.getCapaciteit(),
                    vervoermiddel.getKoers(), vervoermiddel.getStatus(), coördinaten);
        }
    }

    public static void addSchip(String naam, Schepen schip, double latitude, double longitude, double snelheid, double grootte, double capaciteit, int koers) {
        AbstractActorFactory abstractSchip = FactoryProducer.getFactory(Actors.SCHIP);
        Coördinaten coördinaten = new Coördinaten(latitude, longitude);

        boolean alreadyExists = false;

        Vervoermiddel vervoermiddel = abstractSchip.setSchip(schip.toString(), (schip.toString() + "-" + naam), schip, coördinaten, snelheid, grootte, capaciteit, koers,
                geenStrategy, StatusVervoermiddel.OK.toString());

        //Actor toevoegen aan database
        for (Vervoermiddel item: kustwachtQueries.getAllSchepen() ) {
            if (!(item.getNaam().equals(naam))) {
            } else {
                alreadyExists = true;
            }
        }

        if (!alreadyExists) {
            kustwachtQueries.addSchip(vervoermiddel.getEnumNaam(), vervoermiddel.getNaam(), vervoermiddel.getSnelheid(),
                    vervoermiddel.getReactieTijd(), vervoermiddel.getWendbaarheid(),
                    vervoermiddel.getGrootte(), vervoermiddel.getCapaciteit(),
                    vervoermiddel.getKoers(), vervoermiddel.getStatus(), coördinaten);
        }
    }
}
