package calculations;

import model.Actor;
import model.Verkeerstoren;
import model.Vervoermiddel;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 23/12/2018<br/>
 * Time: 16:08<br/>
 * To change this template use File | Settings | File Templates.
 */
public class KortsteAfstand {

    public Verkeerstoren zoekVerkeerstorenDichtsbij(Vervoermiddel vervoermiddel){

        double afstandKortste = 0;
        double afstandBereken = 0;
        Verkeerstoren verkeerstorenKortste = new Verkeerstoren();
        GPSDistance berekenAfstand= new GPSDistance();
        Coördinaten coördinatenS = new Coördinaten();
        Coördinaten coördinatenVT = new Coördinaten();

        coördinatenS = vervoermiddel.getLocatie();
        double breedte = coördinatenS.getBreedte();
        double lengte = coördinatenS.getLengte();


        for (Verkeerstoren item : Actor.verkeerstorens) {    // Actor.verkeerstorens

            coördinatenVT = item.getLocatie();
            afstandBereken = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());

            if (afstandKortste == 0.0){

                afstandKortste = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());
                verkeerstorenKortste = item;
            }

            if (afstandKortste > afstandBereken){

                afstandKortste = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());
                verkeerstorenKortste = item;
            }

        }

        return verkeerstorenKortste;
    }

    public Vervoermiddel zoekHulpdienstDichtsbij (Vervoermiddel vervoermiddel){

        double afstandKortste = 0;
        double afstandBereken = 0;
        Vervoermiddel vervoermiddelKortste = vervoermiddel;
        GPSDistance berekenAfstand= new GPSDistance();
        Coördinaten coördinatenS = new Coördinaten();
        Coördinaten coördinatenVT = new Coördinaten();
        coördinatenS = vervoermiddel.getLocatie();
        double breedte = coördinatenS.getBreedte();
        double lengte = coördinatenS.getLengte();

        for (Vervoermiddel item : Actor.mogelijkeHulpdiensten) {    // Actor.verkeerstorens

            coördinatenVT = item.getLocatie();
            afstandBereken = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());

            if (afstandKortste == 0.0){

                afstandKortste = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());
                vervoermiddelKortste = item;
            }

            if (afstandKortste > afstandBereken){

                afstandKortste = berekenAfstand.GPSDistance(breedte,lengte,coördinatenVT.getBreedte(),coördinatenVT.getLengte());
               vervoermiddelKortste = item;
            }

        }

        return vervoermiddelKortste;


    }

}
