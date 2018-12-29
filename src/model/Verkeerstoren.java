package model;

import calculations.Coördinaten;
import calculations.SnelstTerPlaatse;
import enums.StatusVervoermiddel;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import strategy.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:38<br/>
 * Klasse voor verkeerstorens aan te maken, deze ontvangt het noodsignaal via statusobserver en stuurt signaal naar hulpdiensten
 */
public class Verkeerstoren extends Actor implements INoodSubject, IStatusObserver {

    private String enumNaam;
    private String naam;
    private Coördinaten coördinaten;
    private ArrayList<INoodObserver> hulpdiensten = new ArrayList<INoodObserver>();
    private ArrayList<Vervoermiddel> vervoermiddelKortstebij = new ArrayList<Vervoermiddel>();
    private ArrayList<Vervoermiddel> beschikbareHulpdiensten = new ArrayList<Vervoermiddel>();
    public ArrayList<Vervoermiddel> Redders = new ArrayList<Vervoermiddel>();


    public Verkeerstoren() {

    }

    public Verkeerstoren(String enumNaam, String naam, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {

        //this.coördinaten = coördinaten;
        setCoördinaten(coördinaten);
        //this.enumNaam = enumNaam;
        setEnumNaam(enumNaam);
        //this.naam = naam;
        setNaam(naam);
        super.setHulpdienstStrategy(hulpdienstStrategy);
    }

    public void setNaam(String naam) {

        if (!naam.isEmpty()) {

            this.naam = naam;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen naam ingegeven");
        }

    }

    public String getNaam() {

        return naam;
    }

    public void setCoördinaten(Coördinaten coördinaten) {

        if (coördinaten.getBreedte() < 90 && coördinaten.getBreedte() > -90 && coördinaten.getLengte() < 180 && coördinaten.getLengte() > -180) {

            this.coördinaten = coördinaten;

        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen geldige coordinaten ingegeven limieten zijn voor latitude -90/90 en voor longitude -180/180");
        }
    }

    public Coördinaten getCoördinaten() {

        return coördinaten;
    }

    public void setEnumNaam(String enumNaam) {

        if (!enumNaam.isEmpty()) {

            this.enumNaam = enumNaam;
        } else {

            throw new IllegalArgumentException("Opslaan mislukt, geen EnumNaam ingegeven");
        }

    }

    public String getEnumNaam() {

        return enumNaam;
    }


    //region StatusObserver
    @Override
    public void doUpdate(StatusVervoermiddel statusSchip, Schepen schipInNood) {

        if (statusSchip == StatusVervoermiddel.OK) {
            EventLogger.logger.info(String.format("Schip ok"));
            //System.out.println("Schip ok");
        }

        if (statusSchip != StatusVervoermiddel.OK) {
            SnelstTerPlaatse snelstTerPlaatse = new SnelstTerPlaatse(); //klasse voor berekenen van afstand en snelheid gesorteerd volgens reactiesnelheid
            Redders.clear();
            zoekBeschikbareHulpdienst(schipInNood.getNaam());
            vervoermiddelKortstebij = snelstTerPlaatse.zoekHulpdienstDichtsbij(schipInNood, beschikbareHulpdiensten); //list gesorteerd volgens reactiesnelheid

            EventLogger.logger.info(String.format("Schip in nood (signaal ontvangen door statusobserver) " + schipInNood + " ontvangen door verkeerstoren: " + this.naam + " Noodsignaal is : " + statusSchip));
            //System.out.println("Schip in nood(signaal ontvangen door statusobserver) " + schipInNood + " ontvangen door verkeerstoren: " + this.naam + " Noodsignaal is : " + statusSchip);
            if (vervoermiddelKortstebij.get(0).getGrootte() < schipInNood.getCapaciteit()) {  // controle of capaciteit redder voldoende is anders extra schip voorzien

                int totaleGrootte = 0;
                int i = 0;

                while (totaleGrootte < schipInNood.getCapaciteit() && i < vervoermiddelKortstebij.size()) {
                    totaleGrootte += (vervoermiddelKortstebij.get(i).getGrootte() - vervoermiddelKortstebij.get(i).getCapaciteit());
                    Redders.add(vervoermiddelKortstebij.get(i));
                    ++i;
                }
            } else {
                Vervoermiddel schipKortsteBij = vervoermiddelKortstebij.get(0);
                Redders.add(schipKortsteBij);
            }
        }
    }
    //endregion

    //TODO exceptions voorzien

    public boolean zoekBeschikbareHulpdienst(String naam) {            //controle van beschikbare hulpdiensten volgens status

        beschikbareHulpdiensten.clear();
        try {
            for (Vervoermiddel item : Actor.mogelijkeHulpdiensten) {
                String itemNaam = item.getNaam();
                String itemStatus = item.getStatus();
                String enumStatusOK = StatusVervoermiddel.OK.toString();


                if (itemNaam != naam && itemStatus.equals(enumStatusOK)) {

                    beschikbareHulpdiensten.add(item);
                }

            }
            return true;
        } catch (Exception ex) {

            System.out.print(ex.getMessage());
            return false;
        }

    }

    //region NoodObserver
    @Override
    public void doNotifyNoodObserver(IHulpdienstStrategy reddingsType, Coördinaten coördinaten, String naam) {

        Iterator<Vervoermiddel> it = Redders.iterator();

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


