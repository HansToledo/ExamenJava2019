package model;

import calculations.Coördinaten;
import enums.StatusVervoermiddel;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:38<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Verkeerstoren extends Actor implements INoodSubject, IStatusObserver{

    private String enumNaam;
    private String naam;
    private Coördinaten coördinaten;

    public Verkeerstoren(){

    }

    public Verkeerstoren (String enumNaam, String naam,Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {

        coördinaten = new Coördinaten();
        this.enumNaam = enumNaam;
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

    public String getEnumNaam() {
        return enumNaam;
    }

    public void setEnumNaam(String enumNaam) {
        this.enumNaam = enumNaam;
    }

    public Coördinaten getCoördinaten() {
        return coördinaten;
    }


//region StatusObserver

    @Override
    public void doUpdate(StatusVervoermiddel statusSchip, Coördinaten coördinaten, String naam) {

        if (statusSchip == StatusVervoermiddel.OK) {

            System.out.println("Schip ok");
        }


        if (statusSchip != StatusVervoermiddel.OK) {

            System.out.println("Schip in nood" + naam + "ontvangen door verkeerstoren: " + this.naam + "Noodsignaal is : " + statusSchip);
            // deze moet andere observer aansturen
        }
    }

    //endregion

    @Override
    public String toString() {
        return "Verkeerstoren{" +
                "naam=" + naam +
                ", coördinaten=" + coördinaten +
                ", statusSchip=" +
                '}'  + super.toString() ;
    }


}
