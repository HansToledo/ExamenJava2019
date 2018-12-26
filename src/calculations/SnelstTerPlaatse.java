package calculations;

import model.Actor;
import model.Verkeerstoren;
import model.Vervoermiddel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 23/12/2018<br/>
 * Time: 16:08<br/>
 * To change this template use File | Settings | File Templates.
 */
public class SnelstTerPlaatse {

    public Verkeerstoren zoekVerkeerstorenDichtsbij(Vervoermiddel vervoermiddel) {

        double afstandKortste = 0;
        double afstandBereken = 0;
        Verkeerstoren verkeerstorenKortste = new Verkeerstoren();
        GPSDistance berekenAfstand = new GPSDistance();
        Coördinaten coördinatenS = new Coördinaten();


        coördinatenS = vervoermiddel.getCoördinaten();
        double breedte = coördinatenS.getBreedte();
        double lengte = coördinatenS.getLengte();


        for (Verkeerstoren item : Actor.verkeerstorens) {    // Actor.verkeerstorens


            afstandBereken = berekenAfstand.GPSDistance(breedte, lengte, item.getCoördinaten().getBreedte(), item.getCoördinaten().getLengte());

            if (afstandKortste == 0.0) {

                afstandKortste = berekenAfstand.GPSDistance(breedte, lengte, item.getCoördinaten().getBreedte(), item.getCoördinaten().getLengte());

                verkeerstorenKortste = item;

            }

            if (afstandKortste > afstandBereken) {

                afstandKortste = berekenAfstand.GPSDistance(breedte, lengte, item.getCoördinaten().getBreedte(), item.getCoördinaten().getLengte());

                verkeerstorenKortste = item;

            }

        }


        return verkeerstorenKortste;
    }

    public  ArrayList<Vervoermiddel> zoekHulpdienstDichtsbij(Vervoermiddel vervoermiddel, ArrayList<Vervoermiddel> vervoermiddelenBeschikbaar) {

        ArrayList<Vervoermiddel> vervoermiddelen = new ArrayList<Vervoermiddel>();
        double afstandKortste = 0;
        double afstandBereken = 0;
        Vervoermiddel vervoermiddelKortste = vervoermiddel;
        GPSDistance berekenAfstand = new GPSDistance();
        Coördinaten coördinatenS = new Coördinaten();

        coördinatenS = vervoermiddel.getCoördinaten();
        double breedte = coördinatenS.getBreedte();
        double lengte = coördinatenS.getLengte();


        for (Vervoermiddel item : vervoermiddelenBeschikbaar) {    // Actor.verkeerstorens
            afstandBereken = berekenAfstand.GPSDistance(breedte, lengte, item.getCoördinaten().getBreedte(), item.getCoördinaten().getLengte());
            item.setAfstand(afstandBereken);
            vervoermiddelen.add(item);
        }


        for (Vervoermiddel item : vervoermiddelen){ //reactietijd berekenen
            item.setReactieTijd(Math.floor(item.getAfstand()/item.getSnelheid()+((item.getWendbaarheid()*3.6)/1.852))); //TODO <= Hans:IN ORDE ZO? data wendwaarheid tijd in minuten (1m/u = 3.6km/u), (1kmu/1.852 = 1 mijl per uur) + snelheid is in zeemijl/uur nog aanpassen
        }


        Collections.sort(vervoermiddelen, new Comparator<Vervoermiddel>() {
            @Override
            public int compare(Vervoermiddel o1, Vervoermiddel o2) {
                return Double.valueOf(o1.getReactieTijd()).compareTo(o2.getReactieTijd());  //lijst sorteren volgens afstand
            }
        });

        return vervoermiddelen;

    }

}
