package model;

import calculations.Coördinaten;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:04<br/>
 * Klasse voor scheepsvaartpolitie aan te maken
 */
public class ScheepsvaartPolitie extends Vervoermiddel {

    public ScheepsvaartPolitie(){

    }

    public ScheepsvaartPolitie (String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy, String status) {
        super(enumNaam, naam, coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
    }

}
