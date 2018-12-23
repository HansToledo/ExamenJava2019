package model;

import calculations.Coördinaten;
import enums.Hulpdiensten;
import enums.Schepen;
import database.DBqueries;
import strategy.GeenStrategy;
import strategy.MeldingStrategy;
import strategy.PickupStrategy;

import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 13:29<br/>
 * To change this template use File | Settings | File Templates.
 */
public class HulpdienstFactory extends AbstractActorFactory {
    private final DBqueries kustwachtQueries = new DBqueries();
    IHulpdienstStrategy pickupStrategy = new PickupStrategy();
    IHulpdienstStrategy meldingStrategy = new MeldingStrategy();
    IHulpdienstStrategy geenStrategy = new GeenStrategy();


    @Override
    public Vervoermiddel setHulpDienst(String enumNaam, String naam, Hulpdiensten hulpdienst, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy) {

        switch (hulpdienst) {

            case SCHEEPSVAARTPOLITIE:

                ScheepsvaartPolitie scheepsvaartPolitie = new ScheepsvaartPolitie(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
                Actor.mogelijkeHulpdiensten.add(scheepsvaartPolitie);
                return scheepsvaartPolitie;

            case SEAKING:

                Seaking seaking = new Seaking(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
                Actor.mogelijkeHulpdiensten.add(seaking);
                return seaking;
        }
        return null;
    }


    @Override
    public Vervoermiddel setSchip(String enumNaam, String naam,Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy) {

        return null;
    }


    @Override
    public Verkeerstoren setVerkeersToren(String enumNaam, String naam,Hulpdiensten verkeersToren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {

        switch (verkeersToren) {

            case VERKEERSTOREN:
                Verkeerstoren verkeerstoren = new Verkeerstoren(enumNaam, naam, coördinaten, hulpdienstStrategy);
                Actor.verkeerstorens.add(verkeerstoren);

                //verkeerstoren.addNoodObserver(); nog bekijken

                return verkeerstoren;
        }
        return null;
    }
}
