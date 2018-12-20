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
    public Vervoermiddel setHulpDienst(Hulpdiensten hulpdienst,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers,IHulpdienstStrategy hulpdienstStrategy) {

        switch (hulpdienst) {

            case SCHEEPSVAARTPOLITIE:

                ScheepsvaartPolitie scheepsvaartPolitie = new ScheepsvaartPolitie(coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
                Actor.mogelijkeHulpdiensten.add(scheepsvaartPolitie);
                return scheepsvaartPolitie;

            case SEAKING:

                Seaking seaking = new Seaking(coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
                Actor.mogelijkeHulpdiensten.add(seaking);
                return seaking;
        }

        return null;
    }


    @Override
    public Vervoermiddel setSchip(Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers,IHulpdienstStrategy hulpdienstStrategy) {

        return null;
    }


    @Override
    public Verkeerstoren setVerkeersToren(Hulpdiensten verkeersToren,Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {

        switch (verkeersToren) {

            case VERKEERSTOREN:

                Verkeerstoren verkeerstoren = new Verkeerstoren(coördinaten,hulpdienstStrategy);
                Actor.verkeerstorens.add(verkeerstoren);               //static list in actor class
                return verkeerstoren;

        }

        return null;

    }
}
