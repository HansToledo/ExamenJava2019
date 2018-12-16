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

    public Coördinaten(double breedte, double lengte){

        this.breedte = breedte;
        this.lengte = lengte;

    }

    public double getRandomBreedte(){

        return 0.0;
    }

    public double getRandomLengte(){

        return 0.0;

    }

    public double getBreedte(){

        return this.breedte;
    }

    public double getLengte(){

        return this.lengte;
    }


}
