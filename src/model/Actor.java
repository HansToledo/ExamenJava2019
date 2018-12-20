package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:10<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Actor  {

    public static ArrayList<Verkeerstoren> verkeerstorens = new ArrayList<Verkeerstoren>(); //via factory komen verkeerstorens in lijst
    public static ArrayList<Schepen> schepenOpWater = new ArrayList<Schepen>();             // via factory
    public static ArrayList<Vervoermiddel> mogelijkeHulpdiensten = new ArrayList<Vervoermiddel>();

    public void clearVerkeerstorenLijst(){

        verkeerstorens.clear();

    }
    public double getAfstand() {

        return 0.0;
    };

    public Coördinaten getLocatie() {

        return new Coördinaten();

    };
    //region Strategy Code
    private IHulpdienstStrategy hulpdienstStrategy;

    public IHulpdienstStrategy getHulpdienstStrategy() {
        return hulpdienstStrategy;
    }

    public void setHulpdienstStrategy(IHulpdienstStrategy hulpdienstStrategy) {
        this.hulpdienstStrategy = hulpdienstStrategy;
    }
    //endregion



    @Override
    public String toString() {
        return "Actor{" +
                "hulpdienstStrategy=" + hulpdienstStrategy +
                '}';
    }
}
