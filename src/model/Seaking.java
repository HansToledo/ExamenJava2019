package model;

import calculations.Coördinaten;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:05<br/>
 * Klasse om seaking aan te maken
 */
public class Seaking extends Vervoermiddel {



    public Seaking(){

    }

    public Seaking(String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy,String status) {

        super(enumNaam, naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);

    }

    @Override
    public double getWendbaarheid() {

        return (Math.random() * ((0.5 - 0.1) + 1)) + 0.1;
    }

}
