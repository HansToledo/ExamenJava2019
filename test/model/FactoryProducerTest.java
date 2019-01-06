package model;

import calculations.Coördinaten;
import enums.Actors;
import enums.Hulpdiensten;
import enums.Schepen;
import org.junit.Before;
import org.junit.Test;
import strategy.BrandStrategy;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 25/12/2018<br/>
 * Time: 11:50<br/>
 * To change this template use File | Settings | File Templates.
 */
public class FactoryProducerTest {


    private Schepen schip = Schepen.ZEILBOOT;
    private String naam = "Zeilboot-FX3";
    private double snelheid = 200.0;
    private double grootte = 100.0;
    private double capaciteit = 100.0;
    private int koers = 45;
    private String status = "OK";
    private Coördinaten coördinaten = new Coördinaten(10,10);
    private AbstractActorFactory testFactoryProducer;
    private Vervoermiddel vervoermiddel;
    private IHulpdienstStrategy strategy = new BrandStrategy();
    private Vervoermiddel zeilboot=  new Zeilboot(schip.toString(),naam,coördinaten,snelheid,grootte,capaciteit,koers,strategy,status);


    @Before
    public void setUp() throws Exception {

        testFactoryProducer = FactoryProducer.getFactory(Actors.SCHIP);
        vervoermiddel = testFactoryProducer.setSchip(schip.toString(),naam,schip,coördinaten,snelheid,grootte,capaciteit,koers,strategy,status);

    }

    @Test
    public void getFactory() {

        assertEquals(zeilboot.getNaam(),vervoermiddel.getNaam());
        assertEquals(zeilboot.getStatus(),vervoermiddel.getStatus());
        assertEquals(zeilboot.getEnumNaam(),vervoermiddel.getEnumNaam());
        assertEquals(zeilboot.getCoördinaten(),vervoermiddel.getCoördinaten());
        assertEquals(zeilboot.getCapaciteit(),vervoermiddel.getCapaciteit(),0);
        assertEquals(zeilboot.getGrootte(),vervoermiddel.getGrootte(),0);
        assertEquals(zeilboot.getSnelheid(),vervoermiddel.getSnelheid(),0);
        assertEquals(zeilboot.getHulpdienstStrategy(),vervoermiddel.getHulpdienstStrategy());

    }

}