package calculations;

import model.Actor;
import model.Verkeerstoren;
import model.Vervoermiddel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Klasse met method voor berekenen verkeerstoren dichtstebij en method voor lijst gesorteerd op reactiesnelheid (deze wordt gebruikt door de verkeerstoren klasse)
 */
public class SnelstTerPlaatse {

    public Verkeerstoren zoekVerkeerstorenDichtsbij(Vervoermiddel vervoermiddel) { //zoekt verkeerstoren het dichtste bij voor inschrijving schepen

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

    public  ArrayList<Vervoermiddel> zoekHulpdienstDichtsbij(Vervoermiddel vervoermiddel, ArrayList<Vervoermiddel> vervoermiddelenBeschikbaar) { //maakt gesorteerde lijst volgens reactiesnelheid

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


        for (Vervoermiddel item : vervoermiddelen){ //reactietijd berekenen in minuten

            double test= item.getWendbaarheid();
            item.setReactieTijd(Math.floor((item.getAfstand()/item.getSnelheid()*60)+((item.getWendbaarheid()))));

        }

        Collections.sort(vervoermiddelen, new Comparator<Vervoermiddel>() {
            @Override
            public int compare(Vervoermiddel o1, Vervoermiddel o2) {
                return Double.valueOf(o1.getReactieTijd()).compareTo(o2.getReactieTijd());  //lijst sorteren volgens reactiesnelheid
            }
        });

        return vervoermiddelen;

    }

}
