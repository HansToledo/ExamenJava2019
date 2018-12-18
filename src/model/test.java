package model;

import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import strategies.GeenStrategy;
import strategies.MeldingStrategy;
import strategies.PickupStrategy;


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
    //Initialiseren mogelijke strategies.
    IHulpdienstStrategy pickupStrategy = new PickupStrategy();
    IHulpdienstStrategy meldingStrategy = new MeldingStrategy();
    IHulpdienstStrategy geenStrategy = new GeenStrategy();

    //Aanmaken hulpdiensten
    AbstractActorFactory hulpdiensten = FactoryProducer.getFactory(Actors.HULPDIENST);
    Actor sk1 = hulpdiensten.getHulpDienst(Hulpdiensten.SEAKING);
    Actor sk2 = hulpdiensten.getHulpDienst(Hulpdiensten.SEAKING);
    Actor vt1 = hulpdiensten.getHulpDienst(Hulpdiensten.VERKEERSTOREN);
    Actor vt2 = hulpdiensten.getHulpDienst(Hulpdiensten.VERKEERSTOREN);
    Actor svp1 = hulpdiensten.getHulpDienst(Hulpdiensten.SCHEEPSVAARTPOLITIE);
    Actor svp2 = hulpdiensten.getHulpDienst(Hulpdiensten.SCHEEPSVAARTPOLITIE);

    //Aanmaken schepen
    AbstractActorFactory schepen = FactoryProducer.getFactory(Actors.SCHIP);
    Actor container1 = schepen.getSchip(Schepen.CONTAINERSCHIP);
    Actor zeilboot1 = schepen.getSchip(Schepen.ZEILBOOT);
    Actor tanker1 = schepen.getSchip(Schepen.TANKER);
    Actor motorboot = schepen.getSchip(Schepen.MOTORBOOT);

    System.out.println("CONTAINERSCHIP");
    container1.getLocatie();
    container1.getAfstand();
    container1.setHulpdienstStrategy(geenStrategy);
    System.out.println(container1.getHulpdienstStrategy());
    container1.setHulpdienstStrategy(pickupStrategy);
    System.out.println(container1.getHulpdienstStrategy());
//    container1.setCapaciteit(10);
//    container1.setGrootte(50);
//    container1.setKoers(50);
//    container1.setSnelheid(10);


//    System.out.println("ZEILBOOT");
//    zeilboot1.setCapaciteit(2);
//    zeilboot1.setGrootte(5);
//    zeilboot1.setKoers(5);
//    zeilboot1.setSnelheid(1);
//    System.out.println("ZEILBOOT");



//    Coördinaten cotest = new Coördinaten();
//    double ctb = cotest.getRandomBreedte();
//    double ctl = cotest.getRandomLengte();
//
//
//    System.out.println("Latitude:" + ctb + "  " + "Longitude:" + ctl);


    }
}
