package model;

import calculations.Coördinaten;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
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
