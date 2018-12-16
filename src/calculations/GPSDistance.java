package calculations;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 17:31<br/>
 * To change this template use File | Settings | File Templates.
 */
public class GPSDistance {

    public double GPSDistance(double breedteX, double lengteX, double breedteY, double lengteY) {
        //Latitude = breedte
        //Longitude = lengte
        double afstand = 0;
        try {
            double Radius = 6371; // radius van de aarde is 6371km
            double LatX = Math.toRadians(breedteX);
            double LatY = Math.toRadians(breedteY);
            double differenceLat = Math.toRadians(breedteX - breedteY);
            double differenceLon = Math.toRadians(lengteX - lengteY);

            double a = Math.sin(differenceLat / 2) * Math.sin(differenceLat / 2) + Math.cos(LatY) * Math.cos(LatX) * Math.sin(differenceLon / 2) * Math.sin(differenceLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            afstand = (Radius * c) / 1.852; //uitgedruk in zeemijl dus / 1.852km

        } catch (Exception e) {
            e.printStackTrace();
        }
        return afstand;
    }
}
