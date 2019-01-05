package calculations;

import model.EventLogger;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: Klasse voor berekenen van afstanden uitgedrukt in zeemijl
 */
public class GPSDistance {

    public static double GPSDistance(double breedteX, double lengteX, double breedteY, double lengteY) {
        //Latitude = breedte
        //Longitude = lengte
        double afstand = 0;
        try {
            final double Radius = 6371; // radius van de aarde is 6371km
            double LatX = Math.toRadians(breedteX);
            double LatY = Math.toRadians(breedteY);
            double differenceLat = Math.toRadians(breedteY - breedteX);
            double differenceLon = Math.toRadians(lengteY - lengteX);

            double a = Math.sin(differenceLat / 2) * Math.sin(differenceLat / 2) + Math.cos(LatY) * Math.cos(LatX) * Math.sin(differenceLon / 2) * Math.sin(differenceLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            afstand = (Radius * c) / 1.852; //uitgedruk in zeemijl dus / 1.852km

        } catch (Exception e) {
            EventLogger.logger.error(String.format(e.getMessage()));
            e.printStackTrace();
        }
        return afstand;
    }

}