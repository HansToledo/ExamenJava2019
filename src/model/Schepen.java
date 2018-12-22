package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:37<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Schepen extends Vervoermiddel implements IStatusSubject{

    private List<Actor> verkeerstorens = new ArrayList<Actor>();
    private LinkedList<Actor> statusUpdate = new LinkedList<Actor>();

    public Schepen(){

    }

    public Schepen(String enumNaam, String naam, Coördinaten coördinaten, double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy) {

        super(enumNaam, naam, coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);

    }

    @Override
    public void notifyVerkeerstorenObservers(String status) {
        ListIterator list = verkeerstorens.listIterator();
        while (list.hasNext()) ((Verkeerstoren) list.next()).statusUpdate(statusUpdate);
    }

    @Override
    public void addVerkeerstorenObserver(Actor verkeerstoren) {
        verkeerstorens.add(verkeerstoren);
    }

    @Override
    public void removeVerkeerstorenObserver(Actor verkeerstoren) {
        verkeerstorens.remove(verkeerstoren);
    }
    //endregion

    @Override
    public String toString() {
        return "Schepen{} " + super.toString();
    }
}
