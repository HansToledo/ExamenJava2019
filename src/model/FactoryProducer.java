package model;

import enums.Actors;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 15:08<br/>
 * Abstracte factory, deze factory maakt factories aan voor schepenvia schipfactory en hulpdiensten verkeerstoren via hulpdienstfactory is ook een hulpdienst
 */
public class FactoryProducer {

    public static AbstractActorFactory getFactory(Actors actor){

        switch (actor){
            case HULPDIENST:
                return new HulpdienstFactory();
            case SCHIP:
                return new SchipFactory();
        }
        return null;
    }
}
