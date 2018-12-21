package model;

import enums.Hulpdiensten;
import enums.Schepen;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 10:59<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractActorFactory {

    public abstract Vervoermiddel setHulpDienst(String naam,Hulpdiensten hulpdienst,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy);

    public abstract Vervoermiddel setSchip(String naam,Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers,IHulpdienstStrategy hulpdienstStrategy);

    public abstract Verkeerstoren setVerkeersToren(String naam,Hulpdiensten verkeerstoren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy);

}
