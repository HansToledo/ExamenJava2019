package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:56<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Motorboot extends Schepen {

    public Motorboot(){


    }

    public Motorboot(String enumNaam, String naam,Coördinaten coördinaten,double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy) {

        super(enumNaam, naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);

    }

    @Override
    public String toString() {
        return "Motorboot{} " + super.toString();
    }



}
