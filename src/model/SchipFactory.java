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
public class SchipFactory extends AbstractActorFactory {
    @Override
    public Verkeerstoren getVerkeersToren(String verkeersToren) {
        return null;
    }

    @Override
    public IHulpdienst getHulpDienst(Hulpdiensten hulpdienst) {
        return null;
    }

    @Override
    public ISchip getSchip(Schepen schip) {

        switch (schip) {

            case CONTAINERSCHIP:
                return new ContainerSchip();
            case MOTORBOOT:
                return new Motorboot();
            case TANKER:
                return new Tanker();
            case ZEILBOOT:
                return new Zeilboot();

        }

        return null;
    }


}
