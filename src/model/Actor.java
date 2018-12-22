package model;

import calculations.Coördinaten;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:10<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Actor  {

    public static ArrayList<Verkeerstoren> verkeerstorens = new ArrayList<Verkeerstoren>(); //via factory komen verkeerstorens in lijst
    public static ArrayList<Schepen> schepenOpWater = new ArrayList<Schepen>();
    public static ArrayList<Vervoermiddel> mogelijkeHulpdiensten = new ArrayList<Vervoermiddel>();

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
                "hulpdienstStrategy=" + hulpdienstStrategy +
                '}';
    }
}
