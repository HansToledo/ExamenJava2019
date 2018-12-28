package model;

import calculations.Coördinaten;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:59<br/>
 * Deze klasse maakt een containerschip aan
 */
public class ContainerSchip extends Schepen {

    public ContainerSchip(){

    }

    public ContainerSchip(String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy,String status) {

        super(enumNaam, naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);

    }


}
