package model;

import enums.Actors;
import enums.Hulpdiensten;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 11:31<br/>
 * To change this template use File | Settings | File Templates.
 */
public class test {

    // dit is een test classe mag weg

public void test() {

    AbstractActorFactory testHulpdienst = FactoryProducer.getFactory(Actors.HULPDIENST);
    IHulpdienst hulpdienst = testHulpdienst.getHulpDienst(Hulpdiensten.SCHEEPSVAARTPOLITIE);
    IHulpdienst hulpdienst2 = testHulpdienst.getHulpDienst(Hulpdiensten.SEAKING);


    hulpdienst.test();
    hulpdienst2.test();

}
}