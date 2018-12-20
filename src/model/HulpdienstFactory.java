package model;

import enums.Hulpdiensten;
import enums.Schepen;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 13:29<br/>
 * To change this template use File | Settings | File Templates.
 */
public class HulpdienstFactory extends AbstractActorFactory {


    @Override
    public Vervoermiddel setHulpDienst(Hulpdiensten hulpdienst,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers) {

        switch (hulpdienst) {

            case SCHEEPSVAARTPOLITIE:
                return new ScheepsvaartPolitie(coördinaten,snelheid,grootte,capaciteit,koers);
            case SEAKING:
                return new Seaking(coördinaten,snelheid,grootte,capaciteit,koers);
        }

        return null;
    }


    @Override
    public Vervoermiddel setSchip(Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers) {

        return null;
    }


    @Override
    public Verkeerstoren setVerkeersToren(Hulpdiensten verkeersToren) {

        switch (verkeersToren) {

            case VERKEERSTOREN:

                Verkeerstoren verkeerstoren = new Verkeerstoren();
                Actor.verkeerstorens.add(verkeerstoren);               //static list in actor class
                return verkeerstoren;

        }

        return null;

    }
}
