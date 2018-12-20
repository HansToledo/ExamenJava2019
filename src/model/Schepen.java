package model;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:37<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Schepen extends Vervoermiddel{

    public Schepen(){

    }

    public Schepen(Coördinaten coördinaten,double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy) {

        super(coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);

    }

    @Override
    public String toString() {
        return "Schepen{} " + super.toString();
    }
}
