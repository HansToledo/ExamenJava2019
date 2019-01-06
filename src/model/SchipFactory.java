package model;

import calculations.Coördinaten;
import calculations.GPSDistance;
import calculations.SnelstTerPlaatse;
import enums.Hulpdiensten;
import enums.Schepen;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * Factory voor het aanmaken van schepen, toevoegen van schepen aan lijsten in actor klasse, kijken welke verkeertoren dichtste bij en inschrijven bij verkeertoren dichtste bij
 */
public class SchipFactory extends AbstractActorFactory{

    @Override
    public Vervoermiddel setHulpDienst(String enumNaam, String naam, Hulpdiensten hulpdienst, Coördinaten coördinaten, double snelheid, double grootte, double capaciteit, int koers, IHulpdienstStrategy hulpdienstStrategy, String status) {
        return null;
    }

    @Override
    public Vervoermiddel setSchip(String enumNaam, String naam, Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy,String status) {

        SnelstTerPlaatse snelstTerPlaatse = new SnelstTerPlaatse();
        Verkeerstoren verkeerstoren = new Verkeerstoren();

        switch (schip) {

            case CONTAINERSCHIP:

                ContainerSchip containerSchip = new ContainerSchip(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(containerSchip);
                Actor.schepenOpWater.add(containerSchip);
                verkeerstoren = snelstTerPlaatse.zoekVerkeerstorenDichtsbij(containerSchip);   //bereken welke verkeerstoren kortste bij
                containerSchip.addStatusObserver(verkeerstoren);                               //deze verkeerstoren toevoegen aan statusobserverlijst, 1 op 1 relatie

                return containerSchip;

            case MOTORBOOT:

                Motorboot motorboot = new Motorboot(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(motorboot);
                Actor.schepenOpWater.add(motorboot);
                verkeerstoren = snelstTerPlaatse.zoekVerkeerstorenDichtsbij(motorboot);         //bereken welke verkeerstoren kortste bij
                motorboot.addStatusObserver(verkeerstoren);                                     //deze verkeerstoren toevoegen aan statusobserverlijst, 1 op 1 relatie

                return motorboot;

            case TANKER:

                Tanker tanker = new Tanker(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(tanker);
                Actor.schepenOpWater.add(tanker);
                verkeerstoren = snelstTerPlaatse.zoekVerkeerstorenDichtsbij(tanker);            //bereken welke verkeerstoren kortste bij
                tanker.addStatusObserver(verkeerstoren);                                        //deze verkeerstoren toevoegen aan statusobserverlijst, 1 op 1 relatie

                return tanker;

            case ZEILBOOT:

                Zeilboot zeilboot = new Zeilboot(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(zeilboot);
                Actor.schepenOpWater.add(zeilboot);
                verkeerstoren = snelstTerPlaatse.zoekVerkeerstorenDichtsbij(zeilboot);        //bereken welke verkeerstoren kortste bij
                zeilboot.addStatusObserver(verkeerstoren);                                     //deze verkeerstoren toevoegen aan statusobserverlijst, 1 op 1 relatie

                return zeilboot;
        }
        return null;
    }
    

    @Override
    public Verkeerstoren setVerkeersToren(String enumNaam, String naam,Hulpdiensten verkeerstoren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {
        return null;
    }
}
