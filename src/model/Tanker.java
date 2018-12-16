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
public class Tanker extends Actor implements ISchip,IHulpdienst,IStatusSubject,INoodObserver {

    @Override
    public Coördinaten getLocatie() {
        return null;
    }

    @Override
    public double getAfstand(){ return 0;};

    @Override
    public double getSnelheid() {
        return 0;
    }

    @Override
    public double getReactieTijd() {
        return 0;
    }

    @Override
    public double getWendbaarheid() {
        return 0;
    }

    @Override
    public double getGrootte() {
        return 0;
    }

    @Override
    public double getCapaciteit() {
        return 0;
    }

    @Override
    public int getKoers() {
        return 0;
    }

    @Override
    public void setLocatie(Coördinaten locatie) {

    }

    @Override
    public void setSnelheid(double snelheid) {

    }

    @Override
    public void setGrootte(double grootte) {

    }

    @Override
    public void setCapaciteit(double capaciteit) {

    }

    @Override
    public void setKoers(int koers) {

    }

    @Override
    public void test() {
        System.out.println("Ik ben een tanker.");
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
