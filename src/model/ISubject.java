package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:45<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface ISubject {
    public void addVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver);
    public void removeVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver);
    public void notifyObservers();
}
