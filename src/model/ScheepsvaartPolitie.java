package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:04<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ScheepsvaartPolitie extends Vervoermiddel {

    public ScheepsvaartPolitie(){

    }

    public ScheepsvaartPolitie (String enumNaam, String naam, Coördinaten coördinaten, double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy) {

        super(enumNaam, naam, coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);

    }


    @Override
    public String toString() {
        return "ScheepsvaartPolitie{} " + super.toString();
    }
}
