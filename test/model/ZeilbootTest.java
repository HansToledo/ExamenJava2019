package model;

import calculations.Coördinaten;
import enums.StatusVervoermiddel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Autor: Hans Van De Weyer & Peter Raes
 * @Project: Examen Januari 2019
 * @Purpose: JUnit test op de klasse Zeilboot
 */
public class ZeilbootTest {

    private Zeilboot zeilboot;
    private String naam = "Zeilboot-FX3";
    private double afstand = 100.0;
    private double snelheid = 200.0;
    private String status = "OK";

    @Before
    public void setUp() throws Exception {

        zeilboot = new Zeilboot();
        zeilboot.setSnelheid(snelheid);
        zeilboot.setAfstand(afstand);
        zeilboot.setNoodSignaal(StatusVervoermiddel.BRAND);
        zeilboot.setStatus(status);
        zeilboot.setNaam(naam);

    }
    @Test
    public void getSnelheid() {

        assertEquals(snelheid, zeilboot.getSnelheid(),0);
    }
    @Test
    public void getAfstand() {

        assertEquals(afstand,zeilboot.getAfstand(),0);
    }
    @Test
    public void getNoodSignaal(){

        assertEquals(StatusVervoermiddel.BRAND,zeilboot.getNoodSignaal());
    }
    @Test
    public void getStatus() {

        assertEquals(status,zeilboot.getStatus());
    }
    @Test
    public void getNaam() {

        assertEquals(naam,zeilboot.getNaam());

    }

}