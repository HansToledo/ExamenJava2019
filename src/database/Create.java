package database;

import calculations.Coördinaten;
import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import enums.StatusVervoermiddel;
import model.*;
import strategy.GeenStrategy;

import java.util.List;

public class Create {
    private static final IHulpdienstStrategy geenStrategy = new GeenStrategy();
    private static final DBqueries kustwachtQueries = new DBqueries();

    public static int addVerkeerstoren(String naam, double latitude, double longitude) {
        try {
            String naamVK = "VERKEERSTOREN-" + naam;
            AbstractActorFactory abstractVerkeerstoren = FactoryProducer.getFactory(Actors.HULPDIENST);
            Coördinaten coördinaten = new Coördinaten(latitude, longitude);
            Hulpdiensten hulpdienst = Hulpdiensten.VERKEERSTOREN;

            Verkeerstoren verkeerstoren = abstractVerkeerstoren.setVerkeersToren(hulpdienst.VERKEERSTOREN.toString(), naamVK, Hulpdiensten.VERKEERSTOREN, coördinaten, geenStrategy);

            //Actor toevoegen aan database
            for (Verkeerstoren item : kustwachtQueries.getAllVerkeerstorens()) {
                if ((item.getNaam().equals(naamVK))) {
                    return 0;
                }
            }

            return kustwachtQueries.addVerkeerstoren(verkeerstoren.getEnumNaam(), verkeerstoren.getNaam(), coördinaten);

        }
        catch (Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public static int addHulpdienst(String naam, Hulpdiensten hulpdienst, double latitude, double longitude, double snelheid, double grootte, double capaciteit, int koers) {
        try {
            String naamHulpdienst = hulpdienst.toString() + "-" + naam;
            AbstractActorFactory abstractHulpdienst = FactoryProducer.getFactory(Actors.HULPDIENST);
            Coördinaten coördinaten = new Coördinaten(latitude, longitude);

            Vervoermiddel vervoermiddel = abstractHulpdienst.setHulpDienst(hulpdienst.toString(), naamHulpdienst, hulpdienst, coördinaten, snelheid, grootte, capaciteit, koers,
                    geenStrategy, StatusVervoermiddel.OK.toString());

            //Actor toevoegen aan database
            for (Vervoermiddel item : kustwachtQueries.getAllHulpdiensten()) {
                if ((item.getNaam().equals(naamHulpdienst))) {
                    return 0;
                }
            }

            return kustwachtQueries.addHulpdienst(vervoermiddel.getEnumNaam(), vervoermiddel.getNaam(), vervoermiddel.getSnelheid(),
                    vervoermiddel.getReactieTijd(), vervoermiddel.getWendbaarheid(),
                    vervoermiddel.getGrootte(), vervoermiddel.getCapaciteit(),
                    vervoermiddel.getKoers(), vervoermiddel.getStatus(), coördinaten);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public static int addSchip(String naam, Schepen schip, double latitude, double longitude, double snelheid, double grootte, double capaciteit, int koers) {
        try {
            String naamSchip = schip.toString() + "-" + naam;
            AbstractActorFactory abstractSchip = FactoryProducer.getFactory(Actors.SCHIP);
            Coördinaten coördinaten = new Coördinaten(latitude, longitude);

            Vervoermiddel vervoermiddel = abstractSchip.setSchip(schip.toString(), naamSchip, schip, coördinaten, snelheid, grootte, capaciteit, koers,
                    geenStrategy, StatusVervoermiddel.OK.toString());

            //Actor toevoegen aan database
            for (Vervoermiddel item : kustwachtQueries.getAllSchepen()) {
                if ((item.getNaam().equals(naamSchip))) {
                    return 0;
                }
            }

            return kustwachtQueries.addSchip(vervoermiddel.getEnumNaam(), vervoermiddel.getNaam(), vervoermiddel.getSnelheid(),
                    vervoermiddel.getReactieTijd(), vervoermiddel.getWendbaarheid(),
                    vervoermiddel.getGrootte(), vervoermiddel.getCapaciteit(),
                    vervoermiddel.getKoers(), vervoermiddel.getStatus(), coördinaten);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
