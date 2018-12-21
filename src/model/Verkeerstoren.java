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
    private String naam;
    private Coördinaten coördinaten;

    public Verkeerstoren(){

    }

    public Verkeerstoren (String naam,Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {
        coördinaten = new Coördinaten();
        this.naam = naam;
        this.coördinaten = coördinaten;
        super.setHulpdienstStrategy(hulpdienstStrategy);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
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
                "naam=" + naam +
                ", coördinaten=" + coördinaten +
                ", statusSchip=" + statusSchip +
                '}'  + super.toString() ;
    }
}
