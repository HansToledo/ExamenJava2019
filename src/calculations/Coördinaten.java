package calculations;

/**
 * @Autor: Mathy Paesen
 * @Date: 15/12/2018
 * @Project: Examen Januari 2019
 * @Purpose: Gegeven.
 */

public class Coördinaten {

    private double breedte;
    private double lengte;

    public Coördinaten() {

    }

    public Coördinaten(double breedte, double lengte) {

        this.breedte = breedte;
        this.lengte = lengte;

    }

    public Coördinaten getRandomCoordinaten(){

        this.breedte = getRandomBreedte();
        this.lengte = getRandomLengte();

        return this;

    }

    private double getRandomBreedte() {

        return getRandomDoubleBetweenRange(-90.0,90.0);
    }

    private double getRandomLengte() {

        return getRandomDoubleBetweenRange(-180.0,180.0);
    }

    public double getBreedte() {

        return this.breedte;
    }

    public double getLengte() {

        return this.lengte;
    }

    public static double getRandomDoubleBetweenRange(double min, double max){ // gebruikt worden voor ramdom met range

        double x = (Math.random()*((max-min)+1))+min;
        return Math.floor(x);

    }

    @Override
    public String toString() {
        return "Coördinaten{" +
                "breedte=" + breedte +
                ", lengte=" + lengte +
                '}';
    }
}
