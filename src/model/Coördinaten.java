package model;

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

        this.breedte = getRandomBreedte();
        this.lengte = getRandomLengte();

    }

    public Coördinaten(double breedte, double lengte) {

        this.breedte = breedte;
        this.lengte = lengte;

    }

    private double getRandomBreedte() {

        return (-90.0 + Math.random() * 90.0);
    }

    private double getRandomLengte() {

        return (-180.0 + Math.random() * 180.0);
    }

    public double getBreedte() {

        return this.breedte;
    }

    public double getLengte() {

        return this.lengte;
    }


}
