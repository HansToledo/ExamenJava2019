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
public class Verkeerstoren extends Actor implements INoodSubject, IStatusObserver {

    private String enumNaam;
    private String naam;
    private Coördinaten coördinaten;
    private ArrayList<INoodObserver> hulpdiensten = new ArrayList<INoodObserver>(); //TODO bekijken
    private IHulpdienstStrategy reddingsType;
    private ArrayList<Vervoermiddel> vervoermiddelKorstebij = new ArrayList<Vervoermiddel>();
    private   ArrayList<Vervoermiddel> beschikbareHulpdiensten = new ArrayList<Vervoermiddel>();

    public Verkeerstoren() {

    }

    public Verkeerstoren(String enumNaam, String naam, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {

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
    public void doUpdate(StatusVervoermiddel statusSchip, Schepen schipInNood) {

        if (statusSchip == StatusVervoermiddel.OK) {

            System.out.println("Schip ok");
            // strategy melding strategy meegeven
        }


        if (statusSchip != StatusVervoermiddel.OK) {

            //ArrayList<Vervoermiddel> beschikbareHulpdiensten = new ArrayList<Vervoermiddel>();

            BrandStrategy brandStrategy = new BrandStrategy(); // als test
            KortsteAfstand kortsteAfstand = new KortsteAfstand();

            zoekBeschikbareHulpdienst(schipInNood.getNaam());
            vervoermiddelKorstebij = kortsteAfstand.zoekHulpdienstDichtsbij(schipInNood,beschikbareHulpdiensten); //list gesorteerd volgens reactiesnelhied in list + afstand


          if (vervoermiddelKorstebij.get(0).getCapaciteit() > schipInNood.getCapaciteit()){ // zorgen voor remove uit list => list naar waar versturen nog niet helemaal correct

              Vervoermiddel test = vervoermiddelKorstebij.get(0);
              vervoermiddelKorstebij.clear();
              vervoermiddelKorstebij.add(test);
              //doNotifyNoodObserver(brandStrategy, coördinaten, naam);

          }

          //TODO momenteel maar 1 schip in nood per button klik
            //TODO rekeing houden met strategy volgens type nood keuze maken in gui
            //TODO coordinaten lijken at runtime te wijzigen => bekijken
            //TODO tijd berekenen volgens snelheid afstand en wendbaarheid => done, nog testen
            //TODO capaciteiten vergelijken
            //TODO versturen naar alle observers + statergy naar 1 of meer
            //TODO bij aanmaak nieuw schip at runtime ook inoodobserver voorzienq
            //TODO tijden omrekenen naar minuten
            //TODO later eventueel observable list actor voor schrijven naar database??
            //TODO add + remove observer


            System.out.println("Schip in nood " + schipInNood + " ontvangen door verkeerstoren: " + this.naam + " Noodsignaal is : " + statusSchip );

            doNotifyNoodObserver(brandStrategy, coördinaten, naam); //kan ook naar al de observers verstuurd worden
            // deze moet andere observer aansturen
        }
    }

    //endregion

    public void zoekBeschikbareHulpdienst(String naam) {

        //ArrayList<Vervoermiddel> beschikbareHulpdiensten = new ArrayList<Vervoermiddel>();

        for (Vervoermiddel item : Actor.mogelijkeHulpdiensten) {

            if (item.getNaam() != naam && item.getStatus() == StatusVervoermiddel.OK.toString()) {

                beschikbareHulpdiensten.add(item);
            }

        }

        //return beschikbareHulpdiensten;
    }

    @Override
    public void addNoodObserver(INoodObserver noodObserver) {

        hulpdiensten.add(noodObserver); //TODO bekijken

    }

    //region NoodObserver

    @Override
    public void removeNoodObserver(INoodObserver noodObserver) {

        hulpdiensten.remove(noodObserver); //TODO bekijken
    }

    @Override
    public void doNotifyNoodObserver(IHulpdienstStrategy reddingsType, Coördinaten coördinaten, String naam) {


        Iterator<Vervoermiddel> it = vervoermiddelKorstebij.iterator();

        while (it.hasNext()) {

            INoodObserver hulpdienst = it.next();
            hulpdienst.ontvangNoodsignaal(reddingsType, coördinaten, naam);

        }

    }

    //endregion

    @Override
    public String toString() {
        return naam;
    }


}


