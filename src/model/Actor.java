package model;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 15/12/2018<br/>
 * Time: 11:10<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Actor {

    private Coördinaten locatie;
    private double afstand;
    private double snelheid;
    private double reactieTijd;
    private double wendbaarheid;
    private double grootte;
    private double capaciteit;
    private int koers;

    public Coördinaten getLocatie() {
        return locatie;
    }

    public double getAfstand() {
        return afstand;
    }

    public double getSnelheid() {
        return snelheid;
    }

    public double getReactieTijd() {
        return reactieTijd;
    }

    public double getWendbaarheid() {
        return wendbaarheid;
    }

    public double getGrootte() {
        return grootte;
    }

    public double getCapaciteit() {
        return capaciteit;
    }

    public int getKoers() {
        return koers;
    }

    public void setLocatie(Coördinaten locatie) {
        this.locatie = locatie;
    }

    public void setSnelheid(double snelheid) {
        this.snelheid = snelheid;
    }

    public void setGrootte(double grootte) {
        this.grootte = grootte;
    }

    public void setCapaciteit(double capaciteit) {
        this.capaciteit = capaciteit;
    }

    public void setKoers(int koers) {
        this.koers = koers;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "locatie=" + locatie +
                ", afstand=" + afstand +
                ", snelheid=" + snelheid +
                ", reactieTijd=" + reactieTijd +
                ", wendbaarheid=" + wendbaarheid +
                ", grootte=" + grootte +
                ", capaciteit=" + capaciteit +
                ", koers=" + koers +
                '}';
    }
}
