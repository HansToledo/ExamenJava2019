package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 15:08<br/>
 * To change this template use File | Settings | File Templates.
 */
public class FactoryProducer {

    public static AbstractActorFactory getFactory(Actors actor){

        switch (actor){

            case HULPDIENST:
                return new HulpdienstFactory();
            case SCHIP:
                return new SchipFactory();
            case VERKEERSTOREN:
                return new VerkeersTorenFactory();

        }
        return null;
    }
}
