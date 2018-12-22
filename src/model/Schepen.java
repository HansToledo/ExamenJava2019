package model;

import calculations.Coördinaten;
import enums.StatusVervoermiddel;

import java.util.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:37<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Schepen extends Vervoermiddel implements IStatusSubject {

    private ArrayList<IStatusObserver> verkeerstorens = new ArrayList<IStatusObserver>();
    private StatusVervoermiddel noodSignaal;

    public Schepen() {

    }

    public Schepen(String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy) {

        super(enumNaam, naam, coördinaten, snelheid, grootte, capaciteit, koers, hulpdienstStrategy);

    }

    @Override
    public void addStatusObserver(IStatusObserver statusObserver) {

        verkeerstorens.add(statusObserver);
    }

    @Override
    public void removeStatusObserver(IStatusObserver statusObserver) {
        verkeerstorens.remove(statusObserver);
    }

    @Override
    public void doNotifyStatusObservers() {

        Iterator<IStatusObserver> it = verkeerstorens.iterator();

        while (it.hasNext()) {

            IStatusObserver verkeerstoren = it.next();
            verkeerstoren.doUpdate(noodSignaal, getLocatie(), getNaam());

        }

    }

    public void setNoodSignaal(StatusVervoermiddel noodSignaal) {

        doNotifyStatusObservers();

    }

    @Override
    public String toString() {
        return "Schepen{} " + super.toString();
    }
}
