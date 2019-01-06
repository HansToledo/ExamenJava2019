package model;

import calculations.Coördinaten;

import java.util.LinkedList;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * Interface voor observer van verkeerstoren die het ontvangen noodsignaal verzend naar de hulpdiensten,1 op veel relatie. Observers zijn hier de hulpdiensten (andere verkeerstorens krijgen dit niet)
 */
public interface INoodObserver {

    public void ontvangNoodsignaal(IHulpdienstStrategy reddingsType, Coördinaten coördinaten,String naam);

}
