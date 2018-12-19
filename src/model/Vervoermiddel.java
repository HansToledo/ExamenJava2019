package model;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: peter<br/>
 * Date: 16/12/2018<br/>
 * Time: 19:17<br/>
 * To change this template use File | Settings | File Templates.
 */
public abstract class Vervoermiddel extends Actor implements INoodObserver{

    private double afstand;
    private double snelheid;
    private double reactieTijd;
    private double wendbaarheid;
    private double grootte;
    private double capaciteit;
    private int koers;
    private Coördinaten coördinaten;

    public Vervoermiddel(){

    }

    public Vervoermiddel (Coördinaten coördinaten, double snelheid,double grootte,double capaciteit,int koers){

        coördinaten = new Coördinaten();
        this.coördinaten = coördinaten;
        this.snelheid = snelheid;
        this.grootte = grootte;
        this.capaciteit = capaciteit;
        this.koers = koers;

    }


    public double getAfstand() {

        return this.afstand;
    };


    public double getSnelheid() { // snelheid in knopen containerschip

        return this.snelheid;
    }

    public double getReactieTijd() {

        return this.reactieTijd;
    }


    public double getWendbaarheid() {

        return this.wendbaarheid;
    }


    public double getGrootte() {

        return this.grootte;

    }


    public double getCapaciteit() {

        return this.capaciteit;
    }


    public int getKoers() {

        return this.koers;
    }


    public void setLocatie() {



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
        return "Vervoermiddel{" +
                "afstand=" + afstand +
                ", snelheid=" + snelheid +
                ", reactieTijd=" + reactieTijd +
                ", wendbaarheid=" + wendbaarheid +
                ", grootte=" + grootte +
                ", capaciteit=" + capaciteit +
                ", koers=" + koers +
                ", coördinaten=" + coördinaten +
                "} " + super.toString();
    }
}
