package model;

import calculations.Coördinaten;
import enums.Hulpdiensten;
import enums.Schepen;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * Abstracte klasse voor factories
 */
public abstract class AbstractActorFactory {

    public abstract Vervoermiddel setHulpDienst(String enumNaam, String naam, Hulpdiensten hulpdienst, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy,String status);

    public abstract Vervoermiddel setSchip(String enumNaam, String naam,Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers,IHulpdienstStrategy hulpdienstStrategy,String status);

    public abstract Verkeerstoren setVerkeersToren(String enumNaam, String naam,Hulpdiensten verkeerstoren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy);

}
