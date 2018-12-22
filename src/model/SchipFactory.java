package model;

import calculations.GPSDistance;
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
    public Vervoermiddel setHulpDienst(String enumNaam, String naam,Hulpdiensten hulpdienst,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers,IHulpdienstStrategy hulpdienstStrategy) {
        return null;
    }

    @Override
    public Vervoermiddel setSchip(String enumNaam, String naam, Schepen schip,Coördinaten coördinaten,double snelheid,double grootte,double capaciteit,int koers, IHulpdienstStrategy hulpdienstStrategy) {

        switch (schip) {

            case CONTAINERSCHIP:

                ContainerSchip containerSchip = new ContainerSchip(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
                Actor.mogelijkeHulpdiensten.add(containerSchip);
                Actor.schepenOpWater.add(containerSchip);

                //containerSchip.addStatusObserver(zoekVerkeerstorenDichtsbij(containerSchip)); //bereken welke toren kortste bij

                return containerSchip;

            case MOTORBOOT:

                Motorboot motorboot = new Motorboot(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
                Actor.mogelijkeHulpdiensten.add(motorboot);
                Actor.schepenOpWater.add(motorboot);
                return motorboot;

            case TANKER:

                Tanker tanker = new Tanker(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
                Actor.mogelijkeHulpdiensten.add(tanker);
                Actor.schepenOpWater.add(tanker);
                return tanker;

            case ZEILBOOT:

                Zeilboot zeilboot = new Zeilboot(enumNaam,naam,coördinaten,snelheid,grootte,capaciteit,koers,hulpdienstStrategy);
                Actor.mogelijkeHulpdiensten.add(zeilboot);
                Actor.schepenOpWater.add(zeilboot);
                return zeilboot;
        }
        return null;
    }
    
    public Verkeerstoren zoekVerkeerstorenDichtsbij(Vervoermiddel schepen){

        //afstand opslagen later voor getafstand()

        double afstandKortste = 0;
        double afstandBereken = 0;
        Verkeerstoren verkeerstorenKortste = new Verkeerstoren();
        GPSDistance berekenAfstand = new GPSDistance();
        Coördinaten coördinatenS = new Coördinaten();
        Coördinaten coördinatenVT;

        coördinatenS = schepen.getLocatie();
        double breedte = coördinatenS.getBreedte();
        double lengte = coördinatenS.getLengte();


        for (Verkeerstoren item : Actor.verkeerstorens) {

            coördinatenVT = new Coördinaten();
            coördinatenVT = item.getLocatie();
            afstandBereken = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());

            if (afstandKortste == 0.0){

                afstandKortste = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());
            }

            if (afstandKortste < afstandBereken){

                afstandKortste = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());

                verkeerstorenKortste = item;
            }

        }

        return verkeerstorenKortste;
    }

    @Override
    public Verkeerstoren setVerkeersToren(String enumNaam, String naam,Hulpdiensten verkeerstoren, Coördinaten coördinaten, IHulpdienstStrategy hulpdienstStrategy) {
        return null;
    }
}
