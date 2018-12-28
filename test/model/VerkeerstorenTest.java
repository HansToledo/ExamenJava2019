package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 28/12/2018<br/>
 * Time: 17:15<br/>
 * To change this template use File | Settings | File Templates.
 */
public class VerkeerstorenTest {

    Verkeerstoren verkeerstoren;

    @Before
    public void setUp() throws Exception {

        verkeerstoren = new Verkeerstoren();


    }

    @Test
    public void doUpdate() {

    }

    @Test
    public void zoekBeschikbareHulpdienst() {

        assertEquals(true,verkeerstoren.zoekBeschikbareHulpdienst("testhulpdienst"));

    }

    @Test
    public void doNotifyNoodObserver() {



    }
}