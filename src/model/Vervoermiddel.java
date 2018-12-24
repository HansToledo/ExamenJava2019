package model;

import calculations.Coördinaten;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:17<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Vervoermiddel extends Actor implements INoodObserver{
    private String enumNaam;
    private String naam;
    private double afstand;
    private double snelheid;
    private double reactieTijd;
    private double wendbaarheid;
    private double grootte;
    private double capaciteit;
    private int koers;
    private Coördinaten coördinaten;
    private String status = "OK";
    private int ID;

    public Vervoermiddel(){

    }

    public Vervoermiddel (String enumNaam, String naam, Coördinaten coördinaten, double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy){
        this.enumNaam = enumNaam;
        this.naam = naam;
        this.coördinaten = coördinaten;
        this.snelheid = snelheid;
        this.grootte = grootte;
        this.capaciteit = capaciteit;
        this.koers = koers;
        this.ID = ID;
        super.setHulpdienstStrategy(hulpdienstStrategy);
    }


    //region Getters & Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnumNaam(){
        return this.enumNaam;
    }

    public Coördinaten getCoördinaten() {
        return coördinaten;
    }

    public String getNaam() {

        return this.naam;
    }

    public double getAfstand() {

        return this.afstand;
    }


    public double getSnelheid() { // snelheid in knopen containerschip

        return this.snelheid;
    }

    public double getReactieTijd() {

        return this.reactieTijd;
    }


    public double getWendbaarheid() {

        return this.wendbaarheid;
    }


    public double getGrootte() {

        return this.grootte;

    }


    public double getCapaciteit() {

        return this.capaciteit;
    }


    public int getKoers() {

        return this.koers;
    }

    public void setEnumNaam(){

        this.enumNaam = enumNaam;
    }

    public void setNaam() {

        this.naam = naam;
    }

    public void setLocatie() {

    }

    public void setSnelheid(double snelheid) {

        this.snelheid = snelheid;

    }

    public void setGrootte(double grootte) {

        this.grootte = grootte;

    }

    public void setCapaciteit(double capaciteit) {

        this.capaciteit = capaciteit;

    }

    public void setKoers(int koers) {

        this.koers = koers;

    }
    //endregion


    @Override
    public void ontvangNoodsignaal(IHulpdienstStrategy reddingsType, Coördinaten coördinaten,String naam){

        System.out.println("Noodsignaal ontvangen door hulpdienst: " + this.getNaam()); //ALS TEST

    }

    @Override
    public String toString() {
        return naam;
    }
}

