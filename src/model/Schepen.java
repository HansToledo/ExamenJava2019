package model;

import states.ISchipState;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:37<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Schepen extends Voertuigen implements IStatusSubject, ISchipState {
    @Override
    public void addVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver) {

    }

    @Override
    public void removeVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver) {

    }

    @Override
    public void notifyVerkeerstorenObservers() {

    }
}
