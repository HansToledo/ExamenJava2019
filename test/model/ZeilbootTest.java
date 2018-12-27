package model;

import calculations.Coördinaten;
import enums.StatusVervoermiddel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 27/12/2018<br/>
 * Time: 15:41<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ZeilbootTest {

    private String enumNaam = "Zeilboot";
    private String naam = "Zeilboot-FX3";
    private double afstand = 100.0;
    private double snelheid = 200.0;
    private double reactieTijd;
    private double wendbaarheid;
    private double grootte;
    private double capaciteit;
    private int koers;
    private Coördinaten coördinaten;
    private String status;
    private Zeilboot zeilboot;

    @Before
    public void setUp() throws Exception {

        zeilboot = new Zeilboot();
        zeilboot.setSnelheid(snelheid);
        zeilboot.setAfstand(100.0);
        zeilboot.setNoodSignaal(StatusVervoermiddel.BRAND);

    }


    @Test
    public void getStatus() {
    }

    @Test
    public void setStatus() {
    }

    @Test
    public void getEnumNaam() {
    }

    @Test
    public void getCoördinaten() {
    }

    @Test
    public void getNaam() {
    }

    @Test
    public void getAfstand() {

        assertEquals(100.0,zeilboot.getAfstand(),0);
    }


    @Test
    public void getSnelheid() {

        assertEquals(200.0, zeilboot.getSnelheid(),0);
    }

    @Test
    public void getNoodSignaal(){

        assertEquals(StatusVervoermiddel.BRAND,zeilboot.getNoodSignaal());
    }

    @Test
    public void getReactieTijd() {


    }

    @Test
    public void getWendbaarheid() {
    }

    @Test
    public void getGrootte() {
    }

    @Test
    public void getCapaciteit() {
    }

    @Test
    public void getKoers() {
    }

    @Test
    public void setEnumNaam() {
    }

    @Test
    public void setNaam() {
    }

    @Test
    public void setLocatie() {
    }

    @Test
    public void setSnelheid() {
    }

    @Test
    public void setGrootte() {
    }

    @Test
    public void setCapaciteit() {
    }

    @Test
    public void setKoers() {
    }

    @Test
    public void setReactieTijd() {
    }

    @Test
    public void ontvangNoodsignaal() {
    }

    @Test
    public void getHulpdienstStrategy() {
    }

    @Test
    public void setHulpdienstStrategy() {
    }

    @Test
    public void getAfstand1() {
    }

    @Test
    public void setAfstand() {
    }

    @Test
    public void getLocatie() {
    }

    @Test
    public void toString1() {
    }
}