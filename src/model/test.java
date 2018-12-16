package model;

import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;

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

     AbstractActorFactory testschip = FactoryProducer.getFactory(Actors.SCHIP);
     Actor schip = testschip.getSchip(Schepen.CONTAINERSCHIP);
     int test = schip.getKoers(); // return van 10


    AbstractActorFactory testVerkeerstoren = FactoryProducer.getFactory(Actors.VERKEERSTOREN);
    Actor verkeerstoren = testVerkeerstoren.getVerkeersToren("Test");



    Coördinaten cotest = new Coördinaten();
    double ctb = cotest.getRandomBreedte();
    double ctl = cotest.getRandomLengte();


    System.out.println("Latitude:" + ctb + "  " + "Longitude:" + ctl);


    }
}
