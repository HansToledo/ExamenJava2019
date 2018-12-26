package model;

import calculations.Coördinaten;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:58<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Zeilboot extends Schepen {

    public Zeilboot(){


    }


    public Zeilboot(String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy, String status) {

        super(enumNaam, naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);

    }


}
