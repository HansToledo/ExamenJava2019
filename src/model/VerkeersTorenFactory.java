package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 13:31<br/>
 * To change this template use File | Settings | File Templates.
 */
public class VerkeersTorenFactory extends AbstractActorFactory {
    @Override
    Verkeerstoren getVerkeersToren(String verkeersToren) {
        return new Verkeerstoren();
    }

    @Override
    IHulpdienst getHulpDienst(Hulpdiensten hulpdienst) {
        return null;
    }

    @Override
    ISchip getSchip(Schepen schip) {
        return null;
    }


}
