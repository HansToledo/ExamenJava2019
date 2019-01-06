package model;

import calculations.Coördinaten;
import enums.StatusVervoermiddel;

import java.util.*;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * Abstracte schepen klasse
 */
public abstract class Schepen extends Vervoermiddel implements IStatusSubject {

    private ArrayList<IStatusObserver> verkeerstorens = new ArrayList<IStatusObserver>();  //lijst word gebruikt door statusobser, lijst met verkeerstoren. Wel telkens maar 1. Deze dichtste bij.
    private StatusVervoermiddel noodSignaal;


    public Schepen() {

    }

    public Schepen(String enumNaam, String naam, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy, String status) {

        super(enumNaam, naam, coördinaten, snelheid, grootte, capaciteit, koers, hulpdienstStrategy, status);

    }
//region STATUSOBSERVER
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
            verkeerstoren.doUpdate(noodSignaal, this);
        }

    }

    public void setNoodSignaal(StatusVervoermiddel nieuwNoodSignaal) {

        this.noodSignaal = nieuwNoodSignaal;
        super.setStatus(nieuwNoodSignaal.toString());
        doNotifyStatusObservers();

    }

    //EndRegion STATUSOBSERVER

    public StatusVervoermiddel getNoodSignaal() {

        return this.noodSignaal;
    }

    public IStatusObserver getVerkeerstorenIngeschreven() { //getter verkeerstoren dichtste bij voor schip

        return this.verkeerstorens.get(0);
    }
}
