package model;

/**
 * @Autor: Mathy Paesen
 * @Date: 15/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Gegeven.
 */

public class Coördinaten  {

    private double breedte;
    private double lengte;

    public Coördinaten(){

    }

    public Coördinaten(double breedte, double lengte){

        this.breedte = breedte;
        this.lengte = lengte;

    }

    public double getRandomBreedte(){

        return  (-180.0 + Math.random() * 180.0);
    }

    public double getRandomLengte(){

        return  (-90.0 + Math.random() * 90.0);
    }

    public double getBreedte(){

        return this.breedte;
    }

    public double getLengte(){

        return this.lengte;
    }

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
