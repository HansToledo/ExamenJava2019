package model;

import calculations.Coördinaten;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * Deze klasse maakt een containerschip aan
 */
public class ContainerSchip extends Schepen {

    public ContainerSchip(){

    }

    public ContainerSchip(String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy,String status) {

        super(enumNaam, naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);

    }

    @Override
    public double getWendbaarheid() {

        return (Math.random() * ((30 - 10) + 1)) + 10;
    }


}
