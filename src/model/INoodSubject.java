package model;

import calculations.Coördinaten;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
 * Interface observer die de verkeerstoren gebruikt voor signaal naar observers te zenden. Subject is verkeerstoren.
 */
public interface INoodSubject {

    public void doNotifyNoodObserver(IHulpdienstStrategy reddingsType, Coördinaten coördinaten,String naam);

}
