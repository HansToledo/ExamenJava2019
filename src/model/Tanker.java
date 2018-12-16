package model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:58<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Tanker extends Actor implements ISchip,IHulpdienst,IStatusSubject {

    @Override
    public Coördinaten getLocatie() {
        return null;
    }

    @Override
    public void test() {

    }


    //region StatusObserver
    private List<Verkeerstoren> verkeerstorens;
    private LinkedList<ISchip> status;

    public void notifyVerkeerstorenObservers(){                                             //Naar alle observers een statusupdate versturen.
        ListIterator list = verkeerstorens.listIterator();
        while(list.hasNext()) ((Verkeerstoren) list.next()).statusUpdate(status);
    }
    public void addVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver){
        verkeerstorens.add(verkeerstorenObserver);                                          //lijst met observers wordt aangepast door toevoegen van observer.
    }
    public void removeVerkeerstorenObserver(Verkeerstoren verkeerstorenObserver){
        verkeerstorens.remove(verkeerstorenObserver);                                       //lijst met observers wordt aangepast door verwijderen van observer.
    }
    //endregion


}
