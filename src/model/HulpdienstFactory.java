package model;

import enums.Hulpdiensten;
import enums.Schepen;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 13:29<br/>
 * To change this template use File | Settings | File Templates.
 */
public class HulpdienstFactory extends AbstractActorFactory {
    @Override
    public Actor getHulpDienst(Hulpdiensten hulpdienst) {

        switch (hulpdienst) {

            case SCHEEPSVAARTPOLITIE:
                return new ScheepsvaartPolitie();
            case SEAKING:
                return new Seaking();
            case VERKEERSTOREN:
                return new Verkeerstoren();
        }

        return null;
    }

    @Override
    public Actor getSchip(Schepen schip) {
        return null;
    }
}
