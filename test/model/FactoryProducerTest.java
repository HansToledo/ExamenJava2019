package model;

import enums.Actors;
import enums.Hulpdiensten;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 25/12/2018<br/>
 * Time: 11:50<br/>
 * To change this template use File | Settings | File Templates.
 */
public class FactoryProducerTest {

    private AbstractActorFactory testFactoryProducer;
    private SchipFactory testSchipFactory;


    @Before
    public void setUp() throws Exception {

        testFactoryProducer = FactoryProducer.getFactory(Actors.SCHIP);
        //testSchipFactory.setHulpDienst()


    }

    @Test
    public void getFactory() {



        //assertEquals(testFactoryProducer,testSchipFactory);

    }
}