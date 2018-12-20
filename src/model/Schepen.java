package model;

import enums.SOS;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import static enums.SOS.SOS_NOOD;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:37<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Schepen extends Vervoermiddel implements IStatusSubject {

    private SOS noodSignaal;
    private Set<IStatusObserver> verkeerstorens;

    public Schepen(){

    }

    public Schepen(Coördinaten coördinaten,double snelheid, double grootte, double capaciteit, int koers) {

        super(coördinaten,snelheid,grootte,capaciteit,koers);

    }

    @Override
    public void addVerkeerstorenObserver(IStatusObserver verkeerstoren) {
        verkeerstorens.add(verkeerstoren);
    }

    @Override
    public void removeVerkeerstorenObserver(IStatusObserver verkeerstoren) {
        verkeerstorens.remove(verkeerstoren);
    }
    @Override
    public void notifyVerkeerstorenObservers() {

        Iterator<IStatusObserver> it = verkeerstorens.iterator();

        while (it.hasNext()){

            IStatusObserver verkeerstoren = it.next();
            verkeerstoren.statusUpdate(noodSignaal);

        }

    }

    public void setNoodSignaal(SOS sos){

        this.noodSignaal = sos;
        notifyVerkeerstorenObservers();

    }

    //endregion

    @Override
    public String toString() {
        return "Schepen{} " + super.toString();
    }
}
