package model;

import states.ISchipState;

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
public class Schepen extends Voertuigen implements IStatusSubject, ISchipState {
    //region StatusObserver
    private List<Actor> verkeerstorens;
    private LinkedList<ISchip> status;

    @Override
    public void notifyVerkeerstorenObservers() {
        ListIterator list = verkeerstorens.listIterator();
        while (list.hasNext()) ((Verkeerstoren) list.next()).statusUpdate(status);
    }

    @Override
    public void addVerkeerstorenObserver(Actor actor) {
        verkeerstorens.add(actor);
    }

    @Override
    public void removeVerkeerstorenObserver(Actor actor) {
        verkeerstorens.remove(actor);
    }
    //endregion
}
