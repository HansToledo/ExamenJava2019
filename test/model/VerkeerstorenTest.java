package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: JUnit test op de klasse verkeerstoren klasse
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