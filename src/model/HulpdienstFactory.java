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
    public Vervoermiddel setHulpDienst(String enumNaam, String naam,Hulpdiensten hulpdienst,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers,IHulpdienstStrategy hulpdienstStrategy) {

        switch (hulpdienst) {

            case SCHEEPSVAARTPOLITIE:
                return new ScheepsvaartPolitie(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
            case SEAKING:
                return new Seaking(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
        }
        return null;
    }


    @Override
    public Vervoermiddel setSchip(String enumNaam, String naam,Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy) {

        return null;
    }


    @Override
    public Verkeerstoren setVerkeersToren(String enumNaam, String naam,Hulpdiensten verkeerstoren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {

        switch (verkeerstoren) {

            case VERKEERSTOREN:
                return new Verkeerstoren(enumNaam,naam,coördinaten,hulpdienstStrategy);
        }
        return null;
    }
}
