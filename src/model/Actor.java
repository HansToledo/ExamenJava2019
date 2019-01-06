package model;

import calculations.Coördinaten;

import java.util.ArrayList;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * Abstracte Actor klasse
 */
public abstract class Actor  {

    public static ArrayList<Verkeerstoren> verkeerstorens = new ArrayList<Verkeerstoren>();             //via factory komen verkeerstorens in deze lijst
    public static ArrayList<Schepen> schepenOpWater = new ArrayList<Schepen>();                   //via factory komen de schepen op water in deze lijst
    public static ArrayList<Vervoermiddel> mogelijkeHulpdiensten = new ArrayList<Vervoermiddel>();      //via factory komen de mogelijkehulpdiensten in deze lijst

    private double afstand;


    //region Strategy Code
    private IHulpdienstStrategy hulpdienstStrategy;

    public IHulpdienstStrategy getHulpdienstStrategy() {
        return hulpdienstStrategy;
    }

    public void setHulpdienstStrategy(IHulpdienstStrategy hulpdienstStrategy) {
        this.hulpdienstStrategy = hulpdienstStrategy;
    }
    //endregion

    public double getAfstand() {

        return this.afstand;
    };

    public void setAfstand(double afstand){

        this.afstand = afstand;
    };

    public Coördinaten getLocatie() {

        return new Coördinaten();

    };

    @Override
    public String toString() {
        return "Actor{" +
                "hulpdienstStrategy=" + hulpdienstStrategy + "Afstand="+afstand +
                '}';
    }
}
