package model;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Verkeerstoren extends Actor implements INoodSubject, IStatusObserver{
    private Coördinaten coördinaten;

    public Verkeerstoren(){

    }

    public Verkeerstoren (Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {
        coördinaten = new Coördinaten();
        this.coördinaten = coördinaten;
        super.setHulpdienstStrategy(hulpdienstStrategy);
    }

    //region StatusObserver
    private LinkedList<Actor> statusSchip;

    public void statusUpdate(LinkedList<Actor> statusSchip){
        this.statusSchip = statusSchip;
    }
    //endregion

    @Override
    public String toString() {
        return "Verkeerstoren{" +
                "coördinaten=" + coördinaten +
                ", statusSchip=" + statusSchip +
                '}'  + super.toString() ;
    }
}
