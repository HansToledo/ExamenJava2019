package model;

import enums.SOS;

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

    public Verkeerstoren (Coördinaten coördinaten,IHulpdienstStrategy hulpdienstStrategy) {
        coördinaten = new Coördinaten();
        this.coördinaten = coördinaten;
        super.setHulpdienstStrategy(hulpdienstStrategy);
    }


    private SOS noodsignaal;

    //region StatusObserver

    public void statusUpdate(SOS noodsignaal){

       this.noodsignaal = noodsignaal;

    }
    //endregion
     @Override
     public void addNoodObserver(INoodObserver noodObserver){

     };
     @Override
     public void removeNoodObserver(INoodObserver noodObserver){

     };
      @Override
     public void deNotify(){};

    @Override
    public String toString() {
        return "Verkeerstoren{" +
                "coördinaten=" + coördinaten +
                ", noodsignaal=" + noodsignaal +
                "} " + super.toString();
    }
}
