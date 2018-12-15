package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 10:59<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractActorFactory {

    abstract Verkeerstoren getVerkeersToren(String verkeersToren);
    abstract IHulpdienst getHulpDienst(Hulpdiensten hulpdienst);
    abstract ISchip getSchip(Schepen schip);

}
