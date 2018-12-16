package model;

import enums.Hulpdiensten;
import enums.Schepen;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 13:31<br/>
 * To change this template use File | Settings | File Templates.
 */
public class VerkeersTorenFactory extends AbstractActorFactory {
    @Override
    public Verkeerstoren getVerkeersToren(String verkeersToren) {
        return new Verkeerstoren();
    }

    @Override
    public IHulpdienst getHulpDienst(Hulpdiensten hulpdienst) {
        return null;
    }

    @Override
    public ISchip getSchip(Schepen schip) {
        return null;
    }


}
