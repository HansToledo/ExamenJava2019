package model;

import calculations.Coördinaten;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:56<br/>
 * Klasse voor motorboot aan te maken
 */
public class Motorboot extends Schepen {


    public Motorboot(){


    }

    public Motorboot(String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy, String status) {

        super(enumNaam, naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);

    }

    @Override
    public double getWendbaarheid() {

        return (Math.random() * ((2 - 1) + 1)) + 1;
    }

}
