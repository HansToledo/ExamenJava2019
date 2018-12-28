package model;

import calculations.Coördinaten;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:45<br/>
 * Interface voor observer van verkeerstoren die het ontvangen noodsignaal verzend naar de hulpdiensten,1 op veel relatie. Observers zijn hier de hulpdiensten (andere verkeerstorens krijgen dit niet)
 */
public interface INoodObserver {

    public void ontvangNoodsignaal(IHulpdienstStrategy reddingsType, Coördinaten coördinaten,String naam);

}
