package model;

import calculations.Coördinaten;
import calculations.GPSDistance;
import calculations.SnelstTerPlaatse;
import enums.Hulpdiensten;
import enums.Schepen;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 13:29<br/>
 * To change this template use File | Settings | File Templates.
 */
public class SchipFactory extends AbstractActorFactory{
    
    private GPSDistance afstand = new GPSDistance();
    
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

                verkeerstoren = snelstTerPlaatse.zoekVerkeerstorenDichtsbij(containerSchip);

                containerSchip.addStatusObserver(verkeerstoren); //bereken welke toren kortste bij

                return containerSchip;

            case MOTORBOOT:

                Motorboot motorboot = new Motorboot(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(motorboot);
                Actor.schepenOpWater.add(motorboot);

                verkeerstoren = snelstTerPlaatse.zoekVerkeerstorenDichtsbij(motorboot);


                motorboot.addStatusObserver(verkeerstoren);

                return motorboot;

            case TANKER:

                Tanker tanker = new Tanker(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(tanker);
                Actor.schepenOpWater.add(tanker);

                verkeerstoren = snelstTerPlaatse.zoekVerkeerstorenDichtsbij(tanker);


                tanker.addStatusObserver(verkeerstoren);

                return tanker;

            case ZEILBOOT:

                Zeilboot zeilboot = new Zeilboot(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy,status);
                Actor.mogelijkeHulpdiensten.add(zeilboot);
                Actor.schepenOpWater.add(zeilboot);

                verkeerstoren = snelstTerPlaatse.zoekVerkeerstorenDichtsbij(zeilboot);



                zeilboot.addStatusObserver(verkeerstoren);

                return zeilboot;
        }
        return null;
    }
    

    @Override
    public Verkeerstoren setVerkeersToren(String enumNaam, String naam,Hulpdiensten verkeerstoren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {
        return null;
    }
}
