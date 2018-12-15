package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 13:29<br/>
 * To change this template use File | Settings | File Templates.
 */
public class HulpdienstFactory extends AbstractActorFactory {
    @Override
    Verkeerstoren getVerkeersToren(String verkeersToren) {
        return null;
    }

    @Override
    IHulpdienst getHulpDienst(Hulpdiensten hulpdienst) {

        switch (hulpdienst) {

            case SCHEEPSVAARTPOLITIE:
                return new ScheepsvaartPolitie();
            case SEAKING:
                return new Seaking();

        }

        return null;
    }

    @Override
    ISchip getSchip(Schepen schip) {
        return null;
    }


}
