package model;

import calculations.Coördinaten;
import enums.Hulpdiensten;
import enums.Schepen;
import database.DBqueries;
import strategy.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 13:29<br/>
 * Deze factory wordt aangestuursd via de abstracte factory producer en maakt hulpdiensten aan, verkeerstoren zijn ook hulpdiensten
 */
public class HulpdienstFactory extends AbstractActorFactory {

    @Override
    public Vervoermiddel setHulpDienst(String enumNaam, String naam, Hulpdiensten hulpdienst, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy, String status) {

        switch (hulpdienst) {

            case SCHEEPSVAARTPOLITIE:

                ScheepsvaartPolitie scheepsvaartPolitie = new ScheepsvaartPolitie(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(scheepsvaartPolitie); //toeveoegen aan lijst in actor klasse
                return scheepsvaartPolitie;

            case SEAKING:

                Seaking seaking = new Seaking(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(seaking);  //toevoegen aan lijst in actor klasse
                return seaking;
        }
        return null;
    }


    @Override
    public Vervoermiddel setSchip(String enumNaam, String naam,Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy,String status) {

        return null;
    }


    @Override
    public Verkeerstoren setVerkeersToren(String enumNaam, String naam,Hulpdiensten verkeersToren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {

        switch (verkeersToren) {

            case VERKEERSTOREN:

                Verkeerstoren verkeerstoren = new Verkeerstoren(enumNaam, naam, coördinaten, hulpdienstStrategy);
                Actor.verkeerstorens.add(verkeerstoren); //toevoegen aan lijst in actor klasse
                return verkeerstoren;
        }
        return null;
    }
}
