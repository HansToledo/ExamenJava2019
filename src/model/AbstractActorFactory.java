package model;

import calculations.Coördinaten;
import enums.Hulpdiensten;
import enums.Schepen;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 10:59<br/>
 * Abstracte klasse voor factories
 */
public abstract class AbstractActorFactory {

    public abstract Vervoermiddel setHulpDienst(String enumNaam, String naam, Hulpdiensten hulpdienst, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy,String status);

    public abstract Vervoermiddel setSchip(String enumNaam, String naam,Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers,IHulpdienstStrategy hulpdienstStrategy,String status);

    public abstract Verkeerstoren setVerkeersToren(String enumNaam, String naam,Hulpdiensten verkeerstoren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy);

}
