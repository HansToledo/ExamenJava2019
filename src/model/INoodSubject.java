package model;

import calculations.Coördinaten;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 12:45<br/>
 * Interface observer die de verkeerstoren gebruikt voor signaal naar observers te zenden. Subject is verkeerstoren.
 */
public interface INoodSubject {

    public void doNotifyNoodObserver(IHulpdienstStrategy reddingsType, Coördinaten coördinaten,String naam);

}
