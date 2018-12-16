package model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Verkeerstoren extends Actor implements IHulpdienst,ISubject{
    @Override
    public Coördinaten getLocatie() {
        return null;
    }


    @Override
    public void test() {

    }


    //region Observer Code
    public void notifyObservers(){
        //Naar alle observers een statusupdate versturen.
    }
    public void addVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver){
        //lijst met observers wordt aangepast door toevoegen van observer.
    }
    public void removeVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver){
        //lijst met observers wordt aangepast door verwijderen van observer.
    }
    //endregion


}
