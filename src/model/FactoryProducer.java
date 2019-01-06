package model;

import enums.Actors;

/**
 * @Autor: Peter Raes & Hans Van De Weyer
 * @Project: Examen Januari 2019
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
