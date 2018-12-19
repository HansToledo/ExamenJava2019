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

    public abstract Vervoermiddel setHulpDienst(Hulpdiensten hulpdienst,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers);

    public abstract Vervoermiddel setSchip(Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers);

    public abstract Verkeerstoren setVerkeersToren(Hulpdiensten verkeerstoren);

}
