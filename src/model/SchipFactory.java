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
public class SchipFactory extends AbstractActorFactory{
    @Override
    public Vervoermiddel setHulpDienst(String naam,Hulpdiensten hulpdienst,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers,IHulpdienstStrategy hulpdienstStrategy) {
        return null;
    }

    @Override
    public Vervoermiddel setSchip(String naam, Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy) {

        switch (schip) {

            case CONTAINERSCHIP:
                return new ContainerSchip(naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
            case MOTORBOOT:
                return new Motorboot(naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
            case TANKER:
                return new Tanker(naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
            case ZEILBOOT:
                return new Zeilboot(naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
        }
        return null;
    }

    @Override
    public Verkeerstoren setVerkeersToren(String naam,Hulpdiensten verkeerstoren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {
        return null;
    }
}
