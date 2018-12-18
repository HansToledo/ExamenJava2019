package model;

import enums.Hulpdiensten;
import enums.Schepen;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 10:59<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractActorFactory {

    public abstract Actor setHulpDienst(Hulpdiensten hulpdienst);
    public abstract Actor setSchip(Schepen schip);

}
