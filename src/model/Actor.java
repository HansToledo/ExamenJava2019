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

    public double getAfstand(double breedteX, double lengteX, double breedteY, double lengteY) {
        //Latitude = breedte
        //Longitude = lengte
        double afstand = 0;
        try{
            double Radius = 6371; // radius van de aarde is 6371km
            double LatX = Math.toRadians(breedteX);
            double LatY = Math.toRadians(breedteY);
            double differenceLat = Math.toRadians(breedteX-breedteY);
            double differenceLon = Math.toRadians(lengteX-lengteX);

            double a = Math.sin(differenceLat/2) * Math.sin(differenceLat/2) + Math.cos(LatY) * Math.cos(LatX) * Math.sin(differenceLon/2) * Math.sin(differenceLon/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

            afstand = (Radius * c) / 1.852; //uitgedruk in zeemijl dus / 1.852km
        } catch(Exception e){
            e.printStackTrace();
        }
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
