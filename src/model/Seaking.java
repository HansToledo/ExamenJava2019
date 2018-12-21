package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:05<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Seaking extends Vervoermiddel {

    public Seaking(){

    }

    public Seaking(String naam,Coördinaten coördinaten,double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy) {

        super(naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);

    }

    @Override
    public String toString() {
        return "Seaking{} " + super.toString();
    }
}
