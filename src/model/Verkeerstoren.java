package model;

import calculations.Coördinaten;
import calculations.KortsteAfstand;
import enums.StatusVervoermiddel;
import strategy.*;

import java.util.ArrayList;
import java.util.Iterator;

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
    private ArrayList<INoodObserver> hulpdiensten = new ArrayList<INoodObserver>();
    private IHulpdienstStrategy reddingsType;

    public Verkeerstoren(){

    }

    public Verkeerstoren (String enumNaam, String naam,Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {

        this.coördinaten = coördinaten;
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
            // strategy melding strategy meegeven
        }


        if (statusSchip != StatusVervoermiddel.OK) {

            ArrayList<Vervoermiddel> beschikbareHulpdiensten = new ArrayList<Vervoermiddel>();
            BrandStrategy brandStrategy = new BrandStrategy(); // als test
            KortsteAfstand kortsteAfstand = new KortsteAfstand();
            Vervoermiddel vervoermiddel;

            beschikbareHulpdiensten = zoekBeschikbareHulpdienst(naam); //TODO rekeing houden met strategy volgens type nood

            for (Vervoermiddel item : beschikbareHulpdiensten){

                vervoermiddel = kortsteAfstand.zoekHulpdienstDichtsbij(item); //niet juist

            }


            System.out.println("Schip in nood" + naam + "ontvangen door verkeerstoren: " + this.naam + "Noodsignaal is : " + statusSchip);
            doNotifyNoodObserver(brandStrategy,coördinaten,naam);
            // deze moet andere observer aansturen
        }
    }

    //endregion

    public ArrayList<Vervoermiddel>  zoekBeschikbareHulpdienst(String naam){

        ArrayList<Vervoermiddel> beschikbareHulpdiensten = new ArrayList<Vervoermiddel>();

        for(Vervoermiddel item : Actor.mogelijkeHulpdiensten){

            if (item.getNaam() != naam && item.getStatus() == StatusVervoermiddel.OK.toString()){ //TODO getstatus enum teruggeven statsusen in klasse nog controleren moeten ok zijn bij start en aanpassen indien niet ok actor list

                beschikbareHulpdiensten.add(item);
            }

        }

        return beschikbareHulpdiensten;
    }

    @Override
    public void addNoodObserver(INoodObserver noodObserver) {

        hulpdiensten.add(noodObserver);

    }

    //region NoodObserver

    @Override
    public void removeNoodObserver(INoodObserver noodObserver) {

        hulpdiensten.remove(noodObserver);
    }

    @Override
    public void doNotifyNoodObserver(IHulpdienstStrategy reddingsType, Coördinaten coördinaten,String naam) {

        Iterator<Vervoermiddel> it = Actor.mogelijkeHulpdiensten.iterator();

        while (it.hasNext()) {

            INoodObserver hulpdienst = it.next();
            hulpdienst.ontvangNoodsignaal(reddingsType, coördinaten,naam);

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


